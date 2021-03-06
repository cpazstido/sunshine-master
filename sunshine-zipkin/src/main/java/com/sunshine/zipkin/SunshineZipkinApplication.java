package com.sunshine.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin.server.EnableZipkinServer;

@EnableEurekaClient
@SpringBootApplication
@EnableZipkinServer
public class SunshineZipkinApplication {
    public static void main(String[] args) {
        SpringApplication.run(SunshineZipkinApplication.class,args);
    }
}
