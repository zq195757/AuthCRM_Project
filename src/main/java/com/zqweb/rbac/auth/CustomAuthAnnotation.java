package com.zqweb.rbac.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
// 自定义的声明于方法上且运行期生效级别的权限注解，格式 类别名:权限操作，如employee:delete——员工删除操作权限
public @interface CustomAuthAnnotation {
    String[] value();// 属性不能是私有的

}
