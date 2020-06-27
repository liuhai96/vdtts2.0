let number = /^[0-9]*$/;   //判断字符串是否为数字
let capital = /^[A-Z]+$/;  //判断是字符串大写字母
let lowercase = /^[a-z]+$/;  //判断是字符串小写字母

function AjaxTransfer(url,sendData,funY,fnuN,type,dataType) { //ajax封装方法
  if (funY == undefined) funY = function(msg){alert("返回数据为："+msg);}
  if (fnuN == undefined) fnuN = function(){alert("你的操作非法！\n或服务器正忙，请重试！");}
  if (type == undefined)type = "POST";
  if (dataType == undefined)dataType = "JSON";
  $.ajax( {
    type: type, url: url, data: sendData,
    dataType: dataType, success:funY, error:fnuN
  })
}
function HideOrShow(aLabel,isHide) {//隐藏与显示标签
    if (isHide) aLabel.attr("hidden","hidden");
    else aLabel.removeAttr("hidden");
}
function Skip(c) {//表单换页
    let page = $("#page");
    let count = page.val();
    if (c > 0) count++; else count--;
    page.attr("value",count);
    $("#skip").submit();
}
function skipPage(box) {//普通跳转
    window.location.href = $("#path").val()+box;
}
function skipAbsolute(box) {//绝对跳转
    if(top.location!=self.location)top.location=top.location = $("#path").val()+box;//跳出iframe到指定位置
}
function isContain(beingMeasured,con) {
    if (con == undefined)con = "~`!@#$%^&*()_+-=/\\?><., :;\"'~！@#￥%……&*（）——+·-=：“；”？》《。，、";
    for (let i = 0;i < con.length;i++){
        for (let j = 0;j < beingMeasured.length;j++)
            if (beingMeasured.charAt(i) == con.charAt(j))return true;
    }
    return false;
}

function done(input, LengthBegin, LengthEnd) {//测试账号密码中仅为数字，字母组成
    var pattern = '^[0-9a-zA-Z]{' + LengthBegin+ ',' + LengthEnd+ '}$';
    var regex = new RegExp(pattern);
    if (input.match(regex)) {
        return true;
    } else {
        return false;
    }
}

//用法样例
// var one = "antzone@";//done方法样例
// var two = "softwhy.com888";
// var three = "蚂蚁部落softwhy";
// console.log(done(one, 0,20));
// console.log(done(two, 0, 10));
// console.log(done(three,0, 30));
function cutOut(str,begin,end) {//截取字符
    if (begin == undefined)begin = 0;
    if (end == undefined)str = str.length;
    let strs = "";
    for (let i = begin;i <end;i++) strs += str.charAt(i);
    return strs;
}

//laiui上传图片
// html样式 用法样例
// <div class="layui-upload">Layui_uploadImage
//     <button type="button" class="layui-btn" id="test1">上传图片</button>
//     <div class="layui-upload-list">
//          <img class="layui-upload-img" id="demo1">
//          <p id="demoText"></p>
//     </div>
// </div>
//调用方法样例
//Layui_uploadImage("#test1",$("#path").val()+'/upImage',$('#demo1'),function (mag) {
//             sBusinessPic = mag.fPath;},$('#demoText'));
//js
function Layui_uploadImage(keyId,url,browse,Yfun,arqKey) {//Layui下 上传图片的方法
    layui.use('upload', function() {
        var $ = layui.jquery, upload = layui.upload;//普通图片上传
        var uploadInst = upload.render({
            elem: keyId
            , url: url //改成您自己的上传接口
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    browse.attr('src', result); //图片链接（base64）
                });
            }
            , done: function (res) {
                if (res.code > 0) return layer.msg('上传失败');//如果上传失败
                else Yfun(res);//上传成功
            }
            , error: function () {
                //演示失败状态，并实现重传
                arqKey.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                arqKey.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
            }
        });
    });
}

