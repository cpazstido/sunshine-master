package com.sunshine.admin;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableEurekaClient
@EnableAdminServer
@EnableHystrixDashboard
@EnableTurbine
//@EnableCircuitBreaker
//@EnableHystrix
@SpringBootApplication
public class SunshineAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(SunshineAdminApplication.class,args);
    }
}
