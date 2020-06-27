<%--
  Created by IntelliJ IDEA.
  User: LiLang9725
  Date: 2020/6/12
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    int aId = 0;
    try{ aId = Integer.valueOf(request.getSession().getAttribute("aId").toString()); } catch (Exception e){}
%>
<html>
<head>
    <meta charset="utf-8">
    <title>教练信息</title>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <script src="https://www.layuicdn.com/layui/layui.js"></script>
    <link href="https://www.layuicdn.com/layui/css/layui.css" rel="stylesheet" type="text/css"/>
    <script src="<%=path+"/static/custom_tool.js"%>"></script>
</head>
<body style="background-color: peachpuff">
<div>
    <input hidden="hidden" value="<%=path%>" id="path">
    <input hidden="hidden" value="<%=aId%>" id="aId">
    <input hidden="hidden" value="${teacher.TPhone}" id="tPhone">
    <%
        request.getSession().setAttribute("userHome",path+"/teacherController/teacherInit");
    %>
    <%--教练信息--%>
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <div class="layui-logo">${teacher.SName}</div>
            <!-- 头部区域（可配合layui已有的水平导航） -->
            <ul class="layui-nav layui-layout-left">
                  <a href="javascript:;">教练信息</a>--%>
<%--                    <dl class="layui-nav-child">--%>
<%--                        <dd><a href="">驾校基础信息</a></dd>--%>
<%--                        <dd><a href="">学员评价</a></dd>--%>
<%--                    </dl>--%>
<%--                </li>--%>
            </ul>
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                        ${teacher.TName}
                    </a>
<%--                    <dl class="layui-nav-child">--%>
<%--                        <dd><a href="">基本资料</a></dd>--%>
<%--                        <dd><a href="">安全设置</a></dd>--%>
<%--                    </dl>--%>


        <div class="layui-side layui-bg-black">
            <div class="layui-side-scroll">
                <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
                <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                    <li class="layui-nav-item layui-nav-itemed">
                        <a class="" href="javascript:;">学生管理</a>
                        <dl class="layui-nav-child">
                            <dd><a href="<%=path+"/pages/teacher/look_tudents.jsp"%>" target="iframe_div_iframe">查看学生</a></dd>
                            <dd><a href="<%=path+"/pages/teacher/review-comments.jsp"%>" target="iframe_div_iframe">学生评价</a></dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;">修改信息</a>
                        <dl class="layui-nav-child">
                            <dd><a href="<%=path+"/pages/teacher/teacher_message.jsp"%>" target="iframe_div_iframe">修改联系方式</a></dd>
                            <dd><a href="<%=path+"/pages/teacher/teacher_password.jsp"%>" target="iframe_div_iframe">修改密码</a></dd>
                        </dl>
                    </li>
                </ul>
            </div>
        </div>
        <div class="layui-body">
            <iframe id="iframe_div" style="width: 100%;height: 100%;" name="iframe_div_iframe" src=" " height="100%" width="100%" ></iframe>
        </div>
    </div>
</div>
<script>
    layui.use('element', function(){
        var element = layui.element;
    });
</script>
</body>
</html>
