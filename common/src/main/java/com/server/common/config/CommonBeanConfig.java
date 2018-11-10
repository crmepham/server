package com.server.common.config;

import com.server.common.repository.ErrorRepository;
import com.server.common.repository.FileRepository;
import com.server.common.repository.PropertyRepository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EntityScan("com.server.common.repository")
public class CommonBeanConfig
{
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ErrorRepository getErrorRepository() {
        return new ErrorRepository();
    }

    @Bean
    public FileRepository getFileRepository() {
        return new FileRepository();
    }

    @Bean
    public PropertyRepository getPropertyRepository() {
        return new PropertyRepository();
    }
}
