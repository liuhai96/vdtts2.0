<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/6/16
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="baidu-site-verification" content="JU12JdpLM3">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="<%=path+"/css/pages/student/reset.css"%>">
    <link rel="stylesheet" href="<%=path+"/css/pages/student/idangerous.swiper.css"%>">
    <link rel="stylesheet" href="<%=path+"/css/pages/student/jx_index.css"%>">
    <link rel="stylesheet" href="https://www.layuicdn.com/layui-v2.5.6/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=path+"/css/pages/student/mnks_pc.css"%>">
    <link rel="stylesheet" href="<%=path + "/css/pages/index/jx_video.css"%>">
    <link rel="stylesheet" href="<%=path+"/css/pages/student/comm_style.css"%>">
    <link rel="stylesheet" href="<%=path+"/css/pages/student/bootstrap.min.css"%>">
    <link rel="stylesheet" href="<%=path+"/css/pages/student/comm_style.css"%>">
    <link rel="stylesheet" href="<%=path+"/css/pages/student/footer.css"%>">
</head>
<body>
<input type="hidden" id="userToken" value="${sessionScope.student.getSId()}">
<input type="hidden" id="zjh_msg" value="${zjh_msg}">
<div class="commonhead_line" style="border-top: 1px solid rgb(0, 195, 86); display: none;"></div>
<div class="home">
    <div class="banner banner_top">
        <div class="swiper-container-banner" style="display: block;">
            <div class="swiper-wrapper"
                 style="width: 7596px; height: 360px; transform: translate3d(-3038.4px, 0px, 0px); transition-duration: 0s;">
                <div class="swiper-slide swiper-slide-duplicate" style="width: 1519.2px; height: 360px;">
                    <a href="https://www.58.com/?path=ershouche/pve_5864_3_20?listfrom=dspadvert%26utm_source%3dmarket%26spm%3du-2cq4wx1yn3v43nkdq9g.jiaxiaoyidiantong_banner2"
                       target="_blank">
                        <img data-src="//pic1.58cdn.com.cn//brandads/n_v2318f1ceacae04644b526721382a99096_eb10e259f207b2ee.jpg?h=360&amp;w=1519&amp;ss=1&amp;crop=1&amp;cpos=middle"
                             src="http://www.jxedt.com/xiamen/" alt="">
                    </a>
                </div>
                <div class="swiper-slide" style="width: 1519.2px; height: 360px;">
                    <a href="http://api.jxedt.com/jump/eJibgWga" target="_blank">
                        <img data-src="//pic1.58cdn.com.cn//brandads/n_v24ee9b36af8d448738d78004896acb019_e0e156c5104f12f8.png?h=360&amp;w=1519&amp;ss=1&amp;crop=1&amp;cpos=middle"
                             src="<%=path+"/image/pages/student/n_v24ee9b36af8d448738d78004896acb019_e0e156c5104f12f8.png"%>"
                             alt="">
                    </a>
                </div>
                <div class="swiper-slide swiper-slide-visible swiper-slide-active"
                     style="width: 1519.2px; height: 360px;">
                    <a href="javascript:void(0);">
                        <img data-src="//pic1.58cdn.com.cn//brandads/n_v2f0e76515e929459b8515e270b9abfed1_5725c6ad31bf6884.png?h=360&amp;w=1519&amp;ss=1&amp;crop=1&amp;cpos=middle"
                             src="<%=path+"/image/pages/student/n_v2f0e76515e929459b8515e270b9abfed1_5725c6ad31bf6884.png"%>"
                             alt="">
                    </a>
                </div>
                <div class="swiper-slide" style="width: 1519.2px; height: 360px;">
                    <a href="https://www.58.com/?path=ershouche/pve_5864_3_20?listfrom=dspadvert%26utm_source%3dmarket%26spm%3du-2cq4wx1yn3v43nkdq9g.jiaxiaoyidiantong_banner2"
                       target="_blank">
                        <img data-src="//pic1.58cdn.com.cn//brandads/n_v2318f1ceacae04644b526721382a99096_eb10e259f207b2ee.jpg?h=360&amp;w=1519&amp;ss=1&amp;crop=1&amp;cpos=middle"
                             src="<%=path+"/image/pages/student/n_v2318f1ceacae04644b526721382a99096_eb10e259f207b2ee.jpg"%>"
                             alt="">
                    </a>
                </div>
                <div class="swiper-slide swiper-slide-duplicate" style="width: 1519.2px; height: 360px;">
                    <a href="http://api.jxedt.com/jump/eJibgWga" target="_blank">
                        <img data-src="//pic1.58cdn.com.cn//brandads/n_v24ee9b36af8d448738d78004896acb019_e0e156c5104f12f8.png?h=360&amp;w=1519&amp;ss=1&amp;crop=1&amp;cpos=middle"
                             src="<%=path+"/image/pages/student/n_v24ee9b36af8d448738d78004896acb019_e0e156c5104f12f8(1).png"%>"
                             alt="">
                    </a>
                </div>
            </div>
            <div class="swiper-pagination"><span class="swiper-pagination-switch"></span><span
                    class="swiper-pagination-switch swiper-visible-switch swiper-active-switch"></span><span
                    class="swiper-pagination-switch"></span></div>
        </div>

        <div class="banner_process">
            <ul>
                <h3>学车流程</h3>
                <li class="km1li">
                    <a href="#course1"
                       onclick="clickLog('from=JXEDT_HOME_XCLC_KM1')">
                        <div class="km1"></div>
                        <div>
                            <p>
                                科目一
                            </p>
                            <p>真实模拟，通过率高</p>
                        </div>
                    </a>
                </li>
                <li class="km2li">
                    <a href="#jinxuan"
                       onclick="clickLog('from=JXEDT_HOME_XCLC_KM2')">
                        <div class="km2"></div>
                        <div>
                            <p>
                                科目二
                            </p>
                            <p>桩考、小路，知识及技巧</p>
                        </div>
                    </a>
                </li>
                <li class="km3li">
                    <a href="#jinxuan"
                       onclick="clickLog('from=JXEDT_HOME_XCLC_KM3')">
                        <div class="km3"></div>
                        <div>
                            <p>
                                科目三
                            </p>
                            <p>大路，知识及技巧</p>
                        </div>
                    </a>
                </li>
                <li class="km4li">
                    <a href="#course4"
                       onclick="clickLog('from=JXEDT_HOME_XCLC_KM4')">
                        <div class="km4"></div>
                        <div>
                            <p>
                                科目四
                            </p>
                            <p>2020题库、题新、题准</p>
                        </div>
                    </a>
                </li>
            </ul>
        </div>
    </div>

    <div class="commonhead_line" style="border-top: 1px solid #00C356;"></div>
