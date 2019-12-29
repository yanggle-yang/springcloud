package com.yanggle.comsumer.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

// Feign想要调用外部服务，并且启用Hystrix熔断回调了怎么办呢？
// 不需其它的配置，只需要配置一个@FeignClient的url
@FeignClient(value="app-producer", fallback = ProducerControllerFallback.class)
public interface ProducerController {

    @RequestMapping("/producer/hello")
    public String hello() ;
}
