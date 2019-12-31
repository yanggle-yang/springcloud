package com.yanggle.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 不能将restTemplate.getForObject()和loadBalancerClient写在同一个方法中，两者会冲突，
 * 因为RestTemplate实际上是一个Ribbon客户端，本身已经包含了choose的行为.
 */
@Slf4j
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Value("${config.provider.servicePath}")
    private String providerServicePath;

    @RequestMapping("/date")
    public String getDate() {
        return this.restTemplate.getForObject(this.providerServicePath, String.class);
    }

    @GetMapping("/callProvider")
    public String callProviderInstance() {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("app-provider");

        // 打印当前选择的哪个节点
        log.info("serviceId:{}, host:{}, port:{}, uri:{}",
                serviceInstance.getServiceId(),
                serviceInstance.getHost(),
                serviceInstance.getPort(),
                serviceInstance.getUri());

        return serviceInstance.getUri().toString();
    }
}
