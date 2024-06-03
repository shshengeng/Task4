package com.example.system.model;

import lombok.Data;
import java.sql.Timestamp;


@Data
public class Log {

    private Long logId;
    private String description;
    private String logType;
    private String method;
    private String params;
    private String requestIp;
    private Long time;
    private String username;
    private String address;
    private String browser;
    private String exceptionDetail;
    private Timestamp createTime;
}

