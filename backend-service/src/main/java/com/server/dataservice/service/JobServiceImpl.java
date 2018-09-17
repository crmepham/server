package com.server.dataservice.service;

import com.server.common.model.Action;
import com.server.common.model.Job;
import com.server.common.model.Schedule;
import com.server.dataservice.repository.ActionRepository;
import com.server.dataservice.repository.JobRepository;
import com.server.dataservice.repository.ScheduleRepository;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import static java.lang.String.format;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

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
    private Scheduler scheduler;

    public void invoke(long id, boolean runOnce) {

        Optional<Job> optional = jobRepository.findById(id);
        if (!optional.isPresent()) {
            actionRepository.save(new Action(format("Could not find job with id %s.", id)));
        }

        Job job = optional.get();
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
}
