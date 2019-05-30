package com.server.frontendservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.server.frontendservice.service.SystemService;

@Controller
public class SystemController extends BaseController {
    private static final String PATH = "configuration/system";

    @Autowired
    private SystemService systemService;

    @GetMapping(PATH)
    public void fragments(Model model) {
        css(model, "system");
    }

    @PostMapping(value = PATH + "/stop-application")
    public void stopFrontend() {
        systemService.stopCrawler();
        systemService.stopBackend();
        systemService.stopFrontend();
    }
}
