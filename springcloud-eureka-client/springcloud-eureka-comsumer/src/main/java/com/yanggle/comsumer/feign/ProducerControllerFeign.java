package com.yanggle.comsumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value="app-producer")
public interface ProducerControllerFeign {

    @RequestMapping("/producer/hello")
    public String hello() ;
}
