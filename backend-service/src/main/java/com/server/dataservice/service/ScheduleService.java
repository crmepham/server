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

    public List<Schedule> getAll() {
        return scheduleRepository.findByDeletedFalse();
    }

    public Schedule get(Long id) {
        return scheduleRepository.findById(id).get();
    }

    public void create(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    public List<Job> getJobs(long id) {
        return jobRepository.findByScheduleIdAndDeletedFalse(id);
    }

    public Job getJob(Long id) {
        return jobRepository.findById(id).get();
    }

    public void createJob(Job job) {
        jobRepository.save(job);
    }

    public Boolean invokeJob(int id) {
        return true;
    }
}
