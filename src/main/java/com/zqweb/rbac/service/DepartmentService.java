package com.zqweb.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zqweb.rbac.pojo.DepartmentEntity;
import com.zqweb.rbac.pojo.query.QueryEntity;
import com.zqweb.rbac.pojo.vo.PageResultVo;


public interface DepartmentService extends IService<DepartmentEntity> {

    PageResultVo<DepartmentEntity> listByPage(QueryEntity queryEntity);


    boolean deleteOneById(Long id);
}
