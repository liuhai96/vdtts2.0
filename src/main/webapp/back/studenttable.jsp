<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>学生表</title>
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
			<label class="layui-form-label">学员姓名：</label>
			<div class="layui-input-block">
				<input type="text" name="sName" id="sName" placeholder="请输入姓名" class="layui-input">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">性别</label>
			<div class="layui-input-inline">
				<select id="sSex" name="sSex" >
					<option value="">选择性别查询</option>
					<option value="男">男</option>
					<option value="女">女</option>
				</select>
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">获取时间：</label>
			<div class="layui-input-block">
				<input type="text" name="sLicenseTime" id="sLicenseTime" placeholder="请输入姓名" class="layui-input">
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
<%--		<button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>--%>
<%--		<button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>--%>
<%--		<button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>--%>
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
			,url:'/pipeControl/studentList'
			,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
			,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
				title: '提示'
				,layEvent: 'LAYTABLE_TIPS'
				,icon: 'layui-icon-tips'
			}]
			,title: '学生表数据表'
			,cols: [[
				{type: 'checkbox', fixed: 'left'}
				,{field:'sId', title:'学员ID', width:115, fixed: 'left', unresize: true, sort: true}
				,{field:'schoolName', title:'驾校名字', width:130, edit: 'text'}
				,{field:'sSfz', title:'学员身份证号', width:200, edit: 'text'}
				,{field:'sName', title:'学员姓名', width:130, sort: true}
				,{field:'sSex', title:'学员性别', width:115}
				,{field:'sBirthday', title:'学员出生日期', width:150}
				,{field:'sRegTime', title:'学员注册日期', width:150}
				,{field:'sLicenseTime', title:'获取驾照时间', width:150}
				,{field:'teacherName', title:'所属教练姓名', width:150}
				// ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
			]]
			,page: true
		});

		//头工具栏事件
		table.on('toolbar(test)', function(obj){
			var checkStatus = table.checkStatus(obj.config.id);
			switch(obj.event){
				case 'getCheckData':
					var data = checkStatus.data;
					layer.alert(JSON.stringify(data));
					break;
				case 'getCheckLength':
					var data = checkStatus.data;
					layer.msg('选中了：'+ data.length + ' 个');
					break;
				case 'isAll':
					layer.msg(checkStatus.isAll ? '全选': '未全选');
					break;

				//自定义头工具栏右侧图标 - 提示
				case 'LAYTABLE_TIPS':
					layer.alert('这是工具栏右侧自定义的一个图标按钮');
					break;
			};
		});
		//表单查询
		form.on('submit(search)',function (data) {

			var sName = $("#sName").val();
			var sSex=$("#sSex").val();
            var sLicenseTime=$("#sLicenseTime").val();
			tableinf.reload({
				url:'/pipeControl/studentList',
				page: {
					curr: 1 //重新从第 1 页开始
				},
				where:{
					sName:sName,sLicenseTime:sLicenseTime
					,sSex:sSex
				}
			});

		});
	});
</script>

</body>
</html>