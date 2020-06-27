
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>驾校后台管理端</title>
    <link rel="stylesheet" href=<%=path+"/static/layui/css/layui.css"%>>
    <script type="text/javascript" src=<%=path+"/static/layui/layui.js"%>></script>
    <style>
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">传一驾校系统后台管理</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">用户管理</a></li>
            <li class="layui-nav-item"><a href="">用户中心</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    hello，${admin.acName}
<%--                    hello,${user.userName}--%>
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <div class="site-demo-button" id="layerDemo2" style="margin-bottom: 0;">
                    <button data-method="offset2" data-type="rt" class="layui-btn "style="background:transparent;">修改密码</button>
                </div>
            </li>
            <li class="layui-nav-item">
                <div class="site-demo-button" id="layerDemo" style="margin-bottom: 0;">
                    <button data-method="offset" data-type="rt" id="logout" class="layui-btn "style="background:transparent;">退出</button>
                </div>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">日常工作</a>
                    <dl class="layui-nav-child">
                        <dd><a href="studentmanage.jsp;" target="okframe">学员管理</a></dd>
                        <dd><a href="schoolmanage.jsp;" target="okframe">驾校管理</a></dd>
                        <dd><a href="carmanage.jsp;" target="okframe">教练车管理</a></dd>
                        <dd><a href="teachermanage.jsp;" target="okframe">教练管理</a></dd>
                        <dd><a href="studentmanage.jsp;" target="okframe">题库管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">统计报表</a>
                    <dl class="layui-nav-child">
                        <dd><a href="usermanage.jsp;" target="okframe">学员人数统计</a></dd>
                        <dd><a href="usermanage.jsp;" target="okframe">科目考试人数统计</a></dd>
<%--                        <dd><a href="">超链接</a></dd>--%>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">门户管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="usermanage.jsp;" target="okframe">行业动态发布</a></dd>
                        <dd><a href="usermanage.jsp;" target="okframe">友情链接管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">系统管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="usermanage.jsp;" target="okframe">日志管理</a></dd>
                        <dd><a href="usermanage.jsp;" target="okframe">参数管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">其他</a>
                    <dl class="layui-nav-child">
                        <dd><a href="usermanage.jsp;" target="okframe">退出</a></dd>
                        <dd><a href="usermanage.jsp;" target="okframe">修改账号密码</a></dd>
                        <dd><a href="usermanage.jsp;" target="okframe">登录</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <iframe name="okframe" frameborder="0" src="" style="width: 100%;height: 100%"></iframe>
    </div>

    <div class="layui-footer">
<%--        <!-- 底部固定区域 -->--%>
<%--        © layui.com - 底部固定区域--%>
    </div>
</div>
<%--<script src="../src/layui.js"></script>--%>

<script>
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
                        // layer.msg('账号退出成功，即将跳转到登录界面');
                        location.href = 'adminlogin.jsp'
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

<div id="updatepwd" style="display: none;margin-top: 20px">
    <form class="layui-form" >
        <div class="layui-form-item">
            <label class="layui-form-label">原始密码：</label>
            <div class="layui-input-inline">
                <input type="password" name="aPassword" placeholder="" autocomplete="off" class="layui-input" lay-verify="required">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">新密码：</label>
            <div class="layui-input-inline">
                <input type="password" name="newpwd" autocomplete="off" class="layui-input" lay-verify="required">
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
                <input type="hidden" name="aId" value=${adiminId}  class="layui-input">
                <button class="layui-btn layui-btn-primary" lay-submit lay-filter="formDemo"
                        style="margin-left:20px ">确认修改</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
