package com.kevin.wxservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.kevin.wxservice.mapper")
public class WxserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxserviceApplication.class, args);
    }

}
