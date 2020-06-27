<%--
  Created by IntelliJ IDEA.
  User: zhaohaiyang
  Date: 2020/5/8
  Time: 上午9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>后端登录界面</title>
	<style type="text/css">
		html, body {
			width: 100%;
			height: 100%;
			margin: 0 auto;
			padding: 0 auto;
		}

		body {
			/*background: #0F769D;*/
			background-repeat:no-repeat ;/*不平铺*/
			background-size:100% 100%;
			text-align: center;
			background-image: url("../static/layui/images/photo/02.png");

		}

		.title {
			width: 100%;
			height: 85px;
			font-size: 30px;
			line-height: 150px;
		}

		.input-text.size-L, .btn.size-L {
			font-size: 16px;
			height: 41px;
			padding: 8px;
		}

		.input-text {
			box-sizing: border-box;
			border: solid 1px #ddd;
			width: 100%;
			-webkit-transition: all .2s linear 0s;
			-moz-transition: all .2s linear 0s;
			-o-transition: all .2s linear 0s;
			transition: all .2s linear 0s;
		}

		.loginBtn {
			background: #12b5b0;
			height: 40px;
			text-align: center;
			color: #fff;
			font-size: 20px;
			margin-top: 20px;
			width: 150px;
			border-radius: 0px;
			padding: 0px 0px;
			border-radius: 30px;
		}

		.formControls {
			text-align: center;
			/*margin: 10px 20px 0 20px;*/
			clear: both;
		}
		.cdiv{
			float: right;
			margin: 100px;/*外边距，上右下左*/
			padding: 30px 60px 0 60px;
			/*margin: 80px;*/
			width: 250px;
			height:350px;
			background-color: hsla(23,30%,100%,0.5);
			border: 1px solid skyblue;
			font-size: 20px;
			font-family:华文行楷;
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
<div class="cdiv">
	<div class="title">驾驶员培训系统</div>
	<hr size="1" color="red"/>
	<form action="login" method="post" id="">
		<div class="formControls">
			<label for="account">用户名:</label>   <br/>
			<input type="text" name="account" id="account" class="input-text size-L" placeholder="请输入您的账号"/>
		</div>
		<div class="formControls">
			<label for="password">密码:</label>
			<input type="password" name="password" id="password" class="input-text size-L" placeholder="请输入您的密码"/>
		</div>
		<div>
			<input type="button" id="btnLogin" name="btnLogin" value="登录" class="loginBtn">
		</div>
	</form>
	<div id="messageBox"></div>
</div>
</body>
</html>



