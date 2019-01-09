package com.sunshine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class SunshineOmcApplication {
    public static void main(String[] args) {
        SpringApplication.run(SunshineOmcApplication.class,args);
    }
}
