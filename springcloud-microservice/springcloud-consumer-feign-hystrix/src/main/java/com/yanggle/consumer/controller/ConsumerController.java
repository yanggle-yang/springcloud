package com.yanggle.consumer.controller;

import com.yanggle.consumer.feignClient.ProviderFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {
    @Autowired
    ProviderFeignClient ProviderFeignClient;

    @RequestMapping("/consumer/date")
    public String getDate() {
        return ProviderFeignClient.getDate();
    }
}
