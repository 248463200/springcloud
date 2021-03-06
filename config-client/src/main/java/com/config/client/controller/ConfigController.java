package com.config.client.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigController {

    @Value("${from}")
    private String from;

    @Autowired
    private Environment env;

    @GetMapping("/configFrom")
    public String configFrom(){
        return env.getProperty("from","undefined");
    }
}
