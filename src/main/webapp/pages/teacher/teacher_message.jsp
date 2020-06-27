<%--
  Created by IntelliJ IDEA.
  User: LiLang9725
  Date: 2020/6/14
  Time: 0:40
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
    <title>教练信息查看</title>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <script src="https://www.layuicdn.com/layui/layui.js"></script>
    <link href="https://www.layuicdn.com/layui/css/layui.css" rel="stylesheet" type="text/css"/>
    <script src="<%=path+"/static/custom_tool.js"%>"></script>
</head>
    <body>
        <div>
            <input hidden="hidden" value="<%=path%>" id="path">
            <input hidden="hidden" value="${aId}" id="aId">
            <input hidden="hidden" value="${teacher.TPhone}" id="tPhone">
            <div style="text-align: center;">
                <br><br><br>
                <div>
                    <div class="layui-form-item" >
                        <div class="layui-inline" id="test1">
                            <div class="layui-input-inline">
                                <img src="${teacher.TPic}" id="demo1" height="200px" width="200px">
                            </div>
                            <input hidden id="tPic" value="${teacher.TPic}" >
                        </div>
                        <p id="demoText"></p>
                    </div>


                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">姓名：</label>
                            <div class="layui-input-inline">
                                <input disabled="disabled" type="text" class="layui-input" value="${teacher.TName}">
                            </div>
                            <label class="layui-form-label">性别：</label>
                            <div class="layui-input-inline">
                                <input disabled="disabled" type="text" class="layui-input" value="${teacher.TSex}">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">本月毕业学员上限:</label>
                            <div class="layui-input-inline">
                                <input disabled="disabled" type="text" class="layui-input" value="${teacher.TLimit}">
                            </div>
                            <label class="layui-form-label">本月毕业学员人数:</label>
                            <div class="layui-input-inline">
                                <input disabled="disabled" type="text" class="layui-input" value="${teacher.TCount}">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">身份证号:</label>
                            <div class="layui-input-inline">
                                <input disabled="disabled" type="text" class="layui-input" value="${teacher.TSfz}">
                            </div>
                            <label class="layui-form-label">联系方式:</label>
                            <div class="layui-input-inline">
                                <input disabled="disabled" id="phone" type="text" class="layui-input" value="${teacher.TPhone}">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">教学状态:</label>
                            <div class="layui-input-inline">
                                <c:if test="${teacher.TTeach}">
                                    <input disabled="disabled" type="text" class="layui-input" value="允许教学"></c:if>
                                <c:if test="${!teacher.TTeach}">
                                    <input disabled="disabled" type="text" class="layui-input" value="不允许教学"></c:if>
                            </div>
                            <label class="layui-form-label">出生日期:</label>
                            <div class="layui-input-inline">
                                <input disabled="disabled" type="text" class="layui-input" value="${teacher.TBirthday}">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">任职驾校:</label>
                            <div class="layui-input-inline">
                                <input disabled="disabled" type="text" class="layui-input" value="${teacher.SName}">
                            </div>
                            <label class="layui-form-label">当前学员数量:</label>
                            <div class="layui-input-inline">
                                <input disabled="disabled" type="text" class="layui-input" value="${studentCount}">
                            </div>
                        </div>
                    </div>
                </div>
                <button type="button" class="layui-btn layui-btn-warm" onclick="canVary()" id="action">修改</button>
                <button type="button" hidden class="<%--layui-btn layui-btn-warm--%>" id="callOff" onclick="keys()">取消</button>
                <br><br><br>
            </div>
        </div>
        <script>
            let tPic = $("#tPic").val();
            Layui_uploadImage("#test1",$("#path").val()+'/upImage',$('#demo1'),function (mag) {
            tPic = mag.fPath;},$('#demoText'));
            let vary = false;
            let oldPhone;
            function keys() {//修改手机号码及取消修改
                let phone = $("#phone");
                let callOff = $("#callOff");
                phone.attr("disabled",vary);
                vary = !vary;
                if (!vary){
                    $("#action").html("修改");
                    callOff.attr("class","");
                    phone.attr("disabled",false);
                    phone.val($("#tPhone").val());
                    phone.attr("disabled",true);
                }
                callOff.attr("hidden",!vary);
            }
            function canVary(){//手机号码提交修改
                if(!vary){//修改字样点击
                    $("#action").html("提交");
                    $("#callOff").attr("class","layui-btn layui-btn-warm");
                    oldPhone = $("#phone").val();
                } else {//提交字样点击
                    let phone = $("#phone");
                    if (phone.val().length < 3 || phone.val().length > 15) {alert("联系方式长度为3~15");return;}
                    else if (!number.test(phone.val())) {alert("联系方式为纯数字");return;}
                    if ($("#tPhone").val() == phone.val() && $("#tPic").val() == tPic) {alert("你并未更改信息，不用提交");return;}
                    AjaxTransfer($("#path").val()+"/teacherController/updatePhone",
                        "tPhone="+phone.val()+"&tAccountId="+$("#aId").val()+"&tPic="+tPic,function (mag) {
                            if (confirm(mag.msg+"是否重新登录")){
                                skipAbsolute("/transfer?logo=logout");//跳出iframe到指定位置
                            }
                        });
                }
                keys();
            }
        </script>
    </body>
</html>
