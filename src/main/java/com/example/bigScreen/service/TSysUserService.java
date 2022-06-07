package com.example.bigScreen.service;

import com.example.bigScreen.entity.TSysUser;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author: SuKai
 * @date: 2022/4/13
 * @time: 13:52
 * @description:
 */
public interface TSysUserService {

    TSysUser findByUsername(String username);

    PageInfo<Map<String,Object>> findByPage(Map<String, Object> paramMap);

    PageInfo<Map<String, Object>> findByPageAndSecond(Map<String, Object> paramMap);

    boolean add(Map<String, Object> paramMap);

    List<Map<String, Object>> findById(Map<String, Object> paramMap);

    boolean edit(Map<String, Object> paramMap);

    boolean delete(Map<String, Object> paramMap);
}
