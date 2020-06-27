<%@ page import="com.lsjbc.vdtts.utils.Tool" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/6/11
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String today = new Tool().getDate("yyyy年MM月dd日");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>机动车驾驶员计时培训系统</title>
    <link rel="stylesheet" href="https://www.layuicdn.com/layui-v2.5.6/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=path+"/css/pages/index/common.css"%>">
    <link rel="shortcut icon" type="image/x-icon" href="http://47.96.140.98:20034/static/img/logo_favicon.ico">

    <link rel="stylesheet" href="<%=path+"/css/pages/index/style.css"%>">
    <link rel="stylesheet" href="<%=path+"/css/pages/index/home_main.css"%>">
</head>
<body>

<div class="login-inf">
    <div class="inf-box">
        <div class="inf-time">
            今天是<%=today%>
        </div>
        <div class="inf-login">
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
            <input id="searchSchool" type="text" name="name">
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
            <li id="menu-title-one" class="layui-this menu-title-bg">
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
            </li>
            <li id="menu-title-six">
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
<div class="main">
    <div class="content">
        <div class="layui-carousel" id="homeBox" lay-anim="" lay-indicator="inside" style="width: 380px; height: 280px;">
            <div id="industryTop" class="main table-inf-list" style="margin-bottom: 25px">
                <blockquote class="layui-elem-quote" style="width: 345px;">
                    <img src="<%=path+"/image/pages/index/square.png"%>">
                    <span>行业信息排名</span>
                </blockquote>
                <div class="inf-list list-style">
                    <blockquote class="layui-elem-quote bg-red-img" style="width: 345px;">
                        <img src="<%=path+"/image/pages/index/car.png"%>">驾校资源排名
                        <span id="vehUpdateTime"><img src="<%=path+"/image/pages/index/date1.png"%>">2020年6月11日</span>
                    </blockquote>
                    <table class="layui-table" lay-size="sm" lay-skin="line" style="width: 377px;">
                        <thead>
                            <tr>
                                <th>排名</th>
                                <th>驾培机构名称</th>
                                <th>教练车(辆)</th>
                            </tr>
                        </thead>
                        <tbody class="title-center" id="vehInfoOrder">
                            <c:forEach items="${schoolList}" varStatus="item" var="school">
                                <tr>
                                    <td>
                                        <c:if test="${item.index == 0}">
                                            <img src="<%=path+"/image/pages/index/first.png"%>">
                                        </c:if>
                                        <c:if test="${item.index == 1 || item.index == 2}">
                                            <img src="<%=path+"/image/pages/index/second.png"%>">
                                        </c:if>
                                    </td>
                                    <td title="${school.name}" class="searchSchool">${school.name}</td>
                                    <td>${school.count}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="layui-tab layui-tab-brief main-tab" lay-filter="docDemoTabBrief">
            <ul class="layui-tab-title">
                <li class="layui-this">通知公告</li>
                <li>政策法规</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <table class="layui-table" lay-skin="line">
                        <colgroup>
                            <col width="200">
                            <col width="100">
                            <col width="100">
                            <col>
                        </colgroup>
                        <thead>
                        <tr>
                            <th>标题</th>
                            <th>发布日期</th>
                            <th></th>
                        </tr>
                        </thead>
                            <tbody class="title-png" id="noticeList">
                                <c:forEach items="${noticeList}" var="nc">
                                    <tr>
                                        <td title="${nc.NName}"><a
                                                onclick="publicNotice('notice',1,${nc.NId});"
                                                style="cursor: pointer;color: #8ea8d8;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 450px;display: block;text-align: left;">${nc.NName}</a>
                                        </td>
                                        <td>${nc.NTime}</td>
                                        <td></td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td colspan="4"><a onclick="publicNotice('notice',1,-1);" style="cursor: pointer;">更多&gt;&gt;</a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="layui-tab-item">
                        <table class="layui-table" lay-skin="line">
                            <colgroup>
                                <col width="200">
                                <col width="100">
                                <col width="100">
                            </colgroup>
                            <thead>
                            <tr>
                                <th>标题</th>
                                <th>发布日期</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody class="title-png" id="policyInfoList">
                                <c:forEach items="${lawList}" var="nc">
                                    <tr>
                                        <td title="${nc.NName}"><a
                                                onclick="publicNotice('law',1,${nc.NId});"
                                                style="cursor: pointer;color: #8ea8d8;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 450px;display: block;text-align: left;">${nc.NName}</a>
                                        </td>
                                        <td>${nc.NTime}</td>
                                        <td></td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td colspan="4"><a onclick="publicNotice('law',1,-1);" style="cursor: pointer;">更多&gt;&gt;</a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
            </div>
        </div>


        <img src="<%=path+"/image/pages/index/home_logoJiangxi.jpg"%>" class="home-banner" width="1100px;">

        <img src="<%=path+"/image/pages/index/home_logo2.jpg"%>" class="home-banner">
    </div>
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
<script src="<%=path+"/js/pages/index/City_data.js"%>"></script>
<script src="<%=path+"/js/pages/index/areadata.js"%>"></script>
<script src="<%=path+"/js/pages/index/auto_area.js"%>"></script>
<script src="https://www.layuicdn.com/layui/layui.js"></script>
<script src="<%=path+"/js/pages/index/common.js"%>"></script>
<script src="<%=path+"/js/pages/index/commonpage.js"%>"></script>


<script src="<%=path+"/js/pages/index/index.js"%>"></script>

<script>
layui.use(['form'], function () {
    let $ = layui.$;

    $("td[class*='searchSchool']").on("click",function(event){
        $("#searchSchool").val($(this).html());
        $("select[name$='select']").val("school");
        $("#searchSchoolOrTeacher").submit();
    });
});
</script>

</body>
</html>
