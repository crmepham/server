package com.server.frontendservice.controller;

import com.server.common.model.Action;
import com.server.common.model.Job;
import com.server.common.model.Schedule;
import com.server.frontendservice.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.lang.String.format;

@Controller
public class ScheduleController extends BaseController
{
    private static final String PATH = "configuration/schedules";

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping(PATH)
    public String schedules(Model model) throws ExecutionException, InterruptedException
    {

        CompletableFuture<List<Schedule>> schedules = scheduleService.getAll();

        CompletableFuture.allOf(schedules).join();

        model.addAttribute("schedules", schedules.get());
        model.addAttribute("styles", Arrays.asList("data-tables", "data-tables/schedules", "font-awesome.min"));
        model.addAttribute("sheets", Arrays.asList("data-tables", "font-awesome.min"));

        return "configuration/schedules";
    }

    @GetMapping(PATH + "/{id}")
    public String schedule(Model model, @PathVariable("id") long id) throws ExecutionException, InterruptedException {

        CompletableFuture<Schedule> schedule = scheduleService.getById(id);
        CompletableFuture<List<Job>> jobs = scheduleService.getJobs(id);

        CompletableFuture.allOf(schedule, jobs).join();

        model.addAttribute("styles", Arrays.asList("data-tables", "data-tables/jobs", "font-awesome.min"));
        model.addAttribute("sheets", Arrays.asList("data-tables", "font-awesome.min"));

        model.addAttribute("item", schedule.get());
        model.addAttribute("jobs", jobs.get());
        return "configuration/schedules/edit";
    }

    @PostMapping(PATH + "/update")
    public String create(Model model,
                         @ModelAttribute("schedule") Schedule schedule,
                         RedirectAttributes redirect)
    {
        scheduleService.update(schedule);

        model.addAttribute("item", schedule);

        toast(format("Successfully updated schedule"), redirect);

        return format("redirect:/configuration/schedules");
    }

    @GetMapping(PATH + "/job/{id}")
    public String job(Model model, @PathVariable("id") long id) throws ExecutionException, InterruptedException {

        CompletableFuture<Job> job = scheduleService.getJob(id);

        CompletableFuture.allOf(job).join();

        model.addAttribute("item", job.get());

        return format("%s/job/edit", PATH);
    }

    @PostMapping(PATH + "/job/update")
    public String jobUpdate(Model model,
                         @ModelAttribute("job") Job job,
                         RedirectAttributes redirect)
    {
        scheduleService.updateJob(job);

        model.addAttribute("item", job);

        toast(format("Successfully updated schedule job"), redirect);

        return format("redirect:/configuration/schedules/%s", job.getScheduleId());
    }

    @GetMapping(PATH + "/job/{id}/invoke")
    public String jobUpdate(@PathVariable("id") long id,
                            RedirectAttributes redirect)
    {
        scheduleService.invokeJob(id);

        toast("Job has started. See action %s for the progress of the job.", redirect);

        return format("redirect:/configuration/schedules/job/%s", id);
    }
}
