package com.example.bigScreen.controller;

import com.example.bigScreen.config.Result;
import com.example.bigScreen.service.CompanyService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: SuKai
 * @date: 2022/4/15
 * @time: 11:03
 * @description: 二级单位管理接口
 */
@RestController
@RequestMapping("/company")
@Api(value = "二级单位管理服务", tags = "二级单位管理服务")
public class CompanyController {

    private static final Logger log = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService service;

    /**
     * 查询所有二级单位和二级单位账号
     */
    public Result findByPage(){

        return null;
    }

}
