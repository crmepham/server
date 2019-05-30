package com.server.frontendservice.controller;

import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.server.common.model.Dashboard;
import com.server.frontendservice.service.DashboardService;

@Controller
public class DashboardController extends BaseController
{
    private static final String PATH = "configuration/dashboards";

    @Autowired
    private DashboardService dashboardService;

    @GetMapping(PATH)
    public void dashboards(Model model) throws InterruptedException, ExecutionException {
        model.addAttribute("dashboards", dashboardService.getAll().get());
        css(model, "data-tables", "data-tables/dashboards", "font-awesome.min");
        js(model, "data-tables", "font-awesome.min");
    }

    @GetMapping(PATH + "/{id}")
    public String dashboard(Model model, @PathVariable("id") Long id) {
        model.addAttribute("item", dashboardService.get(id));
        return "/configuration/dashboard/edit";
    }

    @PostMapping(value = PATH + "/update")
    public String update(Model model,
                         @ModelAttribute("dashboard") Dashboard dashboard,
                         RedirectAttributes redirect,
                         HttpServletRequest request)
    {
        dashboardService.update(dashboard, request);
        model.addAttribute("item", dashboard);
        toast("Successfully updated dashboard", redirect);
        return "redirect:/configuration/dashboards";
    }
}
