package com.server.frontendservice.controller;

import com.server.common.model.Email;
import com.server.frontendservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;

@Controller
public class EmailController extends BaseController
{
    private static final String PATH = "reporting/emails";

    @Autowired
    private EmailService emailService;

    @GetMapping(PATH)
    public String emails(Model model) {
        List<Email> all = emailService.getAll();
        model.addAttribute("emails", all);
        model.addAttribute("styles", Arrays.asList("data-tables", "data-tables/emails", "font-awesome.min"));
        model.addAttribute("sheets", Arrays.asList("data-tables", "font-awesome.min"));
        return "reporting/emails";
    }

    @GetMapping(PATH + "/{id}")
    public String email(Model model, @PathVariable("id") long id) {
        model.addAttribute("item", emailService.getById(id));
        return "reporting/emails/edit";
    }
}
