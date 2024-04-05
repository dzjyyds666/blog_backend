package com.Aaron;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.Aaron.mapper")
public class BlogFinalVersionApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogFinalVersionApplication.class, args);
    }

}
