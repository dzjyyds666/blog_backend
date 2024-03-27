package com.Aaron.config;

import com.Aaron.Interceptor.tokenInterceptor;
import com.Aaron.service.IUserService;
import com.Aaron.utils.MyRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private MyRedis myRedis;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器，并指定拦截的路径
        registry.addInterceptor(new tokenInterceptor(myRedis)).addPathPatterns("/**")
                .excludePathPatterns("/admin/login","/admin/logout");//忽略拦截路径
    }

}
