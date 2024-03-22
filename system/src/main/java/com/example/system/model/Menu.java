package com.example.system.model;

import java.sql.Timestamp;

public class Menu {

    private Long menuId;
    private Long pid;
    private Integer subCount;
    private Integer type;
    private String title;
    private String name;
    private String component;
    private Integer menuSort;
    private String icon;
    private String path;
    private boolean iFrame;
    private boolean cache;
    private boolean hidden;
    private String permission;
    private String createBy;
    private String updateBy;
    private Timestamp createTime;
    private Timestamp updateTime;

    public Menu() {
    }

    public Menu(Long menuId, Long pid, Integer subCount, Integer type, String title, String name, String component, Integer menuSort, String icon, String path, boolean iFrame, boolean cache, boolean hidden, String permission, String createBy, String updateBy, Timestamp createTime, Timestamp updateTime) {
        this.menuId = menuId;
        this.pid = pid;
        this.subCount = subCount;
        this.type = type;
        this.title = title;
        this.name = name;
        this.component = component;
        this.menuSort = menuSort;
        this.icon = icon;
        this.path = path;
        this.iFrame = iFrame;
        this.cache = cache;
        this.hidden = hidden;
        this.permission = permission;
        this.createBy = createBy;
        this.updateBy = updateBy;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", pid=" + pid +
                ", subCount=" + subCount +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", component='" + component + '\'' +
                ", menuSort=" + menuSort +
                ", icon='" + icon + '\'' +
                ", path='" + path + '\'' +
                ", iFrame=" + iFrame +
                ", cache=" + cache +
                ", hidden=" + hidden +
                ", permission='" + permission + '\'' +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
