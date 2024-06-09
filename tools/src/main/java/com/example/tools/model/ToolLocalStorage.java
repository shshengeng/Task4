package com.example.tools.model;

import lombok.Data;
import java.util.Date;

@Data
public class ToolLocalStorage {
    private Long storageId;
    private String realName;
    private String name;
    private String suffix;
    private String path;
    private String type;
    private String size;
    private String createBy;
    private String updateBy;
    private Date createTime;
    private Date updateTime;
}
