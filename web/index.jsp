<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/18
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="EmployeeTest" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<%String username = (String) request.getAttribute("username");%>
用户名：<%=username %><br>
</body>
</html>
