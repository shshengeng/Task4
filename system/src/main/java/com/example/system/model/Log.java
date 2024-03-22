package com.example.system.model;

import java.sql.Timestamp;

public class Log {

    private Long logId;
    private String description;
    private String logType;
    private String method;
    private String params;
    private String requestIp;
    private Long time;
    private String userName;
    private String address;
    private String browser;
    private String exceptionDetail;
    private Timestamp createTime;

    public Log() {
    }

    public Log(Long logId, String description, String logType, String method, String params, String requestIp, Long time, String userName, String address, String browser, String exceptionDetail, Timestamp createTime) {
        this.logId = logId;
        this.description = description;
        this.logType = logType;
        this.method = method;
        this.params = params;
        this.requestIp = requestIp;
        this.time = time;
        this.userName = userName;
        this.address = address;
        this.browser = browser;
        this.exceptionDetail = exceptionDetail;
        this.createTime = createTime;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Log{" +
                "logId=" + logId +
                ", description='" + description + '\'' +
                ", logType='" + logType + '\'' +
                ", method='" + method + '\'' +
                ", params='" + params + '\'' +
                ", requestIp='" + requestIp + '\'' +
                ", time=" + time +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", browser='" + browser + '\'' +
                ", exceptionDetail='" + exceptionDetail + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
