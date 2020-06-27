<%--
  Created by IntelliJ IDEA.
  User: 刘海
  Date: 2020/6/6
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>驾校教练管理</title>
    <link rel="shortcut icon" href="#"/>
    <link rel="stylesheet" href=<%=path+"/static/layui/css/layui.css"%>>
    <script type="text/javascript" src=<%=path+"/static/layui/layui.js"%>></script>
</head>
<body>
<div class="layui-container" style="margin-top: 15px">
    <table class="layui-hide" id="test" lay-filter="test"></table>
</div>


<script type="text/html" id="toolbarDemo">
        <div class="layui-form-item" style="display:inline-block">
            <div class="layui-input-block">
                <input type="text" name="inputname" placeholder="请输入教练姓名查询" autocomplete="off" class="layui-input" style="width:100%;">
            </div>
        </div>
        <div class="layui-btn-container" style="display:inline-block">
            <button class="layui-btn layui-btn-sm" lay-event="findTeacher">查询
                <i class="layui-icon">&#xe615;</i>
            </button>
        </div>

</script>

<script type="text/html" id="barDemo">
    {{#  if(d.tTeach=='true'){}}
      <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="noRegistration">禁止学员报名</a>
    {{#  }else{}}
      <a class="layui-btn layui-btn-xs" lay-event="noRegistration">允许学员报名</a>
    {{#  } }}
    {{#  if(d.tLock=='true'){}}
      <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="accountLock">锁定账号</a>
    {{#  }else{}}
      <a class="layui-btn  layui-btn-xs" lay-event="accountLock">解锁账号</a>
    {{#  } }}
</script>

</body>
<script>
    layui.use(['table','layer'],function(){

        var table = layui.table,layer=layui.layer,$=layui.jquery;
        var $table =  table.render({
            elem: '#test'
            ,url:'<%=path%>/teacherController/findTeacherList'
            ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            ,title: '用户数据表'
            ,cols: [[
                {field:'tId', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
                ,{field:'account', title:'账号', edit: 'text'}
                ,{field:'tName', title:'姓名', edit: 'text'}
                ,{field:'tSfz', title:'身份证号'}
                ,{field:'tSex', title:'性别'}
                ,{field:'tTeach', title:'是否允许报名'}
                ,{field:'tLock', title:'是否锁定账号'}
                ,{field:'tPhone', title:'电话'}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo',width: 200}
            ]]
            ,page: {limit: 5,//指定每页显示的条数
                limits: [5, 10, 15, 20,
                    25, 30, 35, 40, 45, 50],},//每页条数的选择项
            done: function (res, curr, count) {
                $("[data-field='tTeach']").children().each(function () {
                    if ($(this).text() == 'true') {
                        $(this).text("允许")
                    } else if ($(this).text() == 'false') {
                        $(this).text("不允许")
                    }
                });
                $("[data-field='tLock']").children().each(function () {
                    if ($(this).text() == 'true') {
                        $(this).text("启用")
                    } else if ($(this).text() == 'false') {
                        $(this).text("禁用")
                    }
                });
            }
        });

        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            var $ = layui.jquery;
            switch(obj.event){
                 case 'findTeacher':
                     var data = checkStatus.data;
                     var inputname = $("input[name='inputname']").val();
                     table.reload('test',{
                         url:'<%=path%>/teacherController/findTeacherList',
                         page:{
                             curr:1//重第一页开始
                         },
                         where:{
                             studentName:"qwe",
                             tName:inputname
                         }
                     });
                     $("input[name='inputname']").val(inputname);
                     break;

            };
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){
            var $ = layui.jquery;
            var data = obj.data
            var tTeach = data.tTeach;
            if(obj.event === 'noRegistration'){
                layer.confirm('您确定要做此操作嘛',{
                    btn:["确定","取消"],
                    btn2:function (index) {
                        layer.close(index);
                    },
                    btn1:function () {
                        $.ajax({
                            type: 'POST',
                            url: '<%=path%>/teacherController/updateTeacherApplyState',
                            dataType: 'JSON',
                            data: {
                                tId: data.tId,
                                tTeach:tTeach
                            },
                            success: function (msg) {
                                if (msg.code == 1) {
                                    layer.msg("禁止报名成功");
                                    $table.reload();
                                } else {
                                    layer.msg("未找到教练相关信息");
                                }
                            }
                        });
                    }
                    });
            }else if (obj.event === 'accountLock'){
                var tLock = data.tLock;
                    layer.confirm('您确定要做此操作嘛',{
                            btn:["确定","取消"],
                            btn2:function (index) {
                                layer.close(index);
                            },
                            btn1:function () {
                                $.ajax({
                                    type: 'POST',
                                    url: '<%=path%>/teacherController/updateTeacherAccountLockState',
                                    dataType: 'JSON',
                                    data:{
                                        tId:data.tId,
                                        tLock:tLock
                                    },
                                    success: function (msg) {
                                        if(msg.code==1){
                                            layer.msg("您已成功锁定该教练账号,该教练下的学员需要您重新分配教练");
                                            $table.reload();
                                        }else{
                                            layer.msg("未找到该教练信息");
                                        }
                                    }
                                });
                            }
                        }
                    );
            }

        });
    });
</script>
</html>
