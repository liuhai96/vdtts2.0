<%--
  Created by IntelliJ IDEA.
  User: LiLang9725
  Date: 2020/6/21
  Time: 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <title>人脸识别</title>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <script src="https://www.layuicdn.com/layui/layui.js"></script>
    <link href="https://www.layuicdn.com/layui/css/layui.css" rel="stylesheet" type="text/css"/>
    <%--调用摄像头--%>
    <script type="text/javascript" src=<%=path + "/static/jqueryFaceDetection/camera.js"%>></script>
    <%--人脸检测--%>
    <script type="text/javascript" src=<%=path + "/static/jqueryFaceDetection/cascade.js"%>></script>
    <script type="text/javascript" src=<%=path + "/static/jqueryFaceDetection/ccv.js"%>></script>
    <script type="text/javascript" src=<%=path + "/static/jqueryFaceDetection/jquery.facedetection.js"%>></script>
    <script type="text/javascript" src="<%=path+"/static/custom_tool.js"%>"></script>
</head>
<body style="text-align: center;">
<input hidden="hidden" value="<%=path%>" id="path">
    <div style="text-align: start">
        &nbsp;&nbsp;<a onclick="history.go(-1)" style="font-size: 20px" href="javascript:void(0);">返回</a>
        <div class="alone-buy layui-btn-container" style="text-align: center;">
            <button id="openCamera" type="button" class="layui-btn" style="position: relative;" >开启摄像头</button>
            <button type="button" class="layui-btn" onclick="skipPage('')">关闭摄像头</button>
        </div>
    </div>
    <label style="font-size: 20px;color: coral">人脸识别登录</label><br>
    <div style="display: none;">
        <div id="videoDiv" style="">
            <video id="video" ></video>
        </div>
        <img id="image">
        <form id="userInCameraForm" action="<%=path+"/lookFace"%>" method="post">
            <input id="faceImg" type="hidden" name="base64">
<%--            <input hidden name="sId" value="5">--%>
        </form>
    </div>
    <div class="layui-main alone-items">
        <div class="methodContent">
            <div id="cameraDiv">
                <canvas id="myCanvas" style="display: block;margin: auto;background-color: black" width="500px" height=350px"></canvas>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        //人脸识别js
        var layer;
        var form;
        var table;
        var laydate;
        layui.use(['layer','upload'], function () {
            var $ = layui.$;
            layer = layui.layer;
            upload=layui.upload;

            $("#iframe",window.parent.document).attr("style","height:"+Number(document.body.scrollHeight)+"px;width:100%;");

            $("#openCamera").on("click",function () {
                var clock2=setInterval(function () {
                    $('#image').faceDetection({
                        complete: function (faces) {
                            if (faces.length == 0) { //说明没有检测到人脸
                                console.log("无人脸");
                            } else {
                                console.log("识别到人脸");
                                let base64 = $('#image').attr("src");
                                $("#faceImg").attr("value",base64);
                                $("#userInCameraForm").submit();
                            }
                        },
                        error: function (code, message) {
                            console.log("complete回调函数出错");
                        }
                    });
                }, 5);
            });
        });
    </script>
</body>
</html>
