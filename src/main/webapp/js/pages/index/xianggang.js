var tobaseDataByNameUrl =  path+"/api/ggfwSchInfo/getDistrictBaseData";

(function (root, factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module.
        define(['exports', 'echarts'], factory);
    } else if (typeof exports === 'object' && typeof exports.nodeName !== 'string') {
        // CommonJS
        factory(exports, require('echarts'));
    } else {
        // Browser globals
        factory({}, root.echarts);
    }
}(this, function (exports, echarts) {
    var log = function (msg) {
        if (typeof console !== 'undefined') {
            console && console.error && console.error(msg);
        }
    }
    if (!echarts) {
        log('ECharts is not Loaded');
        return;
    }
    if (!echarts.registerMap) {
        log('ECharts Map is not loaded')
        return;
    }
    // var path = (basePath());
    initBaseMap(1);
   
    
}));

var my_mapChart = echarts.init(document.getElementById('main_map'));

var isInit = false;


function initBaseMap(type){
    var city = {
	    "四川省":"sichuang.json",
	    "内蒙古自治区":"neimenggu.json",
	    "江西省":"jiangxi.json",
	    "河北省":"hebei.json",
	    "天津市":"tianjin.json",
	    "陕西省":"shanxi.json",
	    "山西省":"sanxi.json",
	    "青海省":"qinghai.json",
	    "广西壮族自治区":"guangxi.json",
	    "南昌市":"nanchang.json",
	    "上饶市":"shangrao.json",
	    "安徽省":"anhui.json",
	    "洛阳市":"luoyang.json",
	    "山东省":"shandong.json",
		"河南省":"henan.json",
		"福建省":"fujian.json",
		"新疆维吾尔自治区":"xinjiang.json"
	    }
    
    $.post(path+"/api/ggfwSchInfo/getDistrictName","useType="+type,function(data){
//	alert(path+"static/maps"+city[data]);
	var json = JSON.parse(data);
	$.get('static/maps/'+city[json.name], function (geoJson) {
	    echarts.registerMap(json.name,geoJson);
		
	    var toolTipData = json.data;
	    var mapData = JSON.parse(JSON.stringify(toolTipData));
	    for(var i = 0;i<mapData.length;i++){
                    if(type ==  1){
                	mapData[i].value = mapData[i].value[0].value;
                    }else if(type == 2){
                	mapData[i].value = mapData[i].value[1].value;
                    }else if(type == 3){
                	mapData[i].value = mapData[i].value[2].value;
                    }
            }
		option = {
	        title: {
	            text: '基础数据总览',
	            left:40,
	            top:5,
	            textStyle:{
	            	//fontWeight:'normal',
	            	fontSize:16,
	            	
	            },
	            //subtext: '更新时间：2017年10月30日 18:46',
	            
	        },

	        graphic: [
	              {
        		type: 'text',
        		right: '10%',
        		top: '5%',
        		style: {
	        		text: "市级数据",
	        		lineWidth :2,
	        		textAlign: 'left',
	        		fill: 'red',
	        		font: '12px/14px sans-serif'
	        	},
	        	onclick: function(){
    	      
        		        $("#baseDataBySchCount").css({"color":"#40bfd5","border":"#40bfd5","border-style":"solid","border-width":"1px"});
        			$("#baseDataByVehCount").css({"color":"#000000","border":"#000000","border-style":"solid","border-width":"1px"});
        			$("#baseDataByCoaCount").css({"color":"#000000","border":"#000000","border-style":"solid","border-width":"1px"});
        			$("#baseDataBySchCount").html("驾培机构<img src='static/img/sort1.png'/>");
        			$("#baseDataByVehCount").html("教练车<img src='static/img/sort2.png'/>");
        			$("#baseDataByCoaCount").html("教练员<img src='static/img/sort2.png'/>");
        			var tobaseDataurl = path+"/api/ggfwSchInfo/baseDataOrder";
        		        initBaseDataTab("schCount","",tobaseDataurl);
        		        initBaseMap(1);
	        	}
	        }],
	        visualMap: {
	            min: 1,
	            max: 10,
	            text:['High','Low'],
	            realtime: false,
//	            seriesIndex: [1],
	            calculable: true,
	            inRange: {
	                color: ['lightskyblue','yellow','#ef5b9c', 'orangered']
	            }
	        },
	        tooltip: {  
	                trigger: 'item',
	                formatter: function(params) {
	                    
	                    var toolTiphtml = ''
	                        for(var i = 0;i<toolTipData.length;i++){
	                            if(params.name==toolTipData[i].name){
	                                toolTiphtml += toolTipData[i].name+':<br>'
	                                for(var j = 0;j<toolTipData[i].value.length;j++){
	                                    toolTiphtml+=toolTipData[i].value[j].name+':'+toolTipData[i].value[j].value+"<br>"
	                                }
	                            }
	                        }
//	                    	alert(toolTiphtml);
	                        return toolTiphtml;
//	                    var useName = "-";
//	                    	if(type == 1){
//	                    	     useName  ="驾培机构";
//	                    	}else if(type == 2){
//	                    	     useName  ="教练车";
//	                    	}else if(type == 3){
//	                    	     useName  ="教练员"
//	                    	}
//	                        return params.name + "<br/>" + useName +
//	                            ": " + 1;
	                } 
	            },

	        series: [
	            {
	                name: '基础数据',
	                type: 'map',
	                mapType: json.name, // 自定义扩展图表类型
//	                itemStyle:{
//	                    normal:{label:{show:true}},
//	                    emphasis:{label:{show:true}}
//	                },	
	                label: {
	                    normal: {
	                        show: true
	                    },
	                    emphasis: {
	                        show: true
	                    }
	                },
	                data:mapData,
//	                zoom :2,
                    roam: true,//滚轮放大缩小功能
                    aspectScale: 0.9,//地图宽高比例值
                    // 自定义名称映射
//	                nameMap: {
//	                    'Central and Western': '中西区',
//	                    'Eastern': '东区',
//	                    'Islands': '离岛',
//	                    'Kowloon City': '九龙城',
//	                    'Kwai Tsing': '葵青',
//	                    'Kwun Tong': '观塘',
//	                    'North': '北区',
//	                    'Sai Kung': '西贡',
//	                    'Sha Tin': '沙田',
//	                    'Sham Shui Po': '深水埗',
//	                    'Southern': '南区',
//	                    'Tai Po': '大埔',
//	                    'Tsuen Wan': '荃湾',
//	                    'Tuen Mun': '屯门',
//	                    'Wan Chai': '湾仔',
//	                    'Wong Tai Sin': '黄大仙',
//	                    'Yau Tsim Mong': '油尖旺',
//	                    'Yuen Long': '元朗'
//	                }

	            },
	        ]
	    };
		if(type == 1){
		    option.visualMap.max = 100;
		}else if(type == 2){
		    option.visualMap.max = 1000;
		}else if(type == 3){
		    option.visualMap.max = 2000;
		}
		if(json.name == '天津市'){
		    option.series[0].label.normal.show = false;
		}
		if(json.name == '河南省'){
		    $("#industryTop").css("display","none");
		}
		
//		console.log(option.visualMap.max);
		my_mapChart.setOption(option);
		
		if(!isInit){
		    if(json.name != '天津市'){
			if(json.districtType == 2){
			    option.series[0].label.normal.show = false;
			    my_mapChart.setOption(option);
			    return true;
			}
        		my_mapChart.on('click', function(params){
        		        var _self = this;
//        		        console.log(params);
        		        $("#baseDataBySchCount").css({"color":"#40bfd5","border":"#40bfd5","border-style":"solid","border-width":"1px"});
        			$("#baseDataByVehCount").css({"color":"#000000","border":"#000000","border-style":"solid","border-width":"1px"});
        			$("#baseDataByCoaCount").css({"color":"#000000","border":"#000000","border-style":"solid","border-width":"1px"});
        			$("#baseDataBySchCount").html("驾培机构<img src='static/img/sort1.png'/>");
        			$("#baseDataByVehCount").html("教练车<img src='static/img/sort2.png'/>");
        			$("#baseDataByCoaCount").html("教练员<img src='static/img/sort2.png'/>");
        			
        		        initBaseDataTab("schCount",params.name,tobaseDataByNameUrl);
        		        initBaseMap(1);
        		    });
		       }

        		my_mapChart.on('georoam', function (params) {
//        		    console.log(params.zoom);
        		});

        		
        		window.addEventListener("resize", function() {
        			my_mapChart.resize();
        		});
        		isInit = true;
		}
	});
    });
}


function basePath(){
    //获取当前网址，如： http://localhost:8080/ems/Pages/Basic/Person.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： /ems/Pages/Basic/Person.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8080
    var localhostPath = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/ems
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    //获取项目的basePath   http://localhost:8080/ems/
    var basePath=localhostPath+projectName+"/";
    return basePath;
};