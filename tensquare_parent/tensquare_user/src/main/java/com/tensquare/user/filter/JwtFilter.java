package com.tensquare.user.filter;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:添加拦截器
 * @date 2019/4/3 0003
 */
@Component
public class JwtFilter implements HandlerInterceptor{
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器...");
        //微服务鉴权
        final String header = request.getHeader("Authorization");

        if( header!=null ){
            if(header.startsWith("Bearer ")){
                String token = header.substring(7);
                Claims claims = jwtUtil.parseJWT(token);//获取载荷
                if(claims!=null){
                    if(claims.get("roles").equals("admin")){//管理员身份
                        request.setAttribute("admin_claims",claims);
                    }
                    if(claims.get("roles").equals("user")){//普通用户
                        request.setAttribute("user_claims",claims);
                        System.out.println("user->userid;"+claims.getId());
                    }
                }
            }
        }
        return true;
    }
}
