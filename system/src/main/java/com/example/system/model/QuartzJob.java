package com.example.system.model;

import java.sql.Timestamp;

public class QuartzJob {

    private Long jodId;
    private String beanName;
    private String cronExpression;
    private boolean isPause;
    private String jobName;
    private String methodName;
    private String params;
    private String description;
    private String personInCharge;
    private String email;
    private String subTask;
    private boolean pauseAfterFailure;
    private String createBy;
    private String updateBy;
    private Timestamp createTime;
    private Timestamp updateTime;

    public QuartzJob() {
    }

    public QuartzJob(Long jodId, String beanName, String cronExpression, boolean isPause, String jobName, String methodName, String params, String description, String personInCharge, String email, String subTask, boolean pauseAfterFailure, String createBy, String updateBy, Timestamp createTime, Timestamp updateTime) {
        this.jodId = jodId;
        this.beanName = beanName;
        this.cronExpression = cronExpression;
        this.isPause = isPause;
        this.jobName = jobName;
        this.methodName = methodName;
        this.params = params;
        this.description = description;
        this.personInCharge = personInCharge;
        this.email = email;
        this.subTask = subTask;
        this.pauseAfterFailure = pauseAfterFailure;
        this.createBy = createBy;
        this.updateBy = updateBy;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getJodId() {
        return jodId;
    }

    public void setJodId(Long jodId) {
        this.jodId = jodId;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public boolean isPause() {
        return isPause;
    }

    public void setPause(boolean pause) {
        isPause = pause;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubTask() {
        return subTask;
    }

    public void setSubTask(String subTask) {
        this.subTask = subTask;
    }

    public boolean isPauseAfterFailure() {
        return pauseAfterFailure;
    }

    public void setPauseAfterFailure(boolean pauseAfterFailure) {
        this.pauseAfterFailure = pauseAfterFailure;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "QuartzJob{" +
                "jodId=" + jodId +
                ", beanName='" + beanName + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", isPause=" + isPause +
                ", jobName='" + jobName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params='" + params + '\'' +
                ", description='" + description + '\'' +
                ", personInCharge='" + personInCharge + '\'' +
                ", email='" + email + '\'' +
                ", subTask='" + subTask + '\'' +
                ", pauseAfterFailure=" + pauseAfterFailure +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
