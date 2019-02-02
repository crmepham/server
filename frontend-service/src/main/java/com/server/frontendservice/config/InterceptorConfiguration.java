package com.server.frontendservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.server.frontendservice.interceptor.DashboardInterceptor;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer
{

    @Autowired
    private DashboardInterceptor dashboardInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(dashboardInterceptor);
    }
}
