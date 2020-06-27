<%--
  Created by IntelliJ IDEA.
  User: LiLang9725
  Date: 2020/6/10
  Time: 23:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String schoolName = "机动车驾驶员计时培训系统";//名称
    String path = request.getContextPath();
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie:cookies){
        if(cookie.getName().equals("schoolName"))schoolName = cookie.getValue();
    }
%>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <script src="https://www.layuicdn.com/layui/layui.js"></script>
    <link href="https://www.layuicdn.com/layui/css/layui.css" rel="stylesheet" type="text/css"/>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no,user-scalable=no">
    <title>机动车驾驶员计时培训系统·企业版 入驻</title>
    <link href="<%=path+"/pages/homepage/driving-in/driving-in_files/main.css"%>" rel="stylesheet" type="text/css">
    <script src="<%=path+"/static/custom_tool.js"%>"></script>
</head>
    <body>
    <input hidden="hidden" value="<%=path%>" id="path">
        <div class="jxb-cooperate-container" data-sp="1">
            <div class="jxb-cooperate">
                <div class="head" skip="true">
                    <div class="jxb-logo"></div>
                    <div class="nav">
                        <div class="tip">联系电话：xxxxx-xxxxxx QQ：xxxxxxxxx</div>
                        <a style="color: cornsilk" href="<%=path+"/index"%>"><%=schoolName%></a>
                        <label style="color: cornsilk;">&nbsp;|&nbsp;</label>
                        <a style="color: cornsilk;" href="<%=path+"/transfer?logo=institutionLogin"%>">驾校登录</a>
                    </div>
                </div>
                <div class="cooperate-container"><h2 skip="true">欢迎入驻<%=schoolName%>-企业版</h2>
                    <div class="cooperate-form">
                        <div class="form">
                            <div class="err-tip"></div>
                            <div class="form-group radio-form" onclick="">
                                <label skip="true">认证图片</label>
                                <div class="user-type-conatiner layui-upload" >
                                    <div class="user-type active layui-upload-list" ref="radioWrap" data-index="0"
                                         style="width: 252px;" id="test1">
                                        <b skip="true">认证图片</b>
                                        <img src="" class="layui-upload-img" id="demo1" height="163px"
                                             width="250px" property="" alt="会展示到驾校推荐首页，
                                             入驻需认证，用于营销、招生、管理">
                                        <p id="demoText"></p>
                                    </div>
                                </div>
                            </div>
                            <div class="err-tip"></div>
                            <div class="form-group">
                                <label>驾校名称<i skip="true">*</i></label>
                                <input class="" type="text" name="fName" placeholder="请输入驾校名称">
                            </div>
                            <div class="err-tip"></div>
                            <div class="form-group">
                                <label>登录密码<i skip="true">*</i></label>
                                <input class="" type="password" name="aPassword" placeholder="请设置你的登录密码">
                            </div>
                            <label id="aPasswordNotify" style="color: crimson"></label>
                            <div class="err-tip"></div>
                            <div class="form-group">
                                <label>确认密码<i skip="true">*</i></label>
                                <input class="" type="password" name="aPassword2" placeholder="请确认你的登录密码">
                            </div>
                            <label id="aPassword2Notify" style="color: crimson"></label>
                            <div class="err-tip"></div>
                            <div class="form-group">
                                <label skip="true">所在城市<i>*</i></label>
                                <input class="" type="text" name="sAddress" placeholder="请输入驾校的所在城市">
                            </div>
                            <label id="sAddressNotify" style="color: crimson"></label>
                            <div class="err-tip"></div>
                            <div class="form-group ">
                                <label skip="true">法人证号<i>*</i></label>
                                <input class="" type="text" name="sOwnerId" ref="input"
                                       placeholder="请填写法人身份证号" maxlength="40">
                            </div>
                            <label id="sOwnerIdNotify" style="color: crimson"></label>
                            <div class="err-tip "></div>
                            <div class="form-group radio-form" onclick="">
                                <label skip="true">法人证件照<i>*</i></label>
                                <div class="user-type-conatiner layui-upload">
                                    <div class="user-type active layui-upload-list" ref="radioWrap" data-index="0"
                                         style="width: 252px;" id="test10">
                                        <b skip="true">法人证件照</b>
                                        <img src="" class="layui-upload-img" id="demo10"  height="163px" width="250px"
                                             property="" alt="用于管理">
                                        <p id="demoText10"></p>
                                    </div>
                                </div>
                            </div>
                            <label id="sRecruitNotify" style="color: crimson"></label>
                            <div class="err-tip "></div>
                            <div class="form-group">
                                <label skip="true">报名费用<i>*</i></label>
                                <input class="" type="text" name="sRegisteryFee" ref="input" placeholder="请输入报名费用(￥)" maxlength="20">
                            </div>
                            <div class="err-tip"></div>
                            <div class="form-group">
                                <label skip="true">招生电话<i>*</i>
                                </label><input class="" type="text" name="sPhone" ref="input" placeholder="招生电话" maxlength="11">
                            </div>
                            <label id="sPhoneNotify" style="color: crimson"></label>
                            <div class="err-tip"></div>
                            <div class="form-group">
                                <label skip="true">验证码<i>*</i></label>
                                <input type="text" value="" placeholder="请输入验证码" class="input-val" style="height: 35px;width: 30%;">
                                <canvas id="canvas" class="layui-col-md4" style="height: 35px;background-color: #000000; margin: 0 0 0 10%;"></canvas>
