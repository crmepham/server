package com.server.frontendservice.controller;

import com.server.common.model.ApplicationError;
import com.server.frontendservice.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;

@Controller
public class ErrorController
{
    private static final String PATH = "configuration/errors";

    @Autowired
    private ErrorService errorService;

    @GetMapping(PATH)
    public String errors(Model model) {
        List<ApplicationError> all = errorService.getAll();
        model.addAttribute("errors", all);
        model.addAttribute("styles", Arrays.asList("data-tables", "data-tables/errors", "font-awesome.min"));
        model.addAttribute("sheets", Arrays.asList("data-tables", "font-awesome.min"));
        return "configuration/errors";
    }

    @GetMapping(PATH + "/{id}")
    public String errors(Model model, @PathVariable("id") long id) {
        model.addAttribute("item", errorService.getById(id));
        return "configuration/errors/detail";
    }
}
