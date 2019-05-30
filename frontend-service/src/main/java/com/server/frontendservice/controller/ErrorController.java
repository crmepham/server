package com.server.frontendservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.server.frontendservice.service.ErrorService;
import lombok.val;

@Controller
public class ErrorController extends BaseController
{
    private static final String PATH = "reporting/errors";

    @Autowired
    private ErrorService errorService;

    @GetMapping(PATH)
    public String errors(Model model) {
        val all = errorService.getAll();
        model.addAttribute("errors", all);
        css(model, "data-tables", "data-tables/errors", "font-awesome.min");
        js(model, "data-tables", "font-awesome.min");
        return "reporting/errors";
    }

    @GetMapping(PATH + "/{id}")
    public String errors(Model model, @PathVariable("id") long id) {
        model.addAttribute("item", errorService.getById(id));
        return "reporting/errors/detail";
    }
}
