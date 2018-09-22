package com.server.dataservice.service;

import com.server.common.model.Job;
import com.server.common.model.Schedule;
import com.server.dataservice.repository.JobRepository;
import com.server.dataservice.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService
{
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobServiceImpl jobService;

    @Autowired
    private ScheduleServiceImpl scheduleService;

    public List<Schedule> getAll() {
        return scheduleRepository.findByDeletedFalse();
    }

    public Schedule get(Long id) {
        return scheduleRepository.findById(id).get();
    }

    public void create(Schedule schedule) {

        final Schedule previousSchedule = scheduleRepository.getOne(schedule.getId());

        if (previousSchedule.isEnabled() && !schedule.isEnabled()) {
            scheduleService.stop(schedule);
        }

        scheduleRepository.save(schedule);
    }

    public List<Job> getJobs(long id) {
        return jobRepository.findByScheduleIdAndDeletedFalse(id);
    }

    public Job getJob(Long id) {
        return jobRepository.findById(id).get();
    }

    public void createJob(Job job) {

        final Job previousJob = jobRepository.getOne(job.getId());
        final Schedule schedule = scheduleRepository.getOne(job.getScheduleId());

        if (previousJob.isEnabled() && !job.isEnabled()) {
            jobService.stop(schedule, job);
        } else if (!previousJob.isEnabled() && job.isEnabled()) {
            jobService.invoke(job, false);
        }

        jobRepository.save(job);
    }

    public Boolean invokeJob(int id) {
        return true;
    }
}
