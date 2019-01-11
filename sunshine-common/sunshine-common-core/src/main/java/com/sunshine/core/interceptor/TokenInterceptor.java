package com.sunshine.core.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunshine.utils.wrapper.WrapMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    @Resource
    private ObjectMapper objectMapper;
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    private static final String USER = "/user";
    private static final String OPTIONS = "OPTIONS";
    private static final String AUTH_PATH1 = "/auth";
    private static final String AUTH_PATH2 = "/oauth";
    private static final String AUTH_PATH3 = "/error";
    private static final String AUTH_PATH4 = "/api";

    /**
     * After completion.
     *
     * @param request  the request
     * @param response the response
     * @param arg2     the arg 2
     * @param ex       the ex
     * @throws Exception the exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception ex) throws Exception {
        if (ex != null) {
            log.error("<== afterCompletion - 解析token失败. ex={}", ex.getMessage(), ex);
            this.handleException(response);
        }
    }

    /**
     * Post handle.
     *
     * @param request  the request
     * @param response the response
     * @param arg2     the arg 2
     * @param mv       the mv
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView mv) {
    }

    /**
     * Pre handle boolean.
     *
     * @param request  the request
     * @param response the response
     * @param handler  the handler
     * @return the boolean
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        log.info("<== preHandle - 权限拦截器.  url={}", uri);
        if (uri.contains(USER) || uri.contains(AUTH_PATH1) || uri.contains(AUTH_PATH2) || uri.contains(AUTH_PATH3) || uri.contains(AUTH_PATH4)) {
            log.info("<== preHandle - 配置URL不走认证.  url={}", uri);
            return true;
        }
        String token = StringUtils.substringAfter(request.getHeader(HttpHeaders.AUTHORIZATION).toLowerCase(), "bearer ");
        if(StringUtils.isEmpty(token)){
            token = request.getParameter("access_token");
        }
        log.info("<== preHandle - 权限拦截器.  token={}", token);
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        OAuth2AccessToken auth2AccessToken = tokenStore.readAccessToken(token);
        if (auth2AccessToken == null) {
            log.error("获取用户信息失败, 不允许操作");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write((objectMapper.writeValueAsString(WrapMapper.unAuthorized("无效token:"+token))));
            return false;
        }
        return true;
    }

    private void handleException(HttpServletResponse res) throws IOException {
        res.resetBuffer();
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write("{\"code\":100009 ,\"message\" :\"解析token失败\"}");
        res.flushBuffer();
    }


}
  