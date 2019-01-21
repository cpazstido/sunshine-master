package com.sunshine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SunshinePmcApplication {
    public static void main(String[] args) {
        SpringApplication.run(SunshinePmcApplication.class,args);
    }
}
