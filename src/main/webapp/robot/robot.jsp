<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/6/21
  Time: 2:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>智能客服</title>
</head>
<body>

<link rel="stylesheet" href="<%=path+"/robot/css/layui.css"%>">
<script src="<%=path+"/robot/layui.js"%>"></script>
<script src="<%=path+"/robot/robot.js"%>"></script>
</body>
</html>
