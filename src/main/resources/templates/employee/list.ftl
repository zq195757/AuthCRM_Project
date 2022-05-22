
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>员工管理</title>
    <#include "../common/link.ftl">

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <#include "../common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="employee"/>
    <#include "../common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>员工管理</h1>
        </section>
        <section class="content">

            <div class="box">
                <!--高级查询--->
                <div style="margin: 10px;">
                    <form class="form-inline" id="searchForm" action="/employee/list" method="post">
                        <input type="hidden" name="currentPage" id="currentPage">
                        <div class="form-group">
                            <label for="keyword">关键字:</label>
                            <input type="text" class="form-control" id="keyword" name="keyword"
                                   value="${(qo.keyword)!}" placeholder="请输入姓名/邮箱">
                        </div>
                        <div class="form-group">
                            <label for="dept"> 部门:</label>
                            <select class="form-control" id="dept" name="deptId">
                                <option value="-1">全部</option>
                                <#list (departments)! as d>
                                    <option value="${(d.id)!}">${(d.name)!}</option>
                                </#list>
                                <#--<c:forEach items="${departments}" var="d">
                                    <option value="${d.id}">${d.name}</option>
                                </c:forEach>-->
                            </select>
                            <script>
                                // 对选择的部门设为保留选中状态
                                $("#dept option[value='${(qo.deptId)!}']").prop("selected", true);
                            </script>
                        </div>
                        <button id="btn_query" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>
                            查询
                        </button>
                        <a href="javascript:;" class="btn btn-success btn_redirect" data-url="/employee/input">
                            <span class="glyphicon glyphicon-plus"></span> 添加
                        </a>

                    </form>
                </div>


                <!--编写内容-->
                <div class="box-body table-responsive no-padding ">
                    <table class="table table-hover table-bordered">
                        <tr>
                            <th>编号</th>
                            <th>名字</th>
                            <th>邮箱</th>
                            <th>年龄</th>
                            <th>部门</th>
                            <th>操作</th>
                        </tr>
                        <#list (pageResult.data)! as entity>
                            <tr>
                                <td>${entity_index+1}</td>
                                <td>${entity.name}</td>
                                <td>${entity.email}</td>
                                <td>${entity.age}</td>
                                <td>${(entity.department.name)!}</td>

                                <td>
                                    <a class="btn btn-info btn-xs btn-input" href="/employee/input?id=${entity.id}&currentPage=${pageResult.currentPage}">
                                        <span class="glyphicon glyphicon-pencil"></span> 编辑
                                    </a>
                                    <a href="#" class="btn btn-danger btn-xs btn-delete"
                                       curpage="${pageResult.currentPage}" onclick="deleteCurEmployee(this)" url_prefix="/employee/delete?id=${entity.id}"
                                    >
                                        <span class="glyphicon glyphicon-trash"></span> 删除
                                    </a>
                                </td>
                            </tr>
                        </#list>
                       <#-- <c:forEach items="${pageResult.data}" var="entity" varStatus="vs">
                            <tr>
                                <td>${vs.count}</td>
                                <td>${entity.name}</td>
                                <td>${entity.email}</td>
                                <td>${entity.age}</td>
                                <td>${entity.department.name}</td>
                                <%--<td>${entity.dept.name}</td>--%>
                                <td>
                                    <a class="btn btn-info btn-xs btn-input" href="/employee/input?id=${entity.id}">
                                        <span class="glyphicon glyphicon-pencil"></span> 编辑
                                    </a>
                                    <a href="/employee/delete?id=${entity.id}" class="btn btn-danger btn-xs btn-delete">
                                        <span class="glyphicon glyphicon-trash"></span> 删除
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>-->
                    </table>
                    <!--分页-->
                    <#include "../common/page.ftl">
<#--                    <#include "../common/page_forplugin.ftl">-->
                </div>
            </div>
        </section>
    </div>
    <#include "../common/footer.ftl">
</div>
<script type="application/javascript">
    // 渲染DOM前就要声明好该函数
    function deleteCurEmployee(pointer){
        console.log("进入删除操作方法...")
        let _this = pointer;// 默认传进来的是window对象，需要显式传入DOM元素（this指针问题）
        // 是否需要翻页（停留在当前删除页号，还是当前页要删除的是最后一个从而删除后去加载前一页的内容）
        let ifNeedToSkipPage =  $(_this).siblings() ? false : true;
        let curPage = Number.parseInt($(_this).attr("curpage"));
        if(ifNeedToSkipPage && curPage > 1){
            curPage--;
        }
        console.log("ifNeedToSkipPage =",ifNeedToSkipPage,",curPage =",curPage);
        // 超链接不支持value属性取值
        let url = ($(_this).attr("url_prefix") + "&currentPage=" + curPage).trim();// 清除两侧留白
        console.log("url=",url);
        location.href = url;// 注意：项目迁移到云上后的url问题
        return false;// 禁用超链接默认跳转行为
    }

</script>
</body>
</html>
