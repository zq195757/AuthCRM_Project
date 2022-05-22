package com.zqweb.rbac.mapper;

import org.apache.ibatis.annotations.Param;

public interface EmployeeRoleMapper {

    Integer insertByBatchIds(@Param("empId")Long employeeId,@Param("ids") Long[] ids);

    Integer deleteOldRelations(Long employeeId);


}
