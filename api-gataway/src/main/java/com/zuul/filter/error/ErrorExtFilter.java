package com.zuul.filter.error;

import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;

public class ErrorExtFilter extends SendErrorFilter {
    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 30; //大于ErrorFilter过滤器
    }

    @Override
    public boolean shouldFilter() {
        // TODO：仅处理来自POST过滤器引起的异常
        return true;
    }

}
