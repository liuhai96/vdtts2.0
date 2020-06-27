var getSchCharstandard = path +"/api/ggfwSchCharstandard/getSchChargestandard";
var getSchTiregion = path +"/api/ggfwSchTiregion/getSchTiregion";
var getSchEvaluation = path + "/api/ggfwSchEvaluation/getschEvaluation";
var getSchEvaluationCount =  path + "/api/ggfwSchEvaluation/countSchEvaluation";
var gotoSchPhotos = path +"/goSchPhotos";

$(function(){
    showBg();
    $("#currentTime").html(getNowFormatDate());
    initSchInfo();
    initImgFile();
//    initSchCharstandard(sch.inscode);
    SchCharstandard(sch.inscode);
    initschTiregion(sch.inscode);
    initEvaList(sch.inscode,1);
    initCountEvaList(sch.inscode);
});

/**
 * 添加驾培机构介绍图片
 */
function initImgFile(){
    for(var i =0;i<fileList.length;i++){
	if(fileList[i].type == 3){
	    $("#schphoto").find(".lun-img").attr("src",fileList[i].fileurl);
	}
	
	if(fileList[i].type == 4){
	    $("#trainingCount").html("共"+fileList[i].pcount+"张");
	    $("#trainingImgFile").attr("src",fileList[i].fileurl);
	}
	if(fileList[i].type == 5){
	    $("#teachToolCount").html("共"+fileList[i].pcount+"张");
	    $("#teachToolImgFile").attr("src",fileList[i].fileurl);
	}
	if(fileList[i].type == 6){
	    $("#trainSceneryCount").html("共"+fileList[i].pcount+"张");
	    $("#trainSceneryImgFile").attr("src",fileList[i].fileurl);
	}
	if(fileList[i].type == 7){
	    $("#schStyleCount").html("共"+fileList[i].pcount+"张");
	    $("#schStyleImgFile").attr("src",fileList[i].fileurl);
	}
    }
}

/**
 * 初始化驾校基本信息
 */


function initSchInfo(){
   if(sch.photo == ''|| sch.photo == null){
       $("#schphoto").html("<img class='lun-img' src='"+path+"/static/img/carousels.jpg'/>")
   }else{
       $("#schphoto").html("<img class='lun-img' src='"+sch.photo+"'/>")
   }
  
   $(".p-cont").html(sch.describe);
   $("#schphone").html(sch.schphone);
   $("#schcomphone").html(sch.schphone);
   $("#pubnumber").html(sch.pubnumber);
   if(sch.pubnumfile == null||sch.pubnumfile == ''){
       $("#pubnumfile").attr("display","none");
   }else{
       $("#pubnumfile").attr("src",sch.pubnumfile);
   }
   $("#shortname").html(sch.name);
   $("#creditcode").html(sch.inscode);
   $("#organizName").html(sch.organizName);
   $("#licetime").html(toTransTimeYMD(sch.licetime));
   $("#busiscope").html(sch.busiscope);
   $("#coacount").html(sch.coacount);
   var level = "-";
   if(sch.level == 1){
       level = "一级"
   }else if(sch.level == 2){
       level = "二级"
   }else if(sch.level == 3){
       level = "三级"
   }
   var www = sch.star*2*15;
   $("#level").html(level);
   $("#vuecount").html(sch.vuecount);
   $("#yearaddstucount").html(sch.yearaddstucount);
   $("#yearendstucount").html(sch.yearendstucount);
   $("#address").html(sch.address);
   $("#star").html(sch.star.toFixed(1));
//   var foreach = Math.ceil(sch.star);
//   var star = '';
//   for (var i=0;i<foreach;i++){
//	star +="<img  class='del-inf-star' src='"+path+"/static/img/star.png'/>"
//   }
   $("#starImg").html("<div class='atar_Show'  style='display:inline;'><p class='atar_Show' tip='"+sch.star+"' style='width: "+www+"px;float: none;'></p></div>");
}


/**
 * 初始化最新收费信息
 */
