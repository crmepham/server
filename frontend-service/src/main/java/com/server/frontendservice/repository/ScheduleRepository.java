package com.server.frontendservice.repository;

import com.server.common.model.Action;
import com.server.common.model.Job;
import com.server.common.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Repository
public class ScheduleRepository extends BaseRepository
{
    private static final String GET_ALL = "schedules/get-all";
    private static final String GET_BY_ID = "schedules/get-by-id/";
    private static final String CREATE = "schedules/create";
    private static final String GET_JOBS = "schedules/get-jobs/";
    private static final String GET_JOB_BY_ID = "schedules/get-job-by-id/";
    private static final String CREATE_JOB = "schedules/create-job";
    private static final String INVOKE_JOB = "schedules/invoke-job/";

    @Autowired
    private RestTemplate template;

    @Value("${base.api.uri}")
    private String baseUri;

    public CompletableFuture<List<Schedule>> getAll() {

        ResponseEntity<List<Schedule>> res = template.exchange(baseUri + GET_ALL, GET, getEntity(), new ParameterizedTypeReference<List<Schedule>>() {});
        return CompletableFuture.completedFuture(res.getBody());
    }

    public CompletableFuture<Schedule> getById(long id) {

        ResponseEntity<Schedule> reminder = template.exchange(baseUri + GET_BY_ID + id, GET, getEntity(), new ParameterizedTypeReference<Schedule>() {});
        return CompletableFuture.completedFuture(reminder.getBody());
    }

    public void create(Schedule schedule) {

        template.exchange(baseUri + CREATE, POST, postJson(schedule), Schedule.class);
    }

    public CompletableFuture<List<Job>> getJobs(long id) {

        ResponseEntity<List<Job>> res = template.exchange(baseUri + GET_JOBS + id, GET, getEntity(), new ParameterizedTypeReference<List<Job>>() {});
        return CompletableFuture.completedFuture(res.getBody());
    }

    public CompletableFuture<Job> getJob(long id) {

        ResponseEntity<Job> job = template.exchange(baseUri + GET_JOB_BY_ID + id, GET, getEntity(), new ParameterizedTypeReference<Job>() {});
        return CompletableFuture.completedFuture(job.getBody());
    }

    public void createJob(Job job) {

        template.exchange(baseUri + CREATE_JOB, POST, postJson(job), Job.class);
    }

    public CompletableFuture<Action> invokeJob(long id) {

        ResponseEntity<Action> response = template.exchange(baseUri + INVOKE_JOB + id, GET, getEntity(), new ParameterizedTypeReference<Action>() {});
        return CompletableFuture.completedFuture(response.getBody());
    }
}