<%--                                <input class=" sms-code" type="text" name="smsCode" ref="input" placeholder="请输入验证码">--%>
<%--                                <div class="form-btn " ref="send-smscode">获取验证码</div>--%>
                            </div>
                            <div class="err-tip"></div>
                            <div skip="true">
                                <span class="form-btn btn layui-btn layui-btn-normal layui-btn-radius"
                                      style="width: 30%;margin: 0 0 0 30%">&nbsp;&nbsp;提&nbsp;&nbsp;交&nbsp;&nbsp;</span>
                            </div><br><br>
                            <div class="err-tip"></div>
<%--                            <div class="protocol">--%>
<%--                                <div class="protocol-checkbox checked" ref="protocol-checkbox"></div>--%>
<%--                                <div>我已阅读并接受--%>
<%--                                    <a href="" target="_blank">《驾校用户使用协议》</a>&lt;%&ndash; https://qiye.jiakaobaodian.com/xieyi1.html&ndash;%&gt;--%>
<%--                                    和<a href="" target="_blank">《企业版隐私政策》</a>&lt;%&ndash;https://qiye.jiakaobaodian.com/xieyi2.html&ndash;%&gt;--%>
<%--                                </div>--%>
<%--                            </div>--%>
                        </div>
                    </div>
                    <div class="copyright" skip="true"><span>Copyright ©xxxxxx信息技术有限公司 |<span class="beian"></span><a
                            style="color: #333" target="_blank" href="">吓公安网备 xxxxxxxxx号</a>&nbsp;&nbsp;
                        <a style="color: #333" target="_blank" href="">吓ICP备xxxxxxxxx号xxx</a></span>
                    </div>
                </div>
            </div>
        </div>
        <script>
            let sBusinessPic = "";//认证
            let sRecruit = "";//法人证件
            Layui_uploadImage("#test1",$("#path").val()+'/upImage',$('#demo1'),function (mag) {
                sBusinessPic = mag.fPath;},$('#demoText'));//认证
            Layui_uploadImage("#test10",$("#path").val()+'/upImage',$('#demo10'),function (mag) {
                sRecruit = mag.fPath;},$('#demoText10'));//法人证件图
            function toService() {
                let isPass = true;
                let fName = $("input[name='fName']");
                let fNameNotify =  $("#fNameNotify");
                let sOwnerId = $("input[name='sOwnerId']");
                let sOwnerIdSfzNotify = $("#sOwnerIdNotify");
                let sAddress = $("input[name='sAddress']");
                let sAddressNotify = $("#sAddressNotify");
                let sPhone = $("input[name='sPhone']");
                let sPhoneNotify = $("#sPhoneNotify");
                let aPassword = $("input[name='aPassword']");
                let aPasswordNotify = $("#aPasswordNotify");
                let aPassword2 = $("input[name='aPassword2']");
                let aPassword2Notify = $("#aPassword2Notify");

                if ( fName.val() == "") {fNameNotify.html("*法人证件照为必要证件，证件照必须上传！");isPass = false;}
                else fNameNotify.html("");

                //校验密码格式
                if (aPassword.val().length < 1){aPasswordNotify.html("*密码为必填项目！");isPass = false;}
                else if (!done(aPassword.val(),0,aPassword.val().length)){aPasswordNotify.html("*密码中含有非法字符！");isPass = false;}
                else {aPasswordNotify.html("");}
                //校验密码真正准确性
                if (aPassword2.val().length < 1){aPassword2Notify.html("");isPass = false;}
                else if (aPassword2.val() != aPassword.val()){aPassword2Notify.html("*密码不一致");isPass = false;}
                else {aPassword2Notify.html("");}

                if (sRecruit == null || sRecruit == "") {$("#sRecruitNotify").html("*法人证件照为必要证件，证件照必须上传！");isPass = false;}
                else $("#sRecruitNotify").html("");
                //校验身份证
                if (sOwnerId.val().length < 1){sOwnerIdSfzNotify.html("*身份证号为必填项目！");isPass = false;}
                else if (sOwnerId.val().length != 15 && sOwnerId.val().length  != 18){sOwnerIdSfzNotify.html("*身份证号长度未15位,或18位！");isPass = false;}
                else if (!number.test(cutOut(sOwnerId.val(),undefined,sOwnerId.val().length-1))){sOwnerIdSfzNotify.html("*身份证号中含有非法字符！");isPass = false;}
                else if (!done(sOwnerId.val(),sOwnerId.val().length-2,sOwnerId.val().length)){sOwnerIdSfzNotify.html("*身份证号中含有非法字符！");isPass = false;}
                else {sOwnerIdSfzNotify.html("");}
                //校验驾校地址
                if (sAddress.val() == "") {sAddressNotify.html("*驾校所在地必须填写！");isPass = false;}
                else if (sAddress.val().length < 5) {sAddressNotify.html("*驾校所在地必须大于5位");isPass = false;}
                else sAddressNotify.html("");
                //校验联系方式
                if (sPhone.val().length < 1){sPhoneNotify.html("*联系方式为必填项目！");isPass = false;}
                else if (sPhone.val().length < 3 || sPhone.val().length  > 11){
                    sPhoneNotify.html("*联系方式长度大于3,小于12位！");
                    isPass = false;
                } else if (!number.test(sPhone.val())){
                    sPhoneNotify.html("*联系方式仅限制数字号码！");
                    isPass = false;
                } else sPhoneNotify.html("");
                if (isPass){
                    AjaxTransfer($("#path").val()+"/SchoolControl/drivingIn","sName="+fName.val()+"&sBusinessPic="+
                        sBusinessPic+"&sOwnerPic="+sRecruit+"&sOwnerId="+sOwnerId.val()+"&sAddress="+sAddress.val()+
                        "&sPhone=" +sPhone.val()+"&sRegisteryFee="+$("input[name='sRegisteryFee']").val()+"&aPassword="
                        +aPassword.val(), function (mag) {
                        alert(mag.data.result);
                    });
                }

            }
        </script>
    </body>
</html>
