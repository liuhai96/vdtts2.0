
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>学员管理</title>
    <link rel="stylesheet" href=<%=path+"/static/layui/css/layui.css"%>>
    <script type="text/javascript" src=<%=path+"/static/layui/layui.js"%>></script>
        <style>
            body{margin: 10px;}
            .demo-carousel{height: 200px; line-height: 200px; text-align: center;}
        </style>
    </head>
<body>

<table class="layui-hide" id="demo" lay-filter="demo"></table>
<script type="text/html" id="toolbarTest">
    <div class="demoTable">
        <div class="layui-inline">
            <input type="text" class="layui-input" name="studentname" id="demoReload" autocomplete="off"placeholder="请输入学员姓名">
        </div>
        <button class="layui-btn layui-btn-normal" lay-event="search"  lay-submit lay-filter="search" data-type="reload">搜索</button>
        <button class="layui-btn " lay-event="add" style="margin-left: 10%">添加学员</button>
    </div>
</script>

<script type="text/html" id="barDemo">
<%--    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>--%>
<%--    <a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>--%>
    <a class="layui-btn layui-btn-xs" lay-event="resetpwd">重置密码</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除/禁用</a>
</script>

<%--<script src="//res.layui.com/layui/dist/layui.js?t=1586046995289"></script>--%>
<script>
    layui.use(['jquery','laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider'], function(){
        var $ = layui.jquery,
            laydate = layui.laydate //日期
            ,laypage = layui.laypage //分页
            ,layer = layui.layer //弹层
            ,table = layui.table //表格
            ,carousel = layui.carousel //轮播
            ,upload = layui.upload //上传
            ,element = layui.element //元素操作
            ,slider = layui.slider //滑块


        //执行一个 table 实例
    table.render({
            elem: '#demo'
            ,height: 420
            ,method: "POST"
            ,toolbar: '#toolbarTest' //开启头部工具栏，并为其绑定左侧模板
            ,url: '/studentController/selectStudentInfo' //数据接口
            ,title: '学员表'
            ,page: true //开启分页
            ,limit: 5 //每行显示5页
            // ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            // ,totalRow: true //开启合计行
            // ,id: 'testReload'
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'sId', title: '序号', width:80, sort: true, fixed: 'left', }
                ,{field: 'sName', title: '姓名', width:80}
                ,{field: 'sAccountId', title: '账号', width:80, sort: true, totalRow: true}
                ,{field: 'teacherName', title: '所属教练', width: 120}
                ,{field: 'schoolName', title: '所属驾校', width: 100}
                ,{field: 'sSex', title: '性别', width:80, sort: true}
                ,{field: 'sSfz', title: '身份证号', width: 170, sort: true, totalRow: true}
                ,{field: 'sPhone', title: '电话号码', width: 120, sort: true, totalRow: true}
                ,{field: 'sRegTime', title: '注册时间', width:110}
                ,{field: 'sLicenseTime', title: '获取驾照时间', width:130}
                // ,{field: 'aId', title: '账号id', width:10, hide:'true'}
                ,{fixed: 'right', title: '管理操作',width: 175, align:'center', toolbar: '#barDemo'}
            ]]
            ,page: {limit: 5,//指定每页显示的条数
            limits: [5, 10, 15, 20,
                25, 30, 35, 40, 45, 50],},//每页条数的选择项
        });

        //监听头工具栏事件
        var addstudent =null;
        var studentId2 = null;
            table.on('toolbar(demo)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id)
                ,data = checkStatus.data; //获取选中的数据
            switch(obj.event){
                // case 'add':
                //  addstudent = layer.open({
                //         type: 1,
                //         title:"学员添加"
                //         , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                //         ,id: 'layerDemo'+'auto' //防止重复弹出
                //         ,content: $('#add')
                //         // ,btn: '关闭全部'
                //         ,btnAlign: 'c' //按钮居中
                //         ,shade: 0 //不显示遮罩
                //         , yes: function(){
                //             layer.closeAll();
                //         }
                //     });
                //     // layer.msg('添加');
                //     break;
                // case 'update':
                //     if(data.length === 0){
                //         layer.msg('请选择一行');
                //     } else if(data.length > 1){
                //         layer.msg('只能同时编辑一个');
                //     } else {
                //         layer.alert('编辑 [id]：'+ checkStatus.data[0].id);
                //     }
                //     break;
                // case 'delete':
                //     if(data.length === 0){
                //         layer.msg('请选择一行');
                //     } else {
                //         layer.msg('删除');
                //     }
                //     break;
                case "search":
                    var demoReload = $('#demoReload').val();
                    //执行重载
                    table.reload('demo', {
                        url: "/studentController/selectStudentInfo",
                        page: {
                            curr: 1 //重新从第 1 页开始
                        }
                        ,where: {
                            sName: demoReload,
                            dealtype: "search",
                        }
                    });
                    $('#demoReload').val(demoReload);
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(demo)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                layer.msg('查看操作');
            }

            else if(layEvent === 'resetpwd'){       //重置密码
                var accountId = data.sAccountId;
                var studentName= data.sName;
                layer.alert('accountId='+accountId);
                var data ={
                    accountId: accountId,
                    dealtype: "delete"
                }
                layer.alert('data='+JSON.stringify(data));
                layer.confirm('确认重置密码？', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    $.ajax({
                        url: "/studentController/resetPwd",
                        type: "POST",
                        dataType: "text",
                        data: data,
                        success: function (msg) {
                            if (msg.trim() == "success") {
                                alert("学员"+studentName+"重置密码成功！");
                                layer.close(addstudent);
                                // location.href = "userlogin.jsp";
                            } else {
                                alert(msg)
                            }
                        }
                    });
                    //向服务端发送删除指令
                });
            }
        //     else if(layEvent === 'del'){       //删除/禁用学员
        //         var studentId = data.studentId;
        //        var studentName= data.studentName;
        //         layer.alert('studentId='+studentId);
        //         var data ={
        //             studentId: studentId,
        //             dealtype: "delete"
        //         }
        //         layer.alert('data='+JSON.stringify(data));
        //         layer.confirm('确认删除此学员信息？', function(index){
        //             obj.del(); //删除对应行（tr）的DOM结构
        //             layer.close(index);
        //             $.ajax({
        //                 url: "/sharefile/studentControl/deletestudent",
        //                 type: "POST",
        //                 dataType: "text",
        //                 data: data,
        //                 success: function (msg) {
        //                     if (msg.trim() == "success") {
        //                         alert("学员"+studentName+"删除成功！");
        //                         layer.close(addstudent);
        //                         // location.href = "userlogin.jsp";
        //                     } else {
        //                         alert(msg)
        //                     }
        //                 }
        //             });
        //             //向服务端发送删除指令
        //         });
        //     }
        //
        //     else if(layEvent === 'edit'){ //修改学员
        //        studentId2 = data.studentId;
        //         addstudent = layer.open({
        //             type: 1,
        //             title:"学员信息修改"
        //             , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
        //             ,id: 'layerDemo'+'auto' //防止重复弹出
        //             ,content: $('#update')
        //             // ,btn: '关闭全部'
        //             ,btnAlign: 'c' //按钮居中
        //             ,shade: 0 //不显示遮罩
        //             , yes: function(){
        //                 layer.closeAll();
        //             }
        //         });
        //     }
        });

        layui.use(['layer', 'form'], function () {
            var layer = layui.layer
                , form = layui.form;
            var $ = layui.jquery;
            form.on('submit(formDemo1)', function (data) {//添加学员
                alert(JSON.stringify(data.field));
                var account = $("input[name='account']").val();
                var pwd = $("input[name='pwd']").val();
                var pwd2 = $("input[name='pwd2']").val();
                if (pwd != pwd2) {
                    alert("密码不一致！")
                } else if (account == '' || pwd == '' || pwd2 == '') {
                    alert("姓名或密码不能为空！")
                } else if (pwd == pwd2) {
                    $.ajax({
                        url: "/sharefile/studentControl/insertstudent",
                        type: "POST",
                        dataType: "text",
                        data: data.field,
                        success: function (msg) {
                            if (msg.trim() == "success") {
                                alert("学员添加成功！");
                                layer.close(addstudent);
                                // location.href = "userlogin.jsp";
                            } else {
                                alert(msg)
                            }
                        }
                    });

                }
                return false;
            });
        });

        // layui.use(['layer', 'form'], function () {
        //     var layer = layui.layer
        //         , form = layui.form;
        //     var $ = layui.jquery;
        //     form.on('submit(formDemo)', function (data) {//修改学员信息
        //         // alert(JSON.stringify(data.field));
        //         var account = $("input[name='account1']").val();
        //         var pwd = $("input[name='studentpwd']").val();
        //         var pwd2 = $("input[name='studentpwd2']").val();
        //         var data={
        //             account1:account,
        //             pwd:pwd,
        //             dealtype: "updatestudent",
        //             studentId2:studentId2,
        //         }
        //         alert("data="+JSON.stringify(data));
        //         if (pwd != pwd2) {
        //             alert("密码不一致！")
        //         } else if (account == '' || pwd == '' || pwd2 == '') {
        //             alert("姓名或密码不能为空!！")
        //         } else if (pwd == pwd2) {
        //             $.ajax({
        //                 url: "/sharefile/studentControl/updatestudent",
        //                 type: "POST",
        //                 dataType: "text",
        //                 data: data,
        //                 success: function (msg) {
        //                     if (msg.trim() == "success") {
        //                         alert("学员信息修改成功！");
        //                         layer.close(addstudent);
        //                         // location.href = "userlogin.jsp";
        //                     } else {
        //                         alert(msg)
        //                     }
        //                 }
        //             });
        //
        //         }
        //         return false;
        //     });
        // });

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $("#cancle").click(function () {
            layer.close(addstudent);
        })
        $("#cancle2").click(function () {
            layer.close(addstudent);
        })
    });