function SchCharstandard(inscode){
    $.post(getSchCharstandard,{"inscode": inscode}, function (json) {
	var index = 0;
	if (json.errorcode==0) {
	    var data = json.data;
        	var html = '';
        	for ( var i in data) {
        	    var d = data[i];
        	    //一次性收费
        	    if(d.chargemode  == 1){
        		index = index+1;
        		html += "<tr>"
        		          +"<td>"+index+"</td>"
        		          +"<td>"+d.service+"</td>"
        		          +"<td>"+d.traintype+"</td>"
        		          +"<td>"+d.payprice+"</td>"
//        		          +"<td>"+d.remarks+"</td>"
        		          +"</tr>";
        		
        	    }else if(d.chargemode == 2){
        		//计时收费
        		$("#material").html(d.material);
        		$("#archives").html(d.archives);
        		$("#insurance").html(d.insurance);
        		$("#classteach").html(d.classteach);
        		$("#distanceteach").html(d.distanceteach);
        		$("#sub1ordtime").html(d.sub1ordtime);
        		$("#sub1peaktime").html(d.sub1peaktime);
        		$("#sub1holidaytime").html(d.sub1holidaytime);
        		$("#sub2ordtime").html(d.sub2ordtime);
        		$("#sub2peaktime").html(d.sub2peaktime);
        		$("#sub2holidaytime").html(d.sub2holidaytime);
        		$("#sub3ordtime").html(d.sub3ordtime);
        		$("#sub3peaktime").html(d.sub3peaktime);
        		$("#sub3holidaytime").html(d.sub3holidaytime);
        	    }
        	}
        	if(index == 0){
        	    html += "<tr>" 
        		    +"<td colspan = '5'>暂无收费信息</td>"
        	            +"</tr>";
        	}
        	$("#charsMode").html(html);
	}
    });
}

/**
 * 初始化收费信息
 */
function initSchCharstandard(inscode){
    $.post(getSchCharstandard,"inscode="+ inscode+"&&chargemode=2", function (json) {
        if (json.errorcode==0) {
            	var data = json.data;
            	var html = '';
            	if(data.length == 0){
            	    html += "<tr>" 
            		    +"<td colspan = '7'>暂无收费信息</td>"
            	            +"</tr>";
            	}
    		for ( var i in data) {
    		    var d = data[i];
    		    var index = i-0+1;
    		    
    		    var subject = '';
    		    if(d.subject == 1){
    			subject = "第一部分集中教学";
    		    }else if(d.subject == 2){
    			subject = "第一部分网络教学";
    		    }else if(d.subject == 3){
    			subject = "第四部分集中教学";
    		    }else if(d.subject == 4){
    			subject = "第四部分网络教学";
    		    }else if(d.subject == 5){
    			subject = "模拟器教学";
    		    }else if(d.subject == 6){
    			subject = "第二部分普通教学";
    		    }else if(d.subject == 7){
    			subject = "第二部分智能教学";
    		    }else if(d.subject == 8){
    			subject = "第三部分普通教学";
    		    }else if(d.subject == 9){
    			subject = "第三部分智能教学";
    		    }
    		    
    		    var trainningtime = '';
    		    if(d.trainningtime == 1){
    			trainningtime = "普通时段";
    		    }else if(d.trainningtime == 2){
    			trainningtime = "高峰时段";
    		    }else if(d.trainningtime == 3){
    			trainningtime = "节假日时段";
    		    }
    		    
    		    var service = "";
    		    if(d.service != null){
    			service = d.service;
    		    }
    		   
    		    html += "<tr>"
    			    +"<td>"+index+"</td>"
    			    +"<td>"+subject+"</td>"
    			    +"<td>"+d.classcurr+"</td>"
    			    +"<td>"+d.vehicletype+"</td>"
    			    +"<td>"+trainningtime+"</td>"
    			    +"<td>"+d.price+"</td>"
    			    +"<td>"+service+"</td>"
    			    +"</tr>";
    		}
    		 $("#charsMode2").html(html);
        }else{
            layer.msg(json.message, {
                icon: 2,
                time: 1000 //2秒关闭（如果不配置，默认是3秒）
            }, function(){
            });
        }
    }, 'json');
    
    $.post(getSchCharstandard,"inscode="+ inscode+"&&chargemode=1", function (json) {
        if (json.errorcode==0) {
            	var data = json.data;
            	var html = '';
            	if(data.length == 0){
            	    html += "<tr>" 
            		    +"<td colspan = '7'>暂无收费信息</td>"
            	            +"</tr>";
            	}
    		for ( var i in data) {
    		    var d = data[i];
    		    var index = i-0+1;
    		    
    		    var subject = '';
    		    if(d.subject == 1){
    			subject = "第一部分集中教学";
    		    }else if(d.subject == 2){
    			subject = "第一部分网络教学";
    		    }else if(d.subject == 3){
    			subject = "第四部分集中教学";
    		    }else if(d.subject == 4){
    			subject = "第四部分网络教学";
    		    }else if(d.subject == 5){
    			subject = "模拟器教学";
    		    }else if(d.subject == 6){
    			subject = "第二部分普通教学";
    		    }else if(d.subject == 7){
    			subject = "第二部分智能教学";
    		    }else if(d.subject == 8){
    			subject = "第三部分普通教学";
    		    }else if(d.subject == 9){
    			subject = "第三部分智能教学";
    		    }
    		    
    		    var trainningtime = '';
    		    if(d.trainningtime == 1){
    			trainningtime = "普通时段";
    		    }else if(d.trainningtime == 2){
    			trainningtime = "高峰时段";
    		    }else if(d.trainningtime == 3){
    			trainningtime = "节假日时段";
    		    }
    		    
    		    var service = "";
    		    if(d.service != null){
    			service = d.service;
    		    }
    		    
    		    
    		   
    		    html += "<tr>"
    			    +"<td>"+index+"</td>"
    			    +"<td>"+subject+"</td>"
    			    +"<td>"+d.classcurr+"</td>"
    			    +"<td>"+d.vehicletype+"</td>"
    			    +"<td>"+trainningtime+"</td>"
    			    +"<td>"+d.price+"</td>"
    			    +"<td>"+service+"</td>"
    			    +"</tr>";
    		}
    		$("#charsMode1").html(html);
        }else{
            layer.msg(json.message, {
                icon: 2,
                time: 1000 //2秒关闭（如果不配置，默认是3秒）
            }, function(){
            });
        }
    }, 'json');
}

