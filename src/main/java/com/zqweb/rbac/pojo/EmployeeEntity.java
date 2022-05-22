package com.zqweb.rbac.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@TableName(value = "employee")
public class EmployeeEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;			// 员工名称
    private String password;	// 员工密码
    private String email;			// 员工邮箱
    private Integer age;			// 员工年龄
    //如果不设置默认值,前台页面不勾选的话,传到后台的admin值是null,设置默认值可以解决这个问题
    private Boolean admin = false;	// 是否是超级管理员,设置默认为false
    @TableField(value = "dept_id") // 表字段
    private Long deptId;
    @TableField(exist = false)// 逻辑业务字段
    private DepartmentEntity department;		// 所属部门信息
    @TableField(exist = false)
    private List<RoleEntity> roles;              // 具有的角色信息，用于编辑回显


}