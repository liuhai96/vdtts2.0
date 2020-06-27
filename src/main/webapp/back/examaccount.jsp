
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>科目考试学员统计</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <link rel="stylesheet" href=<%=path+"/static/layui/css/layui.css"%>>
    <script type="text/javascript" src=<%=path+"/static/layui/layui.js"%>></script>
    <script type="text/javascript" src="<%=path%>/static/layui/echarts.min.js"></script>

    <style>
        /*#chartmain{*/
        /*    border:2px solid #666;*/
        /*    width:49%;*/
        /*    height:450px;*/
        /*    margin: 30px auto;*/
        /*    margin-bottom: 0;*/
        /*}*/
    </style>
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM --><!-- 柱状图bar -->
<div id="echarts_bar" style="width: 1200px;height:630px;margin-top: 30px"></div>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
   var myChart = echarts.init(document.getElementById('echarts_bar'));

     layui.use('table',function () {
         var $ = layui.jquery;
         var xyList=[];
         $.ajax({
             type: "post",
             url: "<%=path%>/studentController/studentExamCount",
             dataType: 'JSON',
            success:function(msg) {
                console.log(msg);
                var title="学员科目考试统计";
                 var option = {
                     title: {
                         text: title,
                         subtext:'移动鼠标至条状图显示数量',
                         right:'center',
                         fontSize: 20
                         // textAlign:'right'
                     },
                     color: [ '#4cabce', '#e5323e'],
                     tooltip: {
                         trigger: 'axis',
                         axisPointer: {
                             type: 'shadow'
                         }
                     },
                     dataset: {
                         source: [
                             ['score', '学员数量', '科目'],
                             [89.3, msg.state1, '科目一考试人数'],
                             [57.1, msg.state2, '科目二考试人数'],
                             [74.4, msg.state3, '科目三考试人数'],
                             [10.1, msg.state4, '科目四考试人数'],
                             [10.1, msg.state4+msg.state2+msg.state3+msg.state1, '科目考试总人数'],
                ]
                     },
                     grid: {containLabel: true},
                     xAxis: {
                         name: '学员数量',
                         nameTextStyle :{
                             color: 'blue',
                             fontSize: 18
                         },
                         axisLabel: {
                             show: true,
                             textStyle: {
                                 color: 'blue',
                             },
                             fontSize: 18,//字体大小
                         },
                     },
                     yAxis: {
                         name: '科目',
                         nameTextStyle :{
                             color: 'blue',
                             fontSize: 18
                         },
                         type: 'category',
                         axisLabel: {
                             show: true,
                             textStyle: {
                                 color: 'blue',
                                 // fontSize: '38',//字体大小
                             },
                             fontSize: 18,//字体大小
                         },
                     },
                     visualMap: {
                         orient: 'horizontal',
                         left: 'center',
                         min: 1,
                         max: 10,
                         text: ['High Score', 'Low Score'],
                         // Map the score column to color
                         dimension: 0,
                         inRange: {
                             color: ['#D7DA8B', '#E15457']
                         }
                     },
                     series: [
                         {
                             type: 'bar',
                             encode: {
                                 // Map the "amount" column to X axis.
                                 x: '学员数量',
                                 // Map the "product" column to Y axis
                                 y: '科目'
                             }
                         }
                     ]
                 };
                 //获取要赋值的DOM控件
                 // var myChart = echarts.init(document.getElementById('chartmain'));
                 // 使用刚指定的配置项和数据显示图表。
                 if (option && typeof option === "object") {
                     myChart.setOption(option, true);
                 }

             }


         })
     })






</script>
</body>
</html>