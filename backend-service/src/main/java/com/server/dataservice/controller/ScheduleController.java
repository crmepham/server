package com.server.dataservice.controller;

import com.google.gson.Gson;
import com.server.common.model.Action;
import com.server.common.model.Job;
import com.server.common.model.Reminder;
import com.server.common.model.Schedule;
import com.server.dataservice.interfaces.JobService;
import com.server.dataservice.service.JobServiceImpl;
import com.server.dataservice.service.ReminderService;
import com.server.dataservice.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("schedules")
public class ScheduleController
{
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private JobServiceImpl jobService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Schedule>> getAll() {
        return new ResponseEntity<>(scheduleService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Schedule> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(scheduleService.get(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = "application/json")
    public void create(@RequestBody String payload) {
        scheduleService.create(new Gson().fromJson(payload, Schedule.class));
    }

    @GetMapping("/get-jobs/{id}")
    public ResponseEntity<List<Job>> getJobs(@PathVariable("id") Long id) {
        return new ResponseEntity<>(scheduleService.getJobs(id), HttpStatus.OK);
    }

    @GetMapping("/get-job-by-id/{id}")
    public ResponseEntity<Job> getJob(@PathVariable("id") Long id) {
        return new ResponseEntity<>(scheduleService.getJob(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create-job", consumes = "application/json")
    public void createJob(@RequestBody String payload) {
        scheduleService.createJob(new Gson().fromJson(payload, Job.class));
    }

    @GetMapping("/invoke-job/{id}")
    public void invokeJob(@PathVariable("id") Long id) {
        jobService.invoke(id, true);
    }
}
