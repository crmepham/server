package com.server.frontendservice.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.server.common.model.DatabaseConfiguration;
import com.server.frontendservice.service.DatabaseConfigurationService;

@Controller
public class DatabaseController extends BaseController
{
    private static final String PATH = "configuration/database";

    @Autowired
    private DatabaseConfigurationService databaseConfigurationService;

    @GetMapping(PATH)
    public void fragments(Model model) throws InterruptedException, ExecutionException {
        List<DatabaseConfiguration> o = databaseConfigurationService.getAll().get();
        model.addAttribute("configurations", o);
        css(model, "data-tables", "data-tables/database-configurations", "font-awesome.min");
        js(model, "data-tables", "font-awesome.min");
    }

    @GetMapping(value = PATH + "/create")
    public String createView(Model model) {
        model.addAttribute("item", new DatabaseConfiguration());
        return "configuration/database/edit";
    }

    @GetMapping(PATH + "/{id}")
    public String configuration(Model model, @PathVariable("id") Long id) {
        model.addAttribute("item", databaseConfigurationService.get(id));
        return "/configuration/database/edit";
    }

    @PostMapping(value = PATH + "/update")
    public String update(Model model,
                         @ModelAttribute("configuration") DatabaseConfiguration configuration,
                         RedirectAttributes redirect) {
        return create(model, configuration, redirect);
    }

    @PostMapping(value = PATH + "/create")
    public String create(Model model,
                         @ModelAttribute("configuration") DatabaseConfiguration configuration,
                         RedirectAttributes redirect) {
        databaseConfigurationService.update(configuration);
        model.addAttribute("item", configuration);

        toast("Successfully updated configuration", redirect);
        return "redirect:/configuration/database";
    }

    @GetMapping(value = PATH + "/{id}/delete")
    public String delete(@PathVariable("id") long id,
                         RedirectAttributes redirect) {
        databaseConfigurationService.delete(id);
        toast("Successfully deleted database configuration", redirect);
        return "redirect:/configuration/database";
    }
}
