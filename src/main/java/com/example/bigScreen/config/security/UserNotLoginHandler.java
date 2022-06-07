package com.example.bigScreen.config.security;

import com.example.bigScreen.config.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: SuKai
 * @date: 2022/4/13
 * @time: 14:11
 * @description: 未登录处理类
 */
@Component
public class UserNotLoginHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        Result.responseJson(response, Result.response(401, "未登录", authException.getMessage()));
    }
}
