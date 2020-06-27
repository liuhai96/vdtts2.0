<%--
  Created by IntelliJ IDEA.
  User: LiLang9725
  Date: 2020/6/14
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    int i = 0;
%>
<html>
<head>
    <meta charset="utf-8">
    <title>查看学生评论</title>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <script src="https://www.layuicdn.com/layui/layui.js"></script>
    <link href="https://www.layuicdn.com/layui/css/layui.css" rel="stylesheet" type="text/css"/>
</head>
    <body>
        <div>
            <input hidden="hidden" value="<%=path%>" id="path">
            <div style="padding-top:40px;width:700px;margin:0 auto;">
                <h2 >全部评论</h2>
                <hr><br>
                <c:forEach items="${evaluate}" step="1" begin="0" end="1000" var="teacher_eva">
                    <input hidden class="ss">
                    <div class="comment">
                        <div style="float:left;"><img style="width:50px;height:50px;border-radius: 50%;"  src="<%=path+"/image/sch.jpg"%>"/></div>
                        <div style="float:left;">
                        <span style="margin-left:10px;color:#3D9EEA;font-size:15px;font-weight:
                         bolder;">匿名评论 </span>     <span>${teacher_eva.ETime}</span>
                            &nbsp;&nbsp;&nbsp;&nbsp;<div id="test<%=i%>">
                        </div>
                            <div style="margin-top:10px;font-size:16px;" >
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${teacher_eva.EContent}</div>
                            <br><br>
                        </div>
                        <hr>
                    </div>
                    <input hidden id="test-xl-<%=i%>" value="${teacher_eva.EScore}">
                    <%i++;%>
                </c:forEach>
            </div>
            <div style="margin:0 auto;clear: both;width:600px;height:40px;background: #F0F0F0;text-align: center;line-height: 40px;">
                没有更多评论了
            </div>
        </div>
        <script>
            layui.use(['rate'], function() {
                let x = 0
                $(".ss").each(function () {
                    var rate = layui.rate;//基础效果
                    rate.render({elem: '#test'+x
                        ,value: $("#test-xl-"+x).val(),readonly: true})
                    x++;
                });
            })
        </script>
    </body>
</html>
