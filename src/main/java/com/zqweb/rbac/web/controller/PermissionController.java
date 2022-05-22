package com.zqweb.rbac.web.controller;

import com.zqweb.rbac.auth.CustomAuthAnnotation;
import com.zqweb.rbac.pojo.PermissionEntity;
import com.zqweb.rbac.pojo.query.PermissionQueryObject;
import com.zqweb.rbac.pojo.vo.PageResultVo;
import com.zqweb.rbac.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@Slf4j
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/reload")
    @CustomAuthAnnotation(value = {"权限加载","permission:reload"})
    public String reload(){
        permissionService.reload();// 将新添加的权限实时加入到数据库保存起来，实现实时的多方面权限验证
        return "redirect:/permission/list";
    }

    @RequestMapping("/noPermission")
    // 权限校验失败，直接返回错误提示页面
    public String back(){
        return "common/nopermission";
    }
    
    @RequestMapping("/list")
    @CustomAuthAnnotation(value = {"权限列表","permission:list"})
    public String list(Model model,@ModelAttribute("qo") PermissionQueryObject qo){
        // 请求传来的qo对象，重新以key=qo，value=PermissionQueryObject qo对象的方式回显回原来的请求页面
        // 保留原来请求时的表单状态
        PageResultVo<PermissionEntity> pageResult =  permissionService.listPage(qo);
//        List<DepartmentEntity> departmentEntities = departmentService.list();
        System.out.println("pages = " + pageResult.getTotalPage() + ",pageSize = " + pageResult.getPageSize() + ",currentPage = " + pageResult.getCurrentPage());
        model.addAttribute("pageResult",pageResult);
//        model.addAttribute("departments",departmentEntities);
        return "permission/list";
    }

    // 映射重用（添加或修改）
    @GetMapping("/input")
    @CustomAuthAnnotation(value = {"权限编辑","permission:input"})
    public String addOne(Long id,Integer currentPage,Model model){
        System.out.println("id = " + id + ",currentPage = " + currentPage);
        if(id != null){
            // 修改逻辑
            PermissionEntity permissionEntity = permissionService.getById(id);
            model.addAttribute("role",permissionEntity);
            model.addAttribute("currentPage",currentPage);
        }else{
            // 添加逻辑
            model.addAttribute("currentPage",1);// 添加完默认展示第一页的数据
        }
        return "permission/input";
    }


    @PostMapping("/saveOrUpdate")
    @CustomAuthAnnotation(value = {"权限修改或更新","permission:saveOrUpdate"})
    public String saveOrUpdate(PermissionEntity permissionEntity, Integer currentPage){
        System.out.println("RoleEntity = " + permissionEntity + ",currentPage = " + currentPage);
        permissionService.saveOrUpdate(permissionEntity);
        return "redirect:/permission/list?currentPage=" + currentPage;// 请求转发（不是请求重定向）
    }

    @GetMapping("/delete")
    @CustomAuthAnnotation(value = {"权限删除","permission:delete"})
    public String deleteById(@RequestParam(value = "id") Long id, @RequestParam(value = "currentPage") Integer currentPage){
        System.out.println("进入员工删除方法，id = " + id + ",currentPage = " + currentPage);
        boolean isDeleted = permissionService.removeById(id);
        log.info(isDeleted ? "删除成功！" : "删除失败！");

        return "redirect:/permission/list?currentPage=" + currentPage;
    }


}
