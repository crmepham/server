package com.server.common.model;

public class InputResult {

    private boolean success;

    private String failureReason;

    private int totalCount;

    private int successCount;

    public InputResult()
    {
        this.success = true;
    }

    public InputResult(int successCount)
    {
        this();
        this.successCount = successCount;
        this.totalCount = successCount;
    }

    public InputResult(final String failureReason)
    {
        this.failureReason = failureReason;
        this.success = false;
    }

    /**
     * Gets the success.
     *
     * @return success
     */
    public boolean isSuccess()
    {
        return success;
    }

    /**
     * Sets the success.
     *
     * @param success the success
     */
    public void setSuccess(boolean success)
    {
        this.success = success;
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
     * Gets the totalCount.
     *
     * @return totalCount
     */
    public int getTotalCount()
    {
        return totalCount;
    }

    /**
     * Sets the totalCount.
     *
     * @param totalCount the totalCount
     */
    public void setTotalCount(int totalCount)
    {
        this.totalCount = totalCount;
    }

    /**
     * Gets the successCount.
     *
     * @return successCount
     */
    public int getSuccessCount()
    {
        return successCount;
    }

    /**
     * Sets the successCount.
     *
     * @param successCount the successCount
     */
    public void setSuccessCount(int successCount)
    {
        this.successCount = successCount;
    }
}
