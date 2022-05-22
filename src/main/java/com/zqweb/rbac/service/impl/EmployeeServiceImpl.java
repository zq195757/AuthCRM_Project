package com.zqweb.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zqweb.rbac.common.LoginException;
import com.zqweb.rbac.mapper.EmployeeMapper;
import com.zqweb.rbac.mapper.EmployeeRoleMapper;
import com.zqweb.rbac.mapper.PermissionMapper;
import com.zqweb.rbac.pojo.EmployeeEntity;
import com.zqweb.rbac.pojo.query.EmployeeQueryObject;
import com.zqweb.rbac.pojo.vo.LoginVo;
import com.zqweb.rbac.pojo.vo.PageResultVo;
import com.zqweb.rbac.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;


@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, EmployeeEntity> implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeRoleMapper employeeRoleMapper;
    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public PageResultVo<EmployeeEntity> listPage(EmployeeQueryObject qo) {
        System.out.println(qo);
        List<EmployeeEntity> employeeEntities = employeeMapper.listPage(qo);
        PageResultVo<EmployeeEntity> pageResult = new PageResultVo<>();

        pageResult.setCurrentPage(qo.getCurrentPage());
        pageResult.setPageSize((qo.getPageSize()));
        // 设置总记录数
        Integer totalCount = employeeMapper.getTotalCount(qo);

        pageResult.setTotalCount(totalCount);
        if(!CollectionUtils.isEmpty(employeeEntities)){
            pageResult.setData(employeeEntities);
            pageResult.setTotalPage();
        }

        return pageResult;
    }

    @Override
    public EmployeeEntity getMergedEmployeeInfoById(Long id) {
        EmployeeEntity employeeEntity = employeeMapper.getMergedEmployeeInfoById(id);
        System.out.println("员工合并后的信息为：" + employeeEntity);
        return employeeEntity;
    }

    @Override
    public boolean saveOrUpdate(EmployeeEntity employeeEntity, Long[] ids) {
        boolean flag = true;
        Long employeeId = employeeEntity.getId();
        System.out.println("employeeId1 = " + employeeId);
        // 添加或更新员工信息
        flag = super.saveOrUpdate(employeeEntity);
        employeeId = employeeEntity.getId();// 在saveOrUpdate完后重新获取变化后的employeeId，防止为null
        System.out.println("employeeId2 = " + employeeId);
        // 维护员工的关系（先删除旧有关系，然后将传入的ids作为新关系添加到员工角色关系表中）
        if(ids != null && ids.length > 0){// 有新关系才删除旧关系并新增新关系
            flag = employeeRoleMapper.deleteOldRelations(employeeId) > 0;
            flag = employeeRoleMapper.insertByBatchIds(employeeId,ids) > 0;
        }
        return flag;
    }

    @Override
    public void loginHandle(LoginVo loginVo) {
        if(loginVo != null){
            // 登录的密码校验
            EmployeeEntity loginEmployee = this.getOne(new QueryWrapper<EmployeeEntity>().eq("name", loginVo.getUsername())
                    .eq("password", loginVo.getPassword()));
            System.out.println("登录的用户信息：" + loginEmployee);
            if(loginEmployee != null){
                // 从请求作用域中获取到请求属性对象Servlet实现类
                ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                HttpSession session = servletRequestAttributes.getRequest().getSession();
                session.setAttribute("EMPLOYEE_IN_SESSION",loginEmployee);
                // 密码校验成功后，给登录用户赋予对应的权限（admin不用设置，因为不作权限校验）
                if(!loginEmployee.getAdmin()){
                    List<String> permissions = permissionMapper.getPermissionsByEmpId(loginEmployee.getId());
                    session.setAttribute("PERMISSIONS_IN_SESSION",permissions);
                }
            }else{
                throw new LoginException("输入的账号或密码不正确！");
            }
        }else{
            throw new LoginException("请输入完整的账号、密码！");
        }

    }

    @Override
    public void logoutHandle() {
        // 从请求作用域中获取到请求属性对象Servlet实现类
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        // 清除登录及权限相关的session信息
        session.removeAttribute("EMPLOYEE_IN_SESSION");
        session.removeAttribute("PERMISSIONS_IN_SESSION");

    }


}
