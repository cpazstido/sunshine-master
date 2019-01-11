package com.sunshine.provider.controller;

import com.sunshine.provider.model.SecurityUser;
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

@RestController
public class UserController {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    /**
     * 用户信息校验
     * @param authentication 信息
     * @return 用户信息
     */
    @RequestMapping("/user")
    public Object user(Authentication authentication) {
        SecurityUser sysUser = new SecurityUser(1L,"admin","","",1L,"");
        return sysUser;
    }

    @RequestMapping("/userLogout")
    public String logout(){
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        String accessToken = ((OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getTokenValue();
        String refreshToken = tokenStore.readAccessToken(accessToken).getRefreshToken().getValue();
        tokenStore.removeAccessToken(accessToken);
        tokenStore.removeRefreshToken(refreshToken);
        return "logout success!";
//        redisTemplate.delete(RedisKeyUtil.getAccessTokenKey(accessToken));
    }
}
