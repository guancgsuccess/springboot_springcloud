package com.tensquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:验证jwt
 * @date 2019/3/27 0027
 */
public class ParseJwtTest {
    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjMiLCJzdWIiOiJzdWNjZXNzIiwiaWF0IjoxNTU0MTIwNTk2LCJleHAiOjE1NTQxMjA2NTUsInJvbGVzIjoiYWRtaW4ifQ.h_lp4VPGdoScl8LDuDYaHVoDX5trBboKMZg1KyZrguk";
        //获取默认的解析器
        Claims claims = Jwts.parser().setSigningKey("aistar").parseClaimsJws(token).getBody();
        System.out.println("user_id:"+claims.getId());
        System.out.println("subject:"+claims.getSubject());
        System.out.println("签发时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getIssuedAt()));
        System.out.println("过期时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getExpiration()));
        //获取自定义样式
        System.out.println("自定义属性:"+claims.get("roles"));
    }
}
