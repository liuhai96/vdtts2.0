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
	<title>驾校后台管理登录</title>
<%--	<link rel="stylesheet" href=<%=path+"/static/layui/css/layui.css"%>>--%>
	<script type="text/javascript" src=<%=path+"/static/layui/layui.js"%>></script>
	<script type="text/javascript" src=<%=path+"/static/layui/layui.js"%>></script>
	<style type="text/css">
		html, body {
			width: 100%;
			height: 100%;
			margin: 0 auto;
			/*padding: 0 auto;*/
		}

		body {
			/*background: #0F769D;*/
			background-repeat:no-repeat ;/*不平铺*/
			background-size:100% 100%;
			/*text-align: center;*/
			background-image: url("../static/layui/images/photo/04.jpg");

		}

		#title {
			width: 100%;
			height: 85px;
			color: #00ACFF;
			font-size: 15px;
			line-height: 120px;
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
			background-color: transparent;
			font-size: 20px;
			margin-left: 22px;
			width: 100px;
			border-radius: 1px;
			/*border: 2px;*/
			padding: 0px 0px;
			/*border-radius: 30px;*/
		}

		#canvas{
			margin: 10px 10px 10px 100px;
		}
		#formControls {
			text-align: center;
			/*margin: 10px 20px 0 20px;*/
			clear: both;
		}
		.layui-container{
			float: right;
			margin: 100px;/*外边距，上右下左*/
			padding: 0 60px 0 60px;
			/*margin: 80px;*/
			width: 300px;
			height:480px;
			background-color: transparent;
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
					<h1>传一驾校后台管理登录</h1>
				</div>
				<hr size="2" color="darkcyan"/>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-inline">
					<label class="layui-form-label">账 号:</label>
					<input type="text" name="acAccount" placeholder="请输入账号" autocomplete="off"
						   class="layui-input" lay-verify="required" value="" style="margin-left: 21px">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-inline">
					<label class="layui-form-label">密 码:</label>
					<input type="password" name="acPassword" placeholder="请输入密码" autocomplete="off"
						   class="layui-input" lay-verify="required"value=""style="margin-left: 21px">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block" >
					<label class="layui-form-label">验证码:</label>
					<input id="idtext" type="text" value="" placeholder="请输入验证码（不区分大小写）"class="layui-input"></canvas>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<canvas id="canvas" width="100" height="43" onclick="verificationCode()"></canvas>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn"  name="confirm" lay-submit lay-filter="formDemo">登 录</button>
					<button type="reset" class="layui-btn " >重 置</button>
				</div>
			</div>
		</form>
	<div id="messageBox"></div>
	</div>
</div>
<script>
	layui.use(['layer', 'form','jquery'], function () {
		var layer = layui.layer
				, form = layui.form;
		var $ = layui.jquery;
		form.on('submit(formDemo)', function (data) {
			alert(JSON.stringify(data.field));
			var idtext = $(".idtext").val();
			var canvas = $(".canvas").val();
			// if(idtext==''){
			//     alert("请输入验证码");
			// }else if (idtext!=canvas){
			//     alert("验证码输入有误，请重新输入");
			// }else if(idtext==canvas){
			$.ajax({
				url: "/adminControl/adminLogin",
				type: "POST",
				dataType: "text",
				data: data.field,
				success: function (msg) {
					if (msg.trim() == "success") {
						layer.alert("管理端登录成功");
						location.href = 'adminmenu.jsp';
					} else {
						alert("登录失败，账号或密码有误")
					}
				}
			});
			return false;
		});
	});
</script>
<script>
	layui.use(['layer', 'jquery'], function () {
		var $ = layui.jquery;
		var show_num = [];
		draw(show_num);

		function verificationCode() {
			draw(show_num);
		}

		$(function () {
			$("input[name='submit']").click(function () {
				var val = $("#idtext").val();
				var num = show_num.join("");
				if (val === "") {
					alert('请输入验证码！');
					return false;
				}
				if (val.toLowerCase() !== num.toLowerCase()) {
					alert('验证码错误！\n你输入的是:  ' + val + "\n正确的是:  " + num + '\n请重新输入！');
					document.getElementById("idtext").value = '';
					draw(show_num);
					return false;
				}

			});
		});

		function draw(show_num) {
			var canvas_width = document.getElementById('canvas').clientWidth;
			var canvas_height = document.getElementById('canvas').clientHeight;
			var canvas = document.getElementById("canvas");//获取到canvas的对象，演员
			var context = canvas.getContext("2d");//获取到canvas画图的环境，演员表演的舞台
			canvas.width = canvas_width;
			canvas.height = canvas_height;
			var sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0,q,w,e,r,t,y,u,i,o,p,a,s,d,f,g,h,j,k,l,z,x,c,v,b,n,m";
			var aCode = sCode.split(",");
			var aLength = aCode.length;//获取到数组的长度

			for (var i = 0; i <= 3; i++) {
				var j = Math.floor(Math.random() * aLength);//获取到随机的索引值
				var deg = Math.random() * 30 * Math.PI / 180;//产生0~30之间的随机弧度
				var txt = aCode[j];//得到随机的一个内容
				show_num[i] = txt;
				var x = 10 + i * 20;//文字在canvas上的x坐标
				var y = 20 + Math.random() * 8;//文字在canvas上的y坐标
				context.font = "bold 23px 微软雅黑";

				context.translate(x, y);
				context.rotate(deg);

				context.fillStyle = randomColor();
				context.fillText(txt, 0, 0);

				context.rotate(-deg);
				context.translate(-x, -y);
			}
			for (var i = 0; i <= 5; i++) { //验证码上显示线条
				context.strokeStyle = randomColor();
				context.beginPath();
				context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
				context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
				context.stroke();
			}
			for (var i = 0; i <= 30; i++) { //验证码上显示小点
				context.strokeStyle = randomColor();
				context.beginPath();
				var x = Math.random() * canvas_width;
				var y = Math.random() * canvas_height;
				context.moveTo(x, y);
				context.lineTo(x + 1, y + 1);
				context.stroke();
			}
		}

		function randomColor() {//得到随机的颜色值
			var r = Math.floor(Math.random() * 256);
			var g = Math.floor(Math.random() * 256);
			var b = Math.floor(Math.random() * 256);
			return "rgb(" + r + "," + g + "," + b + ")";
		}
	});
</script>

</body>
</html>



