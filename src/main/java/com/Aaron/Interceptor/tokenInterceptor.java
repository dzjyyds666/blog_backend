package com.Aaron.Interceptor;

import com.Aaron.utils.JwtToken;
import com.Aaron.utils.MyRedis;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Component
public class tokenInterceptor implements HandlerInterceptor
{
    private final JwtToken jwtToken = new JwtToken();

    private final String signature = "Aaron";

    private final MyRedis myRedis;



    @Autowired
    public tokenInterceptor(MyRedis myRedis) {
        this.myRedis = myRedis;
    }


//  redis写法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        //处理token
        token = jwtToken.dealWithToken(token);
        try {
            Claims claims = Jwts.parser().setSigningKey(signature).parseClaimsJws(token).getBody();
            //先判断token是否存在redis
            String tokenRedis = myRedis.getValue(claims.get("account").toString()+" token");
            if(tokenRedis == null || !Objects.equals(token,tokenRedis)){
                response.setStatus(201);
                return false;
            }
            long expiration = claims.getExpiration().getTime();
            long nowTime = new Date(System.currentTimeMillis()).getTime();
            if((expiration - nowTime) < 1000*60*60*5){
                JwtBuilder jwtBuilder = Jwts.builder();
                String newToken = jwtBuilder
                        .setHeaderParam("typ", "JWT")
                        .setHeaderParam("alg", "HS256")
                        .claim("account", claims.get("account"))
                        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 *24 ))
                        .signWith(SignatureAlgorithm.HS256, signature)
                        .compact();
                //修改redis中的token值
                myRedis.setValue(claims.get("account").toString()+" token",newToken);
                response.addHeader("new-token",newToken);

                // 设置允许跨域请求的响应头
                response.setHeader("Access-Control-Allow-Origin", "*"); // 允许所有来源的请求
                response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS"); // 允许的请求方法
                response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); // 允许的请求头

                // 指定响应头中允许暴露的自定义头
                response.setHeader("Access-Control-Expose-Headers", "new-token");
            }
            return true; // 继续执行后续的Controller方法
        } catch (Exception e) {
            response.setStatus(201);
            return false; // 拦截请求，不执行后续的Controller方法
        }
    }
}