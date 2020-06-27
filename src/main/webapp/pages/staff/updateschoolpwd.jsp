<%--
  Created by IntelliJ IDEA.
  User: 刘海
  Date: 2020/6/16
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
 String path = request.getContextPath();
%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href=<%=path+"/static/layui/css/layui.css"%>>
    <script type="text/javascript" src=<%=path+"/static/layui/layui.js"%>></script>
</head>
<body>
<form class="layui-form" action="" id="updatePwd">
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
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
        </div>
    </div>
</form>
</body>
<script>
    //Demo
    layui.use(['form','layer'], function(){
        var form = layui.form,layer = layui.layer;
        var $ = layui.jquery;
        //监听提交
        form.on('submit(formDemo)', function(data){
            var newPwd = $("input[name='newPwd']").val();
            var repeatPwd = $("input[name='repeatPwd']").val();
                var index = layer.confirm('您确定修改驾校的基本信息？', {
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
                                url: '<%=path%>/SchoolControl/updateSchoolPwd',
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

</html>
