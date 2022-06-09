package com.example.bigScreen.config.security;

import com.example.bigScreen.config.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: SuKai
 * @date: 2022/4/13
 * @time: 10:27
 * @description: 无权限处理类
 */
@Component
public class UserAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result.responseJson(response, Result.response(403, "权限不足，拒绝访问", accessDeniedException.getMessage()));
    }
}