</div>



<br>
<br>
<div class="video-page" border="0">
    <div style="height: auto" class="video-main">
        <p>位置：我的成绩</p>
        <iframe  style="width: 1180px;border:0" src="<%=path%>/examResultController/getStudentResult?sId=${sessionScope.student.getSId()}&logo=result"></iframe>
    </div>
</div>


<br>
<br>
<br>

<div class="commonhead_line" style="border-top: 1px solid #00C356;"></div>
<div class="video-page" border="0">
    <div style="height: auto" class="video-main" border="0">
        <p>位置：我的学时</p>
        <iframe style="width: 1180px;border:0" src="<%=path%>/examResultController/getStudentResult?sId=${sessionScope.student.getSId()}&logo=period"></iframe>
    </div>
</div>


<br>
<br>
<br>

<div class="commonhead_line" style="border-top: 1px solid #00C356;"></div>

<div class="video-page">
    <div class="video-main">
        <p>位置：模拟考试</p>
        <div style="width:937px;margin: 0px auto;border: #a6e1ec solid 1px;height: 515px;">
            <style>
                .chooseClassTab{
                    background-color: #a6e1ec;
                }
                .baseClassTab{
                    background-color: #a9cbe6;
                }
            </style>
            <div class="tabCon nozgz">
                <div id="course1" class="tab chooseClassTab">科目一（交规）</div>
                <div id="course4" class="tab baseClassTab">科目四（安全文明驾驶）</div>
            </div>
            <div class="topicCon">
                <div style="margin-top: -10px;width: 699px;float: left;margin-left: 1px;">
                    <table id="courseTestRecord" lay-filter="demo"></table>
                </div>
                <div class="topic_ks borderL">
                    <div>
                        <a id="test" class="lx-link" style="height: 65%;">
                            <span class="topicIcon topic_mnks" style="margin-top: 70px;"></span>
                            <p>模拟考试</p>
                            <p class="topicDes">模拟在线真实考试</p>
                        </a>
                    </div>
                    <div class="borderB height149">
                        <a id="retest" class="lx-link" style="height: 65%;">
                            <span class="topicIcon topic_yct" style="margin-top: 70px;"></span>
                            <p>错题重做</p>
                            <p class="topicDes">准确把握考试难点</p>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<br>
<br>
<br>

<div class="commonhead_line" style="border-top: 1px solid #00C356;"></div>

<div class="video-page">
    <div class="video-main">
        <p>位置：学车视频</p>
        <!-- 学车视频左侧 -->
        <div class="video-left" style="margin: auto;width: 940px;float: none;">

            <div class="video-left-bottom" id="jinxuan" >
                <ul class="car-video">
                    <li level="2">
                        <a class="active" href="javascript:void(0);">科目二</a>
                    </li>
                    <li level="3">
                        <a href="javascript:void(0);">科目三</a>
                    </li>
                </ul>
                <div class="car-video-border"></div>
                <div class="car-video-list">
                    <div class="mediawarp" id="videoList"></div>
                </div>
            </div>
        </div>
    </div>
</div>


<br>
<br>
<br>

<script src="https://www.layuicdn.com/layui/layui.js"></script>
<script src="<%=path+"/js/pages/student/home.js"%>"></script>
</body>
</html>
