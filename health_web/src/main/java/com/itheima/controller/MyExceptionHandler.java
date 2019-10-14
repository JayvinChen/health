package com.itheima.controller;

import com.itheima.entity.Result;
import com.itheima.exception.MyException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
 * @author Jayvin
 * @date 2019/9/22 18:03
 */
@RestControllerAdvice
public class MyExceptionHandler {
    /** 
     * @Description: 声明自定义异常 
     * @Param: [me] 
     * @return: com.itheima.entity.Result 
     */ 
    @ExceptionHandler(MyException.class)
    public Result handelMyException(MyException me){
        me.printStackTrace();
        return new Result(false,me.getLocalizedMessage());
    }
    
    @ExceptionHandler(Exception.class)
    public Result allException(Exception e){
        e.printStackTrace();
        return new Result(false,"发生异常...");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Result noAccesshandler(AccessDeniedException accessDeniedException){
        return new Result(false,"亲亲没有这个权限哦");
    }
}
