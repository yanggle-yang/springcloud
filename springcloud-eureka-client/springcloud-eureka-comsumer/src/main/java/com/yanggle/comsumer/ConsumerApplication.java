package com.yanggle.comsumer;

import com.yanggle.comsumer.feign.ProducerControllerFeign;
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
    ProducerControllerFeign producerControllerFeign ;

    @RequestMapping("/consumer/toProducer")
    public String hello() {
        return producerControllerFeign.hello();
    }

    public static void main(String[] args) {

        SpringApplication.run(ConsumerApplication.class,args);
    }


}
