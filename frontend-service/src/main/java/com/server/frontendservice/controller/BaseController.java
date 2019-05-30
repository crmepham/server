package com.server.frontendservice.controller;

import static java.util.Arrays.asList;

import com.server.common.service.PropertyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Component
public abstract class BaseController {

    @Autowired
    private PropertyService propertyService;

    protected void css(@NonNull final Model model, @NonNull final String... styles) {
        model.addAttribute("styles", asList(styles));
    }

    protected void js(@NonNull final Model model, @NonNull final String... scripts) {
        model.addAttribute("sheets", asList(scripts));
    }

    protected void toast(@NonNull final String message, RedirectAttributes redirect) {
        redirect.addFlashAttribute("toast", message);
    }

    /**
     * Gets the propertyService.
     *
     * @return propertyService
     */
    public PropertyService getPropertyService()
    {
        return propertyService;
    }
}
