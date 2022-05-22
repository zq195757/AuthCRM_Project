package com.zqweb.rbac.web.controller;

import com.zqweb.rbac.auth.CustomAuthAnnotation;
import com.zqweb.rbac.pojo.PermissionEntity;
import com.zqweb.rbac.pojo.RoleEntity;
import com.zqweb.rbac.pojo.query.RoleQueryObject;
import com.zqweb.rbac.pojo.vo.PageResultVo;
import com.zqweb.rbac.service.DepartmentService;
import com.zqweb.rbac.service.PermissionService;
import com.zqweb.rbac.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PermissionService permissionService;
    
    
    @RequestMapping("/list")
    @CustomAuthAnnotation(value = {"角色列表","role:list"})
    public String list(Model model,@ModelAttribute("qo") RoleQueryObject qo){
        // 请求传来的qo对象，重新以key=qo，value=RoleQueryObject qo对象的方式回显回原来的请求页面
        // 保留原来请求时的表单状态
        PageResultVo<RoleEntity> pageResult =  roleService.listPage(qo);
//        List<DepartmentEntity> departmentEntities = departmentService.list();
        System.out.println("pages = " + pageResult.getTotalPage() + ",pageSize = " + pageResult.getPageSize() + ",currentPage = " + pageResult.getCurrentPage());
        model.addAttribute("pageResult",pageResult);
//        model.addAttribute("departments",departmentEntities);
        return "role/list";
    }

    // 映射重用（添加或修改）
    @GetMapping("/input")
    @CustomAuthAnnotation(value = {"角色编辑","role:input"})
    public String addOne(Long id,Integer currentPage,Model model){
        System.out.println("id = " + id + ",currentPage = " + currentPage);
        List<PermissionEntity> permissions = permissionService.list();
        model.addAttribute("permissions",permissions);
        if(id != null){
            // 修改逻辑
            RoleEntity roleEntity = roleService.getMergeInfoById(id);
            model.addAttribute("role",roleEntity);
            model.addAttribute("currentPage",currentPage);
        }else{
            // 添加逻辑
            model.addAttribute("currentPage",1);// 添加完默认展示第一页的数据
        }
        return "role/input";
    }


    @PostMapping("/saveOrUpdate")
    @CustomAuthAnnotation(value = {"角色添加或修改","role:saveOrUpdate"})
    public String saveOrUpdate(RoleEntity roleEntity,Long[] permissionIds, Integer currentPage){
        System.out.println("RoleEntity = " + roleEntity + ",currentPage = " + currentPage + ",permissionIds = ");
        for (Long permissionId : permissionIds) {
            System.out.print(permissionId + "\t");
        }
        roleService.saveOrUpdate(roleEntity,permissionIds);
        return "redirect:/role/list?currentPage=" + currentPage;// 请求转发（不是请求重定向）
    }

    @GetMapping("/delete")
    @CustomAuthAnnotation(value = {"角色删除","role:delete"})
    public String deleteById(@RequestParam(value = "id") Long id, @RequestParam(value = "currentPage") Integer currentPage){
        System.out.println("进入角色删除方法，id = " + id + ",currentPage = " + currentPage);
        boolean isDeleted = roleService.removeById(id);
        log.info(isDeleted ? "删除成功！" : "删除失败！");

        return "redirect:/role/list?currentPage=" + currentPage;
    }


}
