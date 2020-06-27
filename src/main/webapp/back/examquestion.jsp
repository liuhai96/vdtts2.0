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
    <title>题库管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="#"/>
    <link rel="stylesheet" href=<%=path+"/static/layui/css/layui.css"%>>
    <script type="text/javascript" src=<%=path + "/static/layui/layui.js"%>></script>
    <style>
        .layui-form-label {
            float: left;
            display: block;
            padding: 9px 15px;
            width: 130px;
            font-weight: 400;
            line-height: 20px;
            text-align: right;
        }
    </style>
</head>
<body>

<table class="layui-hide" id="test" lay-filter="test"></table>


<script type="text/html" id="toolbarDemo">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">请选择更新科目</label>
            <div class="layui-input-inline">
                <select name="interest" lay-filter="aihao" id="level">
                    <option value="0">请选择科目</option>
                    <option value="1">科目一</option>
                    <option value="4">科目四</option>
                </select>
            </div>
        </div>
    </form>
    <button class="layui-btn layui-btn-sm" lay-event="getCheckData">更新题库</button>
</script>

<script type="text/html" id="barDemo">
</script>

<script>
    layui.use(['table', 'layer'], function () {
        var table = layui.table, layer = layui.layer, $ = layui.jquery;
        var $table = table.render({
            elem: '#test'
            , url: '<%=path%>/examQuestionController/findExamQuestion'
            , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            , defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                , layEvent: 'LAYTABLE_TIPS'
                , icon: 'layui-icon-tips'
            }]
            , title: '用户数据表'
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'eqId', title: 'ID', width: 80, fixed: 'left'}
                , {field: 'eqQuestion', title: '问题', width: 1000}
                , {field: 'eqLevel', title: '科目', width: 120}
            ]]
            , page: {
                limit: 10,//指定每页显示的条数
                limits: [5, 10, 15, 20,
                    25, 30, 35, 40, 45, 50],
            },//每页条数的选择项
        });

        //头工具栏事件
        table.on('toolbar(test)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'getCheckData':
                    var level = $("#level").val();
                    $.ajax({
                        type: 'POST',
                        data: {
                            level: level
                        },
                        url: '<%=path%>/examQuestionController/examQuestion',
                        success: function (resmsg) {
                            if (resmsg.code == 1) {
                                layer.msg(resmsg.msg);
                                $table.reload();
                            }else{
                                layer.msg(resmsg.msg);
                            }
                        }
                    });
                    $('#level').find('option[value=' + 0 + ']').attr('selected', 'selected');
                    break;
            }
            ;
        });
    });
</script>

</body>
</html>