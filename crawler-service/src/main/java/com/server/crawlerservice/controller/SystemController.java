package com.server.crawlerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.crawlerservice.service.SystemService;

@RestController
@RequestMapping("system")
public class SystemController
{
    @Autowired
    private SystemService systemService;

    @PostMapping(value = "/stop")
    public void stop() {
        systemService.stop();
    }
}
