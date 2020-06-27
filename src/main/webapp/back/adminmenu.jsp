<html class="x-admin-sm">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    if(request.getSession().getAttribute("admin")==null){
        response.sendRedirect("/back/adminlogin.jsp");
    }
%>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>驾校后台管理端</title>
    <link rel="stylesheet" href="<%=path%>/static/preadmin/component/layui/css/layui.css" />
    <link rel="stylesheet" href="<%=path%>/static/preadmin/admin/css/pearTab.css" />
    <link rel="stylesheet" href="<%=path%>/static/preadmin/admin/css/pearTheme.css" />
    <link rel="stylesheet" href="<%=path%>/static/preadmin/admin/css/pearLoad.css" />
    <link rel="stylesheet" href="<%=path%>/static/preadmin/admin/css/pearFrame.css" />
    <link rel="stylesheet" href="<%=path%>/static/preadmin/admin/css/pearAdmin.css" />
    <link rel="stylesheet" href="<%=path%>/static/preadmin/admin/css/pearNotice.css" />
    <link rel="stylesheet" href="<%=path%>/static/preadmin/admin/css/pearSocial.css" />
    <link rel="stylesheet" href="<%=path%>/static/preadmin/admin/css/pearMenu.css" />
    <style id="pearone-bg-color"></style>
    <style>
        .layui-btn{
            color: #cc0000;
            background-color: transparent;
        }
    </style>
</head>
<body class="layui-layout-body pear-admin">
<!-- 布局框架 -->
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <ul class="layui-nav layui-layout-left">
            <li class="collaspe layui-nav-item layui-hide-xs"><a href="#" class="layui-icon layui-icon-shrink-right"></a></li>
            <li class="refresh layui-nav-item"><a href="#" class="layui-icon layui-icon-refresh-1"></a></li>
        </ul>
        <div id="control" class="layui-layout-control"></div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item layui-hide-xs"><a href="#" class="fullScreen layui-icon layui-icon-screen-full"></a></li>
            <li class="layui-nav-item layui-hide-xs"><a href="http://www.pearadmin.cn" class="layui-icon layui-icon-website"></a></li>
            <li class="layui-nav-item layui-hide-xs" id="headerNotice"></li>
            <li class="layui-nav-item" lay-unselect="">
                <a href="javascript:;">hello，${admin.acName} <img src="<%=path%>/static/preadmin/admin/images/avatar.jpg" class="layui-nav-img"></a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" class="pearson">个人信息</a></dd>
                    <dd><a href="javascript:;">安全配置</a></dd>
                    <dd><a href="javascript:;">修改密码</a></dd>
                    <dd><a href="javascript:;">注销登陆</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <div class="site-demo-button" id="layerDemo2" style="margin-bottom: 0;">
                    <button data-method="offset2" data-type="rt" class="layui-btn">修改密码</button>
                </div>
            </li>
            <li class="layui-nav-item">
                <div class="site-demo-button" id="layerDemo" style="margin-bottom: 0;">
                    <button data-method="offset" data-type="rt" id="logout" class="layui-btn">退出</button>
                </div>
            </li>
            <li class="setting layui-nav-item"><a href="#" class="layui-icon layui-icon-more-vertical"></a></li>
        </ul>
    </div>
    <div class="layui-side layui-bg-black">
        <div class="layui-logo">
            <img class="logo" src="<%=path%>/static/preadmin/admin/images/logo.png" />
            <span class="title">传一驾校系统后台管理</span>
        </div>
        <div class="layui-side-scroll">
            <div id="sideMenu"></div>
        </div>
    </div>
    <div class="layui-body">
        <div id="content"></div>
    </div>
</div>

<!-- 移动端 遮盖层 -->
<div class="pear-cover"></div>

<!-- 初始加载 动画-->
<div class="loader-main">
    <div class="loader"></div>
</div>

<!-- 移动端 的 收缩适配 -->
<div class="collaspe pe-collaspe layui-hide-sm">
    <i class="layui-icon layui-icon-shrink-right"></i>
</div>

