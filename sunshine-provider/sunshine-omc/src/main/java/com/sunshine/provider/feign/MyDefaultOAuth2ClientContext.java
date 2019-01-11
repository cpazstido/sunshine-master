package com.sunshine.provider.feign;

import com.sunshine.base.dto.LoginAuthDto;
import com.sunshine.utils.RedisKeyUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.concurrent.TimeUnit;

public class MyDefaultOAuth2ClientContext extends DefaultOAuth2ClientContext {
    private RedisTemplate<String, Object> redisTemplate;

    public MyDefaultOAuth2ClientContext() {
    }

    public MyDefaultOAuth2ClientContext(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void setAccessToken(OAuth2AccessToken token) {
        super.setAccessToken(token);
        //User principal = (User) authentication.getPrincipal();

        LoginAuthDto loginAuthDto = new LoginAuthDto();
        loginAuthDto.setLoginName("admin");

        redisTemplate.opsForValue().set(RedisKeyUtil.getAccessTokenKey(token.getValue()).toLowerCase(), loginAuthDto, 7200, TimeUnit.SECONDS);
    }
}
