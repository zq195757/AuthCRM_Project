package com.zqweb.rbac.web.controller;

import com.zqweb.rbac.auth.CustomAuthAnnotation;
import com.zqweb.rbac.pojo.DepartmentEntity;
import com.zqweb.rbac.pojo.query.QueryEntity;
import com.zqweb.rbac.pojo.vo.PageResultVo;
import com.zqweb.rbac.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/department")
@Slf4j
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/list")
    @CustomAuthAnnotation(value = {"部门列表","department:list"})
    public String list(Model model, QueryEntity queryEntity){
        System.out.println(queryEntity);
        /*List<DepartmentEntity> departmentEntities = departmentService.list();
        model.addAttribute("departments",departmentEntities);*/
        PageResultVo<DepartmentEntity> pageResult = departmentService.listByPage(queryEntity);
        //将查询到的部门信息存储到域对象中
        model.addAttribute("pageResult",pageResult);
        return "department/list";
    }

    // 映射重用（添加或修改）
    @GetMapping("/input")
    @CustomAuthAnnotation(value = {"部门编辑","department:input"})
    public String addOne(Long id,Integer currentPage,Model model){
        System.out.println("id = " + id + ",currentPage = " + currentPage);
        if(id != null){
            // 修改逻辑
            DepartmentEntity departmentEntity = departmentService.getById(id);
            model.addAttribute("department",departmentEntity);
            model.addAttribute("currentPage",currentPage);
        }else{
            // 添加逻辑
            model.addAttribute("currentPage",1);// 添加完默认展示第一页的数据
        }
        return "department/input";
    }


    @PostMapping("/saveOrUpdate")
    @CustomAuthAnnotation(value = {"部门添加或修改","department:saveOrUpdate"})
    public String saveOrUpdate(DepartmentEntity departmentEntity,Integer currentPage){
        System.out.println("departmentEntity = " + departmentEntity + ",currentPage = " + currentPage);
        departmentService.saveOrUpdate(departmentEntity);
        return "redirect:/department/list?currentPage=" + currentPage;// 请求转发（不是请求重定向）
    }

    @GetMapping("/delete")
    @CustomAuthAnnotation(value = {"部门删除","department:delete"})
    public String deleteById(@RequestParam(value = "id") Long id,@RequestParam(value = "currentPage") Integer currentPage){
        System.out.println("进入部门删除方法，id = " + id + ",currentPage = " + currentPage);
        boolean isDeleted = departmentService.deleteOneById(id);
        log.info(isDeleted ? "删除成功！" : "删除失败！");

        return "redirect:/department/list?currentPage=" + currentPage;
    }

}
