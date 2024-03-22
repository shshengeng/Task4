package com.example.system.model;

public class UsersRoles {

    private Long userId;
    private Long jobId;

    public UsersRoles() {
    }

    public UsersRoles(Long userId, Long jobId) {
        this.userId = userId;
        this.jobId = jobId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    @Override
    public String toString() {
        return "UsersRoles{" +
                "userId=" + userId +
                ", jobId=" + jobId +
                '}';
    }
}
