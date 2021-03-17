package com.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class DcController {

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/dc")
    public String dc() throws InterruptedException {
        ServiceInstance services =  discoveryClient.getLocalServiceInstance();
        System.out.println(services);
        return "host："+services.getHost()+"\nprot:"+services.getPort()+"\nServiceId"+services.getServiceId();
    }

}
