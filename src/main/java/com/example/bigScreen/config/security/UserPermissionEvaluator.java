package com.example.bigScreen.config.security;

import com.example.bigScreen.config.exception.MyException;
import com.example.bigScreen.entity.SysUserDetails;
import com.example.bigScreen.entity.TSysRes;
import com.example.bigScreen.service.TSysResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: SuKai
 * @date: 2022/4/13
 * @time: 14:13
 * @description: 权限验证处理类
 */
@Configuration
@Component
public class UserPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private TSysResService tSysResService;

    /**
     * 判断是否拥有权限
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission) {
        SysUserDetails sysUserDetails = null;
        try {
            sysUserDetails = (SysUserDetails) authentication.getPrincipal();
        } catch (Exception e){
            throw new MyException(403,"权限不足");
        }

        Set<String> permissions = new HashSet<String>(); // 用户权限

        List<TSysRes> authList = tSysResService.findResByUserId(sysUserDetails.getId());

        for (int i = 0; i < authList.size() ; i++) {
            permissions.add(authList.get(i).getPermission());
        }

        // 判断是否拥有权限
        if (permissions.contains(permission.toString())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
                                 Object permission) {
        return false;
    }
}
