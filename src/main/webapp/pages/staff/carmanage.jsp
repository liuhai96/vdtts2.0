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
    <title>教练车管理</title>
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
<form action="" id="updateTeacher" style="display: none" class="layui-form">
    <div class="layui-form-item" id="account_div">
        <label class="layui-form-label">车牌号</label>
        <label class="layui-form-label" id="carNumber"></label>
    </div>
    <div class="layui-form-item" id="name_div">
        <label class="layui-form-label">车品牌</label>
        <label class="layui-form-label" id="carLogo"></label>
    </div>
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

<form action="" id="addCar" style="display: none" class="layui-form">
    <div class="layui-form-item">
        <label class="layui-form-label">车品牌</label>
        <div class="layui-input-inline">
            <input type="text"  name="cLogo" required  lay-verify="required" placeholder="请输入车品牌" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">车牌号</label>
        <div class="layui-input-inline">
            <input type="text"  name="cNumber" required  lay-verify="required" placeholder="请输车牌号" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">车型</label>
        <div class="layui-input-inline">
            <input type="text" name="cModel" required  lay-verify="required" placeholder="请输入车型" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">车颜色</label>
        <div class="layui-input-inline">
            <input type="text" name="cColor" required  lay-verify="required" placeholder="请输入车颜色" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script type="text/html" id="toolbarDemo">
        <button class="layui-btn layui-btn-sm" lay-event="addCar">添加教练车</button>
        <div class="layui-form-item" style="display:inline-block">
            <div class="layui-input-block">
                <input type="text" name="inputname" placeholder="请输入车牌号查询" autocomplete="off" class="layui-input" style="width:100%;">
            </div>
        </div>
        <div class="layui-btn-container" style="display:inline-block">
            <button class="layui-btn layui-btn-sm" lay-event="findCar">查询
                <i class="layui-icon">&#xe615;</i>
            </button>
        </div>
</script>

<script type="text/html" id="barDemo">
    {{#  if(d.tName!=null){}}
    <a class="layui-btn layui-btn-xs" lay-event="edit">更换教练</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    {{#  }else{}}
    <a class="layui-btn layui-btn-xs" lay-event="edit">分配教练</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    {{#  } }}
</script>


<script>
    layui.use(['table','layer','form'], function(){
        var table = layui.table;
        var layer = layui.layer;
        var form = layui.form;
        var $table=table.render({
            elem: '#test'
            ,url:'<%=path%>/carControl/findCarList'
            ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            ,title: '车辆管理表'
            ,cols: [[
                {field:'cId', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
                ,{field:'cLogo', title:'品牌', width:120, edit: 'text'}
                ,{field:'cModel', title:'车型', width:80, edit: 'text', sort: true}
                ,{field:'cColor', title:'车辆颜色', width:100}
                ,{field:'cNumber', title:'车牌号'}
                ,{field:'tName', title:'分配教练'}
                // ,{title:'所属教练',templet: '<div>{{d.teacher.tName}}</div>'}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
            ]]
            ,page: {limit: 5,//指定每页显示的条数
                limits: [5, 10, 15, 20,
                    25, 30, 35, 40, 45, 50],},//每页条数的选择项
        });

        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
             var $= layui.jquery;
            switch(obj.event){
                case 'addCar':
                    var data = checkStatus.data;
                    var index =   layer.open({
                        type: 1,
                        area:["350","450px"],
                        skin: 'layui-layer-rim',
                        shadeClose: true,//点击其他地方关闭
                        content:$("#addCar"),
                        cancel:function (index) {
                            layer.close(index);
                            $('#addCar')[0].reset();//重置表单
                            form.render();
                        }
                    });

                    form.on('submit(demo1)', function(data){
                            $.ajax({
                                type: 'POST',
                                url: '<%=path%>/carControl/addCar',
                                dataType: 'JSON',
                                data: data.field,
                                success: function (msg) {
                                    if (msg.code==1){
                                        layer.msg("添加教练车成功");
                                        $table.reload();
                                    }
                                    layer.close(index);
                                    $('#addCar')[0].reset();//重置表单
                                    form.render();
                                }
                            });

                            return false;
                        });
                    break;

                //自定义头工具栏右侧图标 - 提示
                case 'findCar':
                    var data = checkStatus.data;
                    var inputname = $("input[name='inputname']").val();
                    alert(inputname);
                    table.reload('test',{
                        url:'<%=path%>/carControl/findCarList',
                        page:{
                            curr:1//重第一页开始
                        },
                        where:{
                            studentName:"qwe",
                            cNumber:inputname
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
            if(obj.event === 'del'){
                var cId = data.cId;
                layer.confirm('真的删除行么',{
                    btn:["确定","取消"],
                    btn2:function (index) {
                        alert(data.tId);
                        layer.close(index);
                    },
                    btn1:function () {
                        $.ajax({
                            type: 'POST',
                            url: '<%=path%>/carControl/deleteCar',
                            dataType: 'JSON',
                            data:{
                                cId:cId
                            },
                            success: function (msg) {
                                if(msg.code==1){
                                    layer.msg("您已成功删除该教练车信息");
                                }else if(msg.code==0 ){
                                    layer.msg("删除失败");
                                }else{
                                    layer.msg("该教练的账号信息不存在");
                                }
                            }
                        });
                        $table.reload();
                    }

                });
            } else if(obj.event === 'edit'){
                $("#carNumber").html(data.cNumber);
                $("#carLogo").html(data.cLogo);
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
                    var cId = data.cId;
                    form.render();
                    form.on('submit(demo2)', function(data){
                        teacherId = $("#teacherSelect").val();
                        $.ajax({
                            type: 'POST',
                            url: '<%=path%>/carControl/updateCarInfo',
                            dataType:'JSON',
                            data:{
                                cTeacherId:teacherId,
                                cId:cId
                            },
                            success: function (msg) {
                                if (msg.code==0){
                                  layer.msg("请选择教练");
                                }else if(msg.code==1){
                                    layer.msg("修改成功");
                                    layer.close(index1);
                                    $table.reload();
                                }else{
                                    layer.msg("未找到该教练信息");
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

            }
        });
    });
</script>

</body>
</html>
