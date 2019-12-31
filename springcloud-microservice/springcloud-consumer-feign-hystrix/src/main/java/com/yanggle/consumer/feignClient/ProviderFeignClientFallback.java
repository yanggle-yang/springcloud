package com.yanggle.consumer.feignClient;

import org.springframework.stereotype.Component;

@Component
public class ProviderFeignClientFallback implements ProviderFeignClient {
    @Override
    public String getDate() {
        return "服务器挂了";
    }
}
