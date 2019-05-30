package com.server.frontendservice.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.server.common.model.Property;
import com.server.common.service.PropertyService;
import lombok.val;

@Controller
public class PropertyController extends BaseController {
    private static final String PATH = "configuration/properties";

    @Autowired
    private PropertyService propertyService;

    @GetMapping(PATH)
    public String errors(Model model) {
        val all = propertyService.getAll();
        model.addAttribute("properties", all);
        model.addAttribute("styles", Arrays.asList("data-tables", "data-tables/properties", "font-awesome.min"));
        model.addAttribute("sheets", Arrays.asList("data-tables", "font-awesome.min"));
        return "configuration/properties";
    }

    @GetMapping(PATH + "/{reference}")
    public String errors(Model model, @PathVariable("reference") String externalReference) {
        model.addAttribute("item", propertyService.getByExternalReference(externalReference));
        return "configuration/properties/edit";
    }

    @PostMapping(value = PATH + "/update")
    public String update(Model model,
                         @ModelAttribute("property") Property property,
                         RedirectAttributes redirect) {
        propertyService.update(property);
        model.addAttribute("item", property);

        toast("Successfully updated dashboard", redirect);
        return "redirect:/configuration/properties";
    }
}
