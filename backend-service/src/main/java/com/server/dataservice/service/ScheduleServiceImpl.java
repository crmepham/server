package com.server.dataservice.service;

import com.server.common.model.Job;
import com.server.common.model.Schedule;
import com.server.dataservice.repository.ActionRepository;
import com.server.dataservice.repository.JobRepository;
import com.server.dataservice.repository.ScheduleRepository;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl
{
    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private JobServiceImpl jobService;

    public void stop(Schedule schedule) {

        List<Job> jobs = jobRepository.findByScheduleIdAndDeletedFalse(schedule.getId());
        if (jobs.isEmpty()) {
            return;
        }

        List<Job> enabled = jobs.stream().filter(j -> j.isEnabled()).collect(Collectors.toList());
        if (enabled.isEmpty()) {
            return;
        }

        jobService.stop(schedule, enabled);
    }
}
