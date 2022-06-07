package com.example.bigScreen.entity;

import java.util.Date;

/**
 * @author: SuKai
 * @date: 2022/4/13
 * @time: 13:29
 * @description:
 */
public class TSysRole {

    private String id;//主键
    private String roleName;//角色名称
    private String roleExplain;//角色描述
    private String createUser;//创建人
    private Date createDate;//创建时间

    public TSysRole() {
    }

    public TSysRole(String id, String roleName, String roleExplain, String createUser, Date createDate) {
        this.id = id;
        this.roleName = roleName;
        this.roleExplain = roleExplain;
        this.createUser = createUser;
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleExplain() {
        return roleExplain;
    }

    public void setRoleExplain(String roleExplain) {
        this.roleExplain = roleExplain;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
