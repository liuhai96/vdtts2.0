<%@ page import="com.lsjbc.vdtts.utils.Tool" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/6/11
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String today = new Tool().getDate("yyyy年MM月dd日");
%>
<!-- saved from url=(0035)http://47.96.140.98:20034/publicity -->
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>公开公示</title>
    <link rel="stylesheet" href="https://www.layuicdn.com/layui-v2.5.6/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=path+"/css/pages/index/common.css"%>">
    <link rel="shortcut icon" type="image/x-icon" href="http://47.96.140.98:20034/static/img/logo_favicon.ico">

    <link rel="stylesheet" href="<%=path+"/css/pages/index/public_main.css"%>">
    <link rel="stylesheet" href="<%=path+"/css/pages/index/publicity_tablelist.css"%>">
    <script src="<%=path+"/js/pages/index/WdatePicker.js"%>"></script>
    <link rel="stylesheet" href="<%=path+"/css/pages/index/WdatePicker.css"%>">
</head>
<body>
<input type="hidden" id="customType" value="${type}">
<input type="hidden" id="customPage" value="${page}">
<input type="hidden" id="customNoticeId" value="${noticeId}">
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
        <form id="searchSchoolOrTeacher" action="<%=path+"/../../../../../../inquire"%>" method="post" class="top-search">
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
            <li id="menu-title-two" class="menu-title-bg layui-this">
                <img src="<%=path+"/image/pages/index/menu_publicity1.png"%>">
                <a href="<%=path+"/publicity/notice/1/-1"%>">公开公示</a>
            </li>
            <li id="menu-title-three" style="display: block;cursor:hand;">
                <form id="jumpToInquire" action="<%=path+"/../../../../../../inquire"%>" method="post">
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
    <a href="javascript:viod(0)" title="返回上一页" class="sina" onclick="history.go(-1);"></a>
    <a href="javascript:viod(0)" title="刷新" class="tencent" onclick="history.go(0);"></a>
</div>
<div class="main">
    <div class="layui-tab" lay-filter="tz">
        <ul class="layui-tab-title" id="selectParent">
            <c:if test="${type == 'notice'}">
                <li id="selectOne" class="layui-this" lay-id="1" onclick="noticeInfoJump();">通知公告</li>
                <li id="selectThree" lay-id="3" onclick="policyInfoJump();">政策法规</li>
            </c:if>
            <c:if test="${type == 'law'}">
                <li id="selectOne" lay-id="1" onclick="noticeInfoJump();">通知公告</li>
                <li id="selectThree" class="layui-this" lay-id="3" onclick="policyInfoJump();">政策法规</li>
            </c:if>
        </ul>


        <textarea title="消息模版" id="LAY_tpl" style="display:none;">
                {{# layui.each(d.data, function(index, item){ }}
                    <tr>
                        <td><span class="table-style-order">{{ index+1 }}</span></td>
                        <td style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 450px;display: block;text-align: left;"
                            title="{{ item.nname }}"><a onclick="publicNotice('{{ item.nid }}');"
                                                        style="cursor: pointer;">{{ item.nname }}</a></td>
                        <td>{{ item.ntime }}</td>
                        <td></td>
                    </tr>
                {{# }); }}
            </textarea>


        <span class="pub-title"><img src="<%=path+"/image/pages/index/publicity.png"%>">公开公示</span>
        <c:if test="${noticeId <= 0}">
        <div class="layui-tab-content pub-content" id="publicSelct" style="display: block;">
            </c:if>
            <c:if test="${noticeId > 0}">
            <div class="layui-tab-content pub-content" id="publicSelct" style="display: none;">
                </c:if>
                <div id="publicSelctOne" class="layui-tab-item layui-show">
                    <blockquote class="layui-elem-quote">
                        <div class="layui-inline">
                            <label class="layui-form-label">标题：</label>
                            <div class="layui-input-inline pub-inp-box">
                                <input id="selectTitle" type="text" class="layui-input pub-inp" placeholder="请输入标题">
                            </div>
                        </div>
                        <button class="layui-btn pub-but" id="selectNameBtn"><img
                                src="<%=path+"/image/pages/index/menu_inquire.png"%>">查询
                        </button>
                    </blockquote>
                    <div class="table-list">
                        <table class="layui-table" lay-skin="line" style="border-style: none;">
                            <colgroup>
                                <col width="50">
                                <col width="400">
                                <col width="150">
                                <col width="100">
                            </colgroup>
                            <thead>
                            <tr style="background: #e2eaf2;">
                                <th>序号</th>
                                <th>标题</th>
                                <th>发布日期</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody id="noticeInfoList"></tbody>
                        </table>
                        <div id="demo0" style="float: right;"></div>
                    </div>
                </div>
            </div>

            <c:if test="${noticeId <= 0}">
            <div class="layui-tab-content pub-content" id="detail" style="display: none;">
                </c:if>
                <c:if test="${noticeId > 0}">
                <div class="layui-tab-content pub-content" id="detail" style="display: block;">
                    </c:if>
                    <blockquote class="layui-elem-quote" style="background: #fff;">
                        <span id="publicTitle" style="font-size: 25px;line-height: 50px;">${notice.getNName()}</span>
                        <button class="layui-btn returnPrevHtml"
                                style="background: #fff;color: #333;float: right;height: 30px;line-height: 30px;">
                            <img src="<%=path+"/image/pages/index/arrows.png"%>" style="margin-right: 10px;">返回前一级
                        </button>
                    </blockquote>
                    <p>
                    <span id="b"><img src="<%=path+"/image/pages/index/date.png"%>"
                                      style="margin: 0 10px;padding-bottom: 4px;">
                        ${notice.getNTime()}
                    </span>
                    </p>
                    <hr style="clear:none ">
                    <div style="margin-top: 10px;text-indent: 2em;line-height: 25px;" id="context">
                        ${notice.getNContent()}
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

        <script src="<%=path+"/js/pages/index/publicity.js"%>"></script>


</body>
</html>
