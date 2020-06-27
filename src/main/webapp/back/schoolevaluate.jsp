<%--
  Created by IntelliJ IDEA.
  User: 周永哲
  Date: 2020/06/14
  Time: 上午9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<title>学员评价-驾校</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" href=<%=path+"/static/layui/css/layui.css"%>>
	<script type="text/javascript" src=<%=path+"/static/layui/layui.js"%>></script>
	<style type="text/css">
		html, body {
			width: 100%;
			height: 100%;
			margin: 0 auto;
			padding: 0 auto;
		}

		body {
			/*background: #0F769D;*/
			/*background-repeat:no-repeat ;!*不平铺*!*/
			background-size:100% 100%;
			/*text-align: center;*/
			/*background-image: url("../static/layui/images/photo/07.jpg");*/

		}
		.layui-field-title{
			color: #00ACFF;
			font-size: 40px;
			margin: 5px 0 5px ;
			border-width: 1px 0 0;
		}
		.layui-colla-title{
			 color: #FD482C;
			 font-size: 20px;
		 }

		.layui-a{
			color: #FD482C;
			font-size: 20px;
			margin-top:10px ;
			margin-left: 20px;
		}
		.layui-b{
			color: #00ACFF;
			font-size: 20px;
			margin-left: 20px;
		}
		#title {
			width: 100%;
			height: 85px;
			color: #00ACFF;
			font-size: 15px;
			line-height: 120px;
		}
		.starevalute{
			margin-left: 30px;
		}
		.layui-input {
			font-size: 16px;
			height: 18px;
			padding: 8px;
			margin-top: 30px;
			margin-left: 10px;
		}
		.layui-btn {
			background: #12b5b0;
			height: 40px;
			/*text-align: center;*/
			color: #fff;
			/*background-color: transparent;*/
			background-color: #0b90f3;
			font-size: 20px;
			margin-left: 22px;
			width: 100px;
			border-radius: 1px;
			/*border: 2px;*/
			padding: 0px 0px;
			/*border-radius: 30px;*/
		}
		.layui-input-block {
			margin-left: 0px;
		}
		.layui-container{
			float: top;
			margin:50px 0 0 50px;/*外边距，上右下左*/
			padding: 0 60px 0 60px;
			/*margin: 80px;*/
			width: 1410px;
			height:700px;
			/*background-color: #8ea8d8;*/
			background-color: -moz-mac-accentregularhighlight;
			border: 1px solid skyblue;
			font-size: 20px;
			/*font-family:华文行楷;*/
		}
		.layui-form-label{
			color: #00ACFF;
		}
		#messageBox {
			color: red;
			text-align: left;
		}
		input:hover{color:black;
			/*background-color:rgba(234 ,231 ,123 ,0.2);*/
			border: 2px solid darkcyan;}/*鼠标悬停*/
		a:hover{
			color:darkcyan;
			border-radius:30%;
		}/*鼠标悬停*/
	</style>
</head>

<body>
<div class="layui-container" id="loginbody">
		<form class="layui-form" >
			<div class="layui-form-item">
				<div class="layui-input-block" id="title">
					<h1>传一驾校-驾校点评</h1>
				</div>
				<hr size="2" color="darkcyan"/>
			</div>
			<fieldset class="layui-elem-field layui-field-title" id="knowlege"style="margin-top: 30px;">
				<legend><h3>学员点评驾校须知</h3></legend>
			</fieldset>
			<div class="layui-collapse" lay-filter="test"style="margin-right: 200px">
				<div class="layui-colla-item">
					<h1 class="layui-colla-title">驾校点评细则</h1>
					<div class="layui-colla-content">
						<p>为维护您对传一驾校欢迎您各方面的正当评论，请务必如实点评且谨慎用语。若点评内容中含有以下情形中的任何一种，本系统会将其自动删除：</p>
						<span>1、含有色情、暴力、恐怖内容；</span><br>
						<span>2、具有广告性质；</span><br>
						<span>3、含有反动内容；</span><br>
						<span>4、含有人身攻击内容；</span><br>
						<span>5、含有违背伦理道德内容；</span><br>
						<span>6、有恶意、无聊和灌水性质；</span><br>
						<span>7、涉及违法犯罪的内容；</span><br>
						<span>8、其他违反法律的内容；</span><br>
						<span>★所有点评均属于发言人之个人意思表述，不代表本网观点。</span>
					</div>
				</div>
			</div>
			<fieldset class="layui-elem-field layui-field-title" >
				<legend><h3>考试合格率</h3></legend>
			</fieldset>
			<div><div id="test5"class="starevalute"></div></div>
			<fieldset class="layui-elem-field layui-field-title" >
				<legend><h3>教练口碑</h3></legend>
			</fieldset>
			<div><div id="test6"class="starevalute">

			</div></div>
			<fieldset class="layui-elem-field layui-field-title" >
				<legend><h3>场地设施</h3></legend>
			</fieldset>
			<div><div id="test7" class="starevalute"></div></div>
