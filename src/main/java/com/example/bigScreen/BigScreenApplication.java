package com.example.bigScreen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan({"com.example.bigScreen.mapper"})
@EnableTransactionManagement
@EnableScheduling
public class BigScreenApplication {

    public static void main(String[] args) {
        SpringApplication.run(BigScreenApplication.class, args);
    }

}