/**
 * 初始化教学区域信息
 */
var map;
function initschTiregion(inscode){
//    map.plugin(["AMap.ToolBar"], function() {
//        map.addControl(new AMap.ToolBar());
//    });
    $.post(getSchTiregion,"inscode="+ inscode, function (json) {
        if (json.errorcode==0) {
            	map = new AMap.Map('container', {
            	    center: [117.000923, 36.675807],
            	    zoom: 10
            	});
            	var data = json.data;
            	var html = '';
            	$("#tiregionName").html(sch.shortname);
    		for ( var i in data) {
    		     var d = data[i];
    		     
    			   
    			arr= new Array();
			arr=d.polygon.split(';');
			
			var poly=[];
			var pp;
			for(var i=0;i<arr.length;i++){
				 var ss =arr[i];
				 pp=ss.split(',');
				 poly.push(pp);
			}
			 var a=[];
			 for(var i=0;i<=poly.length-1;i++){
				 for(var j =0;j<i;j++){
						 if(poly[i][1] > poly[j][1]){
							a = poly[i];
							poly[i] = poly[j];
							poly[j] = a;
						} 
					} 
			 }
			//顶点经纬度
			var pol=poly[0];
			
    			var polygonArr = new Array();//多边形覆盖物节点坐标数组
    			var polygonArrs = new Array();
    //		map = GDMap.createMapContainer(117.246, 39.117, 'mapContenter', 10);
    		
        		for(var i=0;i<arr.length;i++){
        		 var str=arr[i];
        		 var as=str.split(',');
        		 var marker = new AMap.Marker({
           		 position: as,
           	  	 draggable: true,
            		 cursor: 'move',
            		 raiseOnDrag: true
        			 });  					 
        		 marker.setMap(map);
        		 marker.setLabel({//label默认蓝框白底左上角显示，样式className为：amap-marker-label
           		offset: new AMap.Pixel(20, 20),//修改label相对于maker的位置
          		content: as[0]+" E , "+as[1]+" N"
        		});
        		 polygonArr.push(as);
        		}
        				// 为地图添加插件
        		AMap.plugin([ 'AMap.ToolBar', 'AMap.Scale','AMap.MapType'], function() {
        		var toolBar = new AMap.ToolBar();
        		var scale = new AMap.Scale();
        		var mapType = new AMap.MapType({
        			defaultType : 0
        		});
        		map.addControl(toolBar);
        		map.addControl(scale);
        		map.addControl(mapType);
        		});
        		map.setCenter(polygonArr[0]);
        		map.setZoom(13,13);
    //    		var polyline=GDMap.createPolygon(polygonArr);
    //    		polyline.setMap(map);
        	
    		var  polygon = new AMap.Polygon({
    			        path: polygonArr,//设置多边形边界路径
    			        strokeColor: "#FF33FF", //线颜色
    			        strokeOpacity: 0.2, //线透明度
    			        strokeWeight: 3,    //线宽
    			        fillColor: "#1791fc", //填充色
    			        fillOpacity: 0.35//填充透明度
    			    });
    		polygon.setMap(map);
//    		console.log(polygonArr);
    		html +="<tr>"
			 +"<td>" +
			 "<img onclick='setOneTiregionMap("+JSON.stringify(d)+")' src='"+path+"/static/img/location2.png' style='margin: 0 5px;'/>"+d.name+"</td></tr>"
			 +"<tr><td >"+d.address+"</td>"
			 +"</tr>";
    		}
    		html +="<tr>"
    		        +"<td>电话：<span style='color: #5a86cd;'>"+sch.phone+"</span></td>"
    		        +"</tr>";
    		$("#tiregionList").html(html);
    		
    		
        }else{
            layer.msg(json.message, {
                icon: 2,
                time: 1000 //2秒关闭（如果不配置，默认是3秒）
            }, function(){
            });
        }
    }, 'json');
}

