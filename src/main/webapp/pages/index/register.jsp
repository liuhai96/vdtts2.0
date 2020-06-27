<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/6/20
  Time: 10:37
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
    <title>学员注册</title>
    <link rel="stylesheet" href="<%=path + "/css/pages/index/font_949786_v8zsbvaxz6p.css"%>">
    <link rel="stylesheet" href="<%=path + "/css/pages/index/reset.css"%>">
    <link rel="stylesheet" href="<%=path + "/css/pages/index/registration.css"%>">
    <link rel="stylesheet" href="https://www.layuicdn.com/layui-v2.5.6/css/layui.css" media="all">
    <script type="text/javascript" src=<%=path+"/static/layui/layui.js"%>></script>
    <style>
        .header-wrap {
            width: 100%;
            /*overflow: hidden;*/
        }

        .header-nav {
            width: 1230px;
            margin: 0 auto;
            /*overflow: hidden;*/
        }

        .vip:hover a {
            color: #00c356;
        }

        .left-nav {
            height: 40px;
            line-height: 40px;
            float: left;
        }

        .left-nav div {
            float: left;
        }

        .left-nav .logo-img {}

        .left-nav .logo-img a {
            overflow: hidden;
            height: 40px;
            display: flex;
            align-items: center;
        }

        .left-nav .logo-img img {
            /*margin-top: 5px;*/
            width: 30px;
            height: 30px;
        }

        .left-nav .vip {
            margin: 0 10px;
        }

        .left-nav .vip a {
            font-size: 14px;
        }

        .left-nav .vip i {
            font-size: 12px;
            color: #00c356;
        }

        .left-nav .tel {
            padding-left: 10px;
            /*margin-top: 11px;*/
            font-size: 14px;
            color: #999999;
        }

        .left-nav .tel span {
            color: #00C356;
        }

        .left-nav .nav-btn {
            margin-left: 10px;
        }

        .left-nav .nav-btn span {
            margin: 0 5px;
        }

        .left-nav .nav-btn a {
            font-size: 14px;
            color: #00c356;
        }

        .right-nav {
            float: right;
        }

        .right-nav .right-text {
            margin: 10px;
            color: #00c356;
            font-size: 14px;
        }

        .right-nav .right-text:last-child {
            margin-right: 34px;
        }

        .comment-header-app {
            font-size: 14px;
            color: rgba(102, 102, 102, 1);
            display: inline-block;
            width: 80px;
            height: 40px;
            line-height: 40px;
            text-align: center;
            position: relative;
        }

        .comment-header-app:hover {
            color: #00c356;
            background-color: #fff;
            /* border-color: #E5E5E5; */
            box-shadow: 0 3px 5px 0 rgba(0, 0, 0, 0.1);
            /* border: none; */
            /*border: 1px solid #E5E5E5;*/
        }

        .comment-header-app:hover .down-popup {
            display: inline-block;
        }

        .down-popup {
            border: 1px solid #E5E5E5;
            left: -1px;
            display: none;
            position: absolute;
            top: 40px;
            z-index: 9999;
            height: 76px;
            width: 80px;
            text-align: center;
            margin: 0;
            padding: 0;
            background-color: #fff;
            box-shadow: 0 3px 5px 0 rgba(0, 0, 0, .1);
            border-bottom-left-radius: 4px;
            border-bottom-right-radius: 4px;
            border-top: none;
        }

        .down-popup li {
            margin: 10px 0;
            padding: 0;
            height: 20px;
            text-decoration: none;
            line-height: 20px;
            border-left: 1px solid transparent;
            border-right: 1px solid transparent;
        }

        .down-popup li a {
            font-size: 12px;
            color: #999999;
        }

        .down-popup li a:hover {
            color: #00c356;
        }

        .floating-wrap {
            height: 100%;
            width: 100%;
            background: rgba(0, 0, 0, 0.5);
            position: absolute;
            display: none;
            z-index: 99;
        }

        .floating-wrap .popup {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: #fff;
            width: 800px;
            height: 500px;
            border-radius: 20px;
        }

        .rep_md_panel {
            margin: 0 auto;
            padding: 0 30px;
            height: auto;
        }


        .form-group {
            overflow: hidden;
        }

        .rep_item_input {
            display: inline-block;
        }

        .rep_sm_panel {
            width: 700px;
            height: 54px;
            padding: 15px 20px;
            border: 1px solid #ebf2f1;
            margin: 0 auto 20px;
        }

        #jxkeywordOne,
        #jxkeywordTwo,
        #jxkeywordThree {
            display: inline-block;
        }

        .rep_md_panel .nametip {
            font-size: 14px;
            color: #999;
            font-weight: normal;
            margin-left: 15px;
        }

        .hbank {
            display: inline-block;
            width: 140px;
        }

        .sel_mask {
            color: #999;
        }

        .must {
            color: red;
        }

        .form-group {
            margin: 20px 0;
        }

        select {
            /*Chrome和Firefox里面的边框是不一样的，所以复写了一下*/
            border: none;
            width: 134px;
            padding-left: 10px;
            outline: none;
            height: 28px;
            /*很关键：将默认的select选择框样式清除*/
            appearance: none;
            -moz-appearance: none;
            -webkit-appearance: none;
            /*在选择框的最右侧中间显示小箭头图片*/
            background: url("http://ourjs.github.io/static/2015/arrow.png") no-repeat scroll right center transparent;
            /*为下拉小箭头留出一点位置，避免被文字覆盖*/
            padding-right: 14px;
        }

        /*清除ie的默认选择框样式清除，隐藏下拉箭头*/
        select::-ms-expand {
            display: none;
        }

        .rep_item_input {
            border: 1px solid #999999;
            border-radius: 5px;
        }

        .form-group input {
            height: 28px;
            width: 250px;
            border-radius: 3px;
            border: none;
            outline: none;
            padding-left: 4px;
        }

        .close {
            font-size: 16px;
            font-weight: bold;
            float: right;
            margin-right: 17px;
            margin-top: 10px;
            cursor: pointer;
        }

        #send_code {
            cursor: pointer;
            width: 111px;
            border: none;
            height: 30px;
            background: #00C356;
            color: #fff;
            display: inline-block;
            vertical-align: bottom;
            line-height: 30px;
            text-align: center;
            border-radius: 5px;
        }

        #submit_btn {
            cursor: pointer;
            width: 100px;
            border: none;
            height: 30px;
            background: #00C356;
            color: #fff;
            display: inline-block;
            line-height: 30px;
            text-align: center;
            border-radius: 5px;
            float: right;
            margin-right: 29px;
        }

        .title-phone {
            margin-left: 20px;
            font-size: 14px;
            color: #999;
            font-weight: normal;
        }

        .loading-submit {
            height: 100%;
            width: 100%;
            position: absolute;
            display: none;
            top: 0;
            z-index: 999;
            background: rgba(0, 0, 0, 0.5);
            border-radius: 20px;
            text-align: center;
            line-height: 16;
            font-size: 28px;
            color: #ffffff;
        }

        #registerJXfrom {
            position: relative;
        }
    </style>
    <style type="text/css">
        .yidun.yidun--light.yidun--error.yidun--icon_point .yidun_tips .yidun_tips__icon,
        .yidun.yidun--light.yidun--error.yidun--inference .yidun_tips .yidun_tips__icon,
        .yidun.yidun--light.yidun--error.yidun--maxerror .yidun_tips .yidun_tips__icon,
        .yidun.yidun--light.yidun--error.yidun--point .yidun_tips .yidun_tips__icon,
        .yidun.yidun--light.yidun--error.yidun--sms .yidun_tips .yidun_tips__icon,
        .yidun.yidun--light.yidun--icon_point.yidun--button .yidun_tips .yidun_tips__icon,
        .yidun.yidun--light.yidun--inference.yidun--button .yidun_tips .yidun_tips__icon,
        .yidun.yidun--light.yidun--point.yidun--button .yidun_tips .yidun_tips__icon,
        .yidun.yidun--light.yidun--success.yidun--icon_point .yidun_tips .yidun_tips__icon,
        .yidun.yidun--light.yidun--success.yidun--inference .yidun_tips .yidun_tips__icon,
        .yidun.yidun--light.yidun--success.yidun--point .yidun_tips .yidun_tips__icon,
        .yidun.yidun--light.yidun--success.yidun--sms .yidun_tips .yidun_tips__icon,
        .yidun.yidun--light .yidun_loadbox .yidun_loadbox__inner,
        .yidun.yidun--light .yidun_loadbox .yidun_loadbox__inner .yidun_loadicon,
        .yidun.yidun--light .yidun_tips__answer,
        .yidun_intellisense--light .yidun_classic-tips .yidun_tips__icon,
        .yidun_intellisense--light .yidun_intelli-icon,
        .yidun_popup.yidun_popup--light .yidun_modal,
        .yidun_popup.yidun_popup--light .yidun_modal__close .yidun_icon-close {
            display: inline-block;
            *display: inline;
            zoom: 1;
            vertical-align: top
        }

        .yidun,
        .yidun_pop {
            -webkit-tap-highlight-color: transparent
        }

        .yidun *,
        .yidun_pop * {
            box-sizing: border-box
        }

        .panel_ease_top-enter,
        .panel_ease_top-leave-active {
            opacity: 0;
            -webkit-transform: translateY(20px);
            transform: translateY(20px)
        }

        .panel_ease_bottom-enter,
        .panel_ease_bottom-leave-active {
            opacity: 0;
            -webkit-transform: translateY(-20px);
            transform: translateY(-20px)
        }

        .panel_ease_bottom-enter-active,
        .panel_ease_bottom-leave-active,
        .panel_ease_top-enter-active,
        .panel_ease_top-leave-active {
            transition: all .2s linear;
            pointer-events: none
        }

        .popup_scale-enter,
        .popup_scale-leave-active {
            opacity: 0;
            -webkit-transform: scale(0);
            transform: scale(0)
        }

        .popup_scale-enter-active {
            transition: all .3s cubic-bezier(.76, .01, .35, 1.56)
        }

        .popup_scale-leave-active {
            transition: all .2s ease-out
        }

        .popup_ease-enter {
            opacity: 0;
            -webkit-transform: translateY(-20px);
            transform: translateY(-20px)
        }

        .popup_ease-enter-active {
            transition: opacity .3s linear, -webkit-transform .3s linear;
            transition: opacity .3s linear, transform .3s linear;
            transition: opacity .3s linear, transform .3s linear, -webkit-transform .3s linear
        }

        .popup_ease-leave-active {
            opacity: 0;
            -webkit-transform: translateY(-20px);
            transform: translateY(-20px);
            transition: all .2s ease-out
        }

        @-webkit-keyframes loading {
            0% {
                -webkit-transform: rotate(0deg);
                transform: rotate(0deg)
            }

            to {
                -webkit-transform: rotate(1turn);
                transform: rotate(1turn)
            }
        }

        @keyframes loading {
            0% {
                -webkit-transform: rotate(0deg);
                transform: rotate(0deg)
            }

            to {
                -webkit-transform: rotate(1turn);
                transform: rotate(1turn)
            }
        }

        @-webkit-keyframes ball-scale-multiple {
            0% {
                -webkit-transform: scale(.22);
                transform: scale(.22);
                opacity: 0
            }

            5% {
                opacity: 1
            }

            to {
                -webkit-transform: scale(1);
                transform: scale(1);
                opacity: 0
            }
        }

        @keyframes ball-scale-multiple {
            0% {
                -webkit-transform: scale(.22);
                transform: scale(.22);
                opacity: 0
            }

            5% {
                opacity: 1
            }

            to {
                -webkit-transform: scale(1);
                transform: scale(1);
                opacity: 0
            }
        }

        @-webkit-keyframes bright {
            0% {
                opacity: .5
            }

            to {
                opacity: 1
            }
        }

        @keyframes bright {
            0% {
                opacity: .5
            }

            to {
                opacity: 1
            }
        }

        .yidun_cover-frame {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            border: 0;
            opacity: 0;
            filter: alpha(opacity=0)
        }

        .yidun.yidun--light {
            position: relative;
            margin: auto;
            font-size: 14px
        }

        .yidun.yidun--light .yidun_jigsaw,
        .yidun.yidun--light .yidun_slide_indicator,
        .yidun.yidun--light .yidun_slider {
            display: none
        }

        .yidun.yidun--light.yidun--jigsaw .yidun_jigsaw,
        .yidun.yidun--light.yidun--jigsaw .yidun_slide_indicator,
        .yidun.yidun--light.yidun--jigsaw .yidun_slider {
            display: block
        }

        .yidun.yidun--light .yidun_jigsaw {
            position: absolute;
            left: 0;
            top: 0;
            width: auto;
            height: 100%;
            -webkit-transform: translateZ(0);
            -webkit-perspective: 1000;
            -webkit-backface-visibility: hidden
        }

        .yidun.yidun--light .yidun_icon-point {
            position: absolute;
            width: 26px;
            height: 33px;
            cursor: pointer;
            background-repeat: no-repeat
        }

        .yidun.yidun--light .yidun_icon-point.yidun_point-1 {
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 -367px;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {
            .yidun.yidun--light .yidun_icon-point.yidun_point-1 {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 -367px;
                background-size: 32px 544px
            }
        }

        .yidun.yidun--light .yidun_icon-point.yidun_point-2 {
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 -403px;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {
            .yidun.yidun--light .yidun_icon-point.yidun_point-2 {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 -403px;
                background-size: 32px 544px
            }
        }

        .yidun.yidun--light .yidun_icon-point.yidun_point-3 {
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 -439px;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {
            .yidun.yidun--light .yidun_icon-point.yidun_point-3 {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 -439px;
                background-size: 32px 544px
            }
        }

        .yidun.yidun--light .yidun_icon-point.yidun_point-4 {
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 -475px;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {
            .yidun.yidun--light .yidun_icon-point.yidun_point-4 {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 -475px;
                background-size: 32px 544px
            }
        }

        .yidun.yidun--light .yidun_icon-point.yidun_point-5 {
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 -511px;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {
            .yidun.yidun--light .yidun_icon-point.yidun_point-5 {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 -511px;
                background-size: 32px 544px
            }
        }

        .yidun.yidun--light.yidun--maxerror .yidun_icon-point {
            cursor: default
        }

        .yidun.yidun--light .yidun_inference {
            display: none;
            position: absolute;
            width: 25%;
            height: 50%;
            overflow: hidden;
            box-sizing: border-box;
            background-color: transparent
        }

        .yidun.yidun--light .yidun_inference .yidun_inference__border {
            display: block;
            position: absolute;
            top: 0;
            left: 0;
            bottom: 0;
            right: 0;
            z-index: 1;
            border: 1px solid #fff;
            box-sizing: border-box;
            background: transparent;
            border-radius: inherit;
            transition: border .2s ease-out 0s
        }

        .yidun.yidun--light .yidun_inference.yidun_inference--0,
        .yidun.yidun--light .yidun_inference.yidun_inference--0 .yidun_inference__img {
            top: 0;
            left: 0
        }

        .yidun.yidun--light .yidun_inference.yidun_inference--1 {
            top: 0;
            left: 25%
        }

        .yidun.yidun--light .yidun_inference.yidun_inference--1 .yidun_inference__img {
            top: 0;
            left: -100%
        }

        .yidun.yidun--light .yidun_inference.yidun_inference--2 {
            top: 0;
            left: 50%
        }

        .yidun.yidun--light .yidun_inference.yidun_inference--2 .yidun_inference__img {
            top: 0;
            left: -200%
        }

        .yidun.yidun--light .yidun_inference.yidun_inference--3 {
            top: 0;
            left: 75%
        }

        .yidun.yidun--light .yidun_inference.yidun_inference--3 .yidun_inference__img {
            top: 0;
            left: -300%
        }

        .yidun.yidun--light .yidun_inference.yidun_inference--4,
        .yidun.yidun--light .yidun_inference.yidun_inference--4 .yidun_inference__img {
            bottom: 0;
            left: 0
        }

        .yidun.yidun--light .yidun_inference.yidun_inference--5 {
            bottom: 0;
            left: 25%
        }

        .yidun.yidun--light .yidun_inference.yidun_inference--5 .yidun_inference__img {
            bottom: 0;
            left: -100%
        }

        .yidun.yidun--light .yidun_inference.yidun_inference--6 {
            bottom: 0;
            left: 50%
        }

        .yidun.yidun--light .yidun_inference.yidun_inference--6 .yidun_inference__img {
            bottom: 0;
            left: -200%
        }

        .yidun.yidun--light .yidun_inference.yidun_inference--7 {
            bottom: 0;
            left: 75%
        }

        .yidun.yidun--light .yidun_inference.yidun_inference--7 .yidun_inference__img {
            bottom: 0;
            left: -300%
        }

        .yidun.yidun--light .yidun_inference.yidun_inference--drag .yidun_inference__border,
        .yidun.yidun--light .yidun_inference.yidun_inference--swap .yidun_inference__border,
        .yidun.yidun--light .yidun_inference:hover .yidun_inference__border {
            border-color: #2c6eff;
            border-width: 2px
        }

        .yidun.yidun--light .yidun_inference.yidun_inference--drag,
        .yidun.yidun--light .yidun_inference:hover {
            background-color: #fff
        }

        .yidun.yidun--light .yidun_inference.yidun_inference--drag .yidun_inference__img,
        .yidun.yidun--light .yidun_inference:hover .yidun_inference__img {
            opacity: .3;
            filter: alpha(opacity=30)
        }

        .yidun.yidun--light .yidun_inference:hover {
            cursor: pointer
        }

        .yidun.yidun--light .yidun_inference.yidun_inference--drag {
            z-index: 1;
            box-shadow: 0 2px 6px 30%
        }

        .yidun.yidun--light .yidun_inference.yidun_inference--origin .yidun_inference__border {
            background-color: #d8d8d8
        }

        .yidun.yidun--light .yidun_inference.yidun_inference--swap .yidun_inference__border {
            background: transparent
        }

        .yidun.yidun--light .yidun_inference__img {
            position: absolute;
            width: 400%;
            height: 200%;
            pointer-events: none;
            transition: opacity .2s ease-out
        }

        .yidun.yidun--light.yidun--inference .yidun_inference {
            display: block;
            background-color: #fff
        }

        .yidun.yidun--light.yidun--inference .yidun_bg-img {
            display: none
        }

        .yidun.yidun--light.yidun--float .yidun_panel {
            display: none;
            position: absolute;
            left: 0;
            width: 100%;
            z-index: 999
        }

        .yidun.yidun--light .yidun_panel {
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none
        }

        .yidun.yidun--light .yidun_panel-placeholder {
            pointer-events: auto;
            position: relative;
            padding-top: 50%
        }

        .yidun.yidun--light .yidun_bgimg {
            pointer-events: auto;
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%
        }

        .yidun.yidun--light .yidun_bgimg .yidun_bg-img {
            pointer-events: auto;
            vertical-align: top;
            width: 100%
        }

        .yidun.yidun--light.yidun--icon_point .yidun_bgimg,
        .yidun.yidun--light.yidun--icon_point .yidun_panel-placeholder,
        .yidun.yidun--light.yidun--inference .yidun_bgimg,
        .yidun.yidun--light.yidun--inference .yidun_panel-placeholder {
            overflow: hidden
        }

        .yidun.yidun--light .yidun_loadbox {
            display: none;
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            text-align: center;
            background-image: url("https://cstaticdun.126.net//2.13.7/images/tipBg.ad4e919.png");
            background-color: #f7f9fa;
            background-position: 50%;
            background-size: cover
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {
            .yidun.yidun--light .yidun_loadbox {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/tipBg@2x.c7a9593.png")
            }
        }

        .yidun.yidun--light .yidun_loadbox .yidun_loadbox__inner {
            position: relative;
            top: 50%;
            margin-top: -25px
        }

        .yidun.yidun--light .yidun_loadbox .yidun_loadbox__inner .yidun_loadicon {
            width: 32px;
            height: 32px;
            background-repeat: no-repeat
        }

        .yidun.yidun--light .yidun_loadbox .yidun_loadbox__inner .yidun_loadtext {
            display: block;
            line-height: 20px;
            color: #45494c
        }

        .yidun.yidun--light .yidun_top {
            position: absolute;
            right: 0;
            top: 0;
            max-width: 68px;
            z-index: 2
        }

        .yidun.yidun--light .yidun_refresh {
            float: right;
            width: 30px;
            height: 30px;
            margin-left: 4px;
            cursor: pointer;
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 -233px;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {
            .yidun.yidun--light .yidun_refresh {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 -233px;
                background-size: 32px 544px
            }
        }

        .yidun.yidun--light .yidun_refresh:hover {
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 -266px;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {
            .yidun.yidun--light .yidun_refresh:hover {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 -266px;
                background-size: 32px 544px
            }
        }

        .yidun.yidun--light.yidun--maxerror .yidun_refresh {
            cursor: not-allowed
        }

        .yidun.yidun--light.yidun--maxerror .yidun_refresh:hover {
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 -233px;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {
            .yidun.yidun--light.yidun--maxerror .yidun_refresh:hover {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 -233px;
                background-size: 32px 544px
            }
        }

        .yidun.yidun--light .yidun_feedback {
            float: left;
            display: block;
            width: 30px;
            height: 26px;
            cursor: pointer;
            outline: none;
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 -175px;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {
            .yidun.yidun--light .yidun_feedback {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 -175px;
                background-size: 32px 544px
            }
        }

        .yidun.yidun--light .yidun_feedback:hover {
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 -204px;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {
            .yidun.yidun--light .yidun_feedback:hover {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 -204px;
                background-size: 32px 544px
            }
        }

        .yidun.yidun--light .yidun_control {
            position: relative;
            border: 1px solid #e4e7eb;
            background-color: #f7f9fa
        }

        .yidun.yidun--light .yidun_control.yidun_control--moving .yidun_slide_indicator {
            border-color: #1991fa;
            background-color: #d1e9fe
        }

        .yidun.yidun--light .yidun_control.yidun_control--moving .yidun_slider {
            background-color: #1991fa
        }

        .yidun.yidun--light .yidun_control.yidun_control--moving .yidun_slider .yidun_slider__icon {
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 0;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {
            .yidun.yidun--light .yidun_control.yidun_control--moving .yidun_slider .yidun_slider__icon {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 0;
                background-size: 32px 544px
            }
        }

        .yidun.yidun--light .yidun_slide_indicator {
            position: absolute;
            top: -1px;
            left: -1px;
            width: 0;
            border: 1px solid transparent
        }

        .yidun.yidun--light .yidun_slider {
            position: absolute;
            top: 0;
            left: 0;
            height: 100%;
            background-color: #fff;
            box-shadow: 0 0 3px rgba(0, 0, 0, .3);
            cursor: pointer;
            transition: background .2s linear
        }

        .yidun.yidun--light .yidun_slider:hover {
            background-color: #1991fa
        }

        .yidun.yidun--light .yidun_slider:hover .yidun_slider__icon {
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 0;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {
            .yidun.yidun--light .yidun_slider:hover .yidun_slider__icon {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 0;
                background-size: 32px 544px
            }
        }

        .yidun.yidun--light .yidun_slider .yidun_slider__icon {
            position: absolute;
            top: 50%;
            margin-top: -6px;
            left: 50%;
            margin-left: -6px;
            width: 14px;
            height: 10px;
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 -13px;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {
            .yidun.yidun--light .yidun_slider .yidun_slider__icon {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 -13px;
                background-size: 32px 544px
            }
        }

        .yidun.yidun--light .yidun_slider img.yidun_slider__icon {
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            margin: 0;
            background-image: none !important
        }

        .yidun.yidun--light .yidun_tips {
            text-align: center;
            color: #45494c
        }

        .yidun.yidun--light .yidun_tips .yidun_sms-counter {
            color: #1991fa
        }

        .yidun.yidun--light .yidun_tips__text {
            vertical-align: middle
        }

        .yidun.yidun--light .yidun_tips__answer {
            vertical-align: middle;
            font-weight: 700
        }

        .yidun.yidun--light .yidun_tips__answer.hide {
            display: none
        }

        .yidun.yidun--light.yidun--point .yidun_tips__point {
            display: inline
        }

        .yidun.yidun--light.yidun--point .yidun_tips__img {
            display: none
        }

        .yidun.yidun--light.yidun--icon_point .yidun_tips__answer {
            width: 80px;
            height: 20px;
            margin-left: 8px;
            overflow: hidden;
            position: relative
        }

        .yidun.yidun--light.yidun--icon_point .yidun_tips__point {
            display: none
        }

        .yidun.yidun--light.yidun--icon_point .yidun_tips__img {
            display: block;
            position: absolute;
            bottom: -60px;
            left: 0;
            width: 400%;
            height: 1200%
        }

        .yidun.yidun--light .yidun_answer--r2l .yidun_tips__img {
            bottom: -40px
        }

        .yidun.yidun--light.yidun--loadfail .yidun_bgimg,
        .yidun.yidun--light.yidun--loading .yidun_bgimg {
            display: none
        }

        .yidun.yidun--light.yidun--loadfail .yidun_loadbox,
        .yidun.yidun--light.yidun--loading .yidun_loadbox {
            display: block
        }

        .yidun.yidun--light.yidun--loadfail .yidun_slider,
        .yidun.yidun--light.yidun--loading .yidun_slider {
            cursor: not-allowed
        }

        .yidun.yidun--light.yidun--loadfail .yidun_slider:hover,
        .yidun.yidun--light.yidun--loading .yidun_slider:hover {
            background-color: #fff
        }

        .yidun.yidun--light.yidun--loadfail .yidun_slider:hover .yidun_slider__icon,
        .yidun.yidun--light.yidun--loading .yidun_slider:hover .yidun_slider__icon {
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 -13px;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {

            .yidun.yidun--light.yidun--loadfail .yidun_slider:hover .yidun_slider__icon,
            .yidun.yidun--light.yidun--loading .yidun_slider:hover .yidun_slider__icon {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 -13px;
                background-size: 32px 544px
            }
        }

        .yidun.yidun--light.yidun--loading .yidun_loadicon {
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 -332px;
            background-size: 32px 544px;
            -webkit-animation: loading .8s linear infinite;
            animation: loading .8s linear infinite
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {
            .yidun.yidun--light.yidun--loading .yidun_loadicon {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 -332px;
                background-size: 32px 544px
            }
        }

        .yidun.yidun--light.yidun--loading .yidun_refresh {
            cursor: not-allowed
        }

        .yidun.yidun--light.yidun--loading .yidun_control {
            border-color: #e4e7eb;
            background-color: #f7f9fa
        }

        .yidun.yidun--light.yidun--loadfail .yidun_loadicon {
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 -299px;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {
            .yidun.yidun--light.yidun--loadfail .yidun_loadicon {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 -299px;
                background-size: 32px 544px
            }
        }

        .yidun.yidun--light.yidun--icon_point.yidun--button .yidun_control,
        .yidun.yidun--light.yidun--inference.yidun--button .yidun_control,
        .yidun.yidun--light.yidun--point.yidun--button .yidun_control {
            cursor: pointer;
            background: #f7f9fa;
            background: linear-gradient(180deg, #fff 0, #ebedf0 87%)
        }

        .yidun.yidun--light.yidun--icon_point.yidun--button .yidun_tips .yidun_tips__icon,
        .yidun.yidun--light.yidun--inference.yidun--button .yidun_tips .yidun_tips__icon,
        .yidun.yidun--light.yidun--point.yidun--button .yidun_tips .yidun_tips__icon {
            margin-right: 8px;
            width: 20px;
            height: 20px;
            vertical-align: middle;
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 -152px;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {

            .yidun.yidun--light.yidun--icon_point.yidun--button .yidun_tips .yidun_tips__icon,
            .yidun.yidun--light.yidun--inference.yidun--button .yidun_tips .yidun_tips__icon,
            .yidun.yidun--light.yidun--point.yidun--button .yidun_tips .yidun_tips__icon {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 -152px;
                background-size: 32px 544px
            }
        }

        .yidun.yidun--light.yidun--icon_point.yidun--verifying .yidun_refresh,
        .yidun.yidun--light.yidun--inference.yidun--verifying .yidun_refresh,
        .yidun.yidun--light.yidun--jigsaw.yidun--verifying .yidun_refresh,
        .yidun.yidun--light.yidun--point.yidun--verifying .yidun_refresh {
            cursor: not-allowed
        }

        .yidun.yidun--light.yidun--inference.yidun--verifying .yidun_inference--target .yidun_inference__img {
            -webkit-animation: bright .6s ease-in .3s;
            animation: bright .6s ease-in .3s
        }

        .yidun.yidun--light.yidun--success .yidun_tips {
            color: #52ccba
        }

        .yidun.yidun--light.yidun--success .yidun_refresh {
            display: none
        }

        .yidun.yidun--light.yidun--success.yidun--jigsaw .yidun_control .yidun_slide_indicator {
            border-color: #52ccba;
            background-color: #d2f4ef
        }

        .yidun.yidun--light.yidun--success.yidun--jigsaw .yidun_control .yidun_slider {
            background-color: #52ccba
        }

        .yidun.yidun--light.yidun--success.yidun--jigsaw .yidun_control .yidun_slider .yidun_slider__icon {
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 -26px;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {
            .yidun.yidun--light.yidun--success.yidun--jigsaw .yidun_control .yidun_slider .yidun_slider__icon {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 -26px;
                background-size: 32px 544px
            }
        }

        .yidun.yidun--light.yidun--success.yidun--icon_point .yidun_control,
        .yidun.yidun--light.yidun--success.yidun--inference .yidun_control,
        .yidun.yidun--light.yidun--success.yidun--point .yidun_control,
        .yidun.yidun--light.yidun--success.yidun--sms .yidun_control {
            border-color: #52ccba;
            background-color: #d2f4ef
        }

        .yidun.yidun--light.yidun--success.yidun--icon_point .yidun_tips .yidun_tips__icon,
        .yidun.yidun--light.yidun--success.yidun--inference .yidun_tips .yidun_tips__icon,
        .yidun.yidun--light.yidun--success.yidun--point .yidun_tips .yidun_tips__icon,
        .yidun.yidun--light.yidun--success.yidun--sms .yidun_tips .yidun_tips__icon {
            margin-right: 5px;
            width: 17px;
            height: 12px;
            vertical-align: middle;
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 -67px;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {

            .yidun.yidun--light.yidun--success.yidun--icon_point .yidun_tips .yidun_tips__icon,
            .yidun.yidun--light.yidun--success.yidun--inference .yidun_tips .yidun_tips__icon,
            .yidun.yidun--light.yidun--success.yidun--point .yidun_tips .yidun_tips__icon,
            .yidun.yidun--light.yidun--success.yidun--sms .yidun_tips .yidun_tips__icon {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 -67px;
                background-size: 32px 544px
            }
        }

        .yidun.yidun--light.yidun--error .yidun_tips {
            color: #f57a7a
        }

        .yidun.yidun--light.yidun--error.yidun--jigsaw.yidun--maxerror .yidun_slide_indicator,
        .yidun.yidun--light.yidun--error.yidun--jigsaw.yidun--maxerror .yidun_slider {
            display: none
        }

        .yidun.yidun--light.yidun--error.yidun--jigsaw .yidun_control .yidun_slide_indicator {
            border-color: #f57a7a;
            background-color: #fce1e1
        }

        .yidun.yidun--light.yidun--error.yidun--jigsaw .yidun_control .yidun_slider {
            background-color: #f57a7a
        }

        .yidun.yidun--light.yidun--error.yidun--jigsaw .yidun_control .yidun_slider .yidun_slider__icon {
            width: 12px;
            height: 12px;
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 -82px;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {
            .yidun.yidun--light.yidun--error.yidun--jigsaw .yidun_control .yidun_slider .yidun_slider__icon {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 -82px;
                background-size: 32px 544px
            }
        }

        .yidun.yidun--light.yidun--error.yidun--jigsaw .yidun_control .yidun_slider img.yidun_slider__icon {
            width: 100%;
            height: 100%
        }

        .yidun.yidun--light.yidun--error.yidun--icon_point .yidun_control,
        .yidun.yidun--light.yidun--error.yidun--inference .yidun_control,
        .yidun.yidun--light.yidun--error.yidun--maxerror .yidun_control,
        .yidun.yidun--light.yidun--error.yidun--point .yidun_control,
        .yidun.yidun--light.yidun--error.yidun--sms .yidun_control {
            border-color: #f57a7a;
            background-color: #fce1e1
        }

        .yidun.yidun--light.yidun--error.yidun--icon_point .yidun_tips .yidun_tips__icon,
        .yidun.yidun--light.yidun--error.yidun--inference .yidun_tips .yidun_tips__icon,
        .yidun.yidun--light.yidun--error.yidun--maxerror .yidun_tips .yidun_tips__icon,
        .yidun.yidun--light.yidun--error.yidun--point .yidun_tips .yidun_tips__icon,
        .yidun.yidun--light.yidun--error.yidun--sms .yidun_tips .yidun_tips__icon {
            margin-right: 5px;
            width: 12px;
            height: 12px;
            vertical-align: middle;
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 -97px;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {

            .yidun.yidun--light.yidun--error.yidun--icon_point .yidun_tips .yidun_tips__icon,
            .yidun.yidun--light.yidun--error.yidun--inference .yidun_tips .yidun_tips__icon,
            .yidun.yidun--light.yidun--error.yidun--maxerror .yidun_tips .yidun_tips__icon,
            .yidun.yidun--light.yidun--error.yidun--point .yidun_tips .yidun_tips__icon,
            .yidun.yidun--light.yidun--error.yidun--sms .yidun_tips .yidun_tips__icon {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 -97px;
                background-size: 32px 544px
            }
        }

        .yidun.yidun--light.yidun--error.yidun--maxerror .yidun_tips:hover {
            cursor: pointer
        }

        .yidun.yidun--light.yidun--inference.yidun--error .yidun_inference,
        .yidun.yidun--light.yidun--inference.yidun--maxerror .yidun_inference,
        .yidun.yidun--light.yidun--inference.yidun--success .yidun_inference,
        .yidun.yidun--light.yidun--inference.yidun--verifying .yidun_inference,
        .yidun.yidun--light.yidun--inference .yidun_bgimg--dragging .yidun_inference {
            cursor: default
        }

        .yidun.yidun--light.yidun--inference.yidun--error .yidun_inference:hover .yidun_inference__border,
        .yidun.yidun--light.yidun--inference.yidun--maxerror .yidun_inference:hover .yidun_inference__border,
        .yidun.yidun--light.yidun--inference.yidun--success .yidun_inference:hover .yidun_inference__border,
        .yidun.yidun--light.yidun--inference.yidun--verifying .yidun_inference:hover .yidun_inference__border,
        .yidun.yidun--light.yidun--inference .yidun_bgimg--dragging .yidun_inference:hover .yidun_inference__border {
            content: "";
            border-color: #fff;
            border-width: 1px
        }

        .yidun.yidun--light.yidun--inference.yidun--error .yidun_inference:hover .yidun_inference__img,
        .yidun.yidun--light.yidun--inference.yidun--maxerror .yidun_inference:hover .yidun_inference__img,
        .yidun.yidun--light.yidun--inference.yidun--success .yidun_inference:hover .yidun_inference__img,
        .yidun.yidun--light.yidun--inference.yidun--verifying .yidun_inference:hover .yidun_inference__img,
        .yidun.yidun--light.yidun--inference .yidun_bgimg--dragging .yidun_inference:hover .yidun_inference__img {
            opacity: 1;
            filter: alpha(opacity=100)
        }

        .yidun.yidun--light.yidun--inference.yidun--error .yidun_inference.yidun_inference--drag .yidun_inference__border,
        .yidun.yidun--light.yidun--inference.yidun--error .yidun_inference.yidun_inference--swap .yidun_inference__border,
        .yidun.yidun--light.yidun--inference.yidun--maxerror .yidun_inference.yidun_inference--drag .yidun_inference__border,
        .yidun.yidun--light.yidun--inference.yidun--maxerror .yidun_inference.yidun_inference--swap .yidun_inference__border,
        .yidun.yidun--light.yidun--inference.yidun--success .yidun_inference.yidun_inference--drag .yidun_inference__border,
        .yidun.yidun--light.yidun--inference.yidun--success .yidun_inference.yidun_inference--swap .yidun_inference__border,
        .yidun.yidun--light.yidun--inference.yidun--verifying .yidun_inference.yidun_inference--drag .yidun_inference__border,
        .yidun.yidun--light.yidun--inference.yidun--verifying .yidun_inference.yidun_inference--swap .yidun_inference__border,
        .yidun.yidun--light.yidun--inference .yidun_bgimg--dragging .yidun_inference.yidun_inference--drag .yidun_inference__border,
        .yidun.yidun--light.yidun--inference .yidun_bgimg--dragging .yidun_inference.yidun_inference--swap .yidun_inference__border {
            border-color: #2c6eff;
            border-width: 2px
        }

        .yidun.yidun--light.yidun--inference .yidun_bgimg--dragging .yidun_inference.yidun_inference--target {
            background-color: #000
        }

        .yidun.yidun--light.yidun--inference .yidun_bgimg--dragging .yidun_inference.yidun_inference--target .yidun_inference__border {
            border: 2px solid #2c6eff
        }

        .yidun.yidun--light.yidun--inference .yidun_bgimg--dragging .yidun_inference.yidun_inference--target .yidun_inference__img {
            opacity: .4;
            filter: alpha(opacity=40)
        }

        .yidun_popup.yidun_popup--light {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: 9999;
            text-align: center
        }

        .yidun_popup.yidun_popup--light .yidun_popup__mask {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: #000;
            filter: alpha(opacity=30);
            opacity: .3;
            transition: opacity .3s linear;
            will-change: opacity
        }

        .yidun_popup.yidun_popup--light .yidun_modal {
            position: relative;
            box-sizing: border-box;
            border-radius: 2px;
            border: 1px solid #e4e7eb;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, .3)
        }

        .yidun_popup.yidun_popup--light .yidun_modal__subwrap,
        .yidun_popup.yidun_popup--light .yidun_modal__wrap {
            height: 100%;
            width: 100%
        }

        .yidun_popup.yidun_popup--light .yidun_modal__title {
            padding: 0 15px;
            height: 50px;
            line-height: 50px;
            text-align: left;
            font-size: 16px;
            color: #45494c;
            border-bottom: 1px solid #e4e7eb
        }

        .yidun_popup.yidun_popup--light .yidun_modal__close {
            position: absolute;
            top: 13px;
            right: 9px;
            width: 24px;
            height: 24px;
            text-align: center;
            cursor: pointer
        }

        .yidun_popup.yidun_popup--light .yidun_modal__close .yidun_icon-close {
            margin-top: 6px;
            width: 11px;
            height: 11px;
            vertical-align: top;
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 -39px;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {
            .yidun_popup.yidun_popup--light .yidun_modal__close .yidun_icon-close {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 -39px;
                background-size: 32px 544px
            }
        }

        .yidun_popup.yidun_popup--light .yidun_modal__close:hover .yidun_icon-close {
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 -53px;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {
            .yidun_popup.yidun_popup--light .yidun_modal__close:hover .yidun_icon-close {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 -53px;
                background-size: 32px 544px
            }
        }

        .yidun_popup.yidun_popup--light .yidun_modal__body {
            padding: 15px
        }

        .yidun_popup.yidun_popup--light .yidun_modal__body .yidun {
            *margin: 0
        }

        .yidun_popup.yidun_popup--auto .yidun_modal {
            top: auto;
            *top: -50%
        }

        .yidun_popup.yidun_popup--auto .yidun_modal__wrap {
            display: table;
            *position: relative
        }

        .yidun_popup.yidun_popup--auto .yidun_modal__subwrap {
            display: table-cell;
            vertical-align: middle;
            *height: auto;
            *position: absolute;
            *top: 50%;
            *left: 0
        }

        @supports ((display:-webkit-box) or (display:flex)) {
            .yidun_popup.yidun_popup--auto .yidun_modal {
                top: auto;
                margin: auto
            }

            .yidun_popup.yidun_popup--auto .yidun_modal__wrap {
                display: block
            }

            .yidun_popup.yidun_popup--auto .yidun_modal__subwrap {
                display: -webkit-box;
                display: -ms-flexbox;
                display: flex;
                -webkit-box-orient: vertical;
                -webkit-box-direction: normal;
                -ms-flex-direction: column;
                flex-direction: column;
                -webkit-box-align: center;
                -ms-flex-align: center;
                align-items: center;
                -ms-flex-line-pack: center;
                align-content: center
            }
        }

        .yidun_popup.yidun_popup--append {
            position: absolute
        }

        .yidun_intellisense--light {
            position: relative
        }

        .yidun_intellisense--light * {
            box-sizing: border-box
        }

        .yidun_intellisense--light.yidun_intellisense--checking .yidun_intelli-control,
        .yidun_intellisense--light.yidun_intellisense--loadfail .yidun_intelli-control,
        .yidun_intellisense--light.yidun_intellisense--loading .yidun_intelli-control,
        .yidun_intellisense--light.yidun_intellisense--success .yidun_intelli-control {
            cursor: default
        }

        .yidun_intellisense--light .yidun_intelli-control {
            position: relative;
            height: 40px;
            font-size: 14px;
            cursor: pointer;
            border-radius: 2px;
            border: 1px solid #e4e7eb;
            background-color: #f7f9fa;
            overflow: hidden
        }

        .yidun_intellisense--light .yidun_intelli-tips {
            text-align: center;
            color: #45494c
        }

        .yidun_intellisense--light .yidun_intelli-tips:hover .yidun_intelli-icon {
            background-color: #1991fa;
            box-shadow: 0 2px 6px 1px rgba(25, 145, 250, .5)
        }

        .yidun_intellisense--light .yidun_intelli-tips:hover .yidun_intelli-icon .yidun_logo {
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 -112px;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {
            .yidun_intellisense--light .yidun_intelli-tips:hover .yidun_intelli-icon .yidun_logo {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 -112px;
                background-size: 32px 544px
            }
        }

        .yidun_intellisense--light .yidun_intelli-tips:hover .yidun_intelli-text {
            color: #1991fa
        }

        .yidun_intellisense--light .yidun_intelli-icon {
            position: relative;
            margin-right: 5px;
            width: 28px;
            height: 28px;
            vertical-align: middle;
            border-radius: 50%;
            background-color: #fff;
            box-shadow: 0 2px 8px 1px rgba(188, 196, 204, .5);
            transition: all .2s linear
        }

        .yidun_intellisense--light .yidun_intelli-icon .yidun_logo {
            position: absolute;
            top: 50%;
            left: 50%;
            margin-top: -8px;
            margin-left: -8px;
            width: 15px;
            height: 17px;
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 -132px;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {
            .yidun_intellisense--light .yidun_intelli-icon .yidun_logo {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 -132px;
                background-size: 32px 544px
            }
        }

        .yidun_intellisense--light .yidun_intelli-icon img.yidun_logo {
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            margin: 0;
            border-radius: 50%;
            background-image: none !important
        }

        .yidun_intellisense--light .yidun_intelli-text {
            line-height: 38px;
            vertical-align: middle;
            transition: all .2s linear
        }

        .yidun_intellisense--light .yidun_classic-tips {
            display: none;
            text-align: center
        }

        .yidun_intellisense--light .yidun_classic-tips .yidun_tips__icon {
            margin-right: 5px;
            width: 12px;
            height: 12px;
            vertical-align: middle
        }

        .yidun_intellisense--light .yidun_classic-tips .yidun_tips__text {
            line-height: 38px;
            vertical-align: middle
        }

        .yidun_intellisense--light .yidun_classic-container {
            position: absolute;
            bottom: 0;
            left: 0;
            width: 100%;
            z-index: 1000
        }

        .yidun_intellisense--light .yidun_classic-wrapper {
            display: none;
            padding: 9px;
            border: 1px solid #e4e7eb;
            border-radius: 2px;
            background-color: #fff
        }

        .yidun_intellisense--light.yidun_intellisense--checking .yidun_intelli-icon,
        .yidun_intellisense--light.yidun_intellisense--loading .yidun_intelli-icon {
            background-color: #1991fa
        }

        .yidun_intellisense--light.yidun_intellisense--checking .yidun_intelli-icon .yidun_logo,
        .yidun_intellisense--light.yidun_intellisense--loading .yidun_intelli-icon .yidun_logo {
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 -112px;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {

            .yidun_intellisense--light.yidun_intellisense--checking .yidun_intelli-icon .yidun_logo,
            .yidun_intellisense--light.yidun_intellisense--loading .yidun_intelli-icon .yidun_logo {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 -112px;
                background-size: 32px 544px
            }
        }

        .yidun_intellisense--light.yidun_intellisense--checking .yidun_intelli-text,
        .yidun_intellisense--light.yidun_intellisense--loading .yidun_intelli-text {
            color: #1991fa
        }

        .yidun_intellisense--light.yidun_intellisense--checking .yidun_ball-scale-multiple {
            position: absolute;
            top: 50%;
            left: 50%;
            -webkit-transform: translateY(-80px);
            transform: translateY(-80px)
        }

        .yidun_intellisense--light.yidun_intellisense--checking .yidun_ball-scale-multiple>div:nth-child(2) {
            -webkit-animation-delay: -1.2s;
            animation-delay: -1.2s
        }

        .yidun_intellisense--light.yidun_intellisense--checking .yidun_ball-scale-multiple>div:nth-child(3) {
            -webkit-animation-delay: -.6s;
            animation-delay: -.6s
        }

        .yidun_intellisense--light.yidun_intellisense--checking .yidun_ball-scale-multiple>div {
            position: absolute;
            box-shadow: inset 0 0 40px rgba(25, 145, 250, .5);
            border-radius: 100%;
            -webkit-animation-fill-mode: both;
            animation-fill-mode: both;
            left: -80px;
            top: 0;
            opacity: 0;
            width: 160px;
            height: 160px;
            -webkit-animation: ball-scale-multiple 1.8s 0s linear infinite;
            animation: ball-scale-multiple 1.8s 0s linear infinite
        }

        .yidun_intellisense--light.yidun_intellisense--loading .yidun_logo {
            display: none
        }

        .yidun_intellisense--light.yidun_intellisense--loading .yidun_intelli-loading {
            position: absolute;
            top: 50%;
            left: 50%;
            margin-top: -8px;
            margin-left: -8px;
            width: 16px;
            height: 16px;
            border-radius: 50%;
            border-width: 2px;
            border-style: solid;
            border-color: #fff #fff transparent;
            -webkit-animation: loading .75s linear infinite;
            animation: loading .75s linear infinite;
            background-position: 0 0
        }

        .yidun_intellisense--light.yidun_intellisense--error .yidun_intelli-tips,
        .yidun_intellisense--light.yidun_intellisense--loadfail .yidun_intelli-tips,
        .yidun_intellisense--light.yidun_intellisense--success .yidun_intelli-tips {
            display: none
        }

        .yidun_intellisense--light.yidun_intellisense--error .yidun_classic-tips,
        .yidun_intellisense--light.yidun_intellisense--loadfail .yidun_classic-tips,
        .yidun_intellisense--light.yidun_intellisense--success .yidun_classic-tips {
            display: block
        }

        .yidun_intellisense--light.yidun_intellisense--success .yidun_intelli-control {
            border-color: #52ccba;
            background-color: #d2f4ef
        }

        .yidun_intellisense--light.yidun_intellisense--success .yidun_classic-tips {
            color: #52ccba
        }

        .yidun_intellisense--light.yidun_intellisense--success .yidun_tips__icon {
            width: 17px;
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 -67px;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {
            .yidun_intellisense--light.yidun_intellisense--success .yidun_tips__icon {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 -67px;
                background-size: 32px 544px
            }
        }

        .yidun_intellisense--light.yidun_intellisense--error .yidun_intelli-control,
        .yidun_intellisense--light.yidun_intellisense--loadfail .yidun_intelli-control {
            border-color: #f57a7a;
            background-color: #fce1e1
        }

        .yidun_intellisense--light.yidun_intellisense--error .yidun_classic-tips,
        .yidun_intellisense--light.yidun_intellisense--loadfail .yidun_classic-tips {
            color: #f57a7a
        }

        .yidun_intellisense--light.yidun_intellisense--error .yidun_tips__icon,
        .yidun_intellisense--light.yidun_intellisense--loadfail .yidun_tips__icon {
            background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light.4353d81.png");
            background-position: 0 -97px;
            background-size: 32px 544px
        }

        @media only screen and (-webkit-min-device-pixel-ratio:2),
        only screen and (min-device-pixel-ratio:2) {

            .yidun_intellisense--light.yidun_intellisense--error .yidun_tips__icon,
            .yidun_intellisense--light.yidun_intellisense--loadfail .yidun_tips__icon {
                background-image: url("https://cstaticdun.126.net//2.13.7/images/icon_light@2x.08688ad.png");
                background-position: 0 -97px;
                background-size: 32px 544px
            }
        }

        .yidun_intellisense--light.yidun_intellisense--maxerror .yidun_intelli-control .yidun_tips__text:hover {
            cursor: pointer;
            text-decoration: underline
        }
    </style>
</head>

<body>
<input type="hidden" id="zjh_msg" value="${zjh_msg}">
<div class="registration">
    <article class="login-guide-main">
        <div class="jx-register">
            <div class="jx-register-img">
                <!-- 驾考图片 -->
            </div>
            <div class="register-form jx-register-form">
                <form class="layui-form" action="<%=path+"/student/register"%>" autocomplete="off" id="registerfrom" method="post">
                    <h4>学员注册</h4>
                    <div id="idPhoto" style="display: none;">
	                    <div class="layui-upload">
		                    <button type="button" class="layui-btn" id="test1">上传身份证</button>
		                    <div class="layui-upload-list">
			                    <img class="layui-upload-img" id="demo1" style="width: 360px; height: 200px;" alt="身份证正面照">
			                    <p id="demoText"></p>
		                    </div>
	                    </div>
                    </div>
	                    <br/>
<%--                    <br/>--%>
<%--                    <br/>--%>
                    <div id="idHand" style="display: block;">
                        <div class="layui-form-item" style="margin-left: -25px;">
                            <label class="layui-form-label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</label>
                            <div class="layui-input-inline">
                                <input id="sname" value="${username}" type="text" style="width: 285px" name="username" lay-verify="required|name" placeholder="请输入姓名" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item" style="margin-left: -25px;">
                            <label class="layui-form-label">身份证号码</label>
                            <div class="layui-input-inline">
                                <input id="ssfz" value="${sfz}" type="text" style="width: 285px" name="sfz" lay-verify="required|id" placeholder="请输入身份证号码" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item" style="margin-left: -28px;">
                        <label class="layui-form-label" style="margin-top: 0px;">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</label>
                        <div class="layui-input-block">
                            <input value="${account}" type="text" style="width: 285px" lay-verify="required|account" name="account" placeholder="请输入账号" autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-form-item" style="margin-left: -28px;">
                        <label class="layui-form-label" style="margin-top: 0px;">输&nbsp;入&nbsp;密&nbsp;码</label>
                        <div class="layui-input-block">
                            <input value="${password}" type="password" id="firstPassword" style="width: 285px" lay-verify="required|firstPwd" name="password" placeholder="请输入密码" autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-form-item" style="margin-left: -28px;">
                        <label class="layui-form-label" style="margin-top: 0px;">确&nbsp;认&nbsp;密&nbsp;码</label>
                        <div class="layui-input-block">
                            <input value="${password}" type="password" style="width: 285px" lay-verify="required|secondPwd" placeholder="请输入密码" autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="div-wrap" margin-top: -5px;>
                        <input type="text" name="mobile1" style="display: none">
                        <!--解决浏览器自动填充-->
                        <input value="${phone}" type="text" lay-verify="required|phone" id="phone" name="phone" class="register-phone" maxlength="11" autocomplete="off"
                               placeholder="请输入手机号">
                        <p class="iconfont icon-jinggao">
                        </p>
                        <i></i>
                    </div>
                    <div class="code-block div-wrap">
                        <input type="text" name="code" lay-verify="required" class="register-code" maxlength="6" autocomplete="off"
                               placeholder="请输入验证码">
                        <input id="codeBtn" class="register-code-btn" data-send="true" type="button" value="获取验证码">
                        <p class="iconfont icon-jinggao">
                        </p>
                        <i></i>
                    </div>
                    <div class="captcha-block">
                        <div id="captcha"><input type="hidden" name="NECaptchaValidate" value="" class="yidun_input"></div>
                        <p class="iconfont icon-jinggao"></p>
                    </div>
                    <div>
                        <button type="submit" class="layui-btn register-submit" lay-submit="" lay-filter="demo1">立即提交</button>
                    </div>
                    <div style="float: left;">
                        <a href="JavaScript:;" id="showPhoto" style="display: block;">切换为身份证上传</a>
                        <a href="JavaScript:;" id="showHand" style="display: none;">切换为手动输入</a>
                    </div>

                    <a style="float: right;margin-bottom: 15px;" href="<%=path+"/student"%>">已有账号?直接登录</a>
                </form>
            </div>
        </div>
    </article>
    <footer>
        <p>版权所有：传一科技</p>
    </footer>
</div>

<script src="https://www.layuicdn.com/layui/layui.js"></script>
<script type="text/javascript" src=<%=path+"/js/pages/index/jquery.min.js"%>></script>
<script type="text/javascript" src=<%=path+"/js/pages/index/jquery.cookie.js"%>></script>
<script type="text/javascript" src=<%=path+"/js/pages/index/60sCountdown.js"%>></script>
<script>
    layui.use(['form', 'laydate','layer'],function () {
        let $ = layui.$;
        let form = layui.form;
        let layer = layui.layer;
        let laydate = layui.laydate;

        let path = window.document.location.href.substring(0, (window.document.location.href).indexOf(window.document.location.pathname));



        let msg = $("#zjh_msg").val();
        if(msg.length>0){
            layer.msg(msg);
        }

        form.render();

        let inputByHand = true;

        $("#showPhoto").on("click",function (event) {
            $("#idHand").attr("style","display:none;");
            $("#idPhoto").attr("style","display:block;");
            $("#showPhoto").attr("style","display:none;");
            $("#showHand").attr("style","display:block;");
            inputByHand = false;
        });

        $("#showHand").on("click",function (event) {
            $("#idHand").attr("style","display:block;");
            $("#idPhoto").attr("style","display:none;");
            $("#showPhoto").attr("style","display:block;");
            $("#showHand").attr("style","display:none;");
            inputByHand = true;
        });

        form.verify({
            name:function (value) {
                if(inputByHand){
                    if(value.length==0){
                        return '姓名不得为空';
                    }
                }else{
                    return "请上传身份证照片";
                }

            },
            id:function (value) {
                if(inputByHand) {
                    if (!/^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/.test(value)) {
                        return "身份证格式不正确，请重新输入";
                    }
                }else{
                    return "请上传身份证照片";
                }
            },
            account:function(value){
                if(value.length<6||value.length>18){
                    return "账号限制6-18位";
                }
                if (!/^(?![^a-zA-Z]+$)(?!\D+$)/.test(value)){
                    return "账号必须包含字母和数字";
                }
            },
            firstPwd:function (value) {
                if(value.length<6||value.length>18){
                    return "密码限制6-18位";
                }
                if (!/^(?![^a-zA-Z]+$)(?!\D+$)/.test(value)){
                    return "密码必须包含字母和数字";
                }
            },
            secondPwd:function (value) {
                let firstPwd = $("#firstPassword").val();
                if(firstPwd.length==""){
                    return "请设置密码";
                }
                if(firstPwd!=value){
                    return "两次密码不相同";
                }
            }
        });

        $(document).on('click',"#codeBtn",function(){
            if(checkPhone()){
                $.cookie("total",5);
                timekeeping();
                $.get(path + "/api/sms/register/", { phone: $("#phone").val()});
                alert("模拟验证码为：000000     6个零")
            }
        })

        function checkPhone() {
            let PhoneStr=$("#phone").val().trim();

            if(!/^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\d{8}$/.test(PhoneStr)){
                layer.msg("电话号码格式不正确,请重试");
                return false;
            }else{
                return true;
            }
        }
            layui.use(['jquery', 'layer', 'upload'], function () {
                var $ = layui.jquery, upload = layui.upload;
                //普通图片上传
	            var uploadInst = upload.render({
		            elem: '#test1'
                    , url: '/pipeControl/sfzdiscern' //改成您自己的上传接口

		            , before: function (obj) {
			            //预读本地文件示例，不支持ie8
			            obj.preview(function (index, file, result) {
				            $('#demo1').attr('src', result); //图片链接（base64）
			            });
		            }
		            , done: function (res) {
			            //如果上传失败
			            if (res.code > 0) {
				            return layer.msg('上传失败');
			            }else {
			                alert("识别身份证成功");
                            console.log(res);
			                $('#sname').val(res.name);
                            $('#ssfz').val(res.num);
                        }
			            //上传成功
		            }
		            , error: function () {
			            //演示失败状态，并实现重传
			            var demoText = $('#demoText');
			            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
			            demoText.find('.demo-reload').on('click', function () {
				            uploadInst.upload();
			            });
		            }
	            });
            });
    });
</script>
</body>

</html>
