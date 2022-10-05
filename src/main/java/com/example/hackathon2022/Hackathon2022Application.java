package com.example.hackathon2022;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableConfigurationProperties
@SpringBootApplication
public class Hackathon2022Application {

    public static void main(String[] args) {
        SpringApplication.run(Hackathon2022Application.class, args);
    }

}
