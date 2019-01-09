package com.sunshine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@EnableEurekaClient
@SpringBootApplication
public class SunshineUacApplication {
    public static void main(String[] args) {
        SpringApplication.run(SunshineUacApplication.class,args);
    }
}
