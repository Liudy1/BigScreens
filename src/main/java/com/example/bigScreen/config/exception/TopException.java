package com.example.bigScreen.config.exception;

import com.example.bigScreen.config.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: SuKai
 * @date: 2022/4/13
 * @time: 10:14
 * @description: 自定义异常类
 */
@ControllerAdvice
public class TopException {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){

        if(e instanceof MyException){
            System.out.println("业务日志:" + e);
            MyException myException = (MyException) e;
            return  Result.error(myException.getCode(),myException.getMessage());
        }else if(e instanceof AccessDeniedException){
            return  Result.error(403,"访问权限不足");
        }

        System.out.println("系统日志:" + e);
        return Result.error(1000,"业务繁忙");
    }

}
