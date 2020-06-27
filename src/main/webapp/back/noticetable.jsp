<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>公告表</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" href="<%=path%>/static/layui/css/layui.css" media="all">
	<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<form class="layui-form" lay-filter="component-form-group" id="search_submits" onsubmit="return false" style="margin-top: 15px">
	<div class="layui-form layui-card-header layuiadmin-card-header-auto" lay-filter="layadmin-useradmin-formlist">

		<div class="layui-inline">
			<label class="layui-form-label">发布时间：</label>
			<div class="layui-input-block">
				<input type="text" name="nTime" id="nTime" placeholder="请输入日期" class="layui-input">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">公告标题：</label>
			<div class="layui-input-block">
				<input type="text" name="nName" id="nName" placeholder="请输入标题" class="layui-input">
			</div>
		</div>
		<div class="layui-inline">
			<button class="layui-btn" lay-submit="search_submits" lay-filter="search">查询</button>
		</div>
	</div>
</form>
<table class="layui-hide" id="test" lay-filter="test"></table>

<script type="text/html" id="toolbarDemo">
	<div class="layui-btn-container">
	<button class="layui-btn layui-btn-sm" lay-event="addnotice">增加公告</button>--%>
	</div>
</script>
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-xs" lay-event="del">删除</a>
</script>
<script src="<%=path%>/static/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

<script>
	layui.use(['form','table','jquery'], function(){
		var table = layui.table;
		var form = layui.form;
		var $ = layui.jquery;
		var tableinf = table.render({
			elem: '#test'
			,url:'/pipeControl/noticeList'
			,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
			,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
				title: '提示'
				,layEvent: 'LAYTABLE_TIPS'
				,icon: 'layui-icon-tips'
			}]
			,title: '公告表数据表'
			,cols: [[
				{type: 'checkbox', fixed: 'left'}
				,{field:'nId', title:'公告ID', width:100, fixed: 'left', unresize: true, sort: true}
				,{field:'nName', title:'公告标题', width:350, edit: 'text'}
				,{field:'nTime', title:'公告发布时间', width:120, edit: 'text'}
				,{field:'nContent', title:'公告内容', width:700, sort: true}
				,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:80}
			]]
			,page: true
		});

		//头工具栏事件
		table.on('toolbar(test)', function(obj){
			var checkStatus = table.checkStatus(obj.config.id);
			var $= layui.jquery;
			switch(obj.event){
				case 'addnotice':
					var data = checkStatus.data;
					var index =   layer.open({
						type: 1,
						area:["450","400px"],
						skin: 'layui-layer-rim',
						shadeClose: true,//点击其他地方关闭
						content:$("#addnotice"),
						cancel:function (index) {
							layer.close(index);
						}
					});
					layui.use('form', function(){
						var form = layui.form;
						form.on('submit(demo1)', function(data){
							$.ajax({
								type: 'POST',
								url: '/pipeControl/addnotice',
								dataType: 'JSON',
								data: data.field,
								success: function (msg) {
									if (msg.code==1){
										layer.msg("添加公告成功");
										tableinf.reload();
									}
									layer.close(index);
								}
							});
							return false;
						});
					});
				break;
			};
		});
		//监听行工具事件
		table.on('tool(test)', function(obj) {
			var data = obj.data;
			var $ = layui.jquery;
			var noticeId = "";
			//console.log(obj)
			if (obj.event === 'del') {
				var nId = data.nId;
				layer.confirm('真的删除行么', {
					btn: ["确定", "取消"],
					btn2: function (index) {
						// alert(data.nId);
						layer.close(index);
					},
					btn1: function () {
						$.ajax({
							type: 'POST',
							url: '/pipeControl/deletenotice',
							dataType: 'JSON',
							data: {
								nId: nId
							},
							success: function (msg) {
								if (msg.code == 1) {
									layer.msg("您已成功删除该公告信息");
								} else if (msg.code == 0) {
									layer.msg("删除失败");
								} else {
									layer.msg("该公告信息不存在");
								}
							}
						});
						tableinf.reload();
					}
				});

			}
		// }
		});
		//表单查询
		form.on('submit(search)',function (data) {
			var nName = $("#nName").val();
			var nTime = $("#nTime").val();
			tableinf.reload({
				url:'/pipeControl/noticeList',
				page: {
					curr: 1 //重新从第 1 页开始
				},
				where:{
					nTime:nTime
					,nName:nName
				}
			});

		});
	});
</script>
<form action="" id="addnotice" style="display: none" class="layui-form">
	<div class="layui-form-item">
		<label class="layui-form-label">公告标题</label>
		<div class="layui-input-inline">
			<input type="text"  name="nName" required  lay-verify="required" placeholder="请输入公告标题" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">发布时间</label>
		<div class="layui-input-inline">
			<input type="text"  name="nTime" required  lay-verify="required" placeholder="请输发布时间" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item layui-form-text">
		<label class="layui-form-label">公告内容</label>
		<div class="layui-input-block">
			<textarea  name="nContent" placeholder="请输入公告内容" class="layui-textarea"></textarea>
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</form>
</body>
</html>