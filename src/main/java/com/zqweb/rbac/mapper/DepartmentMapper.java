package com.zqweb.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zqweb.rbac.pojo.DepartmentEntity;
import com.zqweb.rbac.pojo.query.QueryEntity;

import java.util.List;


public interface DepartmentMapper extends BaseMapper<DepartmentEntity> {

    List<DepartmentEntity> listByPage(QueryEntity queryEntity);


}
