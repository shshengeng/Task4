package com.example.system.model;

public class RolesDepts {

    private Long roleId;
    private Long deptID;

    public RolesDepts() {
    }

    public RolesDepts(Long roleId, Long deptID) {
        this.roleId = roleId;
        this.deptID = deptID;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getDeptID() {
        return deptID;
    }

    public void setDeptID(Long deptID) {
        this.deptID = deptID;
    }

    @Override
    public String toString() {
        return "RolesDepts{" +
                "roleId=" + roleId +
                ", deptID=" + deptID +
                '}';
    }
}
