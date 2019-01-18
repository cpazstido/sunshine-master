package com.sunshine.admin;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@EnableAdminServer
@SpringBootApplication
public class SunshineAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(SunshineAdminApplication.class,args);
    }
}
