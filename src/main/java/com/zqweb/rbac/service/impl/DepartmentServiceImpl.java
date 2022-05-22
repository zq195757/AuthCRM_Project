package com.zqweb.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zqweb.rbac.mapper.DepartmentMapper;
import com.zqweb.rbac.mapper.EmployeeMapper;
import com.zqweb.rbac.pojo.DepartmentEntity;
import com.zqweb.rbac.pojo.EmployeeEntity;
import com.zqweb.rbac.pojo.query.QueryEntity;
import com.zqweb.rbac.pojo.vo.PageResultVo;
import com.zqweb.rbac.service.DepartmentService;
import com.zqweb.rbac.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, DepartmentEntity> implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    // 分页查询（仅查询当前页、指定大小的数据量）
    public PageResultVo<DepartmentEntity> listByPage(QueryEntity queryEntity) {
        System.out.println(queryEntity);
        PageResultVo<DepartmentEntity> pageResultVo = new PageResultVo<>();
        // 这里的记录总数count针对的是全体数据
        Integer count = departmentMapper.selectCount(null);
        pageResultVo.setTotalCount(count);
        List<DepartmentEntity> departmentEntities = departmentMapper.listByPage(queryEntity);
        pageResultVo.setCurrentPage(queryEntity.getCurrentPage());
        pageResultVo.setPageSize(queryEntity.getPageSize());
        if(!CollectionUtils.isEmpty(departmentEntities)){
            pageResultVo.setData(departmentEntities);
            pageResultVo.setTotalPage();
        }
        System.out.println(pageResultVo);
        return pageResultVo;
    }

    @Override
    public boolean deleteOneById(Long id) {
        boolean flag = true;
        // 清空指定部门关联的员工的deptId
        List<EmployeeEntity> relatedEmps = employeeService.list(
                new QueryWrapper<EmployeeEntity>().eq("dept_id",id)
        );
        if(!CollectionUtils.isEmpty(relatedEmps)){
            relatedEmps = relatedEmps.stream().map(emp -> {
                emp.setDeptId(null);
                return emp;
            }).collect(Collectors.toList());
            employeeService.updateBatchById(relatedEmps);
        }
        // 然后再将该部门信息删除
        Integer result = departmentMapper.deleteById(id);
        if(result < 0){
            System.out.println(result);
            flag = false;
        }
        return flag;
    }


}
