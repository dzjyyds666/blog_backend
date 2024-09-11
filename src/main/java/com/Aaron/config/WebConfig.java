package com.Aaron.config;

import com.Aaron.Interceptor.tokenInterceptor;
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

//    //跨域配置 在请求头中有token时无效
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOriginPatterns("*")
//                .allowedMethods("GET","POST","DELETE","PUT")
//                .maxAge(3600)
//                .allowedHeaders("*");
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器，并指定拦截的路径
        registry.addInterceptor(new tokenInterceptor(myRedis)).addPathPatterns("/**")
                .excludePathPatterns("/admin/login",
                        "/admin/logout",
                        "/blog/front/**",
                        "/settings/front/**",
                        "/type/front/**",
                        "/messageboards/front/**",
                        "/comments/front/**",
                        "/admin/front/**");//忽略拦截路径
    }

}
