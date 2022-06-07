package com.example.bigScreen.service.impl;

import com.example.bigScreen.mapper.CompanyMapper;
import com.example.bigScreen.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: SuKai
 * @date: 2022/4/15
 * @time: 11:05
 * @description:
 */
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper mapper;
}
