<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>模拟考试</title>
    <link rel="stylesheet" type="text/css" href="https://www.layuicdn.com/layui-v2.5.6/css/layui.css"/>
    <link rel="stylesheet" href="<%=path+"/css/pages/index/m-index.css"%>">
    <link rel="stylesheet" href="<%=path+"/css/pages/index/mnks.css"%>">
    <style>
        body{
            overflow-y:hidden;
        }
    </style>
</head>
<body>
<input type="hidden" id="level" value="${level}">
<input type="hidden" id="userToken" value="${studentId}">
<div class="clear"></div>
<p style="margin-top: 30px;margin-left: 50px;">位置：<a href="<%=path+"/student/main"%>">模拟考试：</a>${levelName}</p>
<div id="mnks">
    <div class="content-body">
        <div class="left fl">
            <div class="kt pos">
                <div class="tit">培训平台</div>
                ${levelName}模拟考场
            </div>
            <div class="user-info pos">
                <div class="tit" style="left: 38px;">考生信息</div>
                <img src="<%=path+"/image/pages/index/face_nomal.png"%>">
                <p><label class="mr">姓</label> <label>名：</label> <span>${studentName}</span></p>
                <p><label class="mr">性</label> <label>别：</label> <span>${sex}</span></p>
                <p><label>考试类型：</label> <span>小车类</span></p>
                <p><label>考试科目：</label> <span>${levelName}</span></p>

            </div>
            <div class="waste-time pos">
                <div class="tit" style="left: 38px;">剩余时间</div>
                <span class="countdown" id="time">45:00</span>
            </div>
        </div>
        <div class="center fl">
            <div class="kstm pos">
                <div class="tit">考试题目</div>
                <p class="name" style="width: 400px;">
                    <strong class="zjh_exam_question_index" id="questionIndex"></strong>、
                    <span id="questionTitle"></span>
                </p>
                <div style="float: right;" class="zjh_exam_question_pic_title layer-photos" id="layer-photos"></div>
                <div class="option">
                    <div id="answers"></div>
                </div>
                <div class="tm-answer">
                    <div class="fl sec-aswer">您的选项是：<span id="userChoice"></span></div>
                    <div class="fr aswer">
                        <span class="fl">
                            <span id="userResult"></span>
                            <div id="answerBtn"></div>
                        </span>
                    </div>
                </div>
            </div>
            <div class="tsxx pos fl">
                <div class="tit">提示信息</div>
                <span class="tsxxt" id="answer_tips_title"></span>

            </div>
            <div class="btn-wrap fl">
                <a href="javascript:;" class="btn pre" id="preBtn">上一题</a>
                <a href="javascript:;" class="btn next" id="postBtn">下一题</a>
                <a href="javascript:;" class="btn jj" id="submitBtn">交卷</a>
            </div>
        </div>
        <div class="right fl">
            <div class="Qn-wrap" id="qunestionIndex"></div>
        </div>
    </div>
</div>

