<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>员工列表</title>
    <!--
        不以/开始的相对路径，会以当前资源路径为基准寻找资源，容易出问题
        以/开始的相对路径，会以服务器的路径为基准寻找资源-->
    <%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
    System.out.println(request.getContextPath());
    %>
    <!-- 引入jquery -->
    <script src="${APP_PATH}/static/js/jquery-3.3.1.js"></script>
    <script src="${APP_PATH}/static/js/dataValidate-1.0.js"></script>
    <script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
</head>
<body>
<!-- 员工添加的模态框 -->
<div id="empAddModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">员工新增</h4>
            </div>
            <div class="modal-body">
                <form id="emp_new_form" class="form-horizontal">
                    <div class="form-group">
                        <label for="empName_add_input" class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-10">
                            <input type="text" name="empName" class="form-control" id="empName_add_input"
                                   placeholder="用户名可以是6-16位数字+字母，或者2-5个汉字">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_add_input" class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-10">
                            <input type="text" name="email" class="form-control" id="email_add_input"
                                   placeholder="SSM@gmail.com">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender1_add_input" value="1" checked="checked"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender2_add_input" value="0"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dept_add_select" class="col-sm-2 control-label">部门</label>
                        <div class="col-sm-4">
                            <select id="dept_add_select" name="dId" class="form-control">

                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 员工修改的模态框 -->
<div id="empUpdateModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">员工修改</h4>
            </div>
            <div class="modal-body">
                <form id="emp_update_form" class="form-horizontal">
                    <div class="form-group">
                        <label for="empName_update_show" class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-10">
                            <p class="form-control-static" id="empName_update_show">email@example.com</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_update_input" class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-10">
                            <input type="text" name="email" class="form-control" id="email_update_input"
                                   placeholder="SSM@gmail.com">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender1_update_input" value="1" checked="checked">
                                男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender2_update_input" value="0"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dept_update_select" class="col-sm-2 control-label">部门</label>
                        <div class="col-sm-4">
                            <select id="dept_update_select" name="dId" class="form-control">

                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="emp_update_btn">更新</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


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
            <button id="emp_add_modal_btn" class="btn btn-primary">新增</button>
            <button id="emp_delete_btn" class="btn btn-danger">删除</button>
        </div>
    </div>
    <!-- 表格中成列数据 -->
    <div class="row">
        <div class="col-md-12">
            <table id="emps_table" class="table">
                <thead>
                <tr>
                    <th><input type="checkbox" id="check_all"></th>
                    <th>编号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>邮箱</th>
                    <th>部门</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>
    <!-- 分页信息 -->
    <div class="row">
        <!--分页文字信息  -->
        <div id="page_info" class="col-md-6">
        </div>
        <!-- 分页条信息 -->
        <div id="page_nav" class="col-md-6">
        </div>
    </div>
</div>
<script type="text/javascript">

    /**
     * 能否提交，只有该值为true时才可以提交 -->
     */
    var submitCode = true;

    /**
     * 页面加载完成之后，直接去发送Ajax信息，获取分页数据
     */
    $(function () {
        toPage(1);
    });

    /**
     * 点击新增弹出模态框
     */
    $("#emp_add_modal_btn").click(function () {
        //请求部门信息，显示到部门下拉
        getDepts("#dept_add_select");
        //弹出模态框
        $("#empAddModal").modal('show');
    });

    /**
     * 点击保存，保存员工
     */
    $("#emp_save_btn").click(function () {
        if (!submitCode) {
            return;
        }
        $.ajax({
            url: "${APP_PATH}/emp",
            type: "POST",
            data: $("#emp_new_form").serialize(),
            success: function (result) {
                addMsg(result);
            }
        })
    });

    /**
     * 姓名值改变触发前端效验
     */
    $("#empName_add_input").change(function () {
        var reg = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
        lostFocus("#empName_add_input", reg, "用户名不合法！", "empName")
    });

    /**
     * 新增 email值改变触发前端效验
     */
    $("#email_add_input").change(function () {
        var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        lostFocus("#email_add_input", reg, "请检查邮箱格式！", "email")
    });

    /**
     * 更新 email值改变触发前端合法性检查效验，提交的时候再去后台查询邮箱是否被使用
     *
     */
    $("#email_update_input").change(function () {
        var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        var originValue = $("#email_update_input").val();
        if (!reg.test(originValue)) {
            submitCode = false;
            show_validate_msg("#email_update_input", "error", "请检查邮箱格式！", "email");
        }
    });

    /**
     * 员工列表
     */
    var empList = $("#emps_table");
    /**
     * 编辑按钮事件
     */
    empList.on("click", ".edit_btn", function () {
        // 1、查出部门信息
        getDepts("#dept_update_select");
        // 2、带出指定的员工信息
        getEmp($(this).attr("empId"));
        // 3、弹出模态框
        $("#empUpdateModal").modal('show');
        // 把编辑按钮的empId值传递给更新按钮
        $("#emp_update_btn").attr("empId", $(this).attr("empId"));
    });

    /**
     * 单个删除按钮事件
     */
    empList.on("click", ".delete_btn", function () {
        //1、弹出是否确认删除对话框
        var empName = $(this).parents("tr").find("td:eq(2)").text();
        if (confirm("确认删除【" + empName + "】吗？")) {
            //确认，发送ajax请求删除即可
            $.ajax({
                url: "${APP_PATH}/emp/" + $(this).attr("empId"),
                type: "DELETE",
                success: function (result) {
                    updateMsg(result);
                }
            })
        }
    });

    /**
     * 一个员工的更新提交按钮事件
     */
    $("#emp_update_btn").on("click", function () {
        if (!submitCode) {
            return;
        }
        $.ajax({
            url: "${APP_PATH}/emp/" + $(this).attr("empId"),
            type: "PUT",
            data: $("#emp_update_form").serialize(),
            success: function (result) {
                updateMsg(result);
            }
        })
    });

    $("#check_all").on("click", function () {
        $(".check_item").prop("checked", $(this).prop("checked"));
    });

    /**
     * 如果当前表格的选择框全部被选中，则全选按钮被选中
     */
    empList.on("click", ".check_item", function () {
        //判断当前表格中的checkBox是否被全部选中
        var flag = $(".check_item:checked").length === $(".check_item").length;
        $("#check_all").prop("checked", flag);
    });

    $("#emp_delete_btn").on("click", function () {
        if (confirm("确认一次删除这么这么多的数据吗！？")) {
            var empIds = [];
            $.each($(".check_item:checked"), function () {
                empIds.push($(this).parents("tr").find("td:eq(1)").text());
            });
            $.ajax({
                url: "${APP_PATH}/emps",
                type: "DELETE",
                data: JSON.stringify(empIds),
                contentType: "application/json;charset=utf-8",
                success: function (result) {
                    updateMsg(result);
                    $("#check_all").prop("checked", false);
                }
            })
        }
    });

</script>
</body>
</html>