</script>
<div id="add" style="display: none">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <label class="layui-form-label">姓名：</label>
                <div class="layui-input-inline">
                    <input type="text" name="account" placeholder="" autocomplete="off" class="layui-input"
                           lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码：</label>
                <div class="layui-input-inline">
                    <input type="password" name="pwd" placeholder="" autocomplete="off" class="layui-input"
                           lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">再次输入：</label>
                <div class="layui-input-inline">
                    <input type="password" name="pwd2" placeholder="" autocomplete="off" class="layui-input"
                           lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">角色：</label>
                <div class="layui-input-inline">
                    <select name="role" lay-filter="aihao">
                        <option value=""></option>
                        <option value="boss">boss</option>
                        <option value="学员" selected="">学员</option>
                        <option value="普通人员">普通人员</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">性别：</label>
                <div class="layui-input-block">
                   <input type="radio" name="sex" value="男" title="男" checked="">
                    <input type="radio" name="sex" value="女" title="女">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="hidden" name="dealtype" value="addstudent" placeholder="" autocomplete="off" class="layui-input"
                           lay-verify="required">
                    <button class="layui-btn layui-btn-primary" lay-submit lay-filter="formDemo1">添加</button>
                    <button type="button" id="cancle2"class="layui-btn layui-btn-primary" style="margin-right: 60px">取消</button>
                </div>
            </div>
        </form>
