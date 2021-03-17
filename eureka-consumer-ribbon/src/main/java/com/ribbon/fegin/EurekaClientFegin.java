package com.ribbon.fegin;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("eureka-client")
public interface EurekaClientFegin {
    @GetMapping("/dc")
    String eurekaClientFegin();
}
