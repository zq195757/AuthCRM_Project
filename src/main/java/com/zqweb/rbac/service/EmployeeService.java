package com.zqweb.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zqweb.rbac.pojo.EmployeeEntity;
import com.zqweb.rbac.pojo.query.EmployeeQueryObject;
import com.zqweb.rbac.pojo.vo.LoginVo;
import com.zqweb.rbac.pojo.vo.PageResultVo;

public interface EmployeeService extends IService<EmployeeEntity> {

    PageResultVo<EmployeeEntity> listPage(EmployeeQueryObject qo);

    EmployeeEntity getMergedEmployeeInfoById(Long id);

    boolean saveOrUpdate(EmployeeEntity employeeEntity,Long[] ids);

    void loginHandle(LoginVo loginVo);

    void logoutHandle();

}
