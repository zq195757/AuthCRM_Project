package com.zqweb.rbac.web.controller;

import com.zqweb.rbac.pojo.vo.LoginVo;
import com.zqweb.rbac.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
public class Login_OutController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = {"/","/index"})
    public String loadIndexPage(){
        return "login";
    }

    @RequestMapping("/login")
    // 用户登录
    // 注：服务器一重启，session便失效
    public String loginHandle(LoginVo loginVo, Model model){
        System.out.println(loginVo);
        try {
            employeeService.loginHandle(loginVo);
        } catch (Exception e) {
            log.warn(e.getMessage());
            model.addAttribute("errorMsg",e.getMessage());
            return "login";
        }
        return "redirect:/department/list";
    }

    @RequestMapping("/logout")
    // 用户登出
    public String logoutHandle(){
        employeeService.logoutHandle();
        return "redirect:/";
    }

}
