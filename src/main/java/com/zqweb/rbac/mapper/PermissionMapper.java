package com.zqweb.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zqweb.rbac.pojo.PermissionEntity;
import com.zqweb.rbac.pojo.query.PermissionQueryObject;

import java.util.List;

public interface PermissionMapper extends BaseMapper<PermissionEntity> {

    List<PermissionEntity> listPage(PermissionQueryObject qo);

    List<String> getPermissionsByEmpId(Long id);

}
