<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>驾校违规处罚表</title>
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
			<label class="layui-form-label">驾校名：</label>
			<div class="layui-input-block">
				<input type="text" name="sName" id="sName" placeholder="请输入姓名" class="layui-input">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">招生状态</label>
			<div class="layui-input-inline">
				<select name="sRecruit" id="sRecruit">
					<option value="">选择招生状态查询</option>
					<option value="true">允许</option>
					<option value="false">不允许</option>
				</select>
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">登录状态</label>
			<div class="layui-input-inline">
				<select name="sLock" id="sLock">
					<option value="">选择登录状态查询</option>
					<option value="true">允许</option>
					<option value="false">不允许</option>
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
<script src="<%=path%>/static/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

<script>
	layui.use(['form','table','jquery'], function(){
		var table = layui.table;
		var form = layui.form;
		var $ = layui.jquery;
		var tableinf = table.render({
			elem: '#test'
			,url:'/SchoolControl/findSchoolList'
			,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
			,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
				title: '提示'
				,layEvent: 'LAYTABLE_TIPS'
				,icon: 'layui-icon-tips'
			}]
			,title: '驾校处罚数据表'
			,cols: [[
				{type: 'checkbox', fixed: 'left'}
				,{field:'sId', title:'驾校ID', width:100, fixed: 'left', unresize: true, sort: true}
				,{field:'sName', title:'驾校名', width:100, edit: 'text'}
				,{field:'sAddress', title:'驾校地址', width:250, edit: 'text'}
				,{field:'sPhone', title:'驾校电话', width:110, sort: true}
				,{field:'sBusinessId', title:'统一信用代码', width:130}
				,{field:'sBusinessPic', title:'营业执照照片保存路径', width:200}
				,{field:'sOwnerId', title:'法人身份证', width:200}
				,{field:'sVerification', title:'是否通过审核', width:130}
				,{field:'sRecruit', title:'是否允许招生', width:130}
				,{field:'sLock', title:'是否允许登录', width:130}
				,{field:'sRegTime', title:'注册时间', width:100}
				,{field:'sOwnerPic', title:'法人代表证件', width:130}
				,{title: '操作',templet: function (d) {
						<%--	var str="<a class=\"layui-btn layui-btn-xs\" lay-event=\"rename\">重命名</a>";--%>
						var str="";
						if(d.sRecruit=='true' && d.sLock=='true'){
							str+="<a class=\"layui-btn layui-btn-danger layui-btn-xs\" lay-event=\"punishcall\">禁止招生</a>";
							str+="<a class=\"layui-btn layui-btn-danger layui-btn-xs\" lay-event=\"punishlogon\">禁止登陆</a>";
						}else if(d.sRecruit=='false' && d.sLock=='true'){
							str+="<a class=\"layui-btn  layui-btn-xs\" lay-event=\"unbindcall\">解禁招生</a>";
							str+="<a class=\"layui-btn layui-btn-danger layui-btn-xs\" lay-event=\"punishlogon\">禁止登陆</a>";
						}else if(d.sRecruit=='true' && d.sLock=='false'){
							str+="<a class=\"layui-btn layui-btn-danger layui-btn-xs\" lay-event=\"punishcall\">禁止招生</a>";
							str+="<a class=\"layui-btn  layui-btn-xs\" lay-event=\"unbindlogon\">解禁登录</a>";
						}else if(d.sRecruit=='false' && d.sLock=='false'){
							str+="<a class=\"layui-btn  layui-btn-xs\" lay-event=\"unbindcall\">解禁招生</a>";
							str+="<a class=\"layui-btn  layui-btn-xs\" lay-event=\"unbindlogon\">解禁登录</a>";
						} else{
							str += "<a>未审核</a>";
						}
							return str;
						}, width:195 }
	              ]]
	             , page: true
		         , id: "test"
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
			var $ = layui.jquery;
			var schoolId="";
			if(obj.event === 'punishcall'){
				var sId = data.sId;
				layer.confirm('处罚该驾校么',{
					btn:["确定","取消"],
					btn2:function (index) {
						// alert(data.sId);
						layer.close(index);
					},
					btn1:function () {
						$.ajax({
							type: 'POST',
							url: '/SchoolControl/punishcall',
							dataType: 'JSON',
							data:{
								sId:sId
							},
							success: function (msg) {
								if(msg.code==1){
									layer.msg("您已成功处罚该驾校禁止招生");
								}else if(msg.code==0 ){
									layer.msg("处罚失败");
								}else{
									layer.msg("该状态不存在");
								}
							}
						});
						tableinf.reload();
					}

				});
			}else if(obj.event === 'unbindcall'){
				var sId = data.sId;
				layer.confirm('解禁该驾校么',{
					btn:["确定","取消"],
					btn2:function (index) {
						// alert(data.sId);
						layer.close(index);
					},
					btn1:function () {
						$.ajax({
							type: 'POST',
							url: '/SchoolControl/unbindcall',
							dataType: 'JSON',
							data:{
								sId:sId
							},
							success: function (msg) {
								if(msg.code==1){
									layer.msg("您已成功解禁该驾校可以招生");
								}else if(msg.code==0 ){
									layer.msg("解禁失败");
								}else{
									layer.msg("该状态不存在");
								}
							}
						});
						tableinf.reload();
					}
			});
			} else if(obj.event === 'punishlogon'){
				var sId = data.sId;
				layer.confirm('处罚该驾校么',{
					btn:["确定","取消"],
					btn2:function (index) {
						// alert(data.sId);
						layer.close(index);
					},
					btn1:function () {
						$.ajax({
							type: 'POST',
							url: '/SchoolControl/punishlogon',
							dataType: 'JSON',
							data:{
								sId:sId
							},
							success: function (msg) {
								if(msg.code==1){
									layer.msg("您已成功处罚该驾校禁止登录");
								}else if(msg.code==0 ){
									layer.msg("处罚失败");
								}else{
									layer.msg("该状态不存在");
								}
							}
						});
						tableinf.reload();
					}

				});
			}else if(obj.event === 'unbindlogon'){
				var sId = data.sId;
				layer.confirm('解禁该驾校么',{
					btn:["确定","取消"],
					btn2:function (index) {
						layer.close(index);
					},
					btn1:function () {
						$.ajax({
							type: 'POST',
							url: '/SchoolControl/unbindlogon',
							dataType: 'JSON',
							data:{
								sId:sId
							},
							success: function (msg) {
								if(msg.code==1){
									layer.msg("您已成功解禁该驾校可以登录");
								}else if(msg.code==0 ){
									layer.msg("解禁失败");
								}else{
									layer.msg("该状态不存在");
								}
							}
						});
						tableinf.reload();
					}
				});
			}
		});
		//表单查询
		form.on('submit(search)',function (data) {
			var sName = $("#sName").val();
			var sLock=$("#sLock").val();
			var sRecruit=$("#sRecruit").val();
			tableinf.reload({
				url:'/SchoolControl/findSchoolList',
				page: {
					curr: 1 //重新从第 1 页开始
				},
				where:{
					sName:sName
					,sLock:sLock
					,sRecruit:sRecruit

				}
			});

		});
	});
</script>

</body>
</html>