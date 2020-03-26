<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/29
  Time: 1:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test JSon</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#textJson").click(function () {
            var url = this.href;
            var args = {};
            $.post(url, args, function (data) {
                for (var i = 0; i < data.length; i++) {
                    var id = data[i].id;
                    var empName = data[i].empName;
                    alert(id + ": " + empName);
                }
            });
        });
        return false;
    })
</script>
<body>
<a id="textJson" href="/mav/json">SessionEmployee</a>
</body>
</html>
