package com.zuul.filter.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

public class AccessFilter extends ZuulFilter {
    /*
     * 过滤器类型，决定过滤器在请求的哪个生命周期中执行
     * pre——代表请求被路由之前执行
     * routing——在路由请求时被调用
     * post——在routing和error过滤器之后被调用
     * error——处理请求时发生错误被调用
     * */
    @Override
    public String filterType() {
        return "pre";
    }

    /*
     *过滤器的执行顺序。
     * 当请求在一个阶段中有多个过滤器时，需要根据该方法返回值依次进行
     * */
    @Override
    public int filterOrder() {
        return 0;
    }

    /*
     * 判断请求是否需要被执行
     * 这里true代表所有
     * */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /*
     * 过滤器具体逻辑
     * */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        System.out.println("Sent "+request.getMethod()+" request to "+request.getRequestURI());
        Object accesssToken = request.getParameter("accesssToken");
        if (accesssToken == null){
            System.out.println("access token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }
        System.out.println("access token ok");
        return null;
    }
}
