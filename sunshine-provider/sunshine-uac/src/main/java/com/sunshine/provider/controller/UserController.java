package com.sunshine.provider.controller;

import com.sunshine.provider.model.SecurityUser;
import com.sunshine.provider.model.domain.UacUser;
import com.sunshine.provider.service.UacUserService;
import com.sunshine.utils.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class UserController {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private UacUserService userService;

    /**
     * 用户信息校验
     *
     * @param authentication 信息
     * @return 用户信息
     */
    @RequestMapping("/user")
    public Object user(Authentication authentication) {
        SecurityUser sysUser = new SecurityUser(1L, "admin", "", "", 1L, "");
        return sysUser;
    }

    @RequestMapping("/userLogout")
    public String logout() {
        String accessToken = ((OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getTokenValue();
        redisTemplate.delete(RedisKeyUtil.getAccessTokenKey(accessToken).toLowerCase());
        return "logout success!";

    }

    @RequestMapping("/getUser")
    public UacUser getUser(String name) {
        return userService.findByLoginName(name);
    }

    @RequestMapping("/getTime")
    public Date getTime() {
        return new Date();
    }
}
