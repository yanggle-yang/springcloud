package com.yanggle.consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

    @HystrixCommand(fallbackMethod = "getDateDefault",commandProperties= {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000"),
            @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds",value="10000")
    },threadPoolProperties= {
            @HystrixProperty(name="coreSize",value="1"),
            @HystrixProperty(name="maxQueueSize",value="10")
    })
    @RequestMapping("/date")
    public String getDate() {
        return this.restTemplate.getForObject(this.providerServicePath, String.class);
    }

    /**
     * 当请求失败、超时、被拒绝，或当断路器打开时，执行的逻辑
     */
    public String getDateDefault() {
        return "服务器忙";
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
