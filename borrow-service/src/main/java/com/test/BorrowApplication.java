package com.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.test.mapper")
public class BorrowApplication {

    public static void main(String[] args) {
        SpringApplication.run(BorrowApplication.class, args);
        System.out.println("接口文档地址：http://localhost:8301/doc.html");
    }
}