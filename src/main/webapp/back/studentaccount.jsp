
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>各驾校学员统计</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <link rel="stylesheet" href=<%=path+"/static/layui/css/layui.css"%>>
    <script type="text/javascript" src=<%=path+"/static/layui/layui.js"%>></script>
    <script type="text/javascript" src="<%=path%>/static/layui/echarts.min.js"></script>
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM --><!-- 柱状图bar -->
<div id="echarts_pie" style="width: 1300px;height:630px"></div>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
   var myChart = echarts.init(document.getElementById('echarts_pie'));

     layui.use('table',function () {
         var $ = layui.jquery;
         var xyList=[];
         $.ajax({
             type: "post",
             url: "<%=path%>/schoolController/studentCount",
             dataType: 'JSON',
            success:function(msg) {
               // alert("msg"+JSON.stringify(msg));
                 $.each(msg, function (i, item) {
                    var count =item.studentCount;
                    var name = item.sname;
                    var list = [count,name];
                    var data={name:name,value:count};
                    xyList.push(data);
                })
                 // alert(JSON.stringify(xyList))
                 var option = {
                     backgroundColor: '#1b6d85',
                     title: {
                         text: '各驾校学员统计',
                         subtext:'移动鼠标至扇形图查询数量',
                         left: 'center',
                         top: 20,
                         textStyle: {
                             color: 'black',
                             fontSize: 18
                         }
                     },

                     tooltip: {
                         trigger: 'item',
                         formatter: '{a} <br/>{b} : {c} ({d}%)'
                     },

                     visualMap: {
                         show: false,
                         min: 80,
                         max: 600,
                         inRange: {
                             colorLightness: [0, 1]
                         }
                     },
                     series: [
                         {
                             name: '访问来源',
                             type: 'pie',
                             radius: '55%',
                             center: ['50%', '50%'],
                             // textStyle: {
                             //     color: 'black',
                             //     fontSize: 18
                             // },
                             data: xyList.sort(function (a, b) {
                                 return a.count - b.count;
                             }),
                             roseType: 'angle',
                             label: {
                                 color: 'rgba(255, 255, 255, 0.3)'
                             },
                             labelLine: {
                                 lineStyle: {
                                     color: 'rgba(255, 255, 255, 0.3)'
                                 },
                                 smooth: 0.2,
                                 length: 10,
                                 length2: 20
                             },
                             itemStyle: {
                                 color: '#c23531',
                                 shadowBlur: 200,
                                 shadowColor: 'rgba(0, 0, 0, 0.5)'
                             },
                             animationType: 'scale',
                             animationEasing: 'elasticOut',
                             animationDelay: function (idx) {
                                 return Math.random() * 200;
                             }
                         }
                     ]
                 };
                 //获取要赋值的DOM控件
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