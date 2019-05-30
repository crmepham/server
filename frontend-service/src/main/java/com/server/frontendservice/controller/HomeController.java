package com.server.frontendservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends BaseController {
    @GetMapping({"/","/home"})
    public String home(Model model) {
        model.addAttribute("name", "pooop");
        css(model, "font-awesome.min");
        js(model, "font-awesome.min");
        return "home";
    }
}
