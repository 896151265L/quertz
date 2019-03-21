package com.example.quertz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@MapperScan("com.example.quertz.mapper")//启动的时候扫描mapper
public class QuertzApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuertzApplication.class, args);
    }

}