</div>
<div id="update" style="display: none">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">姓名：</label>
            <div class="layui-input-inline">
                <input type="text" name="account1" placeholder="" autocomplete="off" class="layui-input"
                       lay-verify="required" >
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码：</label>
            <div class="layui-input-inline">
                <input type="password" name="studentpwd" placeholder="" autocomplete="off" class="layui-input"
                       lay-verify="required">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">再次输入：</label>
            <div class="layui-input-inline">
                <input type="password" name="studentpwd2" placeholder="" autocomplete="off" class="layui-input"
                       lay-verify="required">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">角色：</label>
            <div class="layui-input-inline">
                <select name="role1" lay-filter="aihao">
                    <option value=""></option>
                    <option value="boss">boss</option>
                    <option value="学员" selected="">学员</option>
                    <option value="普通人员">普通人员</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别：</label>
            <div class="layui-input-block">
                <input type="radio" name="sex1" value="男" title="男" checked="">
                <input type="radio" name="sex1" value="女" title="女">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <input type="hidden" name="dealtype" value="updatestudent" placeholder="" autocomplete="off" class="layui-input"
                       lay-verify="required">
<%--                <input type="hidden" name="studentId2" value="" placeholder="" autocomplete="off" class="layui-input"--%>
<%--                       lay-verify="required">--%>
                <button class="layui-btn layui-btn-primary" lay-submit lay-filter="formDemo">确认修改</button>
                <button type="button" id="cancle" class="layui-btn layui-btn-primary" style="margin-right: 60px">取消</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>

