package com.server.dataservice.interfaces;

import com.server.common.model.Action;

public interface JobService
{
    /**
     * Start the actual job.
     * @param action The action that is recording the progress of this job.
     * @return The action.
     * TODO Maybe do this as an event listener?
     */
    void start(Action action);
}
