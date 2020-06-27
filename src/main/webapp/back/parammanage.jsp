
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>参数管理</title>
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
            <input type="text" class="layui-input" name="pmKey" id="demoReload" autocomplete="off"placeholder="请输入科目名称">
        </div>
        <button class="layui-btn layui-btn-normal" lay-event="search"  lay-submit lay-filter="search" data-type="reload">搜索</button>
<%--        <button class="layui-btn " lay-event="add" style="margin-left: 10%">添加驾校</button>--%>
    </div>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="updateinfo">修改学时信息</a>
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
            ,toolbar: '#toolbarTest' //开启头部工具栏，并为其绑定左侧模板
            ,url: '/schoolController/selectParamInfo' //数据接口
            ,title: '参数表'
            ,page: true //开启分页
            ,limit: 5 //每行显示5页
            // ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            // ,totalRow: true //开启合计行
            // ,id: 'testReload'
            ,where: {
                pmKey: ""
            }
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'pmId', title: '序号', width:80, sort: true, fixed: 'left', }
                ,{field: 'pmKey', title: '驾考科目', width:120}
                ,{field: 'pmValue', title: '驾考科目学时', width:150, sort: true, totalRow: true}
                ,{field: 'pmDescribe', title: '驾考科目学时描述', width: 300}
                ,{fixed: 'right', title: '管理操作',width: 175, align:'center', toolbar: '#barDemo'}
                // ,{field: 'sAccountId', title: '账号id', hide:'true'}
                ,{fixed: 'right', title: '备注', align:'center'}
            ]]
                ,page: {limit: 5,//指定每页显示的条数
                    limits: [5, 10, 15, 20,
                        25, 30, 35, 40, 45, 50],},//每页条数的选择项
        });

        //监听头工具栏事件
            table.on('toolbar(demo)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id)
                ,data = checkStatus.data; //获取选中的数据
            switch(obj.event){
                case "search":
                    var demoReload = $('#demoReload').val();
                    //执行重载
                    table.reload('demo', {
                        url: "/schoolController/selectParamInfo",
                        page: {
                            curr: 1 //重新从第 1 页开始
                        }
                        ,where: {
                            pmKey: demoReload
                        }
                    });
                    $('#demoReload').val(demoReload);
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(demo)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
            var schoolId = data.sId
            var layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                layer.msg('查看操作');
            }

            else if(layEvent === 'updateinfo'){ //修改信息
                $("#pmId").val(data.pmId);
                $("#pmKey").val(data.pmKey);
                $("#pmValue").val(data.pmValue);
                $("#pmDescribe").val(data.pmDescribe);
                updateinfo = layer.open({
                    type: 1
                    ,title:"科目学时信息修改"
                    ,area:['400px','400px']
                    , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                    ,id: 'layerDemo'+'auto' //防止重复弹出
                    ,content: $('#updateinfo')
                    // ,btn: '关闭全部'
                    ,btnAlign: 'c' //按钮居中
                    ,shade: 0 //不显示遮罩
                        // , yes: function(){
                        //     layer.closeAll();
                        // }
                });
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    var $ = layui.jquery;
                    form.on('submit(formDemo2)', function (data) {//修改参数信息
                        alert(JSON.stringify(data.field)+"pmId="+pmId);
                        // var updatedata={
                        //     sName:data.field.sName,
                        //     sAddress:data.field.sAddress,
                        //     sId: schoolId,
                        //     sPhone:data.field.sPhone,
                        //     sRecruit:data.field.sRecruit,
                        //     sLock:data.field.sLock,
                        // }
                        $.ajax({
                            url: "/schoolController/updateParam",
                            type: "POST",
                            dataType: "text",
                            data: data.field,
                            success: function (msg) {
                                if (msg.trim() == "success") {
                                    alert("参数信息修改成功！");
                                    layer.close(updateinfo);
                                    window.location.reload();
                                } else {
                                    alert("参数信息修改失败！")
                                }
                            }
                        });
                        return false;
                    });
                });

            }

        });

        $("#cancle").click(function () {
            layer.close(updateinfo);
        })
    });
</script>
<div id="updateinfo" style="display: none">
    <form class="layui-form" action="">
        <div class="layui-form-item" style="margin-top: 10px">
            <label class="layui-form-label">科目名称：</label>
            <div class="layui-input-inline">
                <input type="text" name="pmKey" id="pmKey" autocomplete="off" class="layui-input" lay-verify="required">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">科目学时：</label>
            <div class="layui-input-inline">
                <input type="text" name="pmValue" id="pmValue" autocomplete="off" class="layui-input"  lay-verify="required">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">科目描述：</label>
            <div class="layui-input-inline">
                <input type="text" name="pmDescribe" id="pmDescribe" autocomplete="off" class="layui-input"  lay-verify="required">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <input type="hidden" name="pmId"  id="pmId" class="layui-input" >
                <button class="layui-btn layui-btn-primary" lay-submit lay-filter="formDemo2">确认修改</button>
                <button type="button" id="cancle"class="layui-btn layui-btn-primary" style="margin-right: 60px">取消</button>
            </div>
        </div>
    </form>
</div>
    </form>
</div>
</body>
</html>

