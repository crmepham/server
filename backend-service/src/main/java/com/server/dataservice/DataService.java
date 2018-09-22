package com.server.dataservice;

import com.server.dataservice.service.JobServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EntityScan("com.server.common.model")
public class DataService {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(DataService.class, args);
        JobServiceImpl jobService = (JobServiceImpl) context.getBean("jobServiceImpl");
        jobService.start();
    }
}
