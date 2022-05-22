package com.zqweb.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zqweb.rbac.pojo.RoleEntity;
import com.zqweb.rbac.pojo.query.RoleQueryObject;
import com.zqweb.rbac.pojo.vo.PageResultVo;

public interface RoleService extends IService<RoleEntity> {

    PageResultVo<RoleEntity> listPage(RoleQueryObject qo);

    RoleEntity getMergeInfoById(Long id);

    boolean saveOrUpdate(RoleEntity roleEntity,Long[] permissionIds);

}
