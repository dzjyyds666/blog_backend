package com.Aaron;

import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.util.Date;

@SpringBootTest
class BlogFinalVersionApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    void md5(){
        String md5 = DigestUtils.md5DigestAsHex("1433223qQ".getBytes());
        System.out.println(md5);
    }

    @Test
    void Jwt(){
        long time = 1000*60;
        String signature = "Aaron";
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                //header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //payload
                .claim("account","Aaron111")
                .claim("password",DigestUtils.md5DigestAsHex("1111111".getBytes()))
                .setExpiration(new Date(System.currentTimeMillis()+time))
                //signature
                .signWith(SignatureAlgorithm.HS256,signature)
                .compact();
        System.out.println(jwtToken);
    }

    @Test
    void parse(){
        try{
            String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50IjoiQWFyb24xMTEiLCJwYXNzd29yZCI6IjdmYTgyODJhZDkzMDQ3YTRkNmZlNjExMWM5M2IzMDhhIiwiZXhwIjoxNzExMjk2MzU2fQ.B4g_DB70c7qRxECXaCGMTSFxbRjPpKKvuFdiDoVoyk0";
            JwtParser jwtParser = Jwts.parser();
            Jws<Claims> claimsJwts = jwtParser.setSigningKey("Aaron").parseClaimsJws(token);
            Claims claims = claimsJwts.getBody();
            System.out.println(claims.get("account"));
            System.out.println(claims.getExpiration());
        }catch (ExpiredJwtException e){
            System.out.println("token过期，请重新登录");
        }


    }

}
