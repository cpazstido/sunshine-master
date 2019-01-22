package com.sunshine.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class LoginRateLimiterFilter extends ZuulFilter {
    /**
     * 登录限流redis key前缀
     */
    private static String LOGIN_RATELIMITER_REDIS_KEY_PREFIX = "login_ratelimiter:";

    /**
     * 登录限流时间（s）
     * 3内连续访问接口报错
     */
    private static int LOGIN_RATELIMITER_TIMEOUT = 3;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        String url = request.getRequestURI();
        //只对登录接口限流
        if (url != null && url.contains("/authentication/form")) {
            return true;
        }
        return false;
    }

    /**
     * 从request获取登录的IP
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip != null && ip.compareTo("0:0:0:0:0:0:0:1") == 0) {
                ip = "127.0.0.1";
            }
        }
        return ip;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        String ip = getIpAddress(requestContext.getRequest()).replace(".","_");
        String redisKey = LOGIN_RATELIMITER_REDIS_KEY_PREFIX + ip;
        if (!StringUtils.isEmpty((String) redisTemplate.opsForValue().get(redisKey))) {
            try {
                HttpServletResponse res = requestContext.getResponse();
                res.resetBuffer();
                res.setHeader("Access-Control-Allow-Origin", "*");
                res.setHeader("Access-Control-Allow-Credentials", "true");
                res.setContentType("application/json");
                res.setCharacterEncoding("UTF-8");
                res.getWriter().write("{\"code\":429 ,\"message\" :\"request too often!\"}");
                res.flushBuffer();

                requestContext.setResponse(res);
            } catch (IOException e) {
                e.printStackTrace();
            }
            requestContext.setSendZuulResponse(false);
            return true;
        }
        redisTemplate.opsForValue().set(redisKey, ip, LOGIN_RATELIMITER_TIMEOUT, TimeUnit.SECONDS);
        return false;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
