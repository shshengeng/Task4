package com.example.system.model;

import java.sql.Timestamp;

public class User {

    private Long userId;
    private Long deptId;
    private String userName;
    private String nickName;
    private String gender;
    private String phone;
    private String email;
    private String avatarName;
    private String avatarPath;
    private String password;
    private boolean isAdmin;
    private Long enabled;
    private String createBy;
    private String updateBy;
    private Timestamp pwdResetTime;
    private Timestamp createTime;
    private Timestamp updateTime;

    public User() {
    }

    public User(Long userId, Long deptId, String userName, String nickName, String gender, String phone, String email, String avatarName, String avatarPath, String password, boolean isAdmin, Long enabled, String createBy, String updateBy, Timestamp pwdResetTime, Timestamp createTime, Timestamp updateTime) {
        this.userId = userId;
        this.deptId = deptId;
        this.userName = userName;
        this.nickName = nickName;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.avatarName = avatarName;
        this.avatarPath = avatarPath;
        this.password = password;
        this.isAdmin = isAdmin;
        this.enabled = enabled;
        this.createBy = createBy;
        this.updateBy = updateBy;
        this.pwdResetTime = pwdResetTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Long getEnabled() {
        return enabled;
    }

    public void setEnabled(Long enabled) {
        this.enabled = enabled;
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

    public Timestamp getPwdResetTime() {
        return pwdResetTime;
    }

    public void setPwdResetTime(Timestamp pwdResetTime) {
        this.pwdResetTime = pwdResetTime;
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
        return "User{" +
                "userId=" + userId +
                ", deptId=" + deptId +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", avatarName='" + avatarName + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", enabled=" + enabled +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", pwdResetTime=" + pwdResetTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
