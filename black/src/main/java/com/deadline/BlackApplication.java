package com.deadline;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan(basePackages = "com.deadline.mapper")
@SpringBootApplication
@EnableCaching
public class BlackApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlackApplication.class, args);
    }

}
