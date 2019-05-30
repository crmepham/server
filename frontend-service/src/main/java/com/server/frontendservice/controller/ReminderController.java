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

import com.server.common.model.Reminder;
import com.server.frontendservice.service.ReminderService;
import lombok.val;

@Controller
public class ReminderController extends BaseController {
    private static final String PATH = "applications/reminders";

    @Autowired
    private ReminderService reminderService;

    @GetMapping(PATH)
    public String reminders(Model model) throws ExecutionException, InterruptedException {
        val reminders = reminderService.getAll();

        CompletableFuture.allOf(reminders).join();

        model.addAttribute("reminders", reminders.get());
        css(model, "data-tables", "data-tables/reminders", "font-awesome.min");
        js(model, "data-tables", "font-awesome.min");
        return "/applications/reminders";
    }

    @GetMapping("applications/reminders/{id}")
    public String reminder(Model model, @PathVariable("id") long id) throws ExecutionException, InterruptedException {
        val reminder = reminderService.getById(id);

        CompletableFuture.allOf(reminder).join();

        model.addAttribute("item", reminder.get());
        return "/applications/reminders/edit";
    }

    @GetMapping(value = "applications/reminders/create")
    public String createView(Model model) throws ExecutionException, InterruptedException {
        model.addAttribute("item", new Reminder());
        return "/applications/reminders/edit";
    }

    @PostMapping(value = "applications/reminders/update")
    public String create(Model model,
                         @ModelAttribute("reminder") Reminder reminder,
                         RedirectAttributes redirect) throws ExecutionException, InterruptedException {
        val isNew = reminder.getId() == null;

        reminderService.update(reminder);

        if (isNew) {
            val all = reminderService.getAll();
            CompletableFuture.allOf(all).join();
            Collections.reverse(all.get());
            reminder = all.get().iterator().next();

        }

        model.addAttribute("item", reminder);

        toast(format("Successfully %s reminder", isNew ? "created" : "updated"), redirect);
        return format("redirect:/applications/reminders/%s", reminder.getId());
    }

    @GetMapping(value = "applications/reminders/{id}/delete")
    public String delete(@PathVariable("id") long id,
                         RedirectAttributes redirect) {
        reminderService.delete(id);

        toast("Successfully deleted reminder", redirect);
        return "redirect:/applications/reminders";
    }
}
