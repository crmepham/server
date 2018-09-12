package com.server.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="secret_property")
public class SecretProperty extends BaseEntity
{
    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

    @Column(name = "secret_id")
    private long secretId;

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
     * Gets the value.
     *
     * @return value
     */
    public String getValue()
    {
        return value;
    }

    /**
     * Sets the value.
     *
     * @param value the value
     */
    public void setValue(String value)
    {
        this.value = value;
    }

    /**
     * Gets the secretId.
     *
     * @return secretId
     */
    public long getSecretId()
    {
        return secretId;
    }

    /**
     * Sets the secretId.
     *
     * @param secretId the secretId
     */
    public void setSecretId(long secretId)
    {
        this.secretId = secretId;
    }
}