//layui 验证码
//html样式 用法样例
//<input type="text" value="" placeholder="请输入验证码" class="input-val" style="height: 35px;width: 30%;">
//<canvas id="canvas" class="layui-col-md3" style="height: 35px;background-color: ghostwhite; margin: 0 0 0 10%;"></canvas>
//<button class="btn layui-btn layui-btn-normal layui-btn-radius" style="width: 150px;font-size: 25px;">提交</button>
//js
$(function(){
    let show_num = [];
    draw(show_num);
    $("#canvas").on('click',function(){
        draw(show_num);
    })
    $(".btn").on('click',function(){
        var val = $(".input-val").val().toLowerCase();
        var num = show_num.join("");
        if(val=='')alert('请输入验证码！');
        else {
            if(val == num){
                toService();//在对应界面的文件名（类似于接口）
            } else alert('验证码错误！请重新输入！');
            $(".input-val").val('');
            draw(show_num);
        }
    })
})//验证码处理
function draw(show_num) {
    var canvas_width=$('#canvas').width();
    var canvas_height=$('#canvas').height();
    var canvas = document.getElementById("canvas");//获取到canvas的对象，演员
    var context = canvas.getContext("2d");//获取到canvas画图的环境，演员表演的舞台
    canvas.width = canvas_width;
    canvas.height = canvas_height;
    var sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
    var aCode = sCode.split(",");
    var aLength = aCode.length;//获取到数组的长度

    for (var i = 0; i <= 3; i++) {
        var j = Math.floor(Math.random() * aLength);//获取到随机的索引值
        var deg = Math.random() * 30 * Math.PI / 180;//产生0~30之间的随机弧度
        var txt = aCode[j];//得到随机的一个内容
        show_num[i] = txt.toLowerCase();
        var x = 10 + i * 35;//文字在canvas上的x坐标
        var y = 20 + Math.random() * 8;//文字在canvas上的y坐标
        context.font = "bold 23px 微软雅黑";

        context.translate(x, y);
        context.rotate(deg);

        context.fillStyle = randomColor();
        context.fillText(txt, 0, 0);

        context.rotate(-deg);
        context.translate(-x, -y);
    }
    for (var i = 0; i <= 10; i++) { //验证码上显示线条
        context.strokeStyle = randomColor();
        context.beginPath();
        context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
        context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
        context.stroke();
    }
    for (var i = 0; i <= 50; i++) { //验证码上显示小点
        context.strokeStyle = randomColor();
        context.beginPath();
        var x = Math.random() * canvas_width;
        var y = Math.random() * canvas_height;
        context.moveTo(x, y);
        context.lineTo(x + 1, y + 1);
        context.stroke();
    }
}//验证码值
function randomColor() {//得到随机的颜色值
    var r = Math.floor(Math.random() * 256);
    var g = Math.floor(Math.random() * 256);
    var b = Math.floor(Math.random() * 256);
    return "rgb(" + r + "," + g + "," + b + ")";
}//验证码颜色
//验证码部分完


// //人脸识别操作js样例
// var layer;
// var form;
// var table;
// var laydate;
//
// layui.use(['layer','upload'], function () {
//     var $ = layui.$;
//     layer = layui.layer;
//     upload=layui.upload;
//
//
//     $("#openCamera").on("click",function () {
//         var clock2=setInterval(function () {
//             $('#image').faceDetection({
//                 complete: function (faces) {
//                     if (faces.length == 0) { //说明没有检测到人脸
//                         console.log("无人脸");
//                     } else {
//                         console.log("识别到人脸");
//                         // $("#faceImg").val($('#image').attr("src"));
//                         // $("#userInCameraForm").submit();
//
//                         AjaxTransfer($("#path").val()+"/addFace","base64="+$('#image').attr("src"),
//                             function (mag) {
//
//                             });
//                     }
//                 },
//                 error: function (code, message) {
//                     console.log("complete回调函数出错");
//                 }
//             });
//         }, 60);
//     });
// });