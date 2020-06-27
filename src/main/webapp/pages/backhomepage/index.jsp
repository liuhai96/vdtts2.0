<html class="x-admin-sm">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
%>
<head>
    <meta charset="utf-8">
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=path%>/static/preadmin/component/layui/css/layui.css" />
    <link rel="stylesheet" href="<%=path%>/static/preadmin/admin/css/pearTab.css" />
    <link rel="stylesheet" href="<%=path%>/static/preadmin/admin/css/pearTheme.css" />
    <link rel="stylesheet" href="<%=path%>/static/preadmin/admin/css/pearLoad.css" />
    <link rel="stylesheet" href="<%=path%>/static/preadmin/admin/css/pearFrame.css" />
    <link rel="stylesheet" href="<%=path%>/static/preadmin/admin/css/pearAdmin.css" />
    <link rel="stylesheet" href="<%=path%>/static/preadmin/admin/css/pearNotice.css" />
    <link rel="stylesheet" href="<%=path%>/static/preadmin/admin/css/pearSocial.css" />
    <link rel="stylesheet" href="<%=path%>/static/preadmin/admin/css/pearMenu.css" />
    <style id="pearone-bg-color"></style>
</head>
<body class="layui-layout-body pear-admin">
<!-- 布局框架 -->
<input hidden id="path" value="<%=path%>">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <ul class="layui-nav layui-layout-left">
            <li class="collaspe layui-nav-item layui-hide-xs">
                <a href="#" class="layui-icon layui-icon-shrink-right"></a>
            </li>
            <li class="refresh layui-nav-item">
                <a href="#" class="layui-icon layui-icon-refresh-1"></a>
            </li>
        </ul>
        <div id="control" class="layui-layout-control"></div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item layui-hide-xs"><a href="#" class="fullScreen layui-icon layui-icon-screen-full"></a></li>
            <li class="layui-nav-item" lay-unselect="">
                <a href="javascript:;"><img src="<%=path%>${userHead}" class="layui-nav-img">${name}</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" onclick="Logout()">注销登陆</a></dd>
                </dl>
            </li>
            <li class="setting layui-nav-item"><a href="#" class="layui-icon layui-icon-more-vertical"></a></li>
        </ul>
    </div>
    <div class="layui-side layui-bg-black">
        <div class="layui-logo">
            <img class="logo" src="<%=path%>/static/preadmin/admin/images/logo.png" />
            <span class="title">
                <label style="color: darkorange;font-size: 25px;">
            <c:if test="${aType eq 'school'}">驾校</c:if>
            <c:if test="${aType eq 'teacher'}">教练</c:if>
            <c:if test="${aType eq 'manage'}">运营</c:if>
            管理端
        </label>
            </span>
        </div>
        <div class="layui-side-scroll">
            <div id="sideMenu"></div>
        </div>
    </div>
    <div class="layui-body">
        <div id="content"></div>
    </div>
</div>

<!-- 移动端 遮盖层 -->
<div class="pear-cover"></div>

<!-- 初始加载 动画-->
<div class="loader-main">
    <div class="loader"></div>
</div>


<!-- <div class="loader"></div> -->

<!-- 移动端 的 收缩适配 -->
<div class="collaspe pe-collaspe layui-hide-sm">
    <i class="layui-icon layui-icon-shrink-right"></i>
</div>

<script src="<%=path%>/static/preadmin/component/layui/layui.js"></script>
<script>
    function Logout(){//注销登录方法
        var $ = layui.jquery;
        if (confirm("你真的要注销登录吗？")>0){
            window.location.href = $("#path").val()+"/transfer?logo=exit";
        }
    }
    layui.use(['pearAdmin', 'jquery', 'layer', 'pearTab', 'pearNotice'], function() {
        var pearAdmin = layui.pearAdmin;
        var $ = layui.jquery;
        var pearTab = layui.pearTab;
        var pearNotice = layui.pearNotice;
        var layer = layui.layer;

        var config = {
            keepLoad: 2000, // 主 页 加 载 过 度 时 长 可为 false
            muiltTab: true, // 是 否 开 启 多 标 签 页 true 开启 false 关闭
            control: false, // 是 否 开 启 多 系 统 菜 单 true 开启 false 关闭
            theme: "dark-theme", // 默 认 主 题 样 式 dark-theme 默认主题 light-theme 亮主题
            index: '<%=path%>/pages/backhomepage/welcome.jsp', // 默 认 加 载 主 页
            data: '<%=path%>/frontMenuController/selectMenuList', // 菜 单 数 据 加 载 地 址
        };

        var option = {
            elem: 'headerNotice',
            url: '<%=path%>/static/preadmin/admin/data/notice.json',
            height: '250px',
            click: function(id, title) {
                layer.alert("点击消息 : " + id);
            }
        }


        pearAdmin.render(config);
        pearNotice.render(option);

    })
</script>
<!-- 新 增 百 度 统 计  ( 可 移 除 )-->
<script>

</script>

</body>
</html>