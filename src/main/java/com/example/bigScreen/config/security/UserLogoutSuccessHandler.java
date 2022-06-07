package com.example.bigScreen.config.security;

import com.example.bigScreen.config.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: SuKai
 * @date: 2022/4/13
 * @time: 14:10
 * @description: 登出成功处理类
 */
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) {
        SecurityContextHolder.clearContext();
        Result.responseJson(response, Result.response(200, "登出成功", null));
    }
}
