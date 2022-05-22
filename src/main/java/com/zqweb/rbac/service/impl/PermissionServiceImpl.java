package com.zqweb.rbac.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zqweb.rbac.auth.CustomAuthAnnotation;
import com.zqweb.rbac.mapper.PermissionMapper;
import com.zqweb.rbac.pojo.PermissionEntity;
import com.zqweb.rbac.pojo.query.PermissionQueryObject;
import com.zqweb.rbac.pojo.vo.PageResultVo;
import com.zqweb.rbac.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionEntity> implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private ApplicationContext applicationContext;// 注入Spring容器，获取bean等相关内容


    @Override
    public PageResultVo<PermissionEntity> listPage(PermissionQueryObject qo) {
        System.out.println(qo);
        PageResultVo<PermissionEntity> pageResult = new PageResultVo<>();
        // 这里手动改为4条，CRUD，每4条代表1类权限
        IPage<PermissionEntity> page = this.page(new Page<>(qo.getCurrentPage(),4));
        List<PermissionEntity> permissionEntities = page.getRecords();
        pageResult.setCurrentPage(qo.getCurrentPage());
        pageResult.setPageSize(4);

        // 设置总记录数
        Integer totalCount = this.permissionMapper.selectCount(null);
        pageResult.setTotalCount(totalCount);

        if(!CollectionUtils.isEmpty(permissionEntities)){
            pageResult.setData(permissionEntities);
            pageResult.setTotalPage();
        }

        return pageResult;

    }

    @Override
    public void reload() {
        // 先去查询数据库中已有的权限，将那些不存在的新权限加入到数据库中，防止多次将重复的权限加入数据库（幂等性处理）
        List<String> existedExpressions = this.list().stream().map(PermissionEntity::getExpression).collect(Collectors.toList());
        System.out.println("权限表达式如下：");
        for (String expression : existedExpressions) {
            System.out.println(expression);
        }
        Map<String, Object> annotationBeansMap = applicationContext.getBeansWithAnnotation(Controller.class);
        Collection<Object> objs = annotationBeansMap.values();
        List<PermissionEntity> needInsertPermissions = new ArrayList<>();


        objs.forEach(obj -> {
            System.out.println("obj对象：" + obj);
            Class<?> targetClass = obj.getClass();
            Method[] declaredMethods = targetClass.getDeclaredMethods();
            if(declaredMethods != null && declaredMethods.length > 0){
                Arrays.stream(declaredMethods).forEach(declaredMethod -> {
                    // 注意：forEach遍历时的不可中断性
                    CustomAuthAnnotation[] annotations = declaredMethod.getAnnotationsByType(CustomAuthAnnotation.class);
                    if(annotations != null && annotations.length > 0){
                        System.out.println("类名：" + annotations[0].getClass().getName());
                        String[] values = annotations[0].value();
                        String desp = values[0];
                        String expression = values[1];
                        if(!existedExpressions.contains(expression)){
                            PermissionEntity permissionEntity = new PermissionEntity();
                            permissionEntity.setName(desp);
                            permissionEntity.setExpression(expression);
                            needInsertPermissions.add(permissionEntity);
                        }
                    }
                });
            }
        });

        // 添加至数据库
        if(needInsertPermissions.size() > 0){
            this.saveBatch(needInsertPermissions);
        }

    }


}
