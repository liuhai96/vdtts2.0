var path = window.document.location.href.substring(0, (window.document.location.href).indexOf(window.document.location.pathname));
var customType;
var customPage;
var customNoticeId;
layui.use(['laytpl','laypage'], function() {
    var layer = layui.layer
        , laytpl = layui.laytpl
        , $ = layui.jquery
        , laypage = layui.laypage;

    customType = $("#customType").val();
    customPage = $("#customPage").val();
    customNoticeId = $("#customNoticeId").val();
    let name = $("#selectTitle").val();

    //通过分页和类型来获取数据
    let searchList = function (type, curr, name) {
        $.get(path + '/api/notice/getList', {
            page: curr || 1
            , type: type
            , name: name
        }, function (res) {

            let html = laytpl(LAY_tpl.value).render({
                data: res.list
            });

            $('#noticeInfoList').html(html);

            laypage.render({
                elem: 'demo0'
                , count: res.count
                , limit: 5
                , groups: 3
                , curr: curr || 1
                , layout: ['prev', 'page', 'next', 'skip']
                , jump: function (e, first) {
                    if (!first) { //一定要加此判断,否则初始时会无限刷新
                        searchList(type, e.curr, name);
                    }
                    customType = type;
                    customPage = e.curr;
                }
            });
        });
    }

    if (customNoticeId == -1) {
        searchList(customType, customPage, "");
    }

    //点击返回上一级时，跳转界面
    $("button[class*='returnPrevHtml']").on("click", function (event) {
        window.location.href = path + "/publicity/" + customType + "/" + customPage + "/-1";
    });

    //点击通知公告时，通过分页插件获取数据
    $("#selectOne").on("click", function (event) {
        name = $("#selectTitle").val("");
        searchList("notice", 1, "");
    });

    //点击法律法规时，通过分页插件获取数据
    $("#selectThree").on("click", function (event) {
        name = $("#selectTitle").val("");
        searchList("law", 1, "");
    });

    $("#selectNameBtn").on("click", function (event) {
        name = $("#selectTitle").val();
        searchList(customType, 1, name);
    });
});




var noticeInfourl=path+"/api/ggfwNoticeInfo/queryPageList";
var noticetotalurl=path+"/api/ggfwNoticeInfo/queryByCount";
var industryInfourl=path+"/api/ggfwIndustryInfo/queryPageList";
var industrytotalurl=path+"/api/ggfwIndustryInfo/queryByCount";
//初始化分页
$(function(){
    if(selectType == null || selectType == "notice"){
        $('#noticeTitle').val('');
        noticeSelect("01","01","noticeInfoList","demo0","","","");
        $('#detail').css('display','none');
        $('#publicSelct').css('display','block');
    }else{
        if(selectType == "notice"){
            $("#selectParent").children(".layui-this").removeClass('layui-this');
            $("#selectOne").addClass('layui-this');
            $("#publicSelct").children(".layui-show").removeClass('layui-show');
            $("#publicSelctOne").addClass('layui-show');
            if(detailId == null || detailId == ""){
                noticeSelect("01","01","noticeInfoList","demo0","","","");
                $('#detail').css('display','none');
                $('#publicSelct').css('display','block');
            }else{
                $('#publicSelct').css('display','none');
                $('#detail').css('display','block');
            }
        }else if(selectType == "2"){
            $("#selectParent").children(".layui-this").removeClass('layui-this');
            $("#selectTwo").addClass('layui-this');
            $("#publicSelct").children(".layui-show").removeClass('layui-show');
            $("#publicSelctTwo").addClass('layui-show');
            if(detailId == null || detailId == ""){
                $('#detail').css('display','none');
                $('#publicSelct').css('display','block');
                $('#industryTitle').val('');
//                industrySelect(industryparamData);
                noticeSelect("01","02","industryInfoList","demo1","","","");
            }else{
                $('#publicSelct').css('display','none');
                $('#detail').css('display','block');
            }
        }else if(selectType == "law"){
            $("#selectParent").children(".layui-this").removeClass('layui-this');
            $("#selectThree").addClass('layui-this');
            $("#publicSelct").children(".layui-show").removeClass('layui-show');
            $("#publicSelctThree").addClass('layui-show');
            if(detailId == null || detailId == ""){
                $('#detail').css('display','none');
                $('#publicSelct').css('display','block');
                $('#policyTitle').val('');
                noticeSelect("01","03","policyInfoList","demo2","","","");
            }else{
                $('#publicSelct').css('display','none');
                $('#detail').css('display','block');
            }
        }else if(selectType == "4"){
            $("#selectParent").children(".layui-this").removeClass('layui-this');
            $("#selectFour").addClass('layui-this');
            $("#publicSelct").children(".layui-show").removeClass('layui-show');
            $("#publicSelctFour").addClass('layui-show');
            if(detailId == null || detailId == ""){
                $('#detail').css('display','none');
                $('#publicSelct').css('display','block');
                $('#policyusTitle').val('');
                noticeSelect("01","04","policyusInfoList","demo3","","","");
            }else{
                $('#publicSelct').css('display','none');
                $('#detail').css('display','block');
            }
        }else if(selectType == "5"){
            $("#selectParent").children(".layui-this").removeClass('layui-this');
            $("#selectFive").addClass('layui-this');
            $("#publicSelct").children(".layui-show").removeClass('layui-show');
            $("#publicSelctFive").addClass('layui-show');
            if(detailId == null || detailId == ""){
                $('#detail').css('display','none');
                $('#publicSelct').css('display','block');
                $('#exposureTitle').val('');
                noticeSelect("01","05","exposureInfoList","demo4","","","");
            }else{
                $('#publicSelct').css('display','none');
                $('#detail').css('display','block');
            }
        }
    }
    showBg();
});
//公开公示分页查询
function noticeSelect(audit,noticetype,listId,pageId,year,title,pageIndex) {
    var noticecount = 0;
    var noticeparamData = {};
    noticeparamData.rowsSize = 5;
    noticeparamData.pageIndex = 1;
    noticeparamData.audit = audit;
    noticeparamData.noticetype = noticetype;
    if(year != null && year != ""){
        noticeparamData.year = year;
    }
    if(title != null && title != ""){
        noticeparamData.title = title;
    }
    if(pageIndex != null && pageIndex != ""){
        noticeparamData.pageIndex = pageIndex;
    }
    var addHtml = function(json){
        var html = "";
        var datas = json.data.records;
        for (var i in datas) {
            var d = datas[i];
            d.publishtime = d.publishtime.substring(0,10);
            var title = d.info;
            if(d.info.length > 16){
                d.info = d.info.substring(0,16)+"……";
            }

            if(noticetype == '05'){
                var isCoaTitle = title.search("教练员");
                if(isCoaTitle == 0) {
                    let reg = /\d+/;
                    var num = title.search(reg);
                    if(num>0){
                        title = title.substr(0,num)+"******"+title.substr(num+14,title.length);
                    }
                }
            }

            var index = parseInt(i) + 1;
            html += "<tr>" +
                "<td><span class='table-style-order'>" + index + "</span></td>" +
                "<td title='"+title+"'><a onclick='publicNotice("+noticetype+",\""+d.noticeid+"\");' style='cursor: pointer;'>" + title.substring(0,16)+"……" + "</a></td>" +
                "<td>" + d.articlefrom + "</td>" +
                "<td>" + d.publishtime + "</td>"+"</tr>";
        }
        return html;
    }
    $(document).PageBar(listId,noticeparamData,noticeInfourl,addHtml);
    queryPage();

    var p = $.post(noticetotalurl, noticeparamData, function (json) {
        if (json.errorcode == 0) {
            noticecount = json.data;
            layui.use(['laypage', 'layer'], function(){
                var laypage = layui.laypage
                    ,layer = layui.layer;
                laypage.render({
                    elem: pageId
                    ,count: noticecount //数据总数
                    ,limit:noticeparamData.rowsSize //每页条数
                    ,jump: function(obj, first){
                        //首次不执行
                        if(!first){
                            noticeparamData.pageIndex = obj.curr;
                            $(document).PageBar(listId,noticeparamData,noticeInfourl,addHtml);
                            queryPage();
                        }
                    }
                });
            });
        }
    }, "json");
    p.success(function () {
    });
    p.error(function () {
        layer.alert('加载数据失败请重试!!');
    })
}
//页面点击驾培机构tab跳转
function noticeInfoJump() {
    $('#detail').css('display','none');
    $('#publicSelct').css('display','block');
    $('#noticeTitle').val('');
    // noticeSelect("01","01","noticeInfoList","demo0","","","");
}
//点击按钮根据条件查询通知公告
function noticeSelectClick() {
    var noticeYear = $('#noticeYear').val();
    var noticeTitle = $('#noticeTitle').val();
    noticeSelect("01","01","noticeInfoList","demo0",noticeYear,noticeTitle,1);
}

