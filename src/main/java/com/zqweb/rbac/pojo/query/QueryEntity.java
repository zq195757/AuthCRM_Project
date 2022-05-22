package com.zqweb.rbac.pojo.query;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class QueryEntity {
    //当前页
    private int currentPage = 1;    //默认的当前页是第一页
    //每页显示记录数
    private int pageSize = 5;       //默认每页显示的数据为5条


    // 提供一个方法,用于返回每页第一条数据对应的索引
    public int getStart(){
        return (currentPage - 1) * pageSize;// 第1页 0  第2页 5
    }
}