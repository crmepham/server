package com.server.frontendservice.controller;

import com.server.common.model.Dashboard;
import com.server.frontendservice.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("configuration/dashboards")
public class DashboardController
{
    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public void dashboards(Model model)
    {
        model.addAttribute("dashboards", dashboardService.getAll());
        model.addAttribute("styles", Arrays.asList("data-tables", "data-tables/dashboards", "font-awesome.min"));
        model.addAttribute("sheets", Arrays.asList("data-tables", "font-awesome.min"));
    }

    @GetMapping("/{id}")
    public String dashboard(Model model, @PathVariable("id") Long id)
    {
        Dashboard d = dashboardService.get(id);
        model.addAttribute("item", d);

        return "/configuration/dashboard/edit";
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String update(Model model, @ModelAttribute("dashboard") Dashboard dashboard)
    {
        dashboardService.update(dashboard);
        model.addAttribute("item", dashboard);

        return "redirect:/configuration/dashboards";
    }
}
