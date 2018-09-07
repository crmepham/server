package com.server.frontendservice.controller;

import org.springframework.lang.NonNull;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public abstract class BaseController
{
    protected void toast(@NonNull final String message, RedirectAttributes redirect) {

        redirect.addFlashAttribute("toast", message);
    }
}
