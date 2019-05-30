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

import com.server.common.model.Monitor;
import com.server.frontendservice.service.MonitorService;
import lombok.val;

@Controller
public class MonitorController extends BaseController {
    private static final String PATH = "applications/monitor";

    @Autowired
    private MonitorService monitorService;

    @GetMapping(PATH)
    public String monitors(Model model) throws ExecutionException, InterruptedException {
        val servers = monitorService.getAll();

        CompletableFuture.allOf(servers).join();

        model.addAttribute("servers", servers.get());
        css(model, "data-tables", "data-tables/accounts", "font-awesome.min");
        js(model, "data-tables", "font-awesome.min");
        return "/applications/monitor";
    }

    @GetMapping("applications/monitor/{id}")
    public String monitor(Model model, @PathVariable("id") long id) throws ExecutionException, InterruptedException {
        val server = monitorService.getById(id);

        CompletableFuture.allOf(server).join();

        css(model, "data-tables", "font-awesome.min");
        js(model, "data-tables", "font-awesome.min");

        model.addAttribute("item", server.get());
        return "/applications/monitor/edit";
    }

    @GetMapping(value = "applications/monitor/create")
    public String createView(Model model) {
        model.addAttribute("item", new Monitor());
        return "/applications/monitor/edit";
    }

    @PostMapping(value = "applications/monitor/update")
    public String create(Model model,
                         @ModelAttribute("monitor") Monitor server,
                         RedirectAttributes redirect) throws ExecutionException, InterruptedException {
        val isNew = server.getId() == null;

        monitorService.update(server);

        if (isNew) {
            val all = monitorService.getAll();
            CompletableFuture.allOf(all).join();
            Collections.reverse(all.get());
            server = all.get().iterator().next();
        }

        model.addAttribute("item", server);

        toast(format("Successfully %s monitor", isNew ? "created" : "updated"), redirect);
        return "redirect:/applications/monitor/" + server.getId();
    }

    @GetMapping(value = "applications/monitor/{id}/delete")
    public String delete(@PathVariable("id") long id,
                         RedirectAttributes redirect) {
        monitorService.delete(id);

        toast("Successfully deleted monitor", redirect);
        return "redirect:/applications/monitor";
    }
}
