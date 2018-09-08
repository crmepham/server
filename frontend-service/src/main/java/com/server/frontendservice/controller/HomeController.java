package com.server.frontendservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
public class HomeController
{
    @GetMapping({"/","/home"})
    public String home(Model model) {
        model.addAttribute("name", "pooop");
        model.addAttribute("styles", Arrays.asList("font-awesome.min"));
        model.addAttribute("sheets", Arrays.asList("font-awesome.min"));
        return "home";
    }
}
