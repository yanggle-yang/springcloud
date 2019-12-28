package com.yanggle.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@EnableEurekaClient
@SpringBootApplication
@RestController
public class ProducerApplication {
    public static void main(String[] args) {

        SpringApplication.run(ProducerApplication.class,args);
    }

    @RequestMapping("/producer/hello")
    public String hello() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }
}
