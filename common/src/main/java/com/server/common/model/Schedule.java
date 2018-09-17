package com.server.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="schedule")
public class Schedule extends BaseEntity
{
    @Column(name = "external_reference")
    private String externalReference;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "cron_expression")
    private String cronExpression;

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
     * Gets the cronExpression.
     *
     * @return cronExpression
     */
    public String getCronExpression()
    {
        return cronExpression;
    }

    /**
     * Sets the cronExpression.
     *
     * @param cronExpression the cronExpression
     */
    public void setCronExpression(String cronExpression)
    {
        this.cronExpression = cronExpression;
    }
}
