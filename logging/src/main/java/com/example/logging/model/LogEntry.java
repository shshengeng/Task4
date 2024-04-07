package com.example.logging.model;

public class LogEntry {
    private long timestamp;
    private String level;
    private String className;
    private String methodName;
    private String message;

    public LogEntry() {
    }

    public LogEntry(long timestamp, String level, String className, String methodName, String message) {
        this.timestamp = timestamp;
        this.level = level;
        this.className = className;
        this.methodName = methodName;
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "timestamp=" + timestamp +
                ", level='" + level + '\'' +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
