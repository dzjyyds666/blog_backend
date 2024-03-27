package com.Aaron.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtToken {



    //签名
    private String signature = "Aaron";

    //过期时间，默认为72小时
    private long expiration = 1000 * 60 * 60 * 72;

    //创建token，传入account
    public String createJwt(String account) {

        JwtBuilder jwtBuilder = Jwts.builder();
        return jwtBuilder
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .claim("account", account)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, signature)
                .compact();
    }

    public String dealWithToken(String token){
        if(token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }else{
            return null;
        }
    }
}
