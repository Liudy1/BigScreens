package com.example.bigScreen.entity;

import java.util.Date;

/**
 * @author: SuKai
 * @date: 2022/4/13
 * @time: 12:00
 * @description:
 */
public class TSysRes {

    private String id;//主键
    private String name;//资源名称
    private String resUrl;//资源路径
    private String permission;//做拦截的code
    private String resType;//0菜单   1按钮
    private String pid;//父级id
    private String icon;//菜单图标
    private String createUser;//创建人
    private Date createDate;//创建时间

    public TSysRes() {
    }

    public TSysRes(String id, String name, String resUrl, String permission, String resType, String pid, String icon, String createUser, Date createDate) {
        this.id = id;
        this.name = name;
        this.resUrl = resUrl;
        this.permission = permission;
        this.resType = resType;
        this.pid = pid;
        this.icon = icon;
        this.createUser = createUser;
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
