package com.server.frontendservice.service;

import com.server.common.model.Action;
import com.server.common.model.Job;
import com.server.common.model.Schedule;
import com.server.frontendservice.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Transactional
@Service
public class ScheduleService extends BaseService
{
    @Autowired
    private ScheduleRepository scheduleRepository;

    public CompletableFuture<List<Schedule>> getAll() {

        return scheduleRepository.getAll();
    }

    public void update(Schedule schedule) {
        scheduleRepository.create(schedule);
    }

    public CompletableFuture<Schedule> getById(long id) {

        return scheduleRepository.getById(id);
    }

    public CompletableFuture<List<Job>> getJobs(long id) {

        return scheduleRepository.getJobs(id);
    }

    public CompletableFuture<Job> getJob(long id) {

        return scheduleRepository.getJob(id);
    }

    public void updateJob(Job job) {
        scheduleRepository.createJob(job);
    }

    public CompletableFuture<Action> invokeJob(long id) {
        return scheduleRepository.invokeJob(id);
    }
}
