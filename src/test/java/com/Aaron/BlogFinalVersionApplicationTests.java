package com.Aaron;

import com.Aaron.controller.BlogController;
import com.Aaron.mapper.BlogMapper;
import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class BlogFinalVersionApplicationTests {

    @Autowired
    private BlogMapper blogMapper;

    @Test
    void contextLoads() {
    }


    @Test
    void md5() {
        String md5 = DigestUtils.md5DigestAsHex("1433223qQ".getBytes());
        System.out.println(md5);
    }

    @Test
    void Jwt() {
        long time = 1000 * 60;
        String signature = "Aaron";
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                //header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //payload
                .claim("account", "Aaron111")
                .claim("password", DigestUtils.md5DigestAsHex("1111111".getBytes()))
                .setExpiration(new Date(System.currentTimeMillis() + time))
                //signature
                .signWith(SignatureAlgorithm.HS256, signature)
                .compact();
        System.out.println(jwtToken);
    }




    @Test
    void parse() {
        try {
            String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50IjoiMjUyOTYxOTAzNiIsImV4cCI6MTcxMTM2ODgzMH0.V3AkY2wHHFxBNR0WR0M1OGwXthT7EvpRsFNDHMgG7gM";
            JwtParser jwtParser = Jwts.parser();
            Jws<Claims> claimsJwts = jwtParser.setSigningKey("Aaron").parseClaimsJws(token);
            Claims claims = claimsJwts.getBody();
            Date date = new Date(System.currentTimeMillis());
            System.out.println(date);
            System.out.println(claims.getExpiration());

            long nowTimes = date.getTime();
            long expirationTimes = claims.getExpiration().getTime();

            long timeDifference = expirationTimes - nowTimes;
            if (timeDifference < 1000 * 60 * 60 * 10){
                System.out.println("token即将过时，请重新签发token");
            }else{
                System.out.println("放心用吧，过期还早着呢");
            }
        } catch (Exception e) {
            System.out.println("token过期，请重新登录");
        }
    }

    @Test
    void getYear(){
        System.out.println(blogMapper.getBlogDetail(23));
        System.out.println(blogMapper.getBlogInfo());
    }


    @Autowired
    private ApplicationContext applicationContext;//获取ioc容器对象

    @Test
    void getIoc(){
        //根据对象名获取ioc对象
        System.out.println(applicationContext.getBean("blogController"));//未指定对象名时，默认为类名首字母小写
        //根据对象类型获取对象
        System.out.println(applicationContext.getBean(BlogController.class));
        //根据对象类型和对象名获取对象
        System.out.println(applicationContext.getBean("blogController", BlogController.class));
    }


}
