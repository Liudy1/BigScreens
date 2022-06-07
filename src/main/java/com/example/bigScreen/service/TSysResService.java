package com.example.bigScreen.service;

import com.example.bigScreen.entity.TSysRes;

import java.util.List;

/**
 * @author: SuKai
 * @date: 2022/4/13
 * @time: 14:00
 * @description:
 */
public interface TSysResService {

    List<TSysRes> findResByUserId(String id);
}