//页面点击行业动态tab跳转
function industryInfoJump() {
    $('#detail').css('display','none');
    $('#publicSelct').css('display','block');
    $('#industryTitle').val('');
    noticeSelect("01","02","industryInfoList","demo1","","","");
}
//点击按钮根据条件查询行业动态
function industrySelectClick() {
    var industryYear = $('#industryYear').val();
    var industryTitle = $('#industryTitle').val();
    noticeSelect("01","02","industryInfoList","demo1",industryYear,industryTitle,1);
}
//页面点击政策法规tab跳转
function policyInfoJump() {
    $('#detail').css('display','none');
    $('#publicSelct').css('display','block');
    $('#policyTitle').val('');
    // noticeSelect("01","03","policyInfoList","demo2","","","");
}
//点击按钮根据条件查询政策法规
function policySelectClick() {
    var policyYear = $('#policyYear').val();
    var policyTitle = $('#policyTitle').val();
    noticeSelect("01","03","policyInfoList","demo2",policyYear,policyTitle,1);
}
//页面点击政策法解读tab跳转
function policyusInfoJump() {
    $('#detail').css('display','none');
    $('#publicSelct').css('display','block');
    $('#policyusTitle').val('');
    noticeSelect("01","04","policyusInfoList","demo3","","","");
}
//点击按钮根据条件查询政策解读
function policyusSelectClick() {
    var policyusYear = $('#policyusYear').val();
    var policyusTitle = $('#policyusTitle').val();
    noticeSelect("01","04","policyusInfoList","demo3",policyusYear,policyusTitle,1);
}
//页面点击曝光台tab跳转
function exposureInfoJump() {
    $('#detail').css('display','none');
    $('#publicSelct').css('display','block');
    $('#exposureTitle').val('');
    noticeSelect("01","05","exposureInfoList","demo4","","","");
}
//点击按钮根据条件查询曝光台
function exposureSelectClick() {
    var exposureYear = $('#exposureYear').val();
    var exposureTitle = $('#exposureTitle').val();
    noticeSelect("01","05","exposureInfoList","demo4",exposureYear,exposureTitle,1);
}
//显示文章详情
function publicNotice(id) {
    window.location.href = path + "/publicity/" + customType + "/" + customPage + "/" + id;
}

function showBg() {
    $("#menu-title").children(".menu-title-bg").removeClass('menu-title-bg');
    $("#menu-title-two").addClass('menu-title-bg');
}
