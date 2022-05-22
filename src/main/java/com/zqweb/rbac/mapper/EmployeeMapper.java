package com.zqweb.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zqweb.rbac.pojo.EmployeeEntity;
import com.zqweb.rbac.pojo.query.EmployeeQueryObject;

import java.util.List;

public interface EmployeeMapper extends BaseMapper<EmployeeEntity> {

    List<EmployeeEntity> listPage(EmployeeQueryObject qo);

    List<EmployeeEntity> listAll(EmployeeQueryObject qo);

    Integer getTotalCount(EmployeeQueryObject qo);

    EmployeeEntity getMergedEmployeeInfoById(Long id);

}
