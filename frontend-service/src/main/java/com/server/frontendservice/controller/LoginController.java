package com.server.frontendservice.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public void login(Model model) {
        model.addAttribute("showNavigation", false);
    }

    @GetMapping("/logout")
    public void logout(Map<String, Object> model) {
        model.put("logout", Boolean.valueOf(true));
    }
}
