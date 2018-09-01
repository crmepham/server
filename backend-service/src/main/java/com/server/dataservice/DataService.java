package com.server.dataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.server.common.model")
public class DataService {

    public static void main(String[] args) {

        SpringApplication.run(DataService.class, args);
    }
}
