<%--
  Created by IntelliJ IDEA.
  User: 周永哲
  Date: 2020/6/11
  Time: 22:59
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>教练车管理</title>
    <link rel="stylesheet" href=<%=path+"/static/layui/css/layui.css"%>>
    <script type="text/javascript" src=<%=path+"/static/layui/layui.js"%>></script>
        <style>
            .layui-form-label {
                width: 100px;
            }
        </style>
    </head>
<body>

<table class="layui-hide" id="demo" lay-filter="demo"></table>
<script type="text/html" id="toolbarTest">
    <div class="demoTable">
        <div class="layui-inline">
            <input type="text" class="layui-input" name="cLogo" id="demoReload" autocomplete="off"placeholder="请输入车辆品牌">
        </div>
        <button class="layui-btn layui-btn-normal" lay-event="search"  lay-submit lay-filter="search" data-type="reload">搜索</button>
        <button class="layui-btn " lay-event="add" style="margin-left: 10%">添加教练车</button>
    </div>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="delete">删除教练车</a>
    <a class="layui-btn layui-btn-xs" lay-event="update">教练车信息修改</a>
</script>

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
            ,url: '/carControl/selectCarInfo' //数据接口
            ,title: '驾校表'
            ,page: true //开启分页
            ,limit: 5 //每行显示5页
            // ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            // ,totalRow: true //开启合计行
            // ,id: 'testReload'
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'cId', title: '序号', width:80, sort: true, fixed: 'left', }
                ,{field: 'cLogo', title: '车辆品牌', width:130}
                ,{field: 'cModel', title: '型号', width:100, sort: true, totalRow: true}
                ,{field: 'cColor', title: '颜色', width: 100}
                ,{field: 'cNumber', title: '车牌号码', width: 120}
                ,{field: 'schoolName', title: '所属驾校', width: 150}
                ,{field: 'tName', title: '所属教练', width: 110}
                // ,{field: 'sRegTime', title: '注册时间', width:90 }
                ,{field: 'cSchoolId', title: '驾校id', width:10, hide:'true'}
                ,{fixed: 'right', title: '管理操作',width: 220, align:'center', toolbar: '#barDemo'}
                ,{fixed: 'right', title: '备注', align:'center'}
            ]]
                ,page: {limit: 5,//指定每页显示的条数
                    limits: [5, 10, 15, 20,
                        25, 30, 35, 40, 45, 50],},//每页条数的选择项
        });

        //监听头工具栏事件
        var addstudent =null;
        var addschool = null;
            table.on('toolbar(demo)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id)
                ,data = checkStatus.data; //获取选中的数据
            switch(obj.event){
                case 'add':
                 addschool = layer.open({
                        type: 1,
                        title:"添加教练车"
                        ,area: ['400px','500px']
                        ,offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                        ,id: 'layerDemo'+'auto' //防止重复弹出
                        ,content: $('#add')
                        // ,btn: '关闭全部'
                        ,btnAlign: 'c' //按钮居中
                        ,shade: 0 //不显示遮罩
                        , yes: function(){
                            layer.closeAll();
                        }
                    });
                    break;
                case "search":
                    var demoReload = $('#demoReload').val();
                    //执行重载
                    table.reload('demo', {
                        url: "/carControl/selectCarInfo",
                        page: {
                            curr: 1 //重新从第 1 页开始
                        }
                        ,where: {
                            cLogo: demoReload,
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

            else if(layEvent === 'delete'){       //删除驾校
                var schoolId = data.sId;
                var sName= data.sName;
                layer.alert('schoolId='+schoolId);
                var data ={
                    schoolId: schoolId,
                    dealtype: "delete"
                }
                layer.alert('data='+JSON.stringify(data));
                layer.confirm('确认删除此驾校？', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    $.ajax({
                        url: "/carControl/deleteSchool",
                        type: "POST",
                        dataType: "text",
                        data: data,
                        success: function (msg) {
                            if (msg.trim() == "success") {
                                alert(sName+"删除成功！");
                                layer.close(addstudent);
                                location.reload();
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
            form.on('submit(formDemo)', function (data) {//添加驾校
                alert(JSON.stringify(data.field));
                // var account = $("input[name='account']").val();
                // var pwd = $("input[name='pwd']").val();
                // var pwd2 = $("input[name='pwd2']").val();
                    $.ajax({
                        url: "/schoolController/insertSchool",
                        type: "POST",
                        dataType: "text",
                        data: data.field,
                        success: function (msg) {
                            if (msg.trim() == "success") {
                                alert("添加成功！");
                                layer.close(addschool);
                                location.reload();
                            } else if (msg.trim() == "already") {
                                alert("此账号或驾校名已存在！");
                                layer.close(addschool);
                                location.reload();
                            } else {
                                alert(msg)
                            }
                        }
                    });
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

        // $('.demoTable .layui-btn').on('click', function(){
        //     var type = $(this).data('type');
        //     active[type] ? active[type].call(this) : '';
        // });

        $("#cancle").click(function () {
            layer.close(addstudent);
        })
        $("#cancle2").click(function () {
            layer.close(addschool);
        })
    });
</script>
<div id="add" style="display: none">
        <form class="layui-form" action="">
            <div class="layui-form-item" style="margin-top: 10px">
                <label class="layui-form-label">驾校名称：</label>
                <div class="layui-input-inline">
                    <input type="text" name="sName" placeholder="" autocomplete="off" class="layui-input" lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">账号：</label>
                <div class="layui-input-inline">
                    <input type="text" name="aAccount" placeholder="" autocomplete="off" class="layui-input"  lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码：</label>
                <div class="layui-input-inline">
                    <input type="password" name="aPassword" placeholder="" autocomplete="off" class="layui-input"  lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">驾校地址：</label>
                <div class="layui-input-inline">
                    <input type="text" name="sAddress" placeholder="" autocomplete="off" class="layui-input"
                           lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">报名电话：</label>
                <div class="layui-input-inline">
                    <input type="text" name="sPhone" placeholder="" autocomplete="off" class="layui-input"lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">统一信用代码：</label>
                <div class="layui-input-inline">
                    <input type="text" name="sBusinessId" placeholder="" autocomplete="off" class="layui-input"lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">法人身份证：</label>
                <div class="layui-input-inline">
                    <input type="text" name="sOwnerId" placeholder="" autocomplete="off" class="layui-input"lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="hidden" name="dealtype" value="addstudent" placeholder="" autocomplete="off" class="layui-input"
                           lay-verify="required">
                    <button class="layui-btn layui-btn-primary" lay-submit lay-filter="formDemo">添加</button>
                    <button type="button" id="cancle2"class="layui-btn layui-btn-primary" style="margin-right: 60px">取消</button>
                </div>
            </div>
        </form>
    </div>
    </form>
</div>
</body>
</html>

