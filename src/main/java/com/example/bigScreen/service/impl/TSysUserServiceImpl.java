package com.example.bigScreen.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.bigScreen.entity.TSysUser;
import com.example.bigScreen.mapper.TSysUserMapper;
import com.example.bigScreen.service.TSysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author: SuKai
 * @date: 2022/4/13
 * @time: 13:53
 * @description:
 */
@Service
@Transactional
public class TSysUserServiceImpl implements TSysUserService {

    @Autowired
    private TSysUserMapper mapper;

    @Override
    public TSysUser findByUsername(String username) {
        return mapper.findByUsername(username);
    }

    @Override
    public PageInfo<Map<String,Object>> findByPage(Map<String, Object> paramMap) {
        PageHelper.startPage(Integer.parseInt(paramMap.get("pageNum")+""),Integer.parseInt(paramMap.get("pageSize")+""));
        List<Map<String,Object>> userList = mapper.findByPage();
        return new PageInfo<Map<String,Object>>(userList);
    }

    @Override
    public PageInfo<Map<String, Object>> findByPageAndSecond(Map<String, Object> paramMap) {
        PageHelper.startPage(Integer.parseInt(paramMap.get("pageNum")+""),Integer.parseInt(paramMap.get("pageSize")+""));
        List<Map<String,Object>> userList = mapper.findByPageAndSecond(paramMap.get("username")+"");
        return new PageInfo<Map<String,Object>>(userList);
    }

    @Override
    public boolean add(Map<String, Object> paramMap) {
        paramMap.put("id", UUID.randomUUID());
        //先添加用户信息
        int i = mapper.addUser(paramMap);
        //为用户添加角色
        int j = mapper.addUserRole(paramMap.get("id")+"");
        //为用户绑定二级单位
        int k = mapper.addUserSecond(paramMap);

        if (i == 1 && j == 1 && k == 1){
            return true;
        }
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return false;
    }

    @Override
    public List<Map<String, Object>> findById(Map<String, Object> paramMap) {
        return mapper.findById(paramMap);
    }

    @Override
    public boolean edit(Map<String, Object> paramMap) {
        //先查询该条员工编号是否存在
        List<Map<String,Object>> list = mapper.findById(paramMap);
        if (list != null && list.size() > 0){
            return false;
        }
        //修改用户信息
        int i = mapper.edit(paramMap);
        return i==1;
    }

    @Override
    public boolean delete(Map<String, Object> paramMap) {
        List<Map<String,Object>> user_ids = (List<Map<String, Object>>) JSON.parse( paramMap.get("user_ids")+"");
        int i = 0;
        for (Map<String, Object> user_id : user_ids) {
            i += mapper.delete(user_id);
        }
        return i == user_ids.size();
    }
}
