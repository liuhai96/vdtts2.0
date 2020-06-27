<%@ page import="com.lsjbc.vdtts.utils.Tool" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/6/12
  Time: 10:26
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
    <title>信息查询</title>
    <link rel="stylesheet" href="https://www.layuicdn.com/layui-v2.5.6/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=path+"/css/pages/index/common.css"%>">
    <link rel="shortcut icon" type="image/x-icon" href="http://47.96.140.98:20034/static/img/logo_favicon.ico">

    <link rel="stylesheet" href="<%=path+"/css/pages/index/inquire.css"%>">
    <link rel="stylesheet" href="<%=path+"/css/pages/index/starScore.css"%>">
    <link id="layuicss-layer" rel="stylesheet" href="<%=path+"/css/pages/index/layer.css"%>">
</head>
<body style="background: #fafbfd;">
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
        <ul id="menu-title" class="menu-title">
            <li id="menu-title-one">
                <img src="<%=path+"/image/pages/index/menu_home1.png"%>">
                <a href="<%=path+"/index"%>">首页</a>
            </li>
            <li id="menu-title-two">
                <img src="<%=path+"/image/pages/index/menu_publicity1.png"%>">
                <a href="<%=path+"/publicity/notice/1/-1"%>">公开公示</a>
            </li>
            <li id="menu-title-three" class="layui-this menu-title-bg" style="display: block;cursor:hand;">
                <form id="jumpToInquire" action="<%=path+"/inquire"%>" method="post">
                    <img src="<%=path+"/image/pages/index/menu_inquire1.png"%>">
                    <a onclick="document:jumpToInquire.submit()">信息查询</a>
                </form>
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
    <div class="layui-tab">
        <ul id="selectParent" class="layui-tab-title" style="float: left;width: 150px;height: 200px;top: 45px;">
            <c:if test="${type == 'school' || type == null}">
                <li class="layui-this">查驾培机构</li>
                <li class="">查教练员</li>
            </c:if>
            <c:if test="${type == 'teacher'}">
                <li class="">查驾培机构</li>
                <li class="layui-this">查教练员</li>
            </c:if>
        </ul>
        <span class="inq-inf"><img src="<%=path+"/image/pages/index/inquires.png"%>">信息查询</span>
        <div id="tab-item-parent" class="layui-tab-content" style="margin-left: 165px;">
            <c:if test="${type == 'school' || type == null}">
            <div class="layui-tab-item layui-show">
                </c:if>
                <c:if test="${type == 'teacher'}">
                <div class="layui-tab-item">
                    </c:if>

                    <div class="inq-con">
                        <span class="title-con">查询条件</span>
                    </div>
                    <ul class="ul1">
                        <li>
                            <span class="title-area">驾校查询：</span>
                            <div class="layui-inline">
                                <input id="schoolName" type="text" class="layui-input pub-inp" placeholder="请输入驾校名"
                                       value="${schoolName}">
                            </div>

                            <button class="layui-btn" id="selectSchoolByName" style="background-color: #3c97ff">
                                <img style="margin-left: -10px;margin-top: -3px"
                                     src="<%=path+"/image/pages/index/menu_inquire.png"%>">&nbsp;&nbsp;搜索
                            </button>
                        </li>
                    </ul>
                        <div class="inq-school">
                    <textarea title="消息模版" id="SCHOOL_tpl" style="display:none;">
                            {{# layui.each(d.data, function(index, item){ }}
                            <li class="list-school">
                                <form action="<%=path+"/school"%>" method="post" id="schoolForm{{ item.id }}">
                                    <input type="hidden" name="schoolid" value="{{ item.id }}">
                                    <input type="hidden" name="score" value="{{ item.score }}">
                                    <input type="hidden" name="studentCount" value="{{ item.studentCount }}">
                                    <input type="hidden" name="teachercount" value="{{ item.teacherCount }}">
                                    <input type="hidden" name="carcount" value="{{ item.carCount }}">
                                    <a href="javascript:;" onclick="document:schoolForm{{ item.id }}.submit()">
                                        <img src="<%=path+"/image/pages/index/sch6.jpg"%>">
                                    </a>
                                    <div class="inf-school">
                                        <a href="javascript:;" onclick="document:schoolForm{{ item.id }}.submit()">
                                            <p class="word-1" title="{{ item.name }}">{{ item.name }}</p>
                                        </a>
                                        <p style="float: left;">综合评分：</p>
                                        <div class="atar_Show" style="display:inline;">
                                            <p class="scoreStar" tip="{{ item.score }}"></p>
                                        </div>
                                        <span>{{ item.score }}分</span>
                                        <p></p>
                                        <p>
                                            <span>教练员数：{{ item.teacherCount }}人</span>
                                            <span style="margin-left: 35px;">教练车数：{{ item.carCount }}台</span>
                                        </p>
                                        <p>
                                            <span>总学员数：{{ item.studentCount }}人</span>
                                        </p>
                                        <p title="{{ item.address }}"
                                           style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 275px;display: block;text-align: left;">地址：{{ item.address }}</p>
                                    </div>
                                </form>
                            </li>
                            {{# }); }}
                        </textarea>
                        <ul id="schoolList" style="width: 929px;">
                        </ul>
                        <hr>
                        <div id="demo0" style="float: right;">

                        </div>
                    </div>
                </div>

                <c:if test="${type == 'school' || type == null}">
                <div class="layui-tab-item">
                    </c:if>
                    <c:if test="${type == 'teacher'}">
                    <div class="layui-tab-item layui-show">
                        </c:if>
                        <div class="inq-con">
                            <span class="title-con">查询条件</span>
                        </div>
                        <ul class="ul1">
                            <li>
                                <span class="title-area">教练查询：</span>
                                <div class="layui-inline">
                                    <input class="layui-input" id="teacherName" placeholder="请输入教练姓名"
                                           value="${teacherName}">
                                </div>
                                <button id="selectTeacherByName" class="layui-btn" style="background-color: #3c97ff">
                                    <img style="margin-left: -10px;margin-top: -3px"
                                         src="<%=path+"/image/pages/index/menu_inquire.png"%>">&nbsp;&nbsp;搜索
                                </button>
                            </li>
                            <li id="coaSexParent">
                                <span class="title-area">性　　别：</span>
                                <button class="sexBtn" value="">不限</button>
                                <button class="sexBtn" value="男">男</button>
                                <button class="sexBtn" value="女">女</button>
                            </li>
                        </ul>
                        <div class="inq-student">
                    <textarea title="消息模版" id="TEACHER_tpl" style="display:none;">
                        {{# layui.each(d.data, function(index, item){ }}
                        <li class="list-student">
                            <form action="<%=path+"/teacher"%>" method="post" id="teacherForm{{ item.id }}">
                                <input type="hidden" name="teacherId" value="{{ item.id }}">
                                <input type="hidden" name="score" value="{{ item.score }}">
                                <input type="hidden" name="studentCount" value="{{ item.studentCount }}">
                                <input type="hidden" name="school" value="{{ item.schoolName }}">
                                <a href="javascript:;" onclick="document:teacherForm{{ item.id }}.submit()">
                                    <img width="142px;" height="191px;"
                                         src="<%=path+"/image/pages/index/53461368581418814_23418.jpg"%>">
                                </a>
                                <div class="inf-student">
                                    <a href="javascript:;" onclick="document:teacherForm{{ item.id }}.submit()">
                                        <p class="word-1">{{ item.name }}</p>
                                    </a>>
                                    <p style="float: left;">综合星级：</p>
                                    <div class="atar_Show" style="display:inline;">
                                        <p class="scoreStar" tip="{{ item.score }}"></p>
                                    </div>
                                    <span>{{ item.score }}分</span>
                                    <p></p>
                                    <p>
                                        <span>性别：{{ item.sex }}</span>
                                        <span style="margin-left: 45px;">年龄：{{ item.age }}岁</span>
                                    </p>
                                    <p><span>总学员数：{{ item.studentCount }}人</span></p>
                                    <p style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 275px;display: block;text-align: left;">所属驾培机构：{{ item.schoolName }}</p>
                                </div>
                            </form>
                        </li>
                        {{# }); }}
                    </textarea>
                            <ul id="teacherList" style="width: 929px;">
                            </ul>
                            <hr>
                            <div id="demo1" style="float: right;">

                            </div>
                        </div>
                    </div>
                </div>

            </div>
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


<script src="<%=path+"/js/pages/index/inquire.js"%>"></script>


</body>
</html>
