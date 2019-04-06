<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>员工列表</title>
    <!--
        不以/开始的相对路径，会以当前资源路径为基准寻找资源，容易出问题
        以/开始的相对路径，会以服务器的路径为基准寻找资源-->
    <%
        pageContext.setAttribute("APP_PATH",request.getContextPath());
    %>
    <link rel="stylesheet" href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js" type="javascript"></script>
    <!-- 引入jquery -->
    <script src="${APP_PATH}/static/js/jquery-1.12.4.min.js" type="javascript"></script>
</head>
<body>
<!-- 搭建显示页面 -->
<div class="container">
    <!-- 标题 -->
    <div class="row">
        <div class="col-md-12">
            <h1>SSM框架整合</h1>
        </div>
    </div>
    <!-- 按钮 -->
    <div class="row">
        <div class="col-mod-4 col-md-offset-8">
            <button class="btn btn-primary">新增</button>
            <button class="btn btn-danger">删除</button>
        </div>
    </div>
    <!-- 表格中成列数据 -->
    <div class="row">
        <div class="col-md-12">
            <table class="table">
                <tr>
                    <th>编号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>邮箱</th>
                    <th>部门</th>
                    <th>操作</th>
                </tr>
                <%--@elvariable id="pageInfo" type="javafx.collections.ListChangeListener.Change"--%>
                <c:forEach items="${pageInfo.list}" var="emp">
                    <tr>
                        <td>
                            ${emp.empId}
                        </td>
                        <td>
                            ${emp.empName}
                        </td>
                        <td>
                            ${emp.gender=="0"?"男":"女"}
                        </td>
                        <td>
                            ${emp.email}
                        </td>
                        <td>
                            ${emp.department.deptName}
                        </td>
                        <td>
                            <button class="btn btn-info btn-sm">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                编辑
                            </button>
                            <button class="btn btn-danger btn-sm">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                删除
                            </button>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>
    <!-- 分页信息 -->
    <div class="row">
        <!--分页文字信息  -->
        <div class="col-md-6">当前 ${pageInfo.pageNum }页,总${pageInfo.pages }
            页,总 ${pageInfo.total } 条记录</div>
        <!-- 分页条信息 -->
        <div class="col-md-6">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li><a href="${APP_PATH }/emps?pn=1">首页</a></li>
                    <c:if test="${pageInfo.hasPreviousPage }">
                        <li><a href="${APP_PATH }/emps?pn=${pageInfo.pageNum-1}"
                               aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
                        </a></li>
                    </c:if>

                    <c:forEach items="${pageInfo.navigatepageNums }" var="page_Num">
                        <c:if test="${page_Num == pageInfo.pageNum }">
                            <li class="active"><a href="#">${page_Num }</a></li>
                        </c:if>
                        <c:if test="${page_Num != pageInfo.pageNum }">
                            <li><a href="${APP_PATH }/emps?pn=${page_Num }">${page_Num }</a></li>
                        </c:if>

                    </c:forEach>
                    <c:if test="${pageInfo.hasNextPage }">
                        <li><a href="${APP_PATH }/emps?pn=${pageInfo.pageNum+1 }"
                               aria-label="Next"> <span aria-hidden="true">&raquo;</span>
                        </a></li>
                    </c:if>
                    <li><a href="${APP_PATH }/emps?pn=${pageInfo.pages}">末页</a></li>
                </ul>
            </nav>
        </div>
    </div>
</div>

</body>
</html>
