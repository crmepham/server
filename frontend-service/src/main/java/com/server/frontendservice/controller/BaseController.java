package com.server.frontendservice.controller;

import com.server.frontendservice.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Component
public abstract class BaseController
{
    @Autowired
    private PropertyService propertyService;

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
