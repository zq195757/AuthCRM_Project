package com.zqweb.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zqweb.rbac.pojo.RoleEntity;
import com.zqweb.rbac.pojo.query.RoleQueryObject;

import java.util.List;

public interface RoleMapper extends BaseMapper<RoleEntity> {

    List<RoleEntity> listPage(RoleQueryObject qo);

    List<RoleEntity> selectByEmpId(Long empId);

    RoleEntity getMergeInfoById(Long id);

}
