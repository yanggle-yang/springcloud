package com.yanggle.consumer.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

// Feign想要调用外部服务，并且启用Hystrix熔断回调了怎么办呢？
// 不需其它的配置，只需要配置一个@FeignClient的url
@FeignClient(value="app-provider", fallback = ProviderFeignClientFallback.class)
public interface ProviderFeignClient {

    @RequestMapping("/provider/date")
    public String getDate() ;
}