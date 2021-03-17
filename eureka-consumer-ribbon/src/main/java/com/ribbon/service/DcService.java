package com.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class DcService {
    @HystrixCommand(fallbackMethod = "helloFallback")
    public String hellService() {
        return restTemplate.getForEntity("http://eureka-client/dc",String.class).getBody();
    }

    @Autowired
    private RestTemplate restTemplate;

    public String helloFallback(){
        return "error";
    }
}
