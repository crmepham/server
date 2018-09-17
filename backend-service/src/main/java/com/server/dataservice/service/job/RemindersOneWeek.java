package com.server.dataservice.service.job;

import com.server.dataservice.service.job.handler.BaseJobHandler;
import com.server.dataservice.service.job.handler.RemindersOneWeekHandler;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class RemindersOneWeek extends BaseJobHandler implements Job
{

    @Autowired
    private RemindersOneWeekHandler handler;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        super.execute(this.getClass().getSimpleName(), this, handler);
    }
}
