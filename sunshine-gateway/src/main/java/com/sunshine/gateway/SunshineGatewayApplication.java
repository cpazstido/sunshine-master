package com.sunshine.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

//@EnableHystrix
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
//@EnableOAuth2Sso
public class SunshineGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SunshineGatewayApplication.class, args);
    }

}

