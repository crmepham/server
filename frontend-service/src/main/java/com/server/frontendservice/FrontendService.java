package com.server.frontendservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
public class FrontendService {

    public static void main(String[] args) {

        SpringApplication.run(FrontendService.class, args);
    }
}
