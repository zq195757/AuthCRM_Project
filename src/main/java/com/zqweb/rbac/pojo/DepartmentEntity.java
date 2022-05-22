package com.zqweb.rbac.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("department")
public class DepartmentEntity {
    @TableId(type = IdType.AUTO)
    private Long id;// id
    private String name;// 部门名称
    private String sn;// 部门编号

}
