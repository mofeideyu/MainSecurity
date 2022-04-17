package com.mofei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.mofei.dao")
@SpringBootApplication
public class SecurityWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityWebApplication.class, args);
    }

}
