<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/15
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <!-- 引入jquery -->
    <script src="${APP_PATH}/static/js/jquery-3.3.1.js" type="javascript"></script>
    <link rel="stylesheet" href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js" type="javascript"></script>

</head>
<body>
<h1>这里是导航栏</h1>
<form class="form-horizontal center-block">
    <div class="form-group">
        <label for="inputUserName" class="col-md-2 col-md-offset-3 control-label">UserName</label>
        <div class="col-md-2">
            <input type="text" class="form-control" id="inputUserName" placeholder="UserName">
        </div>
    </div>
    <div class="form-group">
        <label for="inputPassword" class="col-md-2 col-md-offset-3 control-label">Password</label>
        <div class="col-md-2">
            <input type="password" class="form-control" id="inputPassword" placeholder="Password">
        </div>
    </div>
    <div class="form-group">
        <div class="col-md-2 col-md-offset-5 ">
            <div class="checkbox">
                <label>
                    <input type="checkbox"> Remember me
                </label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-md-offset-5 col-md-4">
            <button type="submit" class="btn btn-default ">Sign in</button>
            <button type="submit" class="btn btn-default ">Sign up</button>
        </div>
    </div>
</form>
</body>
</html>
