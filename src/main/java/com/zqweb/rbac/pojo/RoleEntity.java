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
@TableName(value = "role")
public class RoleEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;// 角色名称
    private String sn;// 角色编号

    @TableField(exist = false)
    private List<PermissionEntity> permissions; // 该角色所具备的权限

}
