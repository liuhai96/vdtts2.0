
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>驾校管理</title>
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
            <input type="text" class="layui-input" name="studentname" id="demoReload" autocomplete="off"placeholder="请输入驾校名称">
        </div>
        <button class="layui-btn layui-btn-normal" lay-event="search"  lay-submit lay-filter="search" data-type="reload">搜索</button>
        <button class="layui-btn " lay-event="add" style="margin-left: 10%">添加驾校</button>
    </div>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="updateinfo">修改信息</a>
    <a class="layui-btn layui-btn-xs" lay-event="addcar">添加教练车</a>
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
            ,url: '/schoolController/selectSchoolInfo' //数据接口
            ,title: '驾校表'
            ,page: true //开启分页
            ,limit: 5 //每行显示5页
            // ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            // ,totalRow: true //开启合计行
            // ,id: 'testReload'
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'sId', title: '序号', width:80, sort: true, fixed: 'left', }
                ,{field: 'sName', title: '驾校名称', width:90}
                ,{field: 'sAccount', title: '账号', width:80, sort: true, totalRow: true}
                ,{field: 'sAddress', title: '驾校地址', width: 90}
                ,{field: 'sPhone', title: '报名电话', width: 90}
                ,{field: 'sBusinessId', title: '统一信用代码', width: 120}
                ,{field: 'sOwnerId', title: '法人身份证', width: 110}
                ,{field: 'sRegTime', title: '注册时间', width:90 }
                ,{field: 'sRecruit', title: '允许招生', width:90}
                ,{field: 'sRecruit', title: '允许登录', width:90}
                ,{field: 'sVerification', title: '审核状态', width:90}
                ,{field: 'sLock', title: '锁定', width:70}
                ,{field: 'sAccountId', title: '账号id', width:10, hide:'true'}
                ,{fixed: 'right', title: '管理操作',width: 175, align:'center', toolbar: '#barDemo'}
            ]]
                ,page: {limit: 5,//指定每页显示的条数
                    limits: [5, 10, 15, 20,
                        25, 30, 35, 40, 45, 50],},//每页条数的选择项
        });

        //监听头工具栏事件
        var addcar =null;
        var addschool = null;
            table.on('toolbar(demo)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id)
                ,data = checkStatus.data; //获取选中的数据
            switch(obj.event){
                case 'add':
                 addschool = layer.open({
                        type: 1,
                        title:"添加驾校"
                        ,area: ['400px','500px']
                        ,offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                        ,id: 'layerDemo'+'auto' //防止重复弹出
                        ,content: $('#addschool')
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
                        url: "/schoolController/selectSchoolInfo",
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

        layui.use(['layer', 'form','jquery'], function () {
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
        //监听行工具事件
        table.on('tool(demo)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
            // alert("data ="+JSON.stringify(data));
            var schoolId = data.sId
            var layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                layer.msg('查看操作');
            }

            else if(layEvent === 'updateinfo'){ //修改信息
                 $("input[name='sId']").val(schoolId);
                 $("#sName").val(data.sName);
                 $("#sAddress").val(data.sAddress);
                 $("#sPhone").val(data.sPhone);
                 // $("input[name=sRecruit][value=true]").attr("checked", data.sRecruit == true ? true : false);
                 // $("input[name=sRecruit][value=false]").attr("checked", data.sRecruit == false ? true : false);
                 // layer.alert(JSON.stringify(data));
                updateinfo = layer.open({
                    type: 1
                    ,title:"驾校信息修改"
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
                    form.on('submit(formDemo2)', function (data) {//修改驾校信息
                        alert(JSON.stringify(data.field)+"schoolid="+schoolId);
                        $.ajax({
                            url: "/schoolController/updateSchool",
                            type: "POST",
                            dataType: "text",
                            data: data.field,
                            success: function (msg) {
                                if (msg.trim() == "success") {
                                    alert("驾校信息修改成功！");
                                    layer.close(updateinfo);
                                    window.location.reload();
                                } else {
                                    alert("驾校信息修改失败！")
                                }
                            }
                        });
                        return false;
                    });
                });

            }
            else if(layEvent === 'addcar'){       //添加教练车
                // var data ={
                //     schoolId: schoolId,
                //     dealtype: "addcar"
                // }
                $("#schoolId").val(data.sId);
                // layer.alert('schoolId='+schoolId);
                // layer.alert('data='+JSON.stringify(data));
                    addcar = layer.open({
                        type: 1,
                        title:"添加教练车"
                        ,area:['400px','350px']
                        , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                        ,id: 'layerDemo'+'auto' //防止重复弹出
                        ,content: $('#addcar')
                        // ,btn: '关闭全部'
                        ,btnAlign: 'c' //按钮居中
                        ,shade: 0 //不显示遮罩
                        , yes: function(){
                            layer.closeAll();
                        }
                    });
                }

        });


        layui.use(['layer', 'form','jquery'], function () {
            var layer = layui.layer
                , form = layui.form;
            var $ = layui.jquery;
            form.on('submit(formDemo1)', function (data) {//添加教练车
                // alert(JSON.stringify(data.field));
                // var data={
                //     cLogo:cLogo,
                //     cModel:cModel,
                //     sId: schoolId,
                //     cColor:cColor,
                //     cNumber:cNumber,
                // }
                alert("data="+JSON.stringify(data));
                    $.ajax({
                        url: "/carControl/insertCar",
                        type: "POST",
                        dataType: "text",
                        data: data.field,
                        success: function (msg) {
                            if (msg.trim() == "success") {
                                alert("教练车添加成功！");
                                layer.close(addcar);
                                // location.href = "userlogin.jsp";
                            } else {
                                alert(msg)
                            }
                        }
                    });
                return false;
            });
        });

        // $('.demoTable .layui-btn').on('click', function(){
        //     var type = $(this).data('type');
        //     active[type] ? active[type].call(this) : '';
        // });

        $("#cancle").click(function () {
            layer.close(updateinfo);
        })
        $("#cancle2").click(function () {
            layer.close(addschool);
        })
        $("#cancle3").click(function () {
            layer.close(addcar);
        })
    });
</script>
<div id="addschool" style="display: none">
    <form class="layui-form" action="">
        <div class="layui-form-item" style="margin-top: 10px">
            <label class="layui-form-label">驾校名称：</label>
            <div class="layui-input-inline">
                <input type="text" name="sName"  autocomplete="off" class="layui-input" lay-verify="required">
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
<%--                <input type="hidden" name="dealtype" value="addstudent" placeholder="" autocomplete="off" class="layui-input"--%>
<%--                       lay-verify="required">--%>
                <button class="layui-btn layui-btn-primary" lay-submit lay-filter="formDemo">添加</button>
                <button type="button" id="cancle2"class="layui-btn layui-btn-primary" style="margin-right: 60px">取消</button>
            </div>
        </div>
    </form>
</div>

<div id="addcar" style="display: none">
    <form class="layui-form" action="">
        <div class="layui-form-item" style="margin-top: 10px">
            <label class="layui-form-label">汽车品牌：</label>
            <div class="layui-input-inline">
                <select name="cLogo" lay-filter="aihao">
<%--                    <option value=""></option>--%>
                    <option value="奔驰">奔驰</option>
                    <option value="科尼塞克" selected="">科尼塞克</option>
                    <option value="奥迪">奥迪</option>
                    <option value="宝马">宝马</option>
                    <option value="丰田">丰田</option>
                    <option value="本田">本田</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">车型：</label>
            <div class="layui-input-inline">
                <select name="cModel" lay-filter="aihao">
<%--                    <option value=""></option>--%>
                    <option value="轿车">轿车</option>
                    <option value="SUV" selected="">SUV</option>
                    <option value="MPV">MPV</option>
                    <option value="卡车">卡车</option>
                    <option value="货车">货车</option>
                    <option value="公交车">公交车</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">颜色：</label>
            <div class="layui-input-inline">
                <select name="cColor" lay-filter="aihao">
<%--                    <option value=""></option>--%>
                    <option value="白色">白色</option>
                    <option value="红色" selected="">红色</option>
                    <option value="蓝色">蓝色</option>
                    <option value="粉色">粉色</option>
                    <option value="黑色">黑色</option>
                    <option value="绿色">绿色</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">车牌号码：</label>
            <div class="layui-input-inline">
                <input type="text" name="cNumber" value="闽D88888" placeholder="" autocomplete="off" class="layui-input"lay-verify="required">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <input type="hidden" name="cSchoolId" id="schoolId"  autocomplete="off" class="layui-input">
                <button class="layui-btn layui-btn-primary" lay-submit lay-filter="formDemo1">确认</button>
                <button type="button" id="cancle3"class="layui-btn layui-btn-primary" style="margin-right: 60px">取消</button>
            </div>
        </div>
    </form>
</div>

<div id="updateinfo" style="display: none">
    <form class="layui-form" action="">
        <div class="layui-form-item" style="margin-top: 10px">
            <label class="layui-form-label">驾校名称：</label>
            <div class="layui-input-inline">
                <input type="text" name="sName" id="sName" autocomplete="off" class="layui-input" lay-verify="required">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">驾校地址：</label>
            <div class="layui-input-inline">
                <input type="text" name="sAddress" id="sAddress" autocomplete="off" class="layui-input"  lay-verify="required">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">联系电话：</label>
            <div class="layui-input-inline">
                <input type="text" name="sPhone" id="sPhone" autocomplete="off" class="layui-input"  lay-verify="required">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">允许招生：</label>
            <div class="layui-input-block">
                <input type="radio" name="sRecruit" id="sRecruit1" value="true" title="允许"checked="" >
                <input type="radio" name="sRecruit" id="sRecruit2" value="false" title="禁止">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">允许登录：</label>
            <div class="layui-input-block">
                <input type="radio" name="sLock" value="true" title="允许" checked="">
                <input type="radio" name="sLock" value="false" title="禁止">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <input type="hidden" name="sId" value="${schoolId}"  class="layui-input">
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

