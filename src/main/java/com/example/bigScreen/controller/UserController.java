package com.example.bigScreen.controller;

import com.example.bigScreen.config.Result;
import com.example.bigScreen.service.TSysUserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author: SuKai
 * @date: 2022/4/13
 * @time: 16:12
 * @description: 用户管理接口
 */
@RestController
@RequestMapping("/user")
@Api(value = "系统用户服务", tags = "系统用户服务")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private TSysUserService service;

    /**
     * 查询所有用户
     */
    @PreAuthorize("hasRole('ROLE_/user/findByPage')")
    @PostMapping("findByPage")
    @ApiOperation(value = "查询所有用户", notes = "{\"pageNum\":1,\"pageSize\":10}", produces = "application/json")
    public Result findByPage(@RequestBody Map<String,Object> paramMap){
        //获取当前登录用户名
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if (username.equals("admin")){
            //获取所有批次的用户
            PageInfo<Map<String,Object>> userList = service.findByPage(paramMap);
            return Result.success(userList);
        }
        //获取该账号所属二级单位下的所有批次用户
        paramMap.put("username",username);
        PageInfo<Map<String,Object>> userList = service.findByPageAndSecond(paramMap);
        return Result.success(userList);
    }


    /**
     * 新增用户
     */
    @PreAuthorize("hasRole('ROLE_/user/add')")
    @PostMapping("add")
    @ApiOperation(value = "新增用户",
                  notes = "{\"username\":\"李四\",\"cell_phone\":\"11111111111\",\"sex\":\"0\",\"create_user\":\"admin\",\"batch_id\":\"1\",\"third_name\":\"三级单位\",\"usernum\":\"123456789\",\"second_id\":\"1\"}",
                  produces = "application/json")
    public Result add(@RequestBody Map<String,Object> paramMap){
        if (paramMap == null || paramMap.isEmpty()){
            return Result.error(1,"参数错误");
        }
        //判断是否重复添加
        List<Map<String,Object>> list = service.findById(paramMap);
        if (list != null && list.size() > 0){
            return Result.error(1,"请勿重复添加");
        }
        boolean b = service.add(paramMap);
        return b ? Result.success() : Result.error();
    }

    /**
     * 修改用户
     */
    @PreAuthorize("hasRole('ROLE_/user/edit')")
    @PostMapping("edit")
    @ApiOperation(value = "修改用户", notes = "{\"id\":\"277657251341139964\",\"username\":\"李四\",\"cell_phone\":\"11111111111\",\"sex\":\"0\",\"status\":\"0\",\"quota\":\"2000\",\"third_name\":\"三级单位\",\"usernum\":\"123456789\"}", produces = "application/json")
    public Result edit(@RequestBody Map<String,Object> paramMap){
        if (paramMap == null || paramMap.isEmpty()){
            return Result.error(1,"参数错误");
        }
        boolean b = service.edit(paramMap);
        return b ? Result.success() : Result.error();
    }

    /**
     * 删除用户
     */
    @PreAuthorize("hasRole('ROLE_/user/delete')")
    @PostMapping("delete")
    @ApiOperation(value = "删除用户", notes = "{\"user_ids\":\"[\"277657251341139964\",\"276866508766838784\"]\"}", produces = "application/json")
    public Result delete(@RequestBody Map<String,Object> paramMap){
        if (paramMap == null || paramMap.isEmpty()){
            return Result.error(1,"参数错误");
        }
        boolean b = service.delete(paramMap);
        return b ? Result.success() : Result.error();
    }
}
