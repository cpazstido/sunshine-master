package com.sunshine.core.config;

import com.sunshine.core.interceptor.TokenInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.HandlerInterceptor;

@Configuration
public class CoreConfiguration {

	@Bean
	@Order(0)
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}

	@Bean
	@ConditionalOnMissingBean(HandlerInterceptor.class)
	public TokenInterceptor tokenInterceptor() {
		return new TokenInterceptor();
	}
}
