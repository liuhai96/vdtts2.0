<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>车辆表</title>
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
			<label class="layui-form-label">车牌号：</label>
			<div class="layui-input-block">
				<input type="text" name="cNumber" id="cNumber" placeholder="请输入姓名" class="layui-input">
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
	</div>
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
			,url:'/carControl/carList'
			,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
			,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
				title: '提示'
				,layEvent: 'LAYTABLE_TIPS'
				,icon: 'layui-icon-tips'
			}]
			,title: '车辆表数据表'
			,cols: [[
				{type: 'checkbox', fixed: 'left'}
				,{field:'cId', title:'车辆ID', width:115, fixed: 'left', unresize: true, sort: true}
				,{field:'cLogo', title:'品牌', width:130, edit: 'text'}
				,{field:'schoolName', title:'所属驾校', width:110, edit: 'text'}
				,{field:'cModel', title:'车辆型号', width:115, sort: true}
				,{field:'cColor', title:'车辆颜色', width:115}
				,{field:'cNumber', title:'车牌号', width:150}
			]]
			,page: true
		});
		//表单查询
		form.on('submit(search)',function (data) {
			var cNumber = $("#cNumber").val();
			tableinf.reload({
				url:'/carControl/carList',
				page: {
					curr: 1 //重新从第 1 页开始
				},
				where:{
					cNumber:cNumber
				}
			});

		});
	});
</script>

</body>
</html>