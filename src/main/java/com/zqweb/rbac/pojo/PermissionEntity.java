package com.zqweb.rbac.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName(value = "permission")
public class PermissionEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;// 权限名称
    private String expression;// 权限请求路径(表达式)

}
