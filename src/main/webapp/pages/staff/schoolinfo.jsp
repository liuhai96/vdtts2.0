<%--
  Created by IntelliJ IDEA.
  User: 刘海
  Date: 2020/6/11
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>Title</title>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <link rel="stylesheet" href=<%=path+"/static/layui/css/layui.css"%>>
    <script type="text/javascript" src=<%=path+"/static/layui/layui.js"%>></script>
    <script src="<%=path+"/static/custom_tool.js"%>"></script>
</head>
<body>
<input hidden="hidden" value="<%=path%>" id="path">
    <div style="text-align: center;">
        <div>
            <label style="font-size: 20px;color: chocolate;margin: 0 50% 0 0">驾校信息:</label>
            <div class="layui-form-item" >
                <div class="layui-inline" id="test1">
                    <div class="layui-input-inline">
                        <img src="${school.SImageUrl}" id="demo1" height="200px" width="200px">
                    </div>
                </div>
                <p id="demoText"></p>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">驾校id：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="SId" class="layui-input" disabled="disabled" value="${school.SId}">
                    </div>
                    <label class="layui-form-label">驾校名字：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="SName" class="layui-input canUpdate" disabled="disabled" value="${school.SName}">
                        <input hidden id="SName" value="${school.SName}">
                    </div>
                </div><br>
                <div class="layui-inline">
                    <label class="layui-form-label">驾校账号：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="account" class="layui-input" disabled="disabled" value="${account}">
                    </div>
                    <label class="layui-form-label">驾校地址：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="SAddress" class="layui-input canUpdate" value="${school.SAddress}" disabled="disabled">
                        <input hidden id="SAddress" value="${school.SAddress}">
                    </div>
                </div><br>
                <div class="layui-inline">
                    <label class="layui-form-label">报名电话：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="SPhone" class="layui-input canUpdate" value="${school.SPhone}" disabled="disabled">
                        <input hidden="hidden" id="SPhone" value="${school.SPhone}">
                    </div>
                    <label class="layui-form-label">注册时间：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="SRegTime" class="layui-input" disabled="disabled" value="${school.SRegTime}">
                    </div>
                </div><br>
                <div class="layui-inline">
                    <label class="layui-form-label">信用代码：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="sBusinessId" class="layui-input" disabled="disabled" value="${school.SBusinessId}">
                    </div>
                    <label class="layui-form-label">图片路径：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="SImageUrl" class="layui-input" disabled="disabled" value="${school.SImageUrl}">
                        <input hidden="hidden" id="SImageUrl" value="${school.SImageUrl}">
                    </div>
                </div><br>
                <div class="layui-inline">
                    <label class="layui-form-label">报名费用：</label>
                    <div class="layui-input-inline">
                        <label class="layui-input"><input type="text" class="canUpdate" style="height: 100%;width: 50px;text-align: center;"
                           name="SRegisteryFee" disabled="disabled" value="${school.SRegisteryFee}">元</label>
                        <input hidden id="SRegisteryFee" value="${school.SRegisteryFee}">
                    </div>

                </div><br>
                <label style="font-size: 20px;color: chocolate;margin: 0 50% 0 0">相关证件:</label><br>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <img src="${school.SBusinessPic}" height="200px" width="300px">
                    </div>
                    <label class="layui-form-label">  </label>
                    <div class="layui-input-inline">
                        <img src="${school.SOwnerPic}" height="200px" width="300px">
                    </div>
                </div><br>
                <button type="button" class="layui-btn layui-btn-warm" onclick="canVary()" id="action">&nbsp;&nbsp;修&nbsp;改&nbsp;&nbsp;</button>
                <button type="button" hidden class="" id="callOff" onclick="keys()">&nbsp;&nbsp;取&nbsp;消&nbsp;&nbsp;&nbsp;</button>
            </div>
        </div>
    </div>

<script>
    let SImageUrl = $("#SImageUrl").val();
    Layui_uploadImage("#test1",$("#path").val()+'/upImage',$('#demo1'),function (mag) {
        if (mag.fPath == null){SImageUrl = "";}
        else {SImageUrl = mag.fPath;}
        let SImageUrl2 = $("input[name = 'SImageUrl']");
        SImageUrl2.attr("disabled",false);
        SImageUrl2.attr("value",SImageUrl);
        SImageUrl2.attr("disabled",true);
    },$('#demoText'));

    let idUpdate = true;
    function keys() {
        let action = $("#action");
        let callOff = $("#callOff");
        if (idUpdate){
            action.html("&nbsp;&nbsp;提&nbsp;交&nbsp;&nbsp;");
            callOff.attr("class","layui-btn layui-btn-warm");
        } else {
            action.html("&nbsp;&nbsp;修&nbsp;改&nbsp;&nbsp;");
            callOff.attr("class","");
            $("input[name='SName']").val($("#SName").val());
            $("input[name='SAddress']").val($("#SAddress").val());
            $("input[name='SPhone']").val($("#SPhone").val());
            $("input[name='SRegisteryFee']").val($("#SRegisteryFee").val());
        }
        $(".canUpdate").each(function () {
            $(this).attr("disabled",!idUpdate);
        });
        idUpdate = !idUpdate;
    }

    function canVary() {
        if (!idUpdate){
            let SName = $("input[name='SName']").val(),SAddress = $("input[name='SAddress']").val();
            let SPhone = $("input[name='SPhone']").val(),SRegisteryFee = $("input[name='SRegisteryFee']").val();
            if (SName == "" || SAddress == "" || SPhone == "" ||　SRegisteryFee == "" || SImageUrl == ""){
                alert("信息不能为空");
                return;
            } else if(SAddress == $("#SAddress").val() && SPhone == $("#SPhone").val()
                && SRegisteryFee == $("#SRegisteryFee").val() && $("#SImageUrl").val() == SImageUrl){
                alert("你并未修改信息,无需提交");
                return;
            } else {
                AjaxTransfer($("#path").val()+"/SchoolControl/updateSchoolBasicInfo","sName="+SName+
                    "&sAddress="+SAddress+"&sPhone="+SPhone+"&sImageUrl="+SImageUrl+"&sRegisteryFee="
                    +SRegisteryFee+"&sId="+$("input[name='SId']").val(), function () {
                    if (confirm("修改成功!是否重新登录刷新数据？")){
                        skipAbsolute("/transfer?logo=exit");
                    }
                })
            }
        }
        keys();
    }
</script>
</body>
</html>
