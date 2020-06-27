<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/6/16
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.lsjbc.vdtts.utils.Tool" %>
<%
    String path = request.getContextPath();
    String today = new Tool().getDate("yyyy年MM月dd日");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>学教专区</title>
    <link rel="stylesheet" href="https://www.layuicdn.com/layui-v2.5.6/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=path+"/css/pages/index/common.css"%>">
    <link rel="shortcut icon" type="image/x-icon" href="http://47.96.140.98:20034/static/img/logo_favicon.ico">

    <link rel="stylesheet" href="<%=path+"/css/pages/index/style.css"%>">
</head>
<body>
<input type="hidden" id="zjh_msg" value="${zjh_msg}">
<div class="login-inf">
    <div class="inf-box">
        <div class="inf-time">
            今天是<%=today%>
        </div>
        <div class="inf-login" id="studentName">
            <c:if test="${sessionScope.student == null }">
                <a target="_blank" href="<%=path+"/back/adminlogin.jsp"%>">管理登录</a> |
                <a target="_blank" href="<%=path+"/transfer?logo=institutionLogin"%>">机构登录</a> |
                <a href="<%=path+"/student"%>">学员登录</a> |
                <a target="_blank" href="<%=path+"/transfer?logo=schoolIn"%>">驾校入驻</a>
            </c:if>
            <c:if test="${sessionScope.student != null }">
                <a href="<%=path+"/student"%>" id="studentName">欢迎您！ 学员: ${sessionScope.student.SName}</a>
                <a href="<%=path+"/transfer?logo=alterpass"%>">修改信息</a>
                <a href="<%=path+"/logout/student"%>">退出</a>
            </c:if>
        </div>
    </div>
</div>
<div class="top">
    <div class="top-box">
        <img class="top-logo" src="<%=path+"/image/pages/index/psp-logo.png"%>">
        <div class="top-title">
            <p class="top-title-p1">机动车驾驶员计时培训系统</p>
            <p class="top-title-p2">Timing training system for motor vehicle drivers</p>
        </div>
        <form id="searchSchoolOrTeacher" action="<%=path+"/inquire"%>" method="post" class="top-search">
            <select name="type">
                <option value="school">驾培机构</option>
                <option value="teacher">教练员</option>
            </select>
            <input type="text" name="name">
            <label>
                <a style="cursor: pointer;" onclick="document:searchSchoolOrTeacher.submit()">
                    <img src="<%=path + "/image/pages/index/search.png"%>">搜索
                </a>
            </label>
        </form>
    </div>
</div>
<div class="menu">
    <div class="menu-box">
        <ul id="menu-title" class="menu-title menu-title-bg">
            <li id="menu-title-one">
                <img src="<%=path+"/image/pages/index/menu_home1.png"%>">
                <a href="<%=path+"/index"%>">首页</a>
            </li>
            <li id="menu-title-two">
                <img src="<%=path+"/image/pages/index/menu_publicity1.png"%>">
                <a href="<%=path+"/publicity/notice/1/-1"%>">公开公示</a>
            </li>
            <li id="menu-title-three" style="display: block;cursor:hand;">
                <form id="jumpToInquire" action="<%=path+"/inquire"%>" method="post">
                    <img src="<%=path+"/image/pages/index/menu_inquire1.png"%>">
                    <a onclick="document:jumpToInquire.submit()">信息查询</a>
                </form>
            </li>
            <li id="menu-title-six" class="layui-this menu-title-bg">
                <img src="<%=path+"/image/pages/index/menu_service1.png"%>">
                <a href="<%=path+"/student"%>">学教专区</a>
            </li>
            <li id="menu-title-seven">
                <img src="<%=path+"/image/pages/index/menu_student1.png"%>">
                <a target="_blank" href="<%=path+"/robot"%>">智能客服</a>
            </li>
        </ul>
    </div>
</div>
<div id="share">
    <a id="totop" title="返回顶部" style="display: none;">返回顶部</a>
    <a href="javascript:void(0)" title="返回上一页" class="sina" onclick="history.go(-1);"></a>
    <a href="javascript:void(0)" title="刷新" class="tencent" onclick="history.go(0);"></a>
</div>
<style>
    iframe{
        width: 100%;
        height: 100%;
    }
</style>
<div class="main">
    <iframe id="iframe" src="<%=path%>${iframeUrl}"></iframe>
</div>

<div class="footer">
    <div class="footer-box">
        <p class="footer-p" style="text-align: center;">友情链接</p>
        <div class="footer-friend">

            <c:forEach items="${linkList}" varStatus="item" var="link">
                <c:if test="${item.index % 5 == 0}">
                    <br><br>
                </c:if>
                <a target="_blank" href="${link.lkUrl}">
                    <img class="footer-img" src='<%=path%>${link.lkPic}'>
                </a>
            </c:forEach>

        </div>
    </div>

</div>
<div class="footer-inf">
    <ul style="text-align: center;display: table;">
        <li style="margin: 0 60px 0 0px;">
            <a href="javascript:void(0);">版权所有：传一科技</a>
        </li>
        <li style="margin: 0 60px 0 0px;">
            <a href="javascript:void(0);">技术支持：传一科技</a>
        </li>
    </ul>
</div>
<script src="<%=path+"/js/pages/index/jquery.min.js"%>"></script>
<script src="<%=path+"/js/pages/index/auto_area.js"%>"></script>
<script src="https://www.layuicdn.com/layui/layui.js"></script>
<script src="<%=path+"/js/pages/index/common.js"%>"></script>
<script src="<%=path+"/js/pages/index/commonpage.js"%>"></script>
<script>
    layui.use(['layer'],function () {
        let $ = layui.$;
        let layer = layui.layer;

        let msg = $("#zjh_msg").val();
        if(msg.length>0){
            layer.msg(msg);
        }

    });
</script>
</body>
</html>