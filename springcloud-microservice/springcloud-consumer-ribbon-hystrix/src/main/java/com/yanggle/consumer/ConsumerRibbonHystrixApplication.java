package com.yanggle.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableHystrix
@SpringBootApplication
public class ConsumerRibbonHystrixApplication {
    public static void main(String[] args) {

        SpringApplication.run(ConsumerRibbonHystrixApplication.class,args);
    }
}
