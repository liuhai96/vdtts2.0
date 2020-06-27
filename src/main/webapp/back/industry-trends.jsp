<%--
  Created by IntelliJ IDEA.
  User: LiLang9725
  Date: 2020/6/16
  Time: 0:44
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
    <title>行业动态发布</title>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <script src="https://www.layuicdn.com/layui/layui.js"></script>
    <link href="https://www.layuicdn.com/layui/css/layui.css" rel="stylesheet" type="text/css"/>
</head>
    <body>
        <div>
            <c:if test="${layuiTableData ne null}">
                <c:if test="${layuiTableData.code == 1}">
                    <script>alert("加入成功")</script>
                </c:if>
                <c:if test="${layuiTableData.code != 1}">
                    <script>alert("加入失败")</script>
                </c:if>
            </c:if>

            <input hidden="hidden" value="<%=path%>" id="path">
            <br><br><br>
            <div style="text-align: center;font-size: 30px;font-size-adjust: 0.6;color: coral">
                <label>行业动态发布</label>
                <hr>
            </div>
            <form action="<%=path+"/notice/dynamicIssue"%>" method="post" id="goto">
                <div class="layui-form-item" >
                    <label class="layui-form-label">发布类型</label>
                    <div class="layui-input-inline" >
                        <select name="nType">
                            <option value="">请选择类型</option>
                            <optgroup label="法律条例">
                                <option value="law" onclick="typeTo('《   法》')">法律</option>
                            </optgroup>
                            <optgroup label="通知公告">
                                <option value="notice" onclick="typeTo('特别致谢')">致谢</option>
                                <option value="notice" onclick="typeTo('恭喜  同学')">考试通告</option>
                            </optgroup>
                            <optgroup label="协议条文">
                                <option value="deal" onclick="typeTo('《 xx 协议》')">相关协议</option>
                            </optgroup>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">信息标题</label>
                    <div class="layui-input-block">
                        <input type="text" name="nName" lay-verify="title" autocomplete="off"
                               placeholder="请输入标题，例如《xxx法》" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">发布信息</label>
                    <div class="layui-input-block">
                        <textarea placeholder="请输入你要发布的内容，......"
                                  class="layui-textarea" name="nContent"></textarea>
                    </div>
<%--                    <input hidden value="" name="nContent">--%>
                </div>
                <div class="layui-form-item" style="text-align: center;">
                    <button class="layui-btn" onclick="toService()">
                        <i class="layui-icon">&#xe609;</i>&nbsp;&nbsp;发&nbsp;布&nbsp;&nbsp;</button>
                </div>
            </form>
        </div>
    <script>
        function toService() {
            let nType = $("select[name='nType']");
            let nName = $("input[name='nName']");
            let nContent = $("textarea[name='nContent']");
            if (nName.val() != "") {
                if (nType.val()=="") {
                    nType.val("notice");
                }
                if (nType.val() == "notice") {//通知公告时，类容加到信息标题后
                    nName.val(nName.val()+":"+nContent.val());
                    nContent.val("");
                }
                $("#goto").submit();
            } else alert("信息标题必须填写");
        }
        function typeTo(ty) {
            $("input[name='nName']").val(ty);
        }
    </script>
    </body>
</html>
