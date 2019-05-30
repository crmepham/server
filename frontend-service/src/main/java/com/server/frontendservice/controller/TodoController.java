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

import com.server.common.model.Todo;
import com.server.frontendservice.service.TodoService;
import lombok.val;

@Controller
public class TodoController extends BaseController {
    private static final String PATH = "applications/todos";

    @Autowired
    private TodoService todoService;

    @GetMapping(PATH)
    public String reminders(Model model) throws ExecutionException, InterruptedException {
        val todos = todoService.getAll();

        CompletableFuture.allOf(todos).join();

        model.addAttribute("todos", todos.get());
        css(model, "data-tables", "data-tables/todos", "font-awesome.min");
        js(model, "data-tables", "font-awesome.min");
        return "/applications/todos";
    }

    @GetMapping("applications/todos/{id}")
    public String reminder(Model model, @PathVariable("id") long id) throws ExecutionException, InterruptedException {
        val todo = todoService.getById(id);

        CompletableFuture.allOf(todo).join();

        model.addAttribute("item", todo.get());
        return "/applications/todos/edit";
    }

    @GetMapping(value = "applications/todos/create")
    public String createView(Model model) {
        model.addAttribute("item", new Todo());
        return "/applications/todos/edit";
    }

    @PostMapping(value = "applications/todos/update")
    public String create(Model model,
                         @ModelAttribute("todo") Todo todo,
                         RedirectAttributes redirect) throws ExecutionException, InterruptedException {
        val isNew = todo.getId() == null;

        todoService.update(todo);

        if (isNew)
        {
            val all = todoService.getAll();
            CompletableFuture.allOf(all).join();
            Collections.reverse(all.get());
            todo = all.get().iterator().next();

        }

        model.addAttribute("item", todo);

        toast(format("Successfully %s todo", isNew ? "created" : "updated"), redirect);
        return format("redirect:/applications/todos/%s", todo.getId());
    }

    @GetMapping(value = "applications/todos/{id}/delete")
    public String delete(@PathVariable("id") long id,
                         RedirectAttributes redirect) {
        todoService.delete(id);

        toast("Successfully deleted todo", redirect);
        return "redirect:/applications/todos";
    }
}
