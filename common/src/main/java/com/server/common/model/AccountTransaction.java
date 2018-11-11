package com.server.common.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="account_transaction")
public class AccountTransaction extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "value")
    private double value;

    @Column(name = "percent")
    private double percent;

    @Column(name = "account_id")
    private long accountId;

    public AccountTransaction() {}

    public AccountTransaction(String name, String type, double value, double percent) {
        super();
        this.name = name;
        this.type = type;
        this.value = value;
        this.percent = percent;
    }

    /**
     * Gets the name.
     *
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Gets the type.
     *
     * @return type
     */
    public String getType()
    {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the type
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * Gets the value.
     *
     * @return value
     */
    public double getValue()
    {
        return value;
    }

    /**
     * Sets the value.
     *
     * @param value the value
     */
    public void setValue(double value)
    {
        this.value = value;
    }

    /**
     * Gets the percent.
     *
     * @return percent
     */
    public double getPercent()
    {
        return percent;
    }

    /**
     * Sets the percent.
     *
     * @param percent the percent
     */
    public void setPercent(double percent)
    {
        this.percent = percent;
    }

    /**
     * Gets the accountId.
     *
     * @return accountId
     */
    public long getAccountId()
    {
        return accountId;
    }

    /**
     * Sets the accountId.
     *
     * @param accountId the accountId
     */
    public void setAccountId(long accountId)
    {
        this.accountId = accountId;
    }
}