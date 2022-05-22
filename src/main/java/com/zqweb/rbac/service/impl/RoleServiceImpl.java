package com.zqweb.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zqweb.rbac.mapper.RoleMapper;
import com.zqweb.rbac.mapper.RolePermissionMapper;
import com.zqweb.rbac.pojo.RoleEntity;
import com.zqweb.rbac.pojo.query.RoleQueryObject;
import com.zqweb.rbac.pojo.vo.PageResultVo;
import com.zqweb.rbac.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;


    @Override
    public PageResultVo<RoleEntity> listPage(RoleQueryObject qo) {
        System.out.println(qo);
        PageResultVo<RoleEntity> pageResult = new PageResultVo<>();

        IPage<RoleEntity> page = this.page(new Page<>(qo.getCurrentPage(),qo.getPageSize()),new QueryWrapper<RoleEntity>()
            .orderByDesc("id"));// 降序排列
        List<RoleEntity> roleEntities = page.getRecords();
        pageResult.setCurrentPage(qo.getCurrentPage());
        pageResult.setPageSize(qo.getPageSize());

        // 设置总记录数
        Integer totalCount = this.roleMapper.selectCount(null);
        pageResult.setTotalCount(totalCount);

        if(!CollectionUtils.isEmpty(roleEntities)){
            pageResult.setData(roleEntities);
            pageResult.setTotalPage();
        }

        return pageResult;

    }

    @Override
    public RoleEntity getMergeInfoById(Long id) {
        RoleEntity roleEntity = roleMapper.getMergeInfoById(id);
        System.out.println("设置了权限信息的roleEntity为：" + roleEntity);
        return roleEntity;
    }

    @Override
    public boolean saveOrUpdate(RoleEntity roleEntity, Long[] permissionIds) {
        boolean flag = true;
        flag = super.saveOrUpdate(roleEntity);
        Long roleId = roleEntity.getId();// 获取saveOrUpdate完的回显roleId
        if(permissionIds != null && permissionIds.length > 0){// 有新关系才删除旧关系并新增新关系
            flag = rolePermissionMapper.deleteRelationPermissions(roleId, permissionIds) > 0;
            flag = rolePermissionMapper.insertNewRelations(roleId,permissionIds) > 0;
        }
        System.out.println("flag = " + flag);
        return flag;
    }


}
