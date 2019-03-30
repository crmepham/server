package com.server.crawlerservice.service;

import org.springframework.stereotype.Service;

@Service
public class SystemService
{
    public void stop() {
        System.exit(0);
    }
}
