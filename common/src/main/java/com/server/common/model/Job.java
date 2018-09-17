package com.server.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="job")
public class Job extends BaseEntity
{
    @Column(name = "external_reference")
    private String externalReference;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "implementation")
    private String implementation;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "schedule_id")
    private long scheduleId;

    /**
     * Gets the externalReference.
     *
     * @return externalReference
     */
    public String getExternalReference()
    {
        return externalReference;
    }

    /**
     * Sets the externalReference.
     *
     * @param externalReference the externalReference
     */
    public void setExternalReference(String externalReference)
    {
        this.externalReference = externalReference;
    }

    /**
     * Gets the title.
     *
     * @return title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Sets the title.
     *
     * @param title the title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Gets the description.
     *
     * @return description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Gets the enabled.
     *
     * @return enabled
     */
    public boolean isEnabled()
    {
        return enabled;
    }

    /**
     * Sets the enabled.
     *
     * @param enabled the enabled
     */
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    /**
     * Gets the scheduleId.
     *
     * @return scheduleId
     */
    public long getScheduleId()
    {
        return scheduleId;
    }

    /**
     * Sets the scheduleId.
     *
     * @param scheduleId the scheduleId
     */
    public void setScheduleId(long scheduleId)
    {
        this.scheduleId = scheduleId;
    }

    /**
     * Gets the implementation.
     *
     * @return implementation
     */
    public String getImplementation()
    {
        return implementation;
    }

    /**
     * Sets the implementation.
     *
     * @param implementation the implementation
     */
    public void setImplementation(String implementation)
    {
        this.implementation = implementation;
    }
}
