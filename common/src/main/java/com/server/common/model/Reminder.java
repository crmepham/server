package com.server.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="reminder")
public class Reminder extends BaseEntity
{
    @Column(name = "instruction")
    private String instruction;

    @Column(name = "context")
    private String context;

    @Column(name = "day")
    private int day;

    @Column(name = "month")
    private int month;

    /**
     * Gets the context.
     *
     * @return context
     */
    public String getContext()
    {
        return context;
    }

    /**
     * Sets the context.
     *
     * @param context the context
     */
    public void setContext(String context)
    {
        this.context = context;
    }

    /**
     * Gets the instruction.
     *
     * @return instruction
     */
    public String getInstruction()
    {
        return instruction;
    }

    /**
     * Sets the instruction.
     *
     * @param instruction the instruction
     */
    public void setInstruction(String instruction)
    {
        this.instruction = instruction;
    }

    /**
     * Gets the day.
     *
     * @return day
     */
    public int getDay()
    {
        return day;
    }

    /**
     * Sets the day.
     *
     * @param day the day
     */
    public void setDay(int day)
    {
        this.day = day;
    }

    /**
     * Gets the month.
     *
     * @return month
     */
    public int getMonth()
    {
        return month;
    }

    /**
     * Sets the month.
     *
     * @param month the month
     */
    public void setMonth(int month)
    {
        this.month = month;
    }
}
