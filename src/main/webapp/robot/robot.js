layui.use('layim', function(layim){
    var layim = layui.layim;
    let $ = layui.$;

    let path = window.document.location.href.substring(0, (window.document.location.href).indexOf(window.document.location.pathname));

    layim.config({
        init: {
            //配置客户信息
            mine: {
                "username": "一个十分困惑的人"
                ,"id": "100000123"
                ,"status": "online"
                ,"remark": "在深邃的编码世界，做一枚轻盈的纸飞机"
                ,"avatar": path+"/robot/images/avatar.gif"
            }
        }
        //开启客服模式
        ,brief: true
    });
    //打开一个客服面板
    layim.chat({
        name: '智能客服'
        ,type: 'kefu'
        ,avatar: path+"/robot/images/avatar.gif"
        ,id: 1111111
    });

    layim.on('sendMessage', function(data) {
        let To = data.to;
        $.post(path+"/api/robot/talk", {
            content: data.mine.content //对方用户ID
        }, function(res){
            console.log(res);
            obj = {
                username: To.name
                ,avatar: To.avatar
                ,id: To.id
                ,type: To.type
                ,content: res
            }
            layim.getMessage(obj);
        });
    });
});