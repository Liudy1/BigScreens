package com.example.bigScreen.config.security;

import com.example.bigScreen.config.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: SuKai
 * @date: 2022/4/13
 * @time: 14:08
 * @description: 登录失败处理类
 */
@Component
public class UserLoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) {
        Result.responseJson(response, Result.response(500, "登录失败", exception.getMessage()));
    }
}
