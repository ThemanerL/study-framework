<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/24
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>Title</title>
    <h4>Success!!</h4>
    time:${requestScope.time}
    <br/>

    param1:${requestScope.param1}
    <br/>
    RequestScope-Employee:${requestScope.employee}
    <br/>
    sessionScope-Employee:${sessionScope.employee}
    <br/>
</head>
<body>

</body>
</html>
