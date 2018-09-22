package com.server.frontendservice.controller;

import com.server.frontendservice.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Controller
public class SearchController extends BaseController
{
    private static final String PATH = "search";

    @Autowired
    private SearchService searchService;

    @GetMapping(PATH)
    public void reminders(Model model, @RequestParam("text") String text) throws ExecutionException, InterruptedException
    {
        CompletableFuture<Map<String, List<Object>>> results = searchService.search(text);

        CompletableFuture.allOf(results).join();

        model.addAttribute("searchTerm", text);
        model.addAttribute("empty", isEmpty(results.get()));
        model.addAttribute("results", results.get());
    }

    private boolean isEmpty(Map<String, List<Object>> results) {
        for (Map.Entry<String, List<Object>> entity : results.entrySet()) {
            if (!entity.getValue().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
