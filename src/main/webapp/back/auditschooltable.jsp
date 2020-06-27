<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>审核驾校表</title>
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
			<label class="layui-form-label">审核状态</label>
			<div class="layui-input-inline">
				<select name="sVerification" id="sVerification" >
					<option value="">选择审核状态查询</option>
					<option value="0">未审核</option>
					<option value="1">已通过</option>
				</select>
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
<script type="text/html" id="barDemo">
	{{#  if(d.sVerification == "0"){}}
	<a class="layui-btn layui-btn-xs" lay-event="audit">审核</a>
	{{#  }else{}}
	<a >已审核</a>
	{{#  } }}
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
			, url: '/SchoolControl/findSchoolList'
			, toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
			, defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
				title: '提示'
				, layEvent: 'LAYTABLE_TIPS'
				, icon: 'layui-icon-tips'
			}]
			, title: '审核驾校数据表'
			, cols: [[
				{type: 'checkbox', fixed: 'left'}
				, {field: 'sId', title: '驾校ID', width: 100, fixed: 'left', unresize: true, sort: true}
				, {field: 'sName', title: '驾校名', width: 100, edit: 'text'}
				, {field: 'sAddress', title: '驾校地址', width: 250, edit: 'text'}
				, {field: 'sPhone', title: '驾校电话', width: 110, sort: true}
				, {field: 'sBusinessId', title: '统一信用代码', width: 130}
				, {field: 'sBusinessPic', title: '营业执照照片保存路径', width: 200}
				, {field: 'sOwnerId', title: '法人身份证', width: 200}
				, {field: 'sVerification', title: '是否通过审核', width: 130}
				, {field: 'sRecruit', title: '是否允许招生', width: 130}
				, {field: 'sLock', title: '是否允许登录', width: 130}
				, {field: 'sRegTime', title: '注册时间', width: 100}
				, {field: 'sOwnerPic', title: '法人代表证件', width: 130}
				, {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 150}
			]]
			, page: true
			, done: function (res, curr, count) {
				$("[data-field='sVerification'],[data-field='sRecruit'],[data-field='sLock']").children().each(function () {
					if ($(this).text() == '1') {
						$(this).text("已通过")
					} else if ($(this).text() == '0') {
						$(this).text("未审核")
					}else if ($(this).text() == 'true') {
						$(this).text("允许")
					} else if ($(this).text() == 'false') {
						$(this).text("不允许")
					}

				});
			}
		});
		//监听行工具事件
		table.on('tool(test)', function(obj){
			var data = obj.data;
			$("#sId").html(data.sId);
			$("#sName").html(data.sName);
			$.ajax({
				type: 'POST',
				url: '/SchoolControl/findschool',
				dataType: 'JSON',
				success: function (msg) {
					$.each(msg.data,function (i,item) {
						$("#schoolSelect").append("<option value='" + item.sId + "'>" + item.sName + "</option>");
					});
					layui.use('form',function(){
						var form = layui.form;
						form.render();
					});
				}});
			if(obj.event === 'audit'){
				var sId = data.sId;
				var index1 = layer.open({
					type: 1,
					area:["250","250px"],
					skin: 'layui-layer-rim',
					shadeClose: true,//点击其他地方关闭
					content:$("#updateschool"),
					cancel:function (index) {
						layer.close(index);
					},
				});
				layui.use('form', function(){
					var form = layui.form
					form.render();
					form.on('submit(demo2)', function(data){
						schoolId = $("#schoolSelect").val();
						$.ajax({
							type: 'POST',
							url: '/SchoolControl/updateschoolInfo',
							dataType:'JSON',
							data:{
								sId:sId
							},
							success: function (msg) {
								if (msg.code==0){
									layer.msg("请选择审状态");
								}else if(msg.code==1){
									layer.msg("修改成功");
									layer.close(index1);
									tableinf.reload();
								}else{
									layer.msg("未找到该审核状态");
									layer.close(index1);
								}
								layui.use('form',function(){
									var form = layui.form;
									form.render();
								});
							}
						});
						return false;
					});
				});
			}
			});
		//表单查询
		form.on('submit(search)',function (data) {

			var sVerification = $("#sVerification").val();
			tableinf.reload({
				url: '/SchoolControl/findSchoolList',
				page: {
					curr: 1 //重新从第 1 页开始
				},
				where: {
					sVerification: sVerification
				}
			});
		});
	});
</script>
 <form action="" id="updateschool" style="display: none" class="layui-form">
	<div class="layui-form-item">
		<label class="layui-form-label">审核</label>
		<div class="layui-input-block">
			<select name="sVerification" lay-filter="aihao"  id="schoolSelect">
				<option value="">请选择审批状态</option>
				<option value="true">已通过</option>
			</select>
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button type="submit" class="layui-btn" lay-submit="" lay-filter="demo2">审核提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
 </form>
</body>
</html>