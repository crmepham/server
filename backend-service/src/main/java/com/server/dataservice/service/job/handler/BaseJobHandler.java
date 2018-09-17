package com.server.dataservice.service.job.handler;

import com.server.dataservice.interfaces.JobHandler;
import com.server.dataservice.repository.ActionRepository;
import org.quartz.Job;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Handles the sending of email notifications for reminders occurring in 1 week.
 *
 * @author Chris Mepham
 *
 */
@Component
public class BaseJobHandler {

    @Autowired
    private ActionRepository actionRepository;

    protected void execute(String className, Job jobObject, JobHandler handler) throws JobExecutionException {

        handler.execute(className);
    }
}
