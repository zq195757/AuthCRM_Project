package com.zqweb.rbac.common;

// 自定义的运行时异常封装类
public class LoginException extends RuntimeException{
    public LoginException(){
        super();
    }
    public LoginException(String message){
        super(message);
    }
}
