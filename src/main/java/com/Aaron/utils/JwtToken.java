package com.Aaron.utils;

import com.Aaron.service.impl.TokenblacklistServiceImpl;
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
    private long expiration = 1000 * 60 * 60 * 24 * 3;

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


    //校验token,并延期
    public String parseToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(signature).parseClaimsJws(token).getBody();
            long expiration = claims.getExpiration().getTime();
            long newtime = new Date(System.currentTimeMillis()).getTime();
            if ((expiration - newtime) < 1000 * 60 * 60 * 5) {
                //如果jwt到期时间不足5小时，则分配新的jwt，新jwt过期时间设置为24小时
                return createJwt(claims.get("account").toString());
            }else{
                return "true";
            }
        } catch (Exception e) {
            System.out.println("jwt过期或者jwt有误，请重新登录");
            return "false";
        }
    }

}
