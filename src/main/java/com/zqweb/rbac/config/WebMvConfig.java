package com.zqweb.rbac.config;

import com.zqweb.rbac.web.interceptor.LoginInterceptor;
import com.zqweb.rbac.web.interceptor.PermissionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvConfig implements WebMvcConfigurer {
    @Override
    // 跨域处理
    public void addCorsMappings(CorsRegistry registry) {

    }

    @Override
    // 拦截器处理
    public void addInterceptors(InterceptorRegistry registry) {
        // 指定拦截路径与放行路径
        // 注：登录拦截要验证/permission/noPermission请求，不然nopermission页面渲染报错，
        // 而权限拦截验证则放行该请求，因为此时已经登录了
        registry.addInterceptor(new LoginInterceptor())// 登录验证
                .addPathPatterns("/**") // 拦截所有请求
                .excludePathPatterns("/","/login","/css/**","/js/**","/favicon.ico");
        registry.addInterceptor(new PermissionInterceptor()) // 权限验证
                .addPathPatterns("/**") // 拦截所有请求
                .excludePathPatterns("/","/permission/noPermission","/login","/css/**","/js/**","/favicon.ico");
    }

}
