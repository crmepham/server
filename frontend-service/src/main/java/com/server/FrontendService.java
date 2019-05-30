package com.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableAsync
@EnableWebSecurity
@SpringBootApplication
@ComponentScan("com.server")
@EnableJpaRepositories("com.server")
@EntityScan("com.server")
public class FrontendService {

    public static void main(String[] args) {
        SpringApplication.run(FrontendService.class, args);
    }
}
