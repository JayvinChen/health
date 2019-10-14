package com.itheima.exception;

/*
 * @author Jayvin
 * @date 2019/9/21 20:38
 *
 * 自定义异常：终止已经不符合业务逻辑的操作
 */
public class MyException extends RuntimeException{
    public MyException(String message) {
        super(message);
    }
}
