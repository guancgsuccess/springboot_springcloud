package com.tensquare.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/4/29 0029
 */
@Component
public class MyFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";//post代表后置过滤器
    }

    @Override
    public int filterOrder() {
        return 0;//优先级为0,数字越大,优先级越低
    }

    @Override
    public boolean shouldFilter() {
        return true;//是否执行该过滤器
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("zull过滤器...");
        //向header总能添加鉴权令牌
        RequestContext requestContext = RequestContext.getCurrentContext();
        //获取header
        HttpServletRequest request = requestContext.getRequest();
        String authorization = request.getHeader("Authorization");
        System.out.println(authorization);
        if(authorization!=null){
            //转发token
            requestContext.addZuulRequestHeader("Authorizations",authorization);
        }
        return null;
    }
}
