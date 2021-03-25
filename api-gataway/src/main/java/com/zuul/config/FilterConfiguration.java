package com.zuul.config;

import com.zuul.filter.error.ErrorExtFilter;
import com.zuul.filter.error.ErrorFilter;
import com.zuul.filter.pre.AccessFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {
    @Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }
    @Bean
    public ErrorExtFilter errorExtFilter(){
        return new ErrorExtFilter();
    }
    @Bean
    public ErrorFilter errorFilter(){
        return new ErrorFilter();
    }
}
