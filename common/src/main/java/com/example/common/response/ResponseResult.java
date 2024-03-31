package com.example.common.response;

public class ResponseResult<T>{

    private int statusCode;
    private String msg;
    private T data;
    private long timestamp;

    public ResponseResult() {
    }

    public ResponseResult(int statusCode, String msg, T data, long timestamp) {
        this.statusCode = statusCode;
        this.msg = msg;
        this.data = data;
        this.timestamp = timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
