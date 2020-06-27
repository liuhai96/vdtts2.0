<%--
  Created by IntelliJ IDEA.
  User: LiLang9725
  Date: 2020/6/7
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String schoolName = "机动车驾驶员计时培训系统";//名称
    String path = request.getContextPath();
    int type = 1011010;
    try { type = Integer.parseInt(request.getParameter("type")); } catch (Exception e){}
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie:cookies)
        if(cookie.getName().equals("schoolName"))schoolName = cookie.getValue();
%>
<html>
<head>
    <meta charset="utf-8">
    <title>登录</title>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <script src="https://www.layuicdn.com/layui/layui.js"></script>
    <link href="https://www.layuicdn.com/layui/css/layui.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path+"/css/pages/index/login.css"%>" rel="stylesheet" type="text/css">
    <script src="<%=path+"/static/custom_tool.js"%>"></script>
    <style>
        .inputdiv{
            display:flex;border: 1px solid #D2D2D2!important;background-color: #fff;height: 38px;line-height: 38px;padding: 0px 19px;
            border-radius: 19px;
        }
        .layui-input {
            border-style: none;
        }
    </style>
</head>
    <body>
    <input hidden="hidden" value="<%=path%>" id="path">
    <p style="font-family: newword;color: #274472;font-size: 26px;text-align: center;height: 80px; margin-left: -230px;line-height: 110px;">
        欢迎登录驾驶培训公共服务平台！</p>
    <div class="main-box">
<%--        <form action="">--%>
            <p style="font-size: 18px;margin: 3% 0 3% 10%;">机构登录</p>
            <ul class="reg-box">
                <div class="inputdiv" style="width: 60%;margin: 0 0 0 18%">
                    <i class="layui-icon layui-icon-username"></i>
                    <input type="text" name="aAccount"
                           placeholder="请输入你的账号" class="layui-input" style="text-align:
                                center;background-color: #f3fdff;height: 100%">
                </div>
                <label id="idNotify" style="color:#ff0a29;text-align: left;margin: 0 0 0 18%"></label><br><br>
                <div class="inputdiv" style="width: 60%;margin: 0 0 0 18%">
                    <i class="layui-icon layui-icon-password"></i>
                    <input type="password" name="aPassword"
                           placeholder="请输入你的密码" class="layui-input" style="text-align:
                                center;background-color: #f3fdff;height: 100%;width: 80%">
                    <i class="layui-icon layui-icon-face-surprised" id="viewN"
                       onmousedown="PassView(true)" onmouseup="PassView(false)"></i>
                </div>
                <label id="passNotify" style="color:#ff0a29;text-align: left;margin: 0 0 0 18%;"></label><br><br>
                <li>
                    <div style="width: 300px;margin: 0 0 0 18%">
                        <input type="text" value="" placeholder="验证码" class="input-val sradd photokey" style="height: 35px;width: 25%;">
                        <canvas id="canvas" class="" style="height: 35px;background-color: ghostwhite; margin: 0 0 0 10%;width: 65%"></canvas>
                    </div><br>
                </li>
            </ul>
            <div class="sub space">
                <button class="btn layui-btn layui-btn-normal layui-btn-radius" id="dd" type="submit">登录</button>&nbsp;&nbsp;&nbsp;&nbsp;
                <br><br><br>
                <br><br>
                <button class="" id="cc"  style="display: block;" type="button">重置</button>&nbsp;&nbsp;&nbsp;&nbsp;
            </div><br><br>
            <div class="layui-layer-move"></div>
        </div>
        <script>
            function PassView(vie) {
                let aPassword = $("input[name = 'aPassword']");
                let viewN = $("#viewN");
                if (vie){
                    aPassword.attr("type","text");
                    viewN.attr("class","layui-icon layui-icon-face-smile");
                } else {
                    aPassword.attr("type","password");
                    viewN.attr("class","layui-icon layui-icon-face-surprised");
                }
            }
            function toService() {
                let aAccount = $("input[name = 'aAccount']").val();
                let idNotify = $("#idNotify");
                let aPassword = $("input[name = 'aPassword']").val();
                let passNotify = $("#passNotify");
                let isStop = false;

                //账号验证
                if (aAccount.length < 1) {idNotify.html("*请输入账号！");isStop = true;}
                else if (aAccount.length < 6) {idNotify.html("*账号长度应大于5位！");isStop = true;}
                else if (!done(aAccount,0,aAccount.length)){idNotify.html("*账号中含有法字符！");isStop = true;}
                else idNotify.html("");
                //密码验证
                if (aPassword.length < 1) {passNotify.html("*请输入密码！");isStop = true;}
                else if (aPassword.length < 6) {passNotify.html("*密码长度不得少于6位！");isStop = true;}
                else if (!done(aPassword,0,aPassword.length)){passNotify.html("*密码中含有法字符！");isStop = true;}
                else passNotify.html("");
                if (!isStop){
                    AjaxTransfer($("#path").val()+"/userLogin","aAccount="+aAccount+"&aPassword="+aPassword,function (mag) {
                        alert(mag.msg);//提示信息
                        skipAbsolute("/"+mag.data.url);//路径跳转路径
                    });
                }
            }
        </script>
    </body>
</html>
