package com.example.system.model;

public class UsersRoles {

    private Long userId;
    private Long roleId;

    public UsersRoles() {
    }

    public UsersRoles(Long userId, Long jobId) {
        this.userId = userId;
        this.roleId = jobId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UsersRoles{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
