package com.yanggle.comsumer;

import com.yanggle.comsumer.feignClient.ProducerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@RestController
public class ConsumerApplication {
    @Autowired
    ProducerController producerController ;

    @RequestMapping("/consumer/toProducer")
    public String hello() {
        return producerController.hello();
    }

    public static void main(String[] args) {

        SpringApplication.run(ConsumerApplication.class,args);
    }


}
