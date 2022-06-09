package com.example.bigScreen.service.impl;

import com.example.bigScreen.entity.TSysRes;
import com.example.bigScreen.mapper.TSysResMapper;
import com.example.bigScreen.service.TSysResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: SuKai
 * @date: 2022/4/13
 * @time: 14:00
 * @description:
 */
@Service
@Transactional
public class TSysResServiceImpl implements TSysResService {

    @Autowired
    private TSysResMapper mapper;

    @Override
    public List<TSysRes> findResByUserId(String id) {
        return mapper.findResByUserId(id);
    }
}
