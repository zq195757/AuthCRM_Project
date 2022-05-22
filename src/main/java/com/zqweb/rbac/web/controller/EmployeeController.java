package com.zqweb.rbac.web.controller;

import com.zqweb.rbac.auth.CustomAuthAnnotation;
import com.zqweb.rbac.pojo.DepartmentEntity;
import com.zqweb.rbac.pojo.EmployeeEntity;
import com.zqweb.rbac.pojo.RoleEntity;
import com.zqweb.rbac.pojo.query.EmployeeQueryObject;
import com.zqweb.rbac.pojo.vo.PageResultVo;
import com.zqweb.rbac.service.DepartmentService;
import com.zqweb.rbac.service.EmployeeService;
import com.zqweb.rbac.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private RoleService roleService;


    @RequestMapping("/list")
    @CustomAuthAnnotation(value = {"员工列表","employee:list"})
    public String list(Model model,@ModelAttribute("qo") EmployeeQueryObject qo){
        // 请求传来的qo对象，重新以key=qo，value=EmployeeQueryObject qo对象的方式回显回原来的请求页面
        // 保留原来请求时的表单状态
        PageResultVo<EmployeeEntity> pageResult =  employeeService.listPage(qo);
        List<DepartmentEntity> departmentEntities = departmentService.list();
        System.out.println("pages = " + pageResult.getTotalPage() + ",pageSize = " + pageResult.getPageSize() + ",currentPage = " + pageResult.getCurrentPage());
        model.addAttribute("pageResult",pageResult);
        model.addAttribute("departments",departmentEntities);
        return "employee/list";
    }

    // 映射重用（添加或修改）
    @GetMapping("/input")
    @CustomAuthAnnotation(value = {"员工编辑","employee:input"})
    public String addOne(Long id,Integer currentPage,Model model){
        System.out.println("id = " + id + ",currentPage = " + currentPage);
        List<DepartmentEntity> departmentEntities = departmentService.list();
        model.addAttribute("departments",departmentEntities);
        List<RoleEntity> roleEntities = roleService.list();
        model.addAttribute("roles",roleEntities);

        if(id != null){
            // 修改逻辑
            EmployeeEntity employeeEntity = employeeService.getMergedEmployeeInfoById(id);
            model.addAttribute("employee",employeeEntity);// 此时设置好了部门及角色信息，用于内容回显
            model.addAttribute("currentPage",currentPage);
        }else{
            // 添加逻辑
            model.addAttribute("currentPage",1);// 添加完默认展示第一页的数据
        }
        return "employee/input";
    }


    @PostMapping("/saveOrUpdate")
    @CustomAuthAnnotation(value = {"员工添加或修改","employee:saveOrUpdate"})
    public String saveOrUpdate(EmployeeEntity employeeEntity,Long[] roleIds,Integer currentPage){
        // 对于表单提交过来的数据，需要同名映射接收，不然数据接收会有问题
        employeeEntity.setDeptId(employeeEntity.getDepartment().getId());// 重设deptId（mapper映射问题）
        for (Long id : roleIds) {
            System.out.println(id);
        }
        System.out.println("EmployeeEntity = " + employeeEntity + ",currentPage = " + currentPage);
        boolean flag = employeeService.saveOrUpdate(employeeEntity, roleIds);
        System.out.println(flag ? "执行成功！" : "执行失败！");
        return "redirect:/employee/list?currentPage=" + currentPage;// 请求转发（不是请求重定向）
    }

    @GetMapping("/delete")
    @CustomAuthAnnotation(value = {"员工删除","employee:delete"})
    public String deleteById(@RequestParam(value = "id") Long id, @RequestParam(value = "currentPage") Integer currentPage){
        System.out.println("进入员工删除方法，id = " + id + ",currentPage = " + currentPage);
        boolean isDeleted = employeeService.removeById(id);
        log.info(isDeleted ? "删除成功！" : "删除失败！");

        return "redirect:/employee/list?currentPage=" + currentPage;
    }

}
