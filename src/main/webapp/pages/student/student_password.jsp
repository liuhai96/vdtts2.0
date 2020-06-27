<%--
  Created by IntelliJ IDEA.
  User: LiLang9725
  Date: 2020/6/14
  Time: 0:47
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
    <meta charset="utf-8">
    <title>修改信息</title>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <script src="https://www.layuicdn.com/layui/layui.js"></script>
    <link href="https://www.layuicdn.com/layui/css/layui.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path+"/driving-in_files/main.css"%>" rel="stylesheet" type="text/css">
    <script src="<%=path+"/static/custom_tool.js"%>"></script>
	<link rel="stylesheet" href="https://www.layuicdn.com/layui-v2.5.6/css/layui.css" media="all">
	<link rel="stylesheet" href="<%=path+"/css/pages/index/common.css"%>">
	<link rel="shortcut icon" type="image/x-icon" href="http://47.96.140.98:20034/static/img/logo_favicon.ico">

	<link rel="stylesheet" href="<%=path+"/css/pages/index/style.css"%>">
	<link rel="stylesheet" href="<%=path+"/css/pages/index/home_main.css"%>">
<style>
	body{
		background:url("../../static/layui/images/photo/08.png");
		background-size: 100% 100%;
		background-repeat:no-repeat;
	}
</style>
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
				    <img src="<%=path+"/image/pages/index/menu_student1.png"%>">
				    <a href="<%=path+"/student"%>">学教专区</a>
			    </li>
		    </ul>
	    </div>
    </div>
    <div id="share">
	    <a id="totop" title="返回顶部" style="display: none;">返回顶部</a>
	    <a href="javascript:void(0)" title="返回上一页" class="sina" onclick="history.go(-1);"></a>
	    <a href="javascript:void(0)" title="刷新" class="tencent" onclick="history.go(0);"></a>
    </div>
    <div border="0">
        <button href="javascript:void(0)" onclick="AddFace()" class="layui-btn">人脸录入</button>
        <iframe src="<%=path+"/studentController/studentTransfer?logo=addFace"%>"
                id="addFace" height="0" width="0"></iframe>
    </div>
    <div class="main">
        <input hidden="hidden" value="<%=path%>" id="path">
        <input hidden="hidden" value="${aId}" id="aId"><br>
        <div style="text-align: center;">
            <br> <br>
            <div class="cooperate-form">
                <div class="form">
                    <div style="padding-left: 500px">
                    <div class="form-group radio-form" id="test10" onclick="" type="button" style="padding: auto">
                        <div class="user-type-conatiner layui-upload" style="height: 15%;width: 15% ;">
                            <div class="user-type active layui-upload-list" ref="radioWrap" data-index="0"
                                 style="height:150px ;width: 200px; margin: auto;border: 0px solid black;">
                                <b skip="true">上传头像</b>
                                <img src="" style="height: 100%;width: 100%" class="layui-upload-img" id="demo10"  property="" alt="点击上传头像">
                                <p id="demoText10"></p>
                            </div>
                        </div>
                    </div>
                    </div>
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
	                <form class="layui-form" action="" id="updatePwd" style="padding-left:400px">
		                <div class="layui-form-item">
			                <label class="layui-form-label">旧密码</label>
			                <div class="layui-input-inline">
				                <input type="password" name="oldPwd" required lay-verify="required" placeholder="请输入旧密码" autocomplete="off" class="layui-input">
			                </div>
		                </div>
		                <div class="layui-form-item">
			                <label class="layui-form-label">新密码</label>
			                <div class="layui-input-inline">
				                <input type="password" name="newPwd" required lay-verify="required" placeholder="新密码" autocomplete="off" class="layui-input">
			                </div>
		                </div>
		                <div class="layui-form-item">
			                <label class="layui-form-label">新密码</label>
			                <div class="layui-input-inline">
				                <input type="password" name="repeatPwd" required lay-verify="required" placeholder="请重新输入新密码" autocomplete="off" class="layui-input">
			                </div>
		                </div>
		                <div class="layui-form-item" style="padding-right: 500px">
			                <div class="layui-input-block">
<%--				                <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>--%>
				                <button class=" layui-btn layui-btn-warm" lay-submit lay-filter="formDemo">立即提交</button>
			                </div>
		                </div>
	                </form>
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">新手机号码:</label>
                            <div class="layui-input-inline">
                                <input id="phone" type="text" class="layui-input">
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">验证码:</label>
                            <div class="layui-input-inline">
                                <input id="code" type="text" class="layui-input">
                            </div>
                        </div>
                    </div>


                    </div>
                    <input id="codeBtn" class="layui-btn layui-btn-warm" data-send="true" type="button" value="获取验证码">
                    <button type="button" class="layui-btn layui-btn-warm" id="updatePhone">修改手机号</button>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript" src=<%=path+"/js/pages/index/jquery.min.js"%>></script>
    <script type="text/javascript" src=<%=path+"/js/pages/index/jquery.cookie.js"%>></script>
    <script type="text/javascript" src=<%=path+"/js/pages/index/60sCountdown.js"%>></script>
                </div>
            </div>
        </div>
    </div>
