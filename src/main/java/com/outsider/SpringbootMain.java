package com.outsider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
@MapperScan(basePackages = "com.outsider.mapper")
public class SpringbootMain {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMain.class, args);
    }
}
