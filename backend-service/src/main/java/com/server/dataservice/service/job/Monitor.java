package com.server.dataservice.service.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.server.dataservice.service.job.handler.BaseJobHandler;
import com.server.dataservice.service.job.handler.MonitorHandler;

public class Monitor extends BaseJobHandler implements Job
{

    @Autowired
    private MonitorHandler handler;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        super.execute(this.getClass().getSimpleName(), this, handler);
    }
}
