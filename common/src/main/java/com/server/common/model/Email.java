package com.server.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="email")
public class Email extends BaseEntity {

    public static final String STATE_PENDING = "pending";
    public static final String STATE_SENT = "sent";
    public static final String STATE_FAILED = "failed";

    @Column(name = "to_email")
    private String to;

    @Column(name = "from_email")
    private String from;

    @Column(name = "state")
    private String state;

    @Column(name = "subject")
    private String subject;

    @Column(name = "body")
    private String body;

    @Column(name = "sent")
    private boolean sent;

    @Column(name = "failure_reason")
    private String failureReason;

    @Column(name = "retry_count")
    private int retryCount;

    public Email(String to, String from, String subject, String body)
    {
        super();
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.body = body;
        this.state = STATE_PENDING;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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
     * Gets the failureReason.
     *
     * @return failureReason
     */
    public String getFailureReason()
    {
        return failureReason;
    }

    /**
     * Sets the failureReason.
     *
     * @param failureReason the failureReason
     */
    public void setFailureReason(String failureReason)
    {
        this.failureReason = failureReason;
    }

    /**
     * Gets the retryCount.
     *
     * @return retryCount
     */
    public int getRetryCount()
    {
        return retryCount;
    }

    /**
     * Sets the retryCount.
     *
     * @param retryCount the retryCount
     */
    public void setRetryCount(int retryCount)
    {
        this.retryCount = retryCount;
    }

    @Override
    public String toString() {
        return "Email [to=" + to + ", from=" + from + ", subject=" + subject + ", body=" + body + "]";
    }
}
