package com.zuul.config;

import com.zuul.filter.AccessFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {
    @Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }
}