package com.example.bigScreen.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: SuKai
 * @date: 2022/4/13
 * @time: 11:50
 * @description: 用户实体
 */
public class TSysUser implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;//主键
    private String username;//用户名
    private String password;//密码
    private String cell_phone;
    private Integer status;
    private Integer account_type;
    private Integer sex;
    private String create_user;
    private Date create_date;
    private Float quota;
    private Integer batch_id;
    private Integer batch_state;
    private String third_name;
    private Float thrid_quota;
    private String usernum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCell_phone() {
        return cell_phone;
    }

    public void setCell_phone(String cell_phone) {
        this.cell_phone = cell_phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAccount_type() {
        return account_type;
    }

    public void setAccount_type(Integer account_type) {
        this.account_type = account_type;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Float getQuota() {
        return quota;
    }

    public void setQuota(Float quota) {
        this.quota = quota;
    }

    public Integer getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(Integer batch_id) {
        this.batch_id = batch_id;
    }

    public Integer getBatch_state() {
        return batch_state;
    }

    public void setBatch_state(Integer batch_state) {
        this.batch_state = batch_state;
    }

    public String getThird_name() {
        return third_name;
    }

    public void setThird_name(String third_name) {
        this.third_name = third_name;
    }

    public Float getThrid_quota() {
        return thrid_quota;
    }

    public void setThrid_quota(Float thrid_quota) {
        this.thrid_quota = thrid_quota;
    }

    public String getUsernum() {
        return usernum;
    }

    public void setUsernum(String usernum) {
        this.usernum = usernum;
    }

    public TSysUser() {
    }

    public TSysUser(String id, String username, String password, String cell_phone, Integer status, Integer account_type, Integer sex, String create_user, Date create_date, Float quota, Integer batch_id, Integer batch_state, String third_name, Float thrid_quota, String usernum) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.cell_phone = cell_phone;
        this.status = status;
        this.account_type = account_type;
        this.sex = sex;
        this.create_user = create_user;
        this.create_date = create_date;
        this.quota = quota;
        this.batch_id = batch_id;
        this.batch_state = batch_state;
        this.third_name = third_name;
        this.thrid_quota = thrid_quota;
        this.usernum = usernum;
    }
}
