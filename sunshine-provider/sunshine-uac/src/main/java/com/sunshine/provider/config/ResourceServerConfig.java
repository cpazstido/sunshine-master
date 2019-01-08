package com.sunshine.provider.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;
    @Autowired
    private CustomHttp403ForbiddenEntryPoint customHttp403ForbiddenEntryPoint;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				// Since we want the protected resources to be accessible in the UI as well we need
				// session creation to be allowed (it's disabled by default in 2.0.6)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                    .and()
//                    .requestMatchers().anyRequest()
				.and()
				.anonymous()
				.and()
				.authorizeRequests()
//                    .antMatchers("/product/**").authenticated()
//				.anyRequest().authenticated()
                .antMatchers("/oauth/**").permitAll()
				.antMatchers("/index/**").authenticated()
//                    .antMatchers("/order/**").hasRole("aaaa")
                .and().exceptionHandling().authenticationEntryPoint(customHttp403ForbiddenEntryPoint)
                .and().exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
		;
	}
}
