layui.use(['form', 'table', 'element', 'layer'], function () {
    let table = layui.table;
    let $ = layui.$;
    let form = layui.form;
    let element = layui.element;
    let layer = layui.layer;

    let path = window.document.location.href.substring(0, (window.document.location.href).indexOf(window.document.location.pathname));

    function resizeIframe(newHeight){
        let height = newHeight;

        $("#iframe",window.parent.document).attr("style","height:"+height+"px;");
    }

    resizeIframe(Number(document.body.scrollHeight)+405);


    let msg = $("#zjh_msg").val();

    if (msg.length > 0) {
        $("#iframe",window.parent.document).removeAttr("style");
        resizeIframe(Number(document.body.scrollHeight)+405);
        alert(msg);
    }

    let testLevel = 1;

    $("#course1").on("click", function (event) {
        $("#course4").removeClass("chooseClassTab");
        $("#course4").addClass("baseClassTab");
        $(this).addClass("chooseClassTab");
        $(this).removeClass("baseClassTab");
        testLevel = 1;
        reloadTable();
        testUrl();
    });

    $("#course4").on("click", function (event) {
        $("#course1").removeClass("chooseClassTab");
        $("#course1").addClass("baseClassTab");
        $(this).addClass("chooseClassTab");
        $(this).removeClass("baseClassTab");
        testLevel = 4;
        reloadTable();
        testUrl();
    });

    function testUrl() {
        $("#test").attr("href", path + "/test/" + testLevel);
        $("#retest").attr("href", path + "/retest/" + testLevel);
    }

    testUrl();

    function reloadTable() {
        table.reload('examResult', {
            page: {
                curr: 1 //重新从第 1 页开始
            }, where: {
                level: testLevel
            }
        }, 'data');
    }

    table.render({
        elem: '#courseTestRecord'
        , id: 'examResult'
        , url: path + '/api/exam/record/' + $("#userToken").val()
        , page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            layout: ['count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
            , groups: 5 //只显示 1 个连续页码
            , first: false //不显示首页
            , last: false //不显示尾页

        }
        , cols: [[
            {field: 'esrLevel', title: '科目等级'}
            , {field: 'esrScore', title: '分数', sort: true}
            , {field: 'esrTime', title: '模拟考试时间'}
        ]]
        , where: {
            level: 1
        }
        , done: function (res, curr, count) {
            $(".layui-table-box").find("[data-field='esrId']").css("display", "none");

            $("[data-field='esrLevel']").children().each(function () {
                if ($(this).text() == '1') {
                    $(this).text("科目一")
                } else if ($(this).text() == '4') {
                    $(this).text("科目四")
                }
            });
        }
    });

    function getVideoDetail(level,resize) {

        let oldH = $("#videoList").height();

        $.ajax({
            type: "get",
            url: path + "/api/video/level/" + level,
            contentType: "application/json;charset=utf-8",
            async: true,
            statusCode: {
                404: function () {
                    alert("报了404错误");
                },
                500: function () {
                    alert("报了500错误");
                }
            },
            success: function (res) {

                //获取
                let videoList = $("#videoList");

                let str = "";

                //生成题目下方的题目标签
                for (let index = 0; index < res.length; index++) {
                    str +=
                        "<div class='car-video-list-detail'>" +
                        "<a href='" + path + "/video/" + level + "/" + res[index].vid + "'>" +
                        "<div class='video-img'>" +
                        "<div class='video-play'></div>" +
                        "<img src='" + path + res[index].vpic + "' alt=''>" +
                        "</div>" +
                        "<div class='video-describe'>" +
                        "<div class='video-text'>" +
                        res[index].vtitle +
                        "</div>" +
                        "</div>" +
                        "</a>" +
                        "</div>";
                }
                $(videoList).html(str);

                let newH = $("#videoList").height();

                let subH = newH-oldH;

                if(resize){
                    if(subH>0){
                        resizeIframe(Number(document.body.scrollHeight)-915+subH);
                    }else{
                        resizeIframe(Number(document.body.scrollHeight)+subH);
                    }
                }
            }
        });
    }

    getVideoDetail(2,false);


    $("ul[class*='car-video'] li").on("click", function (event) {
        $("ul[class*='car-video'] li").children().removeClass("active");
        $(this).children().addClass("active");

        getVideoDetail($(this).attr("level"),true);
    });
});