<script src="https://www.layuicdn.com/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['layer'], function () {
        let $ = layui.$;
        let layer = layui.layer;

        $("#iframe",window.parent.document).removeAttr("style");
        $("#iframe",window.parent.document).attr("style","height:"+(Number(document.body.scrollHeight))+"px;");

        let path = window.document.location.href.substring(0, (window.document.location.href).indexOf(window.document.location.pathname));

        $(function () {
            var m = 44;
            var s = 59;
            let countDown = setInterval(function () {
                if (s < 10) {
                    //如果秒数少于10在前面加上0
                    $('#time').html(m + ':0' + s);
                } else {
                    $('#time').html(m + ':' + s);
                }
                s--;
                if (s < 0) {
                    //如果秒数少于0就变成59秒
                    s = 59;
                    m--;
                    if (m < 0) {
                        clearInterval(countDown);
                        submitTest();
                    }
                }
            }, 1000)
        });

        let questionList = null;

        $.ajax({
            type: "get",
            url: path + "/api/exam/text?level=" + $("#level").val(),
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
                //获取题库对象
                questionList = res.data.question;

                //获取
                let table = $("#qunestionIndex");

                let str = "";

                //生成题目下方的题目标签
                for (let index = 0; index < questionList.length;) {
                    for (let j = 0; j < 25 && index < questionList.length; j++, index++) {

                        questionList[index].rightId = new Array();
                        questionList[index].rightChoice = new Array();
                        questionList[index].userChoise = new Array();
                        questionList[index].userChoiseId = new Array();

                        str += "<li class='qn noSel questionIndex" + (index + 1) + "e'><p>" + (index + 1) + "</p></li>";
                    }
                }

                $(table).html(str);

                //显示第一题
                showQuestion(0);
            }
        });


        //点击下边题目索引时，跳转到指定题目
        $(document).on("click", "li[class*='questionIndex']", function (event) {
            // console.log($(this).children().html());
            showQuestion($(this).children().html() - 1);
        });

        //点击上一题
        $(document).on("click", "#preBtn", function (event) {
            let index = Number($("#questionIndex").html() - 1);
            if (index < 1) {
                return;
            }
            showQuestion(--index);
        });

        //点击下一题
        $(document).on("click", "#postBtn", function (event) {
            let index = Number($("#questionIndex").html());
            if (index >= questionList.length) {
                return;
            }
            showQuestion(index);
        });

        //点击答案按钮时，把点击的按钮ID和按钮上的选项存入到题目中
        $(document).on("click", "li[class*='answerBtn']", function (event) {

            let index = Number($("#questionIndex").html() - 1);

            let question = questionList[index];

            //如果是单选题就只保证元素只有一个
            //如果是多选题就保证数组元素可以有多个
            if (question.rightId.length == 1) {
                questionList[index].userChoise = new Array();
                questionList[index].userChoise.push($(this).html());
                questionList[index].userChoiseId = new Array();
                questionList[index].userChoiseId.push(Number($(this).attr("answerId")));

                $("li[class*='answerBtn']").removeClass("active");
                $(this).addClass("active");
            } else {
                customSetGet(question.userChoise, $(this).html());
                customSetGet(question.userChoiseId, Number($(this).attr("answerId")));

                if ($(this).hasClass("active")) {
                    $(this).removeClass("active");
                } else {
                    $(this).addClass("active");
                }
            }

            $("#userChoice").html(question.userChoise);
        });

        //点击交卷按钮时，提交的此操作
        $(document).on("click", "#submitBtn", function (event) {

            let index = 0;

            //遍历题目，如果还有题目没有选择答案，就不让提交
            for (; index < questionList.length; index++) {
                if (questionList[index].userChoise.length == 0) {
                    submit = false;
                    break;
                }
            }

            //如果做题完成，就提交答案
            if (submit) {
                submitTest();
            } else {
                layer.confirm('还有未做完的题目，确定要交卷？', {
                    btn: ['提交','继续做题']
                }, function(layerIndex){
                    submitTest(layerIndex);
                }, function(){
                    showQuestion(index);
                });
            }

        });

        //交卷流程
        function submitTest(layerIndex) {

            layer.close(layerIndex);

            let sum = 0;
            let errorQuestions = [];

            //遍历题库，如果未选择或者出现错题，直接跳过，如果正确，分数+1
            for (let index = 0; index < questionList.length; index++) {

                let result = checkResult(questionList[index]);

                if (result == 1) {
                    sum++;
                }

                if (result == 0 || result == -1) {
                    errorQuestions.push(questionList[index].eqId);
                }
            }


            console.log("计算错题完成，开始提示");

            if (sum > 90) {
                alert("恭喜你，通过了考试，得分：" + sum);
            } else {
                alert("还差一点点就通过了考试，得分：" + sum);
            }


            $.ajax({
                type: 'post',
                dataType: 'json',
                url: path + "/api/exam/record",
                data: {
                    studentId: $("#userToken").val()
                    , score: sum
                    , level: $("#level").val()
                    , errorQuestions: errorQuestions
                    , _method: 'put'
                },
                traditional: true
            });

            window.location.href = path + "/student/main";
        };


        //检测答案是否正确
        //正确：1
        //错误：0
        //未做：-1
        function checkResult(question) {

            //未作
            if (question.userChoise.length == 0) {
                return -1;
            }

            //正确
            if (checkSameItem(question.userChoiseId, question.rightId)) {
                return 1;
            }

            //错误
            return 0;
        }

        //判断两个数组中的元素完全相同
        function checkSameItem(array1, array2) {
            if (array1.length == 0 && array2.length == 0) {
                return true;
            }

            if (array1.length != array2.length) {
                return false;
            }

            for (let i = 0; i < array1.length; i++) {
                if (array2.indexOf(array1[i])) {
                    return false;
                }
            }


            return true;
        }


        //如果数组中不存在就插入,存在就删除
        function customSetGet(array, item) {
            let index = array.indexOf(item);

            if (index == -1) {
                array.push(item);
            } else {
                array.splice(index, 1);
            }
        }

        //如果数组中不存在就插入
        function setIfWithout(array, item) {
            let index = array.indexOf(item);

            if (index == -1) {
                array.push(item);
            }
        }

        //显示新的题目
        function showQuestion(index) {

            //获取上一题的题目编号(不是ID)
            let oldIndex = $("#questionIndex").html();

            //把上一题的答题结果隐藏掉
            $("#userResult").html("");

            //如果不是第一次进入这个界面
            if (oldIndex.length > 0) {

                //获取上一个题目编号
                oldIndex = Number(oldIndex - 1);

                //移除题号跳转的蓝色底色
                $("li[class*='questionIndex" + Number(oldIndex + 1) + "e']").removeClass("active");

                //开始判断上一题是否回答正确
                let oldResult = checkResult(questionList[oldIndex]);

                //如果正确、错误   就变色，如果没做就不变色
                switch (oldResult) {
                    case 1:
                        $("li[class*='questionIndex" + Number(oldIndex + 1) + "e']").children().addClass("rightA");
                        break;
                    case 0:
                        $("li[class*='questionIndex" + Number(oldIndex + 1) + "e']").children().addClass("wrong");
                        break;
                }
            }


            //获取要跳转的题目
            let newQuestion = questionList[index];

            //更新题目编号
            $("#questionIndex").html(index + 1);

            //更新题目信息
            $("#questionTitle").html(newQuestion.eqQuestion);

            let answerString = "";
            let answerBtnString = "";

            //生成答案选项
            for (let i = 0; i < newQuestion.answers.length; i++) {
                let choice = String.fromCharCode((65 + i));

                //读取题目的正确选项ID和正确的A/B/C/D
                if (newQuestion.answers[i].eaRight == 'true') {
                    // newQuestion.rightId=newQuestion.answers[i].eaId;

                    setIfWithout(newQuestion.rightId, newQuestion.answers[i].eaId);

                    setIfWithout(newQuestion.rightChoice, choice);
                }

                //开始拼接答案信息
                answerString += "<p><span style='font-size: x-large;'>" + choice + ".</span><span style='font-size: medium;'>&nbsp;&nbsp;" + newQuestion.answers[i].eaAnswer + "</span></p><br>";

                //开始拼接答案选项
                answerBtnString += "<li class='answerBtn' answerId='" + newQuestion.answers[i].eaId + "'>" + choice + "</li>"
            }

            if (newQuestion.rightId.length == 1) {
                $("#answer_tips_title").html("单选题，请选择答案");
            } else {
                $("#answer_tips_title").html("多选题，请选择答案");
            }


            //显示答案信息
            $("#answers").html(answerString);

            //获取跳转题目的答题信息
            let newResult = checkResult(newQuestion);

            //如果题目未答题，就把用户选项置空，如果已答题，就把用户选项展示在窗口上
            $("#userChoice").html(newResult == -1 ? "" : newQuestion.userChoise);

            //如果答题正确，更新答题结果
            if (newResult == 1) {
                $("#userResult").html("回答正确!")
                $("#userResult").attr("style", "color: #24c27d;")
            }

            //如果答题正确，更新答题结果
            if (newResult == 0) {
                $("#userResult").html("正确答案为：" + newQuestion.rightChoice)
                $("#userResult").attr("style", "color: #fb6e52;")
            }

            if (newResult == -1) {
                $("#userResult").html("请选择：");
                $("#userResult").attr("style", "")
            }

            //如果已答题，就把答案按钮隐藏起来
            if (newResult == 1 || newResult == 0) {
                answerBtnString = "";
            }

            //显示答题按钮
            $("#answerBtn").html(answerBtnString);

            //更新题目的图片
            if (newQuestion.eqPic != "") {
                $("#layer-photos").html("<img id='questionPic' style='max-width: 250px;max-height: 250px' class='zjh_exam_question_pic' src='" + newQuestion.eqPic + "' layer-src='" + newQuestion.eqPic + "' />");
            } else {
                $("#layer-photos").empty();
            }

            //图片放大
            layer.photos({
                photos: '#layer-photos'
                , anim: 5
            });

            //在当前题号上添加蓝色底色
            $("li[class*='questionIndex" + Number(index + 1) + "e']").addClass("active");
        };
    });
</script>
</body>
</html>