<script src="<%=path%>/static/preadmin/component/layui/layui.js"></script>
<script>
    layui.use(['pearAdmin', 'jquery', 'layer', 'pearTab', 'pearNotice'], function() {
        var pearAdmin = layui.pearAdmin;
        var $ = layui.jquery;
        var pearTab = layui.pearTab;
        var pearNotice = layui.pearNotice;
        var layer = layui.layer;

        var config = {
            keepLoad: 2000, // 主 页 加 载 过 度 时 长 可为 false
            muiltTab: true, // 是 否 开 启 多 标 签 页 true 开启 false 关闭
            control: false, // 是 否 开 启 多 系 统 菜 单 true 开启 false 关闭
            theme: "dark-theme", // 默 认 主 题 样 式 dark-theme 默认主题 light-theme 亮主题
            index: '<%=path%>/static/preadmin/view/console/console1.html', // 默 认 加 载 主 页
            data: '<%=path%>/adminControl/adminList', // 菜 单 数 据 加 载 地 址
        };

        var option = {
            elem: 'headerNotice',
            url: '<%=path%>/static/preadmin/admin/data/notice.json',
            height: '250px',
            click: function(id, title) {
                layer.alert("点击消息 : " + id);
            }
        }
        pearAdmin.render(config);
        pearNotice.render(option);
    });

    layui.use(['layer','element'], function(){ //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer, element = layui.element; //独立版的layer无需执行这一句
        //触发事件
        var active = {
            offset: function (othis) {
                var type = othis.data('type')
                    , text = othis.text();
                layer.open({
                    type: 1
                    , title: '退出提示'
                    , offset: 'rt' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                    , id: 'layerDemo' + 'type' //防止重复弹出
                    , content: '<div style="padding: 20px 100px;">' + '确认退出当前账号?' + '</div>'
                    , btn: ['确认', '取消']
                    , btnAlign: 'c' //按钮居中
                    , shade: 0 //不显示遮罩
                    , yes: function () {
                        // alert('账号退出成功，即将跳转到登录界面');
                        // location.href = 'adminlogin.jsp'
                        $.ajax({
                            url: "/adminControl/logout",
                            type: "POST",
                            dataType: "text",
                            data:{
                                logout: 'logout'
                            },
                            success: function (msg) {
                                if (msg.trim() == "success") {
                                    alert('账号退出成功，即将跳转到登录界面');
                                    location.href = 'adminlogin.jsp'
                                } else {
                                    alert("账号退出失败！")
                                }
                            }
                        });
                    }
                    , cancel: function () {
                        layer.closeAll();
                    }
                });
            },

            offset2: function (othis) {
                var type = othis.data('type')
                    , text = othis.text();
                layer.open({
                    type: 1
                    ,title:'修改密码'
                    ,offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                    ,id: 'layerDemo'+'type' //防止重复弹出
                    ,content: $('#updatepwd')
                    ,area:['350px','320px']
                    ,btn: '取消'
                    ,btnAlign: 'c' //按钮居中
                    ,shade: 0 //不显示遮罩
                    ,yes: function(){
                        layer.closeAll();
                    }
                });

         layui.use(['layer', 'form','jquery'], function () {
                    var layer = layui.layer
                        , form = layui.form;
                    var $ = layui.jquery;
                form.on('submit(formDemo)', function (data) {
                    alert("data="+JSON.stringify(data.field));
                    var pwd = $("input[name='acPassword']").val();
                    var pwd2 = $("input[name='newpwd2']").val();
                    if (pwd != pwd2) {
                        alert("密码不一致！")
                    }else {
                        $.ajax({
                            url: "/adminControl/updatePwd",
                            type: "POST",
                            dataType: "text",
                            data: data.field,
                            success: function (msg) {
                                if (msg.trim() == "success") {
                                    // layer.alert("密码修改成功，!即将跳转到登录界面");
                                    // location.href = 'adminlogin.jsp';
                                    layer.confirm('密码修改成功!返回登录界面', {
                                        btn: ['确定'] //可以无限个按钮
                                        ,btn1: function(index, layero){
                                            layer.close(index)
                                            location.href = 'adminlogin.jsp';
                                        }
                                        ,cancel:function (index) {
                                            layer.close(index);
                                            location.href = 'adminlogin.jsp';
                                        }
                                    });
                                    //
                                } else {
                                    alert(msg)
                                }
                            }
                        });
                        return false;
                    }
                });
              });
            }
        };

        $('#layerDemo .layui-btn').on('click', function(){
            var othis = $(this), method = othis.data('method');
            active[method] ? active[method].call(this, othis) : '';
        });

        $('#layerDemo2 .layui-btn').on('click', function(){
            var othis = $(this), method = othis.data('method');
            active[method] ? active[method].call(this, othis) : '';
        });

    });
</script>
<!-- 新 增 百 度 统 计  ( 可 移 除 )-->
<script>
    // var _hmt = _hmt || [];
    // (function() {
    //     var hm = document.createElement("script");
    //     hm.src = "https://hm.baidu.com/hm.js?6ab27a14480b19bc9ce3eef146674287";
    //     var s = document.getElementsByTagName("script")[0];
    //     s.parentNode.insertBefore(hm, s);
    // })();
</script>
<div id="updatepwd" style="display: none;margin-top: 20px">
    <form class="layui-form" >
        <div class="layui-form-item">
            <label class="layui-form-label">原始密码：</label>
            <div class="layui-input-inline">
                <input type="password" name="aPassword" value=${admin.acPassword} placeholder="" autocomplete="off" class="layui-input" lay-verify="required">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">新密码：</label>
            <div class="layui-input-inline">
                <input type="password" name="acPassword" autocomplete="off" class="layui-input" lay-verify="required">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">再次输入：</label>
            <div class="layui-input-inline">
                <input type="password" name="newpwd2"  autocomplete="off" class="layui-input" lay-verify="required">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <input type="hidden" name="acId" value=${admin.acId}  class="layui-input">
                <button class="layui-btn layui-btn-primary" lay-submit lay-filter="formDemo" style="margin-left:20px ">确认修改</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>