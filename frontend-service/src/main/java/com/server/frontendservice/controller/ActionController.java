package com.server.frontendservice.controller;

import com.server.common.model.Action;
import com.server.common.model.Email;
import com.server.frontendservice.service.ActionService;
import com.server.frontendservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;

@Controller
public class ActionController extends BaseController
{
    private static final String PATH = "reporting/actions";

    @Autowired
    private ActionService actionService;

    @GetMapping(PATH)
    public String actions(Model model) {
        List<Action> all = actionService.getAll();
        model.addAttribute("actions", all);
        model.addAttribute("styles", Arrays.asList("data-tables", "data-tables/actions", "font-awesome.min"));
        model.addAttribute("sheets", Arrays.asList("data-tables", "font-awesome.min"));
        return "reporting/actions";
    }

    @GetMapping(PATH + "/{id}")
    public String action(Model model, @PathVariable("id") long id) {
        model.addAttribute("item", actionService.getById(id));
        return "reporting/actions/edit";
    }
}