<%--			<div class="layui-form-item">--%>
<%--				<div class="layui-input-block">--%>
<%--					<button class="layui-btn"  name="confirm" lay-submit lay-filter="formDemo">登 录</button>--%>
<%--					<button type="reset" class="layui-btn " >重 置</button>--%>
<%--				</div>--%>
<%--			</div>--%>
			<fieldset class="layui-elem-field layui-field-title" >
				<legend><h3>传一驾校欢迎您对驾校进行评价</h3></legend>
			</fieldset>
			<div class="layui-form-item layui-form-text" style="margin-right: 200px">
				<div class="layui-input-block">
					<textarea placeholder="请输入对传一驾校的点评内容。如果是学车中遇到不合理、不合法的现象需要相关部门和驾校进行处理的，请点击下方的链接，进入“驾校投诉系统”进行投诉。" class="layui-textarea"></textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button type="submit" class="layui-btn layui-btn-primary" lay-submit lay-filter="formDemo">立即提交</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					<a title="返回" class="layui-b" href="https://www.baidu.com" >返回首页</a>
					<a title="驾校投诉系统" class="layui-a" href="https://www.baidu.com" >点击进入驾校投诉系统</a>
				</div>
			</div>
		</form>
	<div id="messageBox"></div>
	</div>
</div>
<script>
	layui.use(['element', 'layer'], function(){
		var element = layui.element;
		var layer = layui.layer;

		// //监听折叠
		// element.on('collapse(test)', function(data){
		// 	// layer.msg('展开状态：'+ data.show);
		// });
	});
	var t5="",t6="",t7="";
	layui.use(['rate'], function(){
		var rate = layui.rate;
		rate.render({
			elem: '#test5'
			,value: 3
			,text: true
			,setText: function(value){ //自定义文本的回调
				var arrs = {
					'1': '1分 极差'
					,'2': '2分 差'
					,'3': '3分 中等'
					,'4': '4分 好'
					,'5': '5分 极好'
				};
				this.span.text(arrs[value] || ( value + "星"));
				t5=value;
			}
		})
		rate.render({
			elem: '#test6'
			,value: 4
			,text: true
			,theme: '#FE0000'
			,setText: function(value){ //自定义文本的回调
				var arrs = {
					'1': '1分 极差'
					,'2': '2分 差'
					,'3': '3分 中等'
					,'4': '4分 好'
					,'5': '5分 极好'
				};
				this.span.text(arrs[value] || ( value + "星"));
				t6=value;
			}
		})
		rate.render({
			elem: '#test7'
			,value: 5
			,text: true
			,theme: '#009688'
			,setText: function(value){ //自定义文本的回调
				var arrs = {
					'1': '1分 极差'
					,'2': '2分 差'
					,'3': '3分 中等'
					,'4': '4分 好'
					,'5': '5分 极好'
				};
				this.span.text(arrs[value] || ( value + "星"));
				t7=value;
			}
		});

		layui.use(['form','jquery','layer'],function () {
			var layer = layui.layer,form=layui.form;
			var $ = layui.jquery;
			form.on('submit(formDemo)',function (data) {
				var textarea=$('.layui-textarea').val();
				alert('t5='+t5+' t6='+t6+' t7='+t7+' textarea='+textarea)
				$.ajax({
					url: "/evaluateController/schoolEvaluate",
					type: "POST",
					dataType: "text",
					data:{
						examScore:t5,
						teacherScore:t6,
						palaceScore:t7,
						eContent:textarea,
						eToId:3,
						eStudentId:2
					},
					success: function (msg) {
						if (msg.trim() == "success") {
							layer.alert("谢谢您的评价，评价已提交");
						} else {
							alert("评价失败，请重新提交评价")
						}
					}
				});
				return false;
			});
		});




	});
</script>


</body>
</html>



