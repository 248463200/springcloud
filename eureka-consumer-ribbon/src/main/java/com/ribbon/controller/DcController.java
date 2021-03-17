package com.ribbon.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ribbon.fegin.EurekaClientFegin;
import com.ribbon.service.DcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DcController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EurekaClientFegin fegin;
    @Autowired
    DcService dcService;

    @GetMapping("/consumer")
    public String consumer(){
        return restTemplate.getForObject("http://eureka-client/dc", String.class);
    }


    @GetMapping("/consumer2")
    public String consumer2(){
        return fegin.eurekaClientFegin();
    }


    @GetMapping("/consumer3")
    public String consumer3()  {
        return dcService.hellService();
    }


}
