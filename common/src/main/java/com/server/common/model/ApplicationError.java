package com.server.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="error")
public class ApplicationError extends BaseEntity {

    @Column(name = "entity_id")
    private long entityId;

    @Column(name = "entity_reference")
    private String entityReference;

    @Column(name = "entity")
    private String entity;

    @Column(name = "exception")
    private String exception;

    @Column(name = "stackTrace")
    private String stackTrace;

    @Column(name = "context")
    private String context;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getEntityReference() {
        return entityReference;
    }

    public void setEntityReference(String entityReference) {
        this.entityReference = entityReference;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    /**
     * Gets the entityId.
     *
     * @return entityId
     */
    public long getEntityId()
    {
        return entityId;
    }

    /**
     * Sets the entityId.
     *
     * @param entityId the entityId
     */
    public void setEntityId(long entityId)
    {
        this.entityId = entityId;
    }
}
