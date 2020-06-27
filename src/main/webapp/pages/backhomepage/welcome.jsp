<%--
  Created by IntelliJ IDEA.
  User: LiLang9725
  Date: 2020/6/26
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <script src="https://www.layuicdn.com/layui/layui.js"></script>
    <link href="https://www.layuicdn.com/layui/css/layui.css" rel="stylesheet" type="text/css"/>
</head>
<body style="background-image: url(<%=path%>/image/system/institution.jpg);background-size: 100%;">
<div>
    <input hidden="hidden" value="<%=path%>" id="path">
    <br><br><br><br><br><br>
    <div style="text-align: center;margin: auto;">
        <label style="font-size: 35px;color: cornflowerblue">&nbsp;欢&nbsp;迎&nbsp;登&nbsp;录&nbsp;</label>
        <br><br><br>
        <label style="color: darkorange;font-size: 28px;">欢迎使用
            <c:if test="${aType eq 'school'}">驾校</c:if>
            <c:if test="${aType eq 'teacher'}">教练</c:if>
            <c:if test="${aType eq 'manage'}">运营</c:if>管理端
        </label>

    </div>
</div>
</body>
</html>