/**
 * 评价点击查询
 */
function evaClick(type){
    initEvaList(sch.inscode,type);
}

/**
 * 初始化评价统计信息
 * @param inscode
 */
function initCountEvaList(inscode){

    $.post(getSchEvaluationCount,"inscode="+ inscode+"&&type=2", function (json) {
        if (json.errorcode==0) {
            var d = json.data;
            $("#allEva").html("("+d.countEval+")");
            $("#goodEva").html("("+d.goodeva+")");
            $("#midEva").html("("+d.mideva+")");
            $("#badEva").html("("+d.badeva+")");
            $("#allEvaTol").html(d.countEval);
            $("#evaStuNum").html(d.countStunum);
            var evaList = "";
            if (d.evaluateLabel === undefined || d.evaluateLabel.length == 0) {
        	    // array empty or does not exist
        	evaList = "暂无评价";
            }
            for(var i in d.evaluateLabel){
        	var evaDisPlayList = d.evaluateLabel[i];
        	evaList += "<button class='bat-but'>"+evaDisPlayList.evaluateLabel+"(<span class='del-span-color' >"+evaDisPlayList.evacount+"</span>)</button>"
            }
            
            $("#evaDisplayList").html(evaList);
        }else{
            layer.msg(json.message, {
                icon: 2,
                time: 1000 //2秒关闭（如果不配置，默认是3秒）
            }, function(){
            });
        }
    }, 'json');

}


/**
 * 统计驾校评价信息
 */
function initEvaList(inscode,type){
    
    addHtml = function(result) {
	var html = "";
	data = result.data.records;
	for ( var i in data) {
	    var d = result.data.records[i];
	    
	    var img = "";
	 
	    var www=d.overall*2*15;
	    img += "<div class='atar_Show'  style='display:inline;'><p tip='"+d.overall+"' style='width: "+www+"px;float: none;'></p></div>";
	    
	    html += "<div class='coach-title-img' style='float: left;'>"
		
		    if(d.isanonymous == 1){
			html += "<img src='"+d.stuPhotoUrl+"'/>"+
	                "<p style='line-height: 30px;'>"+d.stuname+"<span style='margin-left: 8px;'>"+d.traintype+"</span></p>";
	            }else{
	        	html +="<img src='"+path+"/static/img/head.png'/>"
	        	+ "<p style='line-height: 30px;'>"+d.stuname+"<span style='margin-left: 15px;'>"+d.traintype+"</span></p>"
	            }
	            html += "</div>"
		    +"<div class='' style='float: left;margin-left: 20px;width:90%;'>"
		    +"<p><span>"
		    +img+"</span>"	 
	            +"<span  style='margin-left: 10px;'>"
	            +d.evaluatetime+"</span></p>"
	            +"<p  style='line-height: 40px;float:left;width:100%;'>"
	            +d.teachlevel+"</p>"
	            +"<p>"
	            for(var j in d.ifEvaluateLabelList){
	        	html += "<button style='padding: 5px;margin:10px 10px 10px 0;background: #e2eaf2;border-style:none;color: #274472;'>"
			    +d.ifEvaluateLabelList[j].evaluateLabel+"</button>"
	            }
	    html += "</p>"
		    +"</div>"
	     html += "<hr/>"
	  }
	 return html;
      };
  
    var paramData = {};
    var selectIndex = $("#evaSort").val();
    if(selectIndex == 1){
	paramData.sort = "evaluatetime";
    }else if(selectIndex == 2){
	paramData.sort = "overall";
    }
    
    
    if(inscode != null){
	paramData.inscode = inscode;
    }
    if(type!=null ||type != undefined){
	paramData.scoretype = type;
    }
    if(type!=null ||type != undefined){
	paramData.type = 2;
    }
    
    queryPage('evalutionPage', 'evalMsg', "", 3, 1, getSchEvaluation,
	    addHtml, paramData);
    
}

