
//文本框默认提示文字
function textFocus(el) {
    if (el.defaultValue == el.value) { el.value = ''; el.style.color = '#333'; }
}
function textBlur(el) {
    if (el.value == '') { el.value = el.defaultValue; el.style.color = '#999'; }
}
let idSu = true;
let passSu = true;
$(function(){
    /*生成验证码*/
    (function create_code(){
        function shuffle(){
            var arr = ['1', 'r', 'Q', '4', 'S', '6', 'w', 'u', 'D', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'p',
                'q', '2', 's', 't', '8', 'v', '7', 'x', 'y', 'z', 'A', 'B', 'C', '9', 'E', 'F', 'G', 'H', '0', 'J', 'K', , 'M', 'N', 'P', '3', 'R',
                '5', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
            return arr.sort(function () {
                return (Math.random() - .5);
            });
        };
        shuffle();
        function show_code(){
            var ar1='';
            var code=shuffle();
            for(var i=0;i<4;i++){
                ar1+=code[i];
            };
            //var ar=ar1.join('');
            $(".reg-box .phoKey").text(ar1);
        };
        show_code();
        $(".reg-box .phoKey").click(function(){
            show_code();
        });
    })();


    //登录页面的提示文字
    //账户输入框失去焦点
    (function login_validate(){
        $(".reg-box .account").blur(function(){
            idSu = true;
            if( $(this).val()==""|| $(this).val()=="请输入学员姓名") {
                $(this).addClass("errorC");
                $(this).next().html("账号不能为空");
                $(this).next().css("display","block");
                idSu = false;
            }else if($(".reg-box .account").val().length<6) {
                $(this).addClass("errorC");
                $(this).next().html("账号长度有误");
                $(this).next().css("display","block");
                idSu = false;
            }else{
                $(this).addClass("checkedN");
                $(this).removeClass("errorC");
                $(this).next().empty();
            }
        });

        $(".reg-box .idcard").blur(function(){//密码
            passSu = true;
            if( $(this).val()=="") {
                $(this).addClass("errorC");
                $(this).next().html("登录密码不能为空！");
                $(this).next().css("display","block");
                passSu = false;
            }else if($(".reg-box .idcard").val().length<6) {
                $(this).addClass("errorC");
                $(this).next().html("登录密码长度有误！");
                $(this).next().css("display","block");
                passSu = false;
            }else{
                $(this).addClass("checkedN");
                $(this).removeClass("errorC");
                $(this).next().empty();
            }
        });
    })();

    $("form").submit(function (e) {
        e.preventDefault();
        var key = $("span.phoKey").text().toLowerCase();
        var code = $("input[name=vcode]").val().toLowerCase();
        if (key != code) {
            layer.msg("验证码错误", {
                icon: 2,
                time: 1000 //2秒关闭（如果不配置，默认是3秒）
            });
            $("#vk").click();
            return false;
        }
        $("#vk").click();
        submit($, $("form").serialize());
        return false;
    });

    var name = localStorage.getItem('stuName');
    var idcard = localStorage.getItem('stuIdcard');
    var phone = localStorage.getItem('stuPhone');
    if (name) {
        $("input[name='name']").val(name);
        $("input[name='name']").css("color", "#333");
    }
    if (idcard) {
        $("input[name='idcard']").val(idcard);
        $("input[name='idcard']").css("color", "#333");
    }
    if (phone) {
        $("input[name='phone']").val(phone);
        $("input[name='phone']").css("color", "#333");
    }
});

var baseUrl = window.document.location.href.substring(0, (window.document.location.href).indexOf(window.document.location.pathname));

var loginurl = baseUrl + "/api/login/student";

function submit($, params) {

    $.post(loginurl, params, function (res) {

        if (res.code == 0) {
            //跳转到主页
            window.location.href = baseUrl + "/" + res.data.url;
            let newString = "";
            newString += "<a href='" + baseUrl + "/student'>欢迎您！ 学员: " + res.data.username + "</a>";
            newString += "&nbsp;&nbsp;&nbsp;";
            newString += "<a href='"+baseUrl+"/transfer?logo=alterpass'>修改信息</a>";
            newString += "&nbsp;&nbsp;&nbsp;";
            newString += "<a href='" + baseUrl + "/logout/student'>退出</a>";
            $("#studentName", window.parent.document).html(newString);
        } else {
            layer.msg(res.msg, {icon: 2});
        }
    }, 'json');
}
//重置
function resetMsg() {
    $("input[name='aAccount']").val('');
    $("input[name='aAccount']").css("color","#999");
    $("input[name='aPassword']").val('');
    $("input[name='aPassword']").css("color","#999");
    // $("input[name='name']").val('请输入学员姓名');
    // $("input[name='name']").css("color","#999");
    // $("input[name='idcard']").val('请输入学员身份证号码');
    // $("input[name='idcard']").css("color","#999");
    // $("input[name='phone']").val('请输入信息采集时所填写的手机号');
    // $("input[name='phone']").css("color","#999");
    $("input[name='vcode']").val('验证码');
    $("input[name='vcode']").css("color","#999"); 
    // localStorage.removeItem("stuName");
    // localStorage.removeItem("stuIdcard");
    // localStorage.removeItem("stuPhone");
    $("#vk").click();
}
layui.use(['layer'], function () {
    var layer = layui.layer,
        $ = layui.jquery
});