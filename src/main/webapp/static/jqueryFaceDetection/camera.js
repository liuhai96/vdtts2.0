var mediaStreamTrack=null;
$(function () {

	function success(stream){
		// 兼容webkit核心浏览器
		// var CompatibleURL = window.URL || window.webkitURL;
		// 将视频流转化为video的源
		mediaStreamTrack=stream;
		try {
			// video.src = CompatibleURL.createObjectURL(stream);
			video.srcObject=stream;
			video.play();// 播放视频
		}catch (e) {
			noticeMessage("error","开启摄像头失败，请刷新网页重试");
		}
	}

	function error(error) {
		noticeMessage("error","您选择了禁止浏览器启动摄像头，请刷新网页并允许浏览器调用摄像头")
	}

	function getUserMediaToPhoto(constraints,success,error) {
		if(navigator.mediaDevices.getUserMedia){
			// 最新标准API
			navigator.mediaDevices.getUserMedia(constraints).then(success).catch(error);
		}else if (navigator.webkitGetUserMedia) {
			// webkit核心浏览器
			navigator.webkitGetUserMedia(constraints,success,error);
		}else if(navigator.mozGetUserMedia){
			// firefox浏览器
			navigator.mozGetUserMedia(constraints,success,error);
		}else if(navigator.getUserMedia){
			// 旧版API
			navigator.getUserMedia(constraints,success,error);
		}
	}

	function openUserMedia() {
		if(navigator.mediaDevices.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.getUserMedia){
			getUserMediaToPhoto({video:{width:480,height:320,facingMode: "user"}},success,error);
		}else{
			noticeMessage("error",'你的浏览器不支持访问用户媒体设备');
		}
	}



	var canvas=document.getElementById("myCanvas");
	var ctx=canvas.getContext("2d");




	$("#openCamera").on("click",function () {
		openUserMedia();

		var clock1=setInterval(function () {
			ctx.clearRect(0, 0, canvas.width, canvas.height);
			ctx.drawImage(video, 0, 0);
			$("#image").attr("src",canvas.toDataURL('imnage/pg'));
		}, 60);
	})
})