var markers1=[];
function showqys(polygonArr){
    	map.clearMap();
    	
    	newpolygonArr = JSON.parse(JSON.stringify(polygonArr));
    	alert(newpolygonArr);
//	var as = [longitude, lat];
//	map.setCenter(as);
//	map.setZoom(15,15);
//	var marker = new AMap.Marker({
//		position: as,
//		draggable: true,
//		cursor: 'move',
//		raiseOnDrag: true
//	});
//	markers1.push(marker);
//	marker.setMap(map);
    	
    	var  polygon = new AMap.Polygon({
	        path: newpolygonArr,//设置多边形边界路径
	        strokeColor: "#FF33FF", //线颜色
	        strokeOpacity: 0.2, //线透明度
	        strokeWeight: 3,    //线宽
	        fillColor: "#1791fc", //填充色
	        fillOpacity: 0.35//填充透明度
	    });
    	polygon.setMap(map);
}


function setOneTiregionMap(tiregionData){
//  if (tiregionData.errorcode==0) {
//	var data = tiregionData;
	var html = '';
//	for ( var i in data) {
	map.clearMap();
        var d = tiregionData;
	   
	arr= new Array();
	arr=d.polygon.split(';');
	
	var poly=[];
	var pp;
	for(var i=0;i<arr.length;i++){
		 var ss =arr[i];
		 pp=ss.split(',');
		 poly.push(pp);
	}
	 var a=[];
	 for(var i=0;i<=poly.length-1;i++){
		 for(var j =0;j<i;j++){
				 if(poly[i][1] > poly[j][1]){
					a = poly[i];
					poly[i] = poly[j];
					poly[j] = a;
				} 
			} 
	 }
	//顶点经纬度
	var pol=poly[0];
	
	var polygonArr = new Array();//多边形覆盖物节点坐标数组
	var polygonArrs = new Array();
//map = GDMap.createMapContainer(117.246, 39.117, 'mapContenter', 10);

      for(var i=0;i<arr.length;i++){
       var str=arr[i];
       var as=str.split(',');
       var marker = new AMap.Marker({
      	 position: as,
      	 draggable: true,
      	 cursor: 'move',
      	 raiseOnDrag: true
      	 });  					 
       marker.setMap(map);
       marker.setLabel({//label默认蓝框白底左上角显示，样式className为：amap-marker-label
      	offset: new AMap.Pixel(20, 20),//修改label相对于maker的位置
      	content: as[0]+" E , "+as[1]+" N"
      });
       polygonArr.push(as);
      }
      		// 为地图添加插件
      AMap.plugin([ 'AMap.ToolBar', 'AMap.Scale','AMap.MapType'], function() {
      var toolBar = new AMap.ToolBar();
      var scale = new AMap.Scale();
      var mapType = new AMap.MapType({
      	defaultType : 0
      });
      map.addControl(toolBar);
      map.addControl(scale);
      map.addControl(mapType);
      });
      map.setCenter(polygonArr[0]);
      map.setZoom(13,13);
      //var polyline=GDMap.createPolygon(polygonArr);
      //polyline.setMap(map);
      
      var  polygon = new AMap.Polygon({
      	        path: polygonArr,//设置多边形边界路径
      	        strokeColor: "#FF33FF", //线颜色
      	        strokeOpacity: 0.2, //线透明度
      	        strokeWeight: 3,    //线宽
      	        fillColor: "#1791fc", //填充色
      	        fillOpacity: 0.35//填充透明度
      	    });
      polygon.setMap(map);
//	}
//  }
}

function showPhotos(type){
    layer.open({
	  type: 2,
	  title:"相册",
	  skin: 'layui-layer-demo', //样式类名
	  closeBtn: 0, //不显示关闭按钮
	  anim: 4,
	  area: ['750px', '570px'],
	  shadeClose: true, //开启遮罩关闭
	  content:gotoSchPhotos+"?type="+type+"&inscode="+sch.inscode
	});
}

function showBg() {
    $("#menu-title").children(".menu-title-bg").removeClass('menu-title-bg');
    $("#menu-title-three").addClass('menu-title-bg');
}