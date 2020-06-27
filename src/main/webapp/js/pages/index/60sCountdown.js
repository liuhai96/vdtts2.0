var $;
var layer;
var form;
layui.use(["layer","form"],function () {
    $=layui.jquery;
    layer=layui.layer;
    form=layui.form;

    if($.cookie("total")!=undefined&&$.cookie("total")!='NaN'&&$.cookie("total")!='null'){//cookie存在倒计时
        timekeeping();
    }else{//cookie 没有倒计时
        $('#codeBtn').attr("disabled", false);
    }
})

function timekeeping(){
    //把按钮设置为不可以点击
    $('#codeBtn').attr("disabled", true);
    var interval=setInterval(function(){//每秒读取一次cookie
        //从cookie 中读取剩余倒计时
        total=$.cookie("total");
        //在发送按钮显示剩余倒计时
        $('#codeBtn').val('请等待'+total+'秒');
        //把剩余总倒计时减掉1
        total--;
        if(total==0){//剩余倒计时为零，则显示 重新发送，可点击
            //清除定时器
            clearInterval(interval);
            //删除cookie
            total=$.cookie("total",total, { expires: -1 });
            //显示重新发送
            $('#codeBtn').val('重新发送');
            //把发送按钮设置为可点击
            $('#codeBtn').attr("disabled", false);
        }else{//剩余倒计时不为零
            //重新写入总倒计时
            $.cookie("total",total);
        }
    },1000);
}