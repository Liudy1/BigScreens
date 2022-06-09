package com.example.bigScreen.config.security;

import com.example.bigScreen.config.Result;
import com.example.bigScreen.entity.SysUserDetails;
import com.example.bigScreen.utils.JWTTokenUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: SuKai
 * @date: 2022/4/13
 * @time: 14:09
 * @description: 登录成功处理类
 */
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {
        SysUserDetails sysUserDetails = (SysUserDetails) authentication.getPrincipal();
        String token = JWTTokenUtil.createAccessToken(sysUserDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        Result.responseJson(response, Result.response(0, "登录成功", tokenMap));
    }
}
