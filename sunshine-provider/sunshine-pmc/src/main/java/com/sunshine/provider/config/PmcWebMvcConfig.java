package com.sunshine.provider.config;

import com.sunshine.core.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

@Configuration
@EnableWebMvc
public class PmcWebMvcConfig extends WebMvcConfigurerAdapter {

	@Resource
	private TokenInterceptor interceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(interceptor)
				.addPathPatterns("/**")
                ;
	}


}
