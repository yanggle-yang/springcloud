package com.yanggle.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer //开启Eureka Server
@SpringBootApplication
public class SpringCloudEurekaServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringCloudEurekaServerApplication.class, args);
    }
}