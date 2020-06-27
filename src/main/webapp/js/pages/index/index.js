var path = window.document.location.href.substring(0, (window.document.location.href).indexOf(window.document.location.pathname));
var tourl = path+"/api/ggfwSchInfo/industrySupplyOrder";
var tobaseDataurl =  path+"/api/ggfwSchInfo/baseDataOrder";
var tovehInfoOrder = path+"/api/ggfwSchInfo/vehInfoOrder";
var toyearAddStuOrder = path+"/api/ggfwSchInfo/yearAddStuOrder";
var todayAddStuOrder= path+"/api/ggfwSchInfo/dayAddStuOrder";
$(function() {
	showBg();
    initIndustryTab();
    initBaseDataTab();
    initDayAddStuOrder();
    initVehInfoOrder();
    initYearAddStuOrder();
    initNoticeList("01","noticeList");
    initNoticeList("02","industryInfoList");
    initNoticeList("03","policyInfoList");
    initNoticeList("04","policyusInfoList");
    initNoticeList("05","exposureInfoList");
    initTopImg();
    $("#baseDataBySchCount").click(function () {
	$("#baseDataBySchCount").css({"color":"#40bfd5","border":"#40bfd5","border-style":"solid","border-width":"1px"});
	$("#baseDataByVehCount").css({"color":"#000000","border":"#000000","border-style":"solid","border-width":"1px"});
	$("#baseDataByCoaCount").css({"color":"#000000","border":"#000000","border-style":"solid","border-width":"1px"});
	$("#baseDataBySchCount").html("驾培机构<img src='static/img/sort1.png'/>");
	$("#baseDataByVehCount").html("教练车<img src='static/img/sort2.png'/>");
	$("#baseDataByCoaCount").html("教练员<img src='static/img/sort2.png'/>");
	initBaseDataTab('schCount');
	initBaseMap(1);
    });
    $("#baseDataByVehCount").click(function () {  
	$("#baseDataBySchCount").css({"color":"#000000","border":"#000000","border-style":"solid","border-width":"1px"});
	$("#baseDataByVehCount").css({"color":"#40bfd5","border":"#40bfd5","border-style":"solid","border-width":"1px"});
	$("#baseDataByCoaCount").css({"color":"#000000","border":"#000000","border-style":"solid","border-width":"1px"});
	$("#baseDataBySchCount").html("驾培机构<img src='static/img/sort2.png'/>");
	$("#baseDataByVehCount").html("教练车<img src='static/img/sort1.png'/>");
	$("#baseDataByCoaCount").html("教练员<img src='static/img/sort2.png'/>");
	initBaseDataTab('vehCount');
	initBaseMap(2);
    });
    $("#baseDataByCoaCount").click(function () {
	$("#baseDataBySchCount").css({"color":"#000000","border":"#000000","border-style":"solid","border-width":"1px"});
	$("#baseDataByVehCount").css({"color":"#000000","border":"#000000","border-style":"solid","border-width":"1px"});
	$("#baseDataByCoaCount").css({"color":"#40bfd5","border":"#40bfd5","border-style":"solid","border-width":"1px"});
	$("#baseDataBySchCount").html("驾培机构<img src='static/img/sort2.png'/>");
	$("#baseDataByVehCount").html("教练车<img src='static/img/sort2.png'/>");
	$("#baseDataByCoaCount").html("教练员<img src='static/img/sort1.png'/>");
	initBaseDataTab('coaCount');
	initBaseMap(3);
    });
 });


var  topImgurl=path+"/api/ggfwNoticeInfo/getTopFileImg";

function  initTopImg(){
    $.ajax({ url: topImgurl,
	type:"post",
	dataType:"json",
	async:false,
	success: function(result){
	    var data = result.data;
	    var  html="";
	    for(var i in data){
		var d = data[i];
	        html += "<div>"
	              +"<img src='"+d.img+"' onclick='publicNotice(\"1\",\""+d.noticeid+"\");' style='width: 380px;height: 280px;cursor:pointer' />"
	              +"<a class='carousel-title' title='"+d.info+"'>"+cutstr(isEmpt(d.info),50)+"</a>"
	              +"</div>"
		
	    }
	    $("#homeTopFile").html(html);
	}});
}

var noticeInfourl=path+"/api/ggfwNoticeInfo/listGgfwNoticeInfo";
//公开公示查询
function initNoticeList(noticetype,listId) {
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
			html += "<tr>" +
				"<td title='"+title+"'>" +
				"<a onclick='publicNotice("+noticetype+",\""+d.noticeid+"\");' style='cursor: pointer;color: #8ea8d8;'>" +
                title.substring(0,16)+"……" + "</a></td>" +
				"<td>" + d.articlefrom + "</td>" +
				"<td>" + d.publishtime + "</td>"
				+"</tr>" ;
		}
		if(datas.length > 0){
			html+="<tr>"+
				"<td colspan='4'><a  onclick='publicNotice("+noticetype+",\"\");' style='cursor: pointer;'>更多&gt;&gt;</a></td> " +
				"</tr>";
		}
		return html;
	}
 	var paramData = {};
	paramData.noticetype = noticetype;
 	queryPage('', listId, "", "4","1", noticeInfourl, addHtml, paramData);
	// $(document).PageBar(listId,paramData,noticeInfourl,addHtml);
	// queryPage();
}
//显示文章详情
function publicNotice(type,page,id) {
	window.location.href = path + "/publicity/" + type + "/" + page + "/" + id;
}
//查询查询页面跳转
function inquireJump(selectType) {
	window.location.href=path + "/inquire/"+selectType;
}
//查询学员页面跳转
function selectRecJump() {
	window.location.href="http://47.98.242.153:8666/student/stutrainlog.jsp";
}

var distictName;

//初始化基础数据总览信息
function initBaseDataTab(sort,name,tobaseDataByNameUrl) {
		var addHtml = function(result) {
		var html = "";
		var data = result.data.records;
		for ( var i in data) {
			var d = result.data.records[i];
			var index = i - 0 + 1;
			html += "<tr>";
			html += "<td title='"+d.area+"'><span>" + index + "</span>" + cutstr(isEmpt(d.area),12) + "</td>"
				+ "<td>" + d.schCount + "</td>" + "<td>" + d.vehCount
				+ "</td>" + "<td>" + d.coaCount + "</td>"
			html += "</tr>"
		}
		return html;
    };
    var paramData = {};
    if(sort!=null){
		paramData.orderByField = sort;
    }
    if(name!= null && typeof(name)!= "undefined"){
		distictName = name;
    }
    if(distictName!=null && typeof(distictName)!= "undefined"){
		paramData.name = distictName;
    }
    paramData.districtType = 2;
    if(tobaseDataByNameUrl!= null && typeof(tobaseDataByNameUrl)!= "undefined"){
		tobaseDataurl = tobaseDataByNameUrl;
    }
    queryPage('demo01', 'baseDataList', "", "10", "1", tobaseDataurl,addHtml, paramData);
}

//初始化行业信息排名
function initVehInfoOrder(){
    var addHtml = function(result) {
		var html = "";
		var nowDate = getNowFormatDate().substring(0,10);
		$("#vehUpdateTime").html("<img src='static/img/date1.png'/>"+nowDate);
		var data = result.data.records;
		for ( var i in data) {
			var d = result.data.records[i];
			var index = i - 0 + 1;
			if(index == 1){
			index = "<img src='static/img/first.png'/>";
			}else if(index == 2){
			index = "<img src='static/img/second.png'/>";
			}else if(index == 3){
			index = "<img src='static/img/second.png'/>";
			}
			var vehCount = '-';
			if(d.vehCount){
			vehCount = d.vehCount;
			}

			html += "<tr>";
			html += "<td>"+index+"</td>"
				+ "<td title = '"+d.shortname+"'>" + cutstr(isEmpt(d.shortname),10) + "</td>" + "<td title='"+d.districtName+"'>" + cutstr(isEmpt(d.districtName),10)
				+ "</td>" + "<td>" + vehCount + "</td>";
			html += "</tr>";
		}
		html+="<tr>"+
		"<td colspan='4'><a href='"+path+"/industryList#type=1'>更多&gt;&gt;</a></td> " +
		"</tr>";
		return html;
    };
    var paramData = {};
    paramData.orderByField = "vehCount";
    queryPage('', 'vehInfoOrder', "", "10", "1", tovehInfoOrder, addHtml, paramData);
}

function initYearAddStuOrder(){
    var addHtml = function(result) {
		var html = "";
		var nowDate = getNowFormatDate().substring(0,10);
		$("#yearAddStuUpdateTime").html("<img src='static/img/date1.png'/>"+nowDate);
		var data = result.data.records;
		for ( var i in data) {
			var d = result.data.records[i];
			var index = i - 0 + 1;
			if(index == 1){
			index = "<img src='static/img/first.png'/>";
			}else if(index == 2){
			index = "<img src='static/img/second.png'/>";
			}else if(index == 3){
			index = "<img src='static/img/second.png'/>";
			}
			var yearAddCount = '-';
			if(d.yearAddCount){
			yearAddCount = d.yearAddCount;
			}

			html += "<tr>";
			html += "<td>"+index+"</td>"
				+ "<td title='"+d.shortname+"'>" + cutstr(isEmpt(d.shortname),10) + "</td>" + "<td title='"+d.districtName+"'>" + cutstr(isEmpt(d.districtName),10)
				+ "</td>" + "<td>" + yearAddCount + "</td>";
			html += "</tr>";
		}
		html+="<tr>"+
		"<td colspan='4'><a href='"+path+"/industryList#type=2'>更多&gt;&gt;</a></td> " +
		"</tr>";
		return html;
    };
    var paramData = {};
    paramData.orderByField = "yearAddCount";
    queryPage('', 'yearAddStuOrder', "", "10", "1", toyearAddStuOrder, addHtml, paramData);
}

function initDayAddStuOrder(){
    var addHtml = function(result) {
		var html = "";
		var nowDate = getNowFormatDate().substring(0,10);
	    $("#dayAddStuUpdateTime").html("<img src='static/img/date1.png'/>"+nowDate);
		var data = result.data.records;
		for ( var i in data) {
			var d = result.data.records[i];
			var index = i - 0 + 1;
			if(index == 1){
			index = "<img src='static/img/first.png'/>";
			}else if(index == 2){
			index = "<img src='static/img/second.png'/>";
			}else if(index == 3){
			index = "<img src='static/img/second.png'/>";
			}
			var dayAddCount = '-';
			if(d.dayAddCount){
			dayAddCount = d.dayAddCount;
			}
			html += "<tr>";
			html += "<td>"+index+"</td>"
				+ "<td title = '"+d.shortname+"'>" + cutstr(isEmpt(d.shortname),10) + "</td>" + "<td title='"+d.districtName+"'>" +cutstr(isEmpt(d.districtName),10)
				+ "</td>" + "<td>" + dayAddCount + "</td>";
			html += "</tr>";
		}
		html+="<tr>"+
		"<td colspan='4'><a href='"+path+"/industryList#type=3'>更多&gt;&gt;</a></td> " +
		"</tr>";
		return html;
    };
    var paramData = {};
    paramData.orderByField = "dayAddCount";
    paramData.isAsc = false;
    queryPage('', 'dayAddStuOrder', "", "10", "1", todayAddStuOrder, addHtml, paramData);
}

function showBg() {
	$("#menu-title").children(".menu-title-bg").removeClass('menu-title-bg');
	$("#menu-title-one").addClass('menu-title-bg');
}