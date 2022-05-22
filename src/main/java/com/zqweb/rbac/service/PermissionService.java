package com.zqweb.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zqweb.rbac.pojo.PermissionEntity;
import com.zqweb.rbac.pojo.query.PermissionQueryObject;
import com.zqweb.rbac.pojo.vo.PageResultVo;

public interface PermissionService extends IService<PermissionEntity> {

    PageResultVo<PermissionEntity> listPage(PermissionQueryObject qo);

    void reload();


}
