package com.server.frontendservice.controller;

import static java.lang.String.format;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.server.common.model.Secret;
import com.server.common.model.SecretProperty;
import com.server.frontendservice.service.SecretService;
import lombok.val;

@Controller
public class SecretController extends BaseController {
    private static final String PATH = "applications/passwords";

    @Autowired
    private SecretService secretService;

    @GetMapping(PATH)
    public String secrets(Model model) throws ExecutionException, InterruptedException {
        val secrets = secretService.getAll();
        val types = secretService.getAllTypes();

        CompletableFuture.allOf(secrets).join();

        model.addAttribute("secrets", secrets.get());
        model.addAttribute("types", types);
        css(model, "data-tables", "data-tables/secrets", "font-awesome.min");
        js(model, "data-tables", "font-awesome.min");
        return "applications/secrets";
    }

    @GetMapping("applications/secrets/{id}")
    public String secret(Model model, @PathVariable("id") long id) throws ExecutionException, InterruptedException {
        val secret = secretService.getById(id);
        val secretProperties = secretService.getProperties(id);
        val types = secretService.getAllTypes();
        val propertyNames = secretService.getAllPropertyNames();

        CompletableFuture.allOf(secret, secretProperties).join();

        model.addAttribute("item", secret.get());
        model.addAttribute("properties", secretProperties.get());
        model.addAttribute("propertyNames", propertyNames);
        model.addAttribute("types", types);
        return "/applications/secrets/edit";
    }

    @GetMapping(value = "applications/secrets/create")
    public String createView(Model model) {
        val types = secretService.getAllTypes();

        model.addAttribute("types", types);
        model.addAttribute("item", new Secret());
        return "/applications/secrets/edit";
    }

    @PostMapping(value = "applications/secrets/update")
    public String create(Model model,
                         @ModelAttribute("secret") Secret secret,
                         RedirectAttributes redirect) throws ExecutionException, InterruptedException {
        val isNew = secret.getId() == null;

        secretService.update(secret);

        if (isNew)
        {
            val all = secretService.getAll();
            CompletableFuture.allOf(all).join();
            Collections.reverse(all.get());
            secret = all.get().iterator().next();

        }

        model.addAttribute("item", secret);

        toast(format("Successfully %s secret", isNew ? "created" : "updated"), redirect);
        return format("redirect:/applications/secrets/%s", secret.getId());
    }

    @PostMapping(value = "applications/secrets/{id}/property/create")
    public String createProperty(Model model,
                         @PathVariable("id") long id,
                         @ModelAttribute("secretProperty") SecretProperty secretProperty,
                         RedirectAttributes redirect) throws ExecutionException, InterruptedException {

        secretProperty.setSecretId(id);
        secretProperty.setId(null);

        secretService.createProperty(secretProperty);

        val secret = secretService.getById(id);
        val secretProperties = secretService.getProperties(id);

        CompletableFuture.allOf(secret, secretProperties).join();

        model.addAttribute("item", secret.get());
        model.addAttribute("properties", secretProperties.get());

        toast("Successfully created new property", redirect);
        return format("redirect:/applications/secrets/%s", id);
    }

    @GetMapping(value = "applications/secrets/{secretId}/property/{id}/delete")
    public String deleteProperty(@PathVariable("secretId") long secretId,
                                 @PathVariable("id") long id,
                                 RedirectAttributes redirect) {
        secretService.deleteProperty(id);

        toast("Successfully deleted property", redirect);
        return format("redirect:/applications/secrets/%s", secretId);
    }

    @GetMapping(value = "applications/secrets/{id}/delete")
    public String delete(@PathVariable("id") long id,
                         RedirectAttributes redirect) {
        secretService.delete(id);

        toast("Successfully deleted secret", redirect);
        return "redirect:/applications/passwords";
    }
}
