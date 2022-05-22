package com.zqweb.rbac.pojo.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class PageResultVo<T>{
    //需要查询数据库
    private List<T> data;        //从数据库查出全部数据集
    private int totalCount; //从数据库查出总条数
    //用户输入
    private int pageSize;       //每页显示多少条数据
    private int currentPage;    //当前页

    //计算
    private int totalPage;    //总页数(总条数/每页显示条数)
    private int prevPage;        //上一页
    private int nextPage;    //下一页

    // 计算上一页和下一页
    public void setTotalPage(){
        this.totalPage = this.totalCount % this.pageSize == 0 ? this.totalCount / this.pageSize : (this.totalCount / this.pageSize + 1);
        this.prevPage = Math.max(this.currentPage - 1,1);
        this.nextPage = Math.min(totalPage,this.currentPage + 1);
    }

    // 构造器（整合Mybatis-Plus分页插件）
    public PageResultVo(IPage<T> page){
        this.currentPage = Integer.parseInt(page.getCurrent() + "");
        this.pageSize = (int) page.getSize();
        this.totalCount = (int) page.getTotal();
        this.data = page.getRecords();
        this.setTotalPage();
    }
    // 空构造器
    public PageResultVo(){
    }


}