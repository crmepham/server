package com.server.dataservice.service;

import com.server.common.model.Action;
import com.server.common.model.ApplicationError;
import com.server.common.model.Job;
import com.server.common.model.Schedule;
import com.server.dataservice.repository.ActionRepository;
import com.server.dataservice.repository.ErrorRepository;
import com.server.dataservice.repository.JobRepository;
import com.server.dataservice.repository.ScheduleRepository;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Transactional
@Service
public class JobServiceImpl
{
    private static final String JOB_PATH = "com.server.dataservice.service.job.";

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ErrorRepository errorRepository;

    @Autowired
    private Scheduler scheduler;

    public void start() {
        List<Job> jobs = jobRepository.findByEnabledTrueAndDeletedFalse();
        jobs.forEach(j -> invoke(j, false));
    }

    public void invoke(long id, boolean runOnce) {

        Optional<Job> optional = jobRepository.findById(id);
        if (!optional.isPresent()) {
            actionRepository.save(new Action(format("Could not find job with id %s.", id)));
        }

        invoke(optional.get(), runOnce);

    }

    public void invoke(Job job, boolean runOnce) {

        Schedule schedule = scheduleRepository.getOne(job.getScheduleId());

        // start the action.
        Action action = actionRepository.save(new Action(job));
        Class<? extends org.quartz.Job> cls;
        try {

            cls = (Class<? extends org.quartz.Job>) Class.forName(JOB_PATH + job.getImplementation());

            JobDetail jobDetail = newJob(cls)
                    .withIdentity(job.getExternalReference(), schedule.getExternalReference())
                    .build();

            if (runOnce) {
                JobKey jobKey = new JobKey(job.getExternalReference(), schedule.getExternalReference());
                JobDetail runOnceJob = newJob(cls)
                        .withIdentity(jobKey)
                        .storeDurably()
                        .build();
                scheduler.addJob(runOnceJob, true);
                scheduler.triggerJob(jobKey);
            } else {
                Trigger triggerDetail = newTrigger()
                        .withIdentity(job.getExternalReference(), schedule.getExternalReference())
                        .startNow()
                        .withSchedule(cronSchedule(schedule.getCronExpression()))
                        .build();
                scheduler.scheduleJob(jobDetail, triggerDetail);
            }
        } catch (Exception e) {
            action.setResultMessage(format("Failed to start job.%n%s", e.getMessage()));
            action.setState(Action.STATE_FAILED);
            action.setLastUpdated(new Date());
            action.setLastUpdatedUser("system");
            actionRepository.save(action);
        }
    }

    public void stop(Schedule schedule, List<Job> jobs)
    {
        jobs.forEach(j -> {
            stop(schedule, j);
            j.setEnabled(false);
            j.setLastUpdated(new Date());
        });

        jobRepository.saveAll(jobs);
    }

    public void stop(Schedule schedule, Job j) {
        try
        {
            scheduler.unscheduleJob(new TriggerKey(j.getExternalReference(), schedule.getExternalReference()));
        } catch (SchedulerException e) {
            ApplicationError error = new ApplicationError();
            error.setEntityId(j.getId());
            error.setEntity(j.getClass().getSimpleName());
            error.setException(e.getMessage());
            error.setContext("Could not unschedule this job.");
            errorRepository.save(error);
        }
    }
}
