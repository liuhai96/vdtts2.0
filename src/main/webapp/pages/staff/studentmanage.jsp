<%--
  Created by IntelliJ IDEA.
  User: 刘海
  Date: 2020/6/8
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>学员报名审核</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="#"/>
    <link rel="stylesheet" href=<%=path+"/static/layui/css/layui.css"%>>
    <script type="text/javascript" src=<%=path+"/static/layui/layui.js"%>></script>
    <style>
        .layui-input-block{
            margin-right: 10px;
            margin-top: 10px;
        }
    </style>
</head>
<body>

<table class="layui-hide" id="test" lay-filter="test"></table>

<script type="text/html" id="toolbarDemo">
        <div class="layui-form-item" style="display:inline-block">
            <div class="layui-input-block">
                <input type="text" name="inputname" placeholder="请输入学员姓名查询" autocomplete="off" class="layui-input" style="width:100%;">
            </div>
        </div>
        <div class="layui-btn-container" style="display:inline-block">
            <button class="layui-btn layui-btn-sm" lay-event="findStudent">查询
                <i class="layui-icon">&#xe615;</i>
            </button>
        </div>
</script>

<script type="text/html" id="barDemo">
    {{#  if(d.sApplyState==0){}}
    <a class="layui-btn layui-btn-xs" lay-event="edit">
        审核
    </a>
    {{#  }else{}}
    <a class="layui-btn layui-btn-xs" lay-event="addTeacher">分配教练</a>
    {{#  } }}
</script>


<script>
    layui.use('table', function(){
        var table = layui.table;
        var $ = layui.jquery;
        var $table=table.render({
            elem: '#test'
            ,url:'<%=path%>/studentController/findStudentList'
            ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            ,title: '学员报名审核'
            ,cols: [[
                {field:'sId', title:'ID', width:80, fixed: 'left'}
                ,{field:'sName', title:'姓名', width:120}
                ,{field:'sPhone', title:'电话', width:80,sort: true}
                ,{field:'sSex', title:'性别', width:100}
                ,{field:'sSfz', title:'身份证号'}
                ,{field:'teacherName', title:'教练名'}
                ,{field:'sApplyState', title:'报名审核状态'}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
            ]]
            ,page: {limit: 5,//指定每页显示的条数
                limits: [5, 10, 15, 20,
                    25, 30, 35, 40, 45, 50],},//每页条数的选择项
            done: function (res, curr, count) {
                $("[data-field='sApplyState']").children().each(function () {
                    if ($(this).text() == '0') {
                        $(this).text("待审核")
                    } else if ($(this).text() == '1') {
                        $(this).text("已审核")
                    }
                });
            }
        });

        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
             var $= layui.jquery;
            switch(obj.event){
                case 'findStudent':
                    var data = checkStatus.data;
                    var inputname = $("input[name='inputname']").val();
                    table.reload('test',{
                        url:'<%=path%>/studentController/findStudentList',
                        page:{
                            curr:1//重第一页开始
                        },
                        where:{
                            sName:inputname
                        }
                    });
                    $("input[name='inputname']").val(inputname);
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            var $ = layui.jquery;
            var teacherId="";
            //console.log(obj)
            if(obj.event === 'addTeacher'){
                $.ajax({
                    type: 'POST',
                    url: '<%=path%>/teacherController/findTeacher',
                    dataType: 'JSON',
                    success: function (msg) {
                        $("#teacherSelect").html("");
                        $.each(msg.data,function (i,item) {
                            $("#teacherSelect").append("<option value='" + item.tId + "'>" + item.tName + "</option>");
                        });
                        layui.use('form',function(){
                            var form = layui.form;
                            form.render();
                        });
                    }});
                var index1 = layer.open({
                    type: 1,
                    area:["350","400px"],
                    skin: 'layui-layer-rim',
                    shadeClose: true,//点击其他地方关闭
                    content:$("#updateTeacher"),
                    cancel:function (index) {
                        layer.close(index);
                    },
                });
                layui.use('form', function(){
                    var form = layui.form
                    var sId = data.sId;
                    form.render();
                    form.on('submit(demo2)', function(data){
                        teacherId = $("#teacherSelect").val();
                        $.ajax({
                            type: 'POST',
                            url: '<%=path%>/studentController/updateStudentTeacherId',
                            dataType:'JSON',
                            data:{
                                sTeacherId:teacherId,
                                sId:sId
                            },
                            success: function (remsg) {
                                if (remsg.code==-1){
                                    layer.msg(remsg.msg);
                                }else if(remsg.code==1){
                                    layer.msg(remsg.msg);
                                    layer.close(index1);
                                    $table.reload();
                                }else{
                                    layer.msg(remsg.msg);
                                    layer.close(index1);
                                }
                                layui.use('form',function(){
                                    var form = layui.form;
                                    form.render();
                                });
                            }
                        });
                        return false;
                    });
                });
            } else if(obj.event === 'edit'){
                $.ajax({
                    data:{
                        sId:data.sId
                    },
                    type: 'POST',
                    url: '<%=path%>/studentController/updateStudentApplyState',
                    dataType: 'JSON',
                    success: function (regmsg) {
                        if (regmsg.code==1){
                            layer.msg("您已成功审核该学员信息")
                            $table.reload();
                        }else{
                            layer.msg(regmsg.msg)
                        }
                    }
                });
                return false;
            }
        });
    });
</script>
<form action="" id="updateTeacher" style="display: none" class="layui-form">
    <div class="layui-form-item">
        <label class="layui-form-label">教练</label>
        <div class="layui-input-block">
            <select name="interest" lay-filter="aihao"  id="teacherSelect">
                <option value="0">请选择教练</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo2">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</body>
</html>
