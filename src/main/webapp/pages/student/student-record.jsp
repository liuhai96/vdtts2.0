<%--
  Created by IntelliJ IDEA.
  User: LiLang9725
  Date: 2020/6/18
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String[] head = {"科目一成绩","科目二成绩","科目三成绩","科目四成绩"};
%>
<html>
<head>
    <meta charset="utf-8">
    <title>学员成绩</title>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <script src="https://www.layuicdn.com/layui/layui.js"></script>
    <link href="https://www.layuicdn.com/layui/css/layui.css" rel="stylesheet" type="text/css"/>
    <script src="<%=path+"/static/custom_tool.js"%>"></script>
</head>
    <body>
        <div>
            <div style="text-align: center;width: 80%;margin: 2% 10% 0 10%">
                <div class="layui-form">
                    <table class="layui-table">
                        <thead>
                        <tr>
                            <c:forEach items="<%=head%>" begin="0" step="1" end="10" var="h">
                                <th style="font-size: 20px;text-align: center;">${h}</th>
                            </c:forEach>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td style="text-align: center;">${data.data.examResult.erScore1}</td>
                            <td style="text-align: center;">${data.data.examResult.erScore2}</td>
                            <td style="text-align: center;">${data.data.examResult.erScore3}</td>
                            <td style="text-align: center;">${data.data.examResult.erScore4}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
