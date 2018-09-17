package com.server.frontendservice.controller;

import com.server.common.model.Secret;
import com.server.common.model.SecretProperty;
import com.server.frontendservice.service.SecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.lang.String.format;

@Controller
public class SecretController extends BaseController
{
    private static final String PATH = "passwords";

    @Autowired
    private SecretService secretService;

    @GetMapping(PATH)
    public String secrets(Model model) throws ExecutionException, InterruptedException
    {

        CompletableFuture<List<Secret>> secrets = secretService.getAll();
        List<String> types = secretService.getAllTypes();

        CompletableFuture.allOf(secrets).join();

        model.addAttribute("secrets", secrets.get());
        model.addAttribute("types", types);
        model.addAttribute("styles", Arrays.asList("data-tables", "data-tables/secrets", "font-awesome.min"));
        model.addAttribute("sheets", Arrays.asList("data-tables", "font-awesome.min"));

        return "secrets";
    }

    @GetMapping("secrets/{id}")
    public String secret(Model model, @PathVariable("id") long id) throws ExecutionException, InterruptedException {

        CompletableFuture<Secret> secret = secretService.getById(id);
        CompletableFuture<List<SecretProperty>> secretProperties = secretService.getProperties(id);
        List<String> types = secretService.getAllTypes();
        List<String> propertyNames = secretService.getAllPropertyNames();

        CompletableFuture.allOf(secret, secretProperties).join();

        model.addAttribute("item", secret.get());
        model.addAttribute("properties", secretProperties.get());
        model.addAttribute("propertyNames", propertyNames);
        model.addAttribute("types", types);
        return "/secrets/edit";
    }

    @GetMapping(value = "secrets/create")
    public String createView(Model model) throws ExecutionException, InterruptedException
    {
        List<String> types = secretService.getAllTypes();

        model.addAttribute("types", types);
        model.addAttribute("item", new Secret());

        return "/secrets/edit";
    }

    @PostMapping(value = "secrets/update")
    public String create(Model model,
                         @ModelAttribute("secret") Secret secret,
                         RedirectAttributes redirect) throws ExecutionException, InterruptedException
    {
        final boolean isNew = secret.getId() == null;

        secretService.update(secret);

        if (isNew)
        {
            CompletableFuture<List<Secret>> all = secretService.getAll();
            CompletableFuture.allOf(all).join();
            Collections.reverse(all.get());
            secret = all.get().iterator().next();

        }

        model.addAttribute("item", secret);

        toast(format("Successfully %s secret", isNew ? "created" : "updated"), redirect);

        return format("redirect:/secrets/%s", secret.getId());
    }

    @PostMapping(value = "secrets/{id}/property/create")
    public String createProperty(Model model,
                         @PathVariable("id") long id,
                         @ModelAttribute("secretProperty") SecretProperty secretProperty,
                         RedirectAttributes redirect) throws ExecutionException, InterruptedException
    {

        secretProperty.setSecretId(id);
        secretProperty.setId(null);

        secretService.createProperty(secretProperty);

        CompletableFuture<Secret> secret = secretService.getById(id);
        CompletableFuture<List<SecretProperty>> secretProperties = secretService.getProperties(id);

        CompletableFuture.allOf(secret, secretProperties).join();

        model.addAttribute("item", secret.get());
        model.addAttribute("properties", secretProperties.get());

        toast("Successfully created new property", redirect);

        return format("redirect:/secrets/%s", id);
    }

    @GetMapping(value = "secrets/{secretId}/property/{id}/delete")
    public String deleteProperty(@PathVariable("secretId") long secretId,
                                 @PathVariable("id") long id,
                                 RedirectAttributes redirect)
    {
        secretService.deleteProperty(id);

        toast("Successfully deleted property", redirect);

        return format("redirect:/secrets/%s", secretId);
    }

    @GetMapping(value = "secrets/{id}/delete")
    public String delete(@PathVariable("id") long id,
                         RedirectAttributes redirect) throws ExecutionException, InterruptedException
    {
        secretService.delete(id);

        toast("Successfully deleted secret", redirect);

        return "redirect:/passwords";
    }
}
