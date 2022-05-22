package com.zqweb.rbac.mapper;

import com.zqweb.rbac.pojo.PermissionEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionMapper {
    // 该方法Service层未引用，只是提供给RoleMapper作多表数据查询引用
    List<PermissionEntity> getPermissionsByRoleId(Long roleId);

    Integer deleteRelationPermissions(@Param("roleId")Long roleId,@Param(value = "ids") Long[] permissionIds);

    Integer insertNewRelations(@Param("roleId")Long roleId,@Param(value = "ids") Long[] permissionIds);



}
