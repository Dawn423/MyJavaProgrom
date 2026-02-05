package com.example.ipptest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.ipptest.mapper")
public class IppTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(IppTestApplication.class, args);
    }
}
