package com.zqweb.rbac.pojo.query;

import lombok.Data;
import lombok.ToString;
import org.springframework.util.StringUtils;

@Data
@ToString
public class EmployeeQueryObject extends QueryEntity {
    private String keyword;		//关键字搜索
    private Long deptId = -1L;	//下拉框部门显示(默认为-1,即查询全部.前端页面已定义)


    //判空操作写在java类中,而不是写在mapper.xml文件中
    public String getKeyword(){
        //调用工具类判断用户输入的关键字是否为空，即空串代表该项未设置检索条件，默认查询所有
        return StringUtils.isEmpty(keyword) ? null : keyword;
    }
}
