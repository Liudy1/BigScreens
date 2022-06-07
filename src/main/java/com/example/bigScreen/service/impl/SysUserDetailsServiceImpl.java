package com.example.bigScreen.service.impl;

import com.example.bigScreen.entity.SysUserDetails;
import com.example.bigScreen.entity.TSysRes;
import com.example.bigScreen.entity.TSysUser;
import com.example.bigScreen.mapper.TSysResMapper;
import com.example.bigScreen.service.TSysResService;
import com.example.bigScreen.service.TSysUserService;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: SuKai
 * @date: 2022/4/13
 * @time: 11:55
 * @description:
 */
@Service
public class SysUserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private TSysUserService tSysUserService;

    @Resource
    private TSysResMapper tSysResMapper;

    @Autowired
    private TSysResService tSysResService;

    /**
     * 说明：重写UserDetailsService中的loadUserByUsername，就是查询用户详细信息封装到 UserDetails
     * 业务：
     *      ①如果是admin会拥有全部权限
     *      ②如果不是admin就去查用户信息和用户拥有的权限
     * [username]
     * @return {@link UserDetails}
     * @throws
     * @author 李庆伟
     * @date 2021/12/7 19:49
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<TSysRes> resList = null;
        if(username.equals("admin")){
            TSysUser tSysUser = new TSysUser();
            tSysUser.setId("admin");
            tSysUser.setUsername("admin");
            SysUserDetails sysUserDetails = new SysUserDetails();
            BeanUtils.copyProperties(tSysUser, sysUserDetails);
            Set<GrantedAuthority> authorities = new HashSet<>(); // 角色集合

            //admin用户有的资源集合
            resList = tSysResMapper.selectList();

            for (int i = 0; i < resList.size() ; i++) {
                if(StringUtil.isNotEmpty(resList.get(i).getPermission())){
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + resList.get(i).getPermission()));
                }
            }
            sysUserDetails.setAuthorities(authorities);
            return sysUserDetails;
        }
        TSysUser tSysUser = tSysUserService.findByUsername(username);
        if (tSysUser != null) {
            SysUserDetails sysUserDetails = new SysUserDetails();
            BeanUtils.copyProperties(tSysUser, sysUserDetails);

            Set<GrantedAuthority> authorities = new HashSet<>(); // 角色集合

            resList = tSysResService.findResByUserId(sysUserDetails.getId());//当前用户有的资源集合

            if(resList != null){
                for (int i = 0; i < resList.size() ; i++) {
                    if(StringUtil.isNotEmpty(resList.get(i).getPermission())){
                        authorities.add(new SimpleGrantedAuthority("ROLE_" + resList.get(i).getPermission()));
                    }
                }
            }
            sysUserDetails.setAuthorities(authorities);
            return sysUserDetails;
        }
        return null;
    }

}