<%--    <div class="footer">--%>
<%--	    <div class="footer-box">--%>
<%--		    <p class="footer-p" style="text-align: center;">友情链接</p>--%>
<%--		    <div class="footer-friend">--%>
<%--			    <c:forEach items="${linkList}" varStatus="item" var="link">--%>
<%--				    <c:if test="${item.index % 5 == 0}">--%>
<%--					    <br><br>--%>
<%--				    </c:if>--%>
<%--				    <a target="_blank" href="${link.lkUrl}">--%>
<%--					    <img class="footer-img" src='<%=path%>${link.lkPic}'>--%>
<%--				    </a>--%>
<%--			    </c:forEach>--%>
<%--		    </div>--%>
<%--	    </div>--%>
<%--    </div>--%>
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
    <script>
        let isAdd = true;
        function AddFace() {//人脸录入方法启闭开关
            let addFace = $("#addFace");
            if (isAdd){
                addFace.attr("height","60%");
                addFace.attr("width","100%");
            } else{
                addFace.attr("height","0");
                addFace.attr("width","0");
            }
            isAdd = !isAdd;
        }

        let path = window.document.location.href.substring(0, (window.document.location.href).indexOf(window.document.location.pathname));

        $(document).on('click',"#codeBtn",function(){
            if(checkPhone()){
                $.cookie("total",5);
                timekeeping();
                $.get(path + "/api/sms/update/", {
                    phone: $("#phone").val()
                },function (res) {
                    layui.use('layer',function () {
                        let layer = layui.layer;

                        if(res.code!=0){
                            layer.msg(res.msg);
                        }
                    })
                });
                alert("模拟验证码为：000000     6个零")
            }
        });

        $(document).on("click","#updatePhone",function () {
            if($("#code").val().length==6){
                $.post(path + "/api/student/phone/", {
                    phone: $("#phone").val()
                    , code: $("#code").val()
                    , _method: "put"
                },function (res) {
                    alert(res.msg);
                });
            }else{
                alert("验证码不符合规范");
            }
        });

        function checkPhone() {
            let PhoneStr=$("#phone").val().trim();

            if(!/^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\d{8}$/.test(PhoneStr)){
                layer.msg("电话号码格式不正确,请重试");
                return false;
            }else{
                return true;
            }
        }

        let verify = -1;
        let passTypes = true;
        let sPic = "";//修改头像
        Layui_uploadImage("#test10",$("#path").val()+'/upImage',$('#demo10'),function (mag) {
        sPic = mag.fPath;},$('#demoText10'));//修改头像
        function verifyPass(){//旧密码验证
            AjaxTransfer($("#path").val()+"/verifyAdmin","aId="+$("#aId").val()+
                "&aPassword="+ $("#oldPass input").val(),function (mag) {
                if (mag.msg > 0);
                else {alert("验证密码失败！");}
                verify = mag.msg;
            });
        }

        layui.use(['form','layer'], function(){
	        var form = layui.form,layer = layui.layer;
	        var $ = layui.jquery;
	        //监听提交
	        form.on('submit(formDemo)', function(data){
		        var newPwd = $("input[name='newPwd']").val();
		        var repeatPwd = $("input[name='repeatPwd']").val();
		        var index = layer.confirm('您确定修改用户的基本信息？', {
			        btn: ["确定", "取消"],
			        btn2: function (index) {
				        layer.close(index);
				        layer.close(index1)
				        $('#updatePwd')[0].reset();//重置表单
				        form.render();
			        },
			        btn1: function () {
				        if (newPwd != repeatPwd) {
					        layer.msg("两次新密码输入不同请重新输入");
				        } else {
					        $.ajax({
						        type: 'POST',
						        url: '<%=path%>/studentController/updatestudentPwd',
						        dataType: 'JSON',
						        data:{
							        oldPwd:$("input[name='oldPwd']").val(),
							        newPwd:$("input[name='newPwd']").val(),
							        repeatPwd:$("input[name='repeatPwd']").val()
						        },
						        success: function (resmsg) {
							        if (resmsg.code == 1) {
								        layer.msg(resmsg.msg);
							        } else {
								        layer.msg(resmsg.msg);
								        layer.close(index);
							        }
							        $('#updatePwd')[0].reset();//重置表单
							        form.render();
						        }
					        });
				        }
			        }
		        });
		        return false;

	        });
        });
    </script>
    </body>
</html>
