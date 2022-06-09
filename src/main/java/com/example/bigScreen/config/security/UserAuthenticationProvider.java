package com.example.bigScreen.config.security;

import com.example.bigScreen.entity.SysUserDetails;
import com.example.bigScreen.service.impl.SysUserDetailsServiceImpl;
import com.example.bigScreen.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author: SuKai
 * @date: 2022/4/13
 * @time: 11:01
 * @description:
 */
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private SysUserDetailsServiceImpl userDetailsService;

    /**
     * 身份验证
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal(); // 获取用户名
        String password = (String) authentication.getCredentials(); // 获取密码
        if (username.isEmpty() || password.isEmpty()) {
            throw new UsernameNotFoundException("用户名或密码不能为空");
        }
        if (username.equals("admin") && password.equals("123456")) {
            SysUserDetails sysUserDetails = (SysUserDetails) userDetailsService.loadUserByUsername(username);
            return new UsernamePasswordAuthenticationToken(sysUserDetails, password, sysUserDetails.getAuthorities());
        } else if (username.equals("admin") && !password.equals("123456")) {
            throw new BadCredentialsException("用户名或密码错误");
        } else {
            SysUserDetails sysUserDetails = (SysUserDetails) userDetailsService.loadUserByUsername(username);
            if (sysUserDetails == null) {
                throw new UsernameNotFoundException("用户名不存在");
            }
            if (!sysUserDetails.getPassword().equals(Md5Util.MD5(password))) {
                throw new BadCredentialsException("用户名或密码错误");
            }
            return new UsernamePasswordAuthenticationToken(sysUserDetails, password, sysUserDetails.getAuthorities());
        }
    }

    /**
     * 支持指定的身份验证
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
