package com.server.frontendservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.server.frontendservice.service.ActionService;
import lombok.val;

@Controller
public class ActionController extends BaseController
{
    private static final String PATH = "reporting/actions";

    @Autowired
    private ActionService actionService;

    @GetMapping(PATH)
    public String actions(Model model) {
        val all = actionService.getAll();
        model.addAttribute("actions", all);
        css(model, "data-tables", "data-tables/actions", "font-awesome.min");
        js(model, "data-tables", "font-awesome.min");
        return "reporting/actions";
    }

    @GetMapping(PATH + "/{id}")
    public String action(Model model, @PathVariable("id") long id) {
        model.addAttribute("item", actionService.getById(id));
        return "reporting/actions/edit";
    }
}
