<%--
  Created by IntelliJ IDEA.
  User: 刘海
  Date: 2020/6/10
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>学员考试安排</title>
    <link rel="shortcut icon" href="#"/>
    <link rel="stylesheet" href=<%=path+"/static/layui/css/layui.css"%>>
    <script type="text/javascript" src=<%=path+"/static/layui/layui.js"%>></script>
</head>
<body>

<table class="layui-hide" id="test" lay-filter="test"></table>
<form id="scheduleExam" style="display: none" class="layui-form">
    <div class="layui-form-item">
        <label class="layui-form-label">考试学员</label>
        <label class="layui-form-label" id="studentName"></label>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">考试科目</label>
        <div class="layui-input-block">
            <select name="interest" lay-filter="aihao"  id="examSelect">
                <option value="0">请选择考试科目</option>
                <option value="1">科目一</option>
                <option value="2">科目二</option>
                <option value="3">科目三</option>
                <option value="4">科目四</option>
            </select>
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
    <div class="layui-form-item" style="display:inline-block">
        <div class="layui-input-block">
            <input type="text" name="inputname" placeholder="请输入学生姓名查询" autocomplete="off" class="layui-input" style="width:100%;">
        </div>
    </div>
    <div class="layui-btn-container" style="display:inline-block">
        <button class="layui-btn layui-btn-sm" lay-event="findStudent">查询
            <i class="layui-icon">&#xe615;</i>
        </button>
    </div>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">考试安排</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
    layui.use(['table','layer'], function(){
        var $ = layui.jquery,layer = layui.layer;
        var table = layui.table;
      var $table = table.render({
            elem: '#test'
            ,url:'<%=path%>/examResultController/selectStudentExamList'
            ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            ,title: '用户数据表'
            ,cols: [[
                {field:'erId', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
                ,{field:'sId',title:'学生Id',templet: '<div>{{d.student.sId}}</div>'}
                ,{title:'学生姓名',templet: '<div>{{d.student.sName}}</div>'}
                ,{title:'性别',templet: '<div>{{d.student.sSex}}</div>'}
                ,{field:'erState1', title:'科目一考试状态',sort: true}
                ,{field:'erState2', title:'科目二考试状态',sort: true}
                ,{field:'erState3', title:'科目三考试状态',sort: true}
                ,{field:'erState4', title:'科目四考试状态',sort: true}
                ,{field:'tName', title:'所属教练',sort: true}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
                ,{title:'所属教练ID',templet: '<div>{{d.student.sTeacherId}}</div>',width:80,hide:true}
            ]]
            ,page: {limit: 5,//指定每页显示的条数
                limits: [5, 10, 15, 20,
                    25, 30, 35, 40, 45, 50],}//每页条数的选择项
            ,done: function (res, curr, count) {
            $("[data-field='erState1'],[data-field='erState2'],[data-field='erState3'],[data-field='erState4']").children().each(function () {
                if ($(this).text() == '0') {
                    $(this).text("未通过")
                } else if ($(this).text() == '1') {
                    $(this).text("已通过")
                } else if ($(this).text() == '2'){
                    $(this).text("正在考试中")
                }
            });
        }
        });

        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'findStudent':
                    var data = checkStatus.data;
                    var inputname = $("input[name='inputname']").val();
                    table.reload('test',{
                        url:'<%=path%>/examResultController/selectStudentExamList',
                        page:{
                            curr:1//重第一页开始
                        },
                        where:{
                            studentName:"qwe",
                            sName:inputname
                        }
                    });
                    $("input[name='inputname']").val(inputname);
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){
            var examSujectId ="";
            var data = obj.data;
            var sId = data.student.sId;
            var erId = data.erId;
            var teacherId = data.student.sTeacherId;
          if(obj.event === 'edit'){
                $("#studentName").html(data.student.sName);
                var index = layer.open({
                    type: 1,
                    area:["400","300px"],
                    skin: 'layui-layer-rim',
                    shadeClose: true,//点击其他地方关闭
                    content:$("#scheduleExam"),
                    cancel:function (index) {
                        layer.close(index);
                    }
                });
                    var form = layui.form;
                    form.on('submit(demo1)', function(data){
                        examSujectId = $("#examSelect").val();
                        $.ajax({
                            type: 'POST',
                            url: '<%=path%>/examResultController/arringeExam',
                            dataType: 'JSON',
                            data: {
                                sId:sId,
                                erId:erId,
                                examSujectId:examSujectId,
                                teacherId:teacherId
                            },
                            success: function (remsg) {
                                if (remsg.code==0){
                                    layer.msg(remsg.msg);
                                    layer.close(index);
                                    $table.reload();
                                    $('#scheduleExam')[0].reset();//重置表单
                                    form.render();
                                }else{
                                    layer.msg(remsg.msg);
                                    layer.close(index);
                                    $('#scheduleExam')[0].reset();//重置表单
                                    form.render();
                                }

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
