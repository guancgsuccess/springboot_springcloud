package com.tensquare.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:http://www.ibloger.net/article/3075.html
 * @date 2019/3/27 0027
 */
public class CreateJwtTest {
    public static void main(String[] args) {
        //设置验证过期时间
        //获取当前系统的毫秒数
        long now = System.currentTimeMillis();
        //设置过期时间Wie一分钟
        long exp = now + 1000*60;

        //创建证书
        JwtBuilder jwtBuilder = Jwts.builder()
                                    .setId("123") //登录用户id
                                    .setSubject("success")//主题 - 用户名
                                    .setIssuedAt(new Date())//签发时间
                                    .signWith(SignatureAlgorithm.HS256,"aistar")//签名算法以及秘钥
                                    .setExpiration(new Date(exp))
                                    //设置自定义样式
                                    .claim("roles","admin");
        System.out.println(jwtBuilder.compact());//由于加入了签发时间,所以每次运行的结果都是不一样的
    }
}
