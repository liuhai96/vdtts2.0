<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/6/15
  Time: 23:01
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

    <meta name="viewport"  content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>教练员详情</title>
    <link rel="stylesheet" href="https://www.layuicdn.com/layui-v2.5.6/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=path+"/css/pages/index/common.css"%>">

    <link rel="stylesheet" href="<%=path+"/css/pages/index/inquireCoachDetails.css"%>">
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
    <div class="inq-coach">
        <img src="<%=path+"/image/pages/index/53461368581418814_23418.jpg"%>">
        <div class="coach-inf">
            <p><span>${name}</span></p>
            <p>
                <b>全国统一编号：</b>
                <span id="teacherId">${tid}</span>
                <b style="margin-left: 40px;">证件号码：</b>
                <span>${id}</span>
            </p>
            <p>
                <b>性别：</b>
                <span>${sex}</span>
                <b style="margin-left: 214px;">年龄：</b>
                <span>${age}</span>岁
            </p>
            <p>
                <b>任职驾培机构：</b>
                <span id="shortname">${school}</span>
                <b style="margin-left: 182px;">年带教学员数：</b>
                <span>${count}</span>人
            </p>
            <hr>
            <p>
                <b style="float: left;">综合星级：</b>
            </p>
            <div style="margin-top: 7px;">
                <div class="atar_Show">
                    <p class="atar_Show scoreStar" tip="${score}"></p>
                </div>
                <span style="font-size: 17px;color: #65B0F1;">${score}</span>
            </div>
	        <div class="site-demo-button" id="layerDemo" style="margin-bottom: 0; text-align: center">
		        <button data-method="notice" data-type="auto" class="layui-btn">学生报名</button>
	        </div>
            <p></p>
        </div>
    </div>
    <div class="coach-stu-evaluate">
        <textarea title="评价模板" id="evaluate_tpl" style="display:none;">
            {{# layui.each(d.data, function(index, item){ }}
            <div>
            {{= item.econtent }}<br>
            {{= item.etime }}
                <div style="float: right;">
                    <div class="atar_Show">
                        <p class="atar_Show scoreStar" tip="{{ item.escore }}"></p>
                    </div>
                    <span style="font-size: 17px;color: #65B0F1;">{{ item.escore }}分</span>
                </div>
            </div>
            <hr>
            {{# }); }}
        </textarea>
        <p>学员评价</p>
        <hr>
        <div id="evaluateDiv">
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
<script src="https://www.layuicdn.com/layui/layui.js"></script>
<script src="<%=path+"/js/pages/index/City_data.js"%>"></script>
<script src="<%=path+"/js/pages/index/areadata.js"%>"></script>
<script src="<%=path+"/js/pages/index/auto_area.js"%>"></script>
<script src="<%=path+"/js/pages/index/common.js"%>"></script>
<script src="<%=path+"/js/pages/index/commonpage.js"%>"></script>

<SCRIPT src="<%=path+"/js/pages/index/inquireSchDetails.js"%>"></SCRIPT>
<script>
    layui.use(['laytpl', 'flow','layer'], function () {
        var laytpl = layui.laytpl
            , $ = layui.$
            , flow = layui.flow
	        , layer = layui.layer;


        let path = window.document.location.href.substring(0, (window.document.location.href).indexOf(window.document.location.pathname));


        function showStar() {
            let stars = $("p[class*='scoreStar']");

            for (let i = 0; i < stars.length; i++) {
                let score = $(stars[i]).attr("tip");
                let starWidth = score / 5 * 150;
                $(stars[i]).attr("style", "width:" + starWidth + "px;float:none;");
            }
        }

        showStar();

        //请求消息
        let renderMsg = function (page, callback) {
            $.get(path + "/api/evaluate/teacher/" + $("#teacherId").html(), {
                page: page || 1
            }, function (res) {
                if (res.code != 0) {
                    return layer.msg(res.msg);
                }

                callback && callback(res.data, res.pages);
            });
        };

        flow.load({
            elem: '#evaluateDiv' //流加载容器
            , isAuto: true
            , end: "没有更多评价了"
            , done: function (page, next) { //加载下一页
                renderMsg(page, function (data, pages) {
                    let html = laytpl(evaluate_tpl.value).render({
                        data: data
                        , page: page
                    });

                    next(html, page < pages);

                    showStar();
                })
            }
        });

	    $('.layui-btn').on('click', function () {
		    var othis = $(this), method = othis.data('method');
		    active[method] ? active[method].call(this, othis) : '';
	    });

	    var active = {
		    notice: function () {
			    var index = layer.open({
				    type: 1
				    ,
				    title: '学员报名'
				    ,
				    closeBtn: true
				    ,
				    area: '400px;'
				    ,
				    shade: 0.8
				    ,
				    id: 'LAY_layuipro'
				    ,
				    btn: ['立即报名', '残忍拒绝']
				    ,
				    btnAlign: 'c'
				    ,
				    moveType: 1
				    ,
				    offset: ['100px', '300px']
                    ,
                    content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">确认报名该教练？ </div>'
        //             //         '欢迎报名该教练！' +
        //             //         '<br>姓名<br><input type="text" name="sName" id="sName" placeholder="请输入姓名" class="layui-input" >' +
        //             //         '<br>身份证<br><input type="text" name="sSfz" id="sSfz" placeholder="请输入身份证" class="layui-input" > ' +
        //             //         '</div>'
				    ,
				    yes: function (index, layer) {
                        // let sName = $("#sName").val();
                        let teacherId = $("#teacherId").html();
                        // let sSfz = $("#sSfz").val();
                        $.ajax({
                            type: 'get',
                            url: '/teacherController/checksSfz',
                            dataType: 'JSON',
                            data: {
                            //     sName: sName
                            //     , sSfz: sSfz
                            //     ,
                                    teacherId:teacherId
                            },
                            success: function (remsg) {
                                console.log(remsg);
                                alert(remsg.msg);
	                            layer.close(index);
                            }
                        })
                    }
                })
            }
        }
    });

</script>


</body>
</html>
