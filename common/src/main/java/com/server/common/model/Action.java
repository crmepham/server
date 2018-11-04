package com.server.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="action")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Action extends BaseEntity
{
    public static final String STATE_STARTED = "started";
    public static final String STATE_COMPLETED = "completed";
    public static final String STATE_FAILED = "failed";

    /** The result of the action. */
    @Column(name = "result_message")
    private String resultMessage;

    /** The current state of the action. */
    @Column(name = "state")
    private String state;

    @Column(name = "class_name")
    private String className;

    @Column(name = "object_reference")
    private String objectReference;

    public Action() {
        super();
    }

    public Action(Job job) {

        super();
        this.state = STATE_STARTED;
        this.className = job.getImplementation();
        this.objectReference = job.getExternalReference();
    }

    public Action(String failureMessage) {

        this.state = STATE_FAILED;
        this.resultMessage = failureMessage;
    }

    /**
     * Gets the state.
     *
     * @return state
     */
    public String getState()
    {
        return state;
    }

    /**
     * Sets the state.
     *
     * @param state the state
     */
    public void setState(String state)
    {
        this.state = state;
    }

    /**
     * Gets the resultMessage.
     *
     * @return resultMessage
     */
    public String getResultMessage()
    {
        return resultMessage;
    }

    /**
     * Sets the resultMessage.
     *
     * @param resultMessage the resultMessage
     */
    public void setResultMessage(String resultMessage)
    {
        this.resultMessage = resultMessage;
    }

    /**
     * Gets the className.
     *
     * @return className
     */
    public String getClassName()
    {
        return className;
    }

    /**
     * Sets the className.
     *
     * @param className the className
     */
    public void setClassName(String className)
    {
        this.className = className;
    }

    /**
     * Gets the objectReference.
     *
     * @return objectReference
     */
    public String getObjectReference()
    {
        return objectReference;
    }

    /**
     * Sets the objectReference.
     *
     * @param objectReference the objectReference
     */
    public void setObjectReference(String objectReference)
    {
        this.objectReference = objectReference;
    }
}
