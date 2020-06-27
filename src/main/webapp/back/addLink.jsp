<%--
  Created by IntelliJ IDEA.
  User: LiLang9725
  Date: 2020/6/16
  Time: 23:16
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
    <title>加友情链接</title>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <script src="https://www.layuicdn.com/layui/layui.js"></script>
    <link href="https://www.layuicdn.com/layui/css/layui.css" rel="stylesheet" type="text/css"/>
    <script src="<%=path+"/static/custom_tool.js"%>"></script>
</head>
    <body>
        <br>
        <input hidden value="<%=request.getParameter("lkId")%>" id="lkId">
        <div style="text-align: center;">
            <label style="color: coral;font-size: 20px">增加友情链接</label>
            <br><hr>
        </div>
        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="<%=path+"/back/links.jsp"%>" class="layui-btn layui-btn-normal">&nbsp;返&nbsp;&nbsp;回&nbsp;</a>
            <input hidden="hidden" value="<%=path%>" id="path">
            <div style="margin: 3% 10% 0 10%">
                <div class="layui-form-item">
                    <label class="layui-form-label">链接标签</label>
                    <div class="layui-input-block">
                        <input type="text" value="" name="lkName" placeholder="请输入链接标签" class="layui-input">
                        <label style="color: red;"></label>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">跳转路径</label>
                    <div class="layui-input-block">
                        <input type="text" value="" name="lkUrl" lay-verify="title" placeholder="请输入跳转路径" class="layui-input">
                    </div>
                </div>
<%--                <div class="form-group radio-form layui-form-item" id="test1" onclick="" type="button" style="width: 50%;">--%>
<%--                    <label skip="true" class="layui-form-label">链接图标</label>--%>
<%--                    <div class="user-type-conatiner layui-upload" style="background-color: coral;">--%>
<%--                        <div class="user-type active layui-upload-list" ref="radioWrap" data-index="0"--%>
<%--                             style="width: 60%">--%>
<%--                            <img src="" class="layui-upload-img" id="demo1"--%>
<%--                                 property="" alt="点击此区域选择">--%>
<%--                            <p id="demoText"></p>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
                <div class="layui-form-item">
                    <label class="layui-form-label">图标路径</label>
                    <div class="layui-input-block">
                        <input type="text" value="" name="lkPic" lay-verify="title"
                               placeholder="请输入该链接的图标路径" class="layui-input">
                    </div>
                </div>
                <div style="text-align: center;">
                    <input type="button" class="layui-btn layui-btn-normal" value="&nbsp;提&nbsp;&nbsp;
                    交&nbsp;" onclick="Present()">
                </div>
            </div>
        </div>
    <script>
        let path = $("#path").val();
        let lkPic = "";
        // Layui_uploadImage("#test1",path+'/upImage',$('#demo1'),function (mag) {
        //     lkPic = mag.fPath;//将上传的路径修改到显示框架里
        //     },$('#demoText'));//上传图片
        function Present() {
            let isOut = false;
            let data = "";
            let lkUrl = $("input[name='lkUrl']").val();
            let lkName = $("input[name='lkName']").val();
            let lkPic = $("input[name='lkPic']").val();
            if (lkName == ""){alert("链接标签不能为空！");return;}
            else {data += "lkName="+lkName;isOut = true;}
            if (lkUrl == ""){alert("链接路径不能为空！");return;}
            else {data += "&lkUrl="+lkUrl;isOut = true;}
            if (lkPic == ""){alert("链接图像不能为空！");return;}
            else {data += "&lkPic="+lkPic;isOut = true;}
            if (isOut){
                alert("adfasdf")
                AjaxTransfer(path+"/link/addLink",data,function (mag) {
                    if (mag.code > 0){
                        if (confirm(mag.msg)){
                            skipPage(path+"/back/links.jsp");
                        }
                    } else {
                        if (confirm(mag.msg)){
                            skipPage(path+"/back/links.jsp");
                        }
                    }
                });
            } else {
                alert("你的数据并未修改");
            }
        }
    </script>
    </body>
</html>
