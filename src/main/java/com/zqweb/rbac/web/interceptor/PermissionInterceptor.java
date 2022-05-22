package com.zqweb.rbac.web.interceptor;

import com.zqweb.rbac.auth.CustomAuthAnnotation;
import com.zqweb.rbac.pojo.EmployeeEntity;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


public class PermissionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*
            权限校验流程：
                ———— 判断当前访问用户是不是超级管理员admin
                   ———— 是：放行
                   ———— 否：判断请求的处理器方法是否需要进行权限验证
                     ———— 否：放行
                     ———— 是：获取对应处理器方法的所需权限及当前访问用户拥有的自身权限，判断是否有权限访问对应的方法
                       ———— 是：放行
                       ———— 否：打回no permission 提示页面
         */
        HttpSession session = request.getSession();
        EmployeeEntity employee = (EmployeeEntity) session.getAttribute("EMPLOYEE_IN_SESSION");
        if(employee.getAdmin()){// admin身份由前台控制（TODO 前台控制比较危险，需要设定权限）
            return true;
        }
        if(!(handler instanceof HandlerMethod)){
            // SpringMVC机制：对于访问处理器方法的请求都会被包装为HandlerMethod类型，不是该类型的请求即可放行
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        CustomAuthAnnotation customAuthAnnotation = handlerMethod.getMethodAnnotation(CustomAuthAnnotation.class);
        if(customAuthAnnotation == null){
            return true;
        }
        String neededPermission = customAuthAnnotation.value()[1];
        List<String> ownPermissions = (List<String>) session.getAttribute("PERMISSIONS_IN_SESSION");
        if(ownPermissions != null && ownPermissions.contains(neededPermission)){
            return true;
        }
        // 上述验证失败，则最终返回no permission提示页面
        response.sendRedirect("/permission/noPermission");
        return false;
    }
}
