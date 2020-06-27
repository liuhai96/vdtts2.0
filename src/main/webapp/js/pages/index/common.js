$(function(){
   $(".blist").on("click","li",function(){
	 // 设index为当前点击
	 var index = $(this).index();
	 // 点击添加样式利用siblings清除其他兄弟节点样式
	 $(this).addClass("active").siblings().removeClass("active");
	 // 同理显示与隐藏
	 $(this).parents(".table-list").find(".blsit-list>li").eq(index).show().siblings().hide();
   })
	$(".statistics").on("click","li",function(){
		// 设index为当前点击
		var index = $(this).index();
		// 点击添加样式利用siblings清除其他兄弟节点样式
		$(this).addClass("active").siblings().removeClass("active");
	});

    //首先将#back-to-top隐藏
    $("#totop").hide();
    //当滚动条的位置处于距顶部100像素以下时，跳转链接出现，否则消失
    $(function(){
        $(window).scroll(function(){
            if ($(window).scrollTop()>100){
                $("#totop").fadeIn();
            }else{
                $("#totop").fadeOut();
            }
        });
        //当点击跳转链接后，回到页面顶部位置
        $("#totop").click(function(){
            $('body,html').animate({scrollTop:0},500);
            return false;
        });
    });
})
$(function(){
	layui.use(['element','form','carousel','laypage', 'layer'], function(){
		var $ = layui.jquery
		  ,element = layui.element//Tab的切换功能，切换事件监听等，需要依赖element模块
		  ,laypage = layui.laypage
		  ,carousel = layui.carousel
		  ,layer = layui.layer
		  ,form = layui.form
		  form.render();
		 if(location.pathname.indexOf('/publicity')>=0){
		        if(location.hash.replace(/^#type=/, '')!=''){
		            element.tabChange('tz', location.hash.replace(/^#type=/, ''));
		            var val = location.hash.replace(/^#type=/, '');
		            if(val ==1){
//		        	noticeInfo();
		            }else if(val == 2){
//		        	industryInfo();
		            }else if(val == 3){
//		        	policyInfo();
		            }else if(val == 4){
//		        	policyusInfo();
		            }else if(val == 5){
//		        	exposureInfo();
		            }
		        }
		 }else if(location.pathname.indexOf('/industryList')>=0){
		     if(location.hash.replace(/^#type=/, '')!=''){
		            element.tabChange('rank', 1);
		            var val = location.hash.replace(/^#type=/, '');
		            if(val ==1){
				selVehCountSort("vehCount");
		            }else if(val == 2){
		        	selyearAddCountSort("yearAddCount");
		            }else if(val == 3){
		        	seldayAddCountSort("dayAddCount");
		            }
		        }
		 }
		
		  
		  //常规轮播
		  	carousel.render({
		    	elem: '#homeBox'
		    	,width: '380px'
		    	,height: '280px'
		    	,interval: 3000
		  	});
			carousel.render({
				elem: '#schphotoBox'
				,width: '700px'
				,height: '460px'
				,interval: 3000
			});
		  	//总页数低于页码总数
//		  laypage.render({
//		    elem: 'demo0'
//		    ,count: 50 //数据总数
//		  });
		  //总页数低于页码总数
		  laypage.render({
		    elem: 'demo01'
		    ,count: 50 //数据总数
		  });
	});
	
//	$.extend({
//    		"PaginBar":function(){
    		   
        		queryPage = function(divId,table,selectFrom,rows,pageIndex,tourl,addhtml,paramData,tname){
        		    var tableId = $("#"+table+"");
            		    var fromId = $("#"+selectFrom+"");
            		    var url = url;
            		    var totalUrl = totalUrl;
            		    var pageIndex = pageIndex;
            		    var rows = rows;
            		    var count = 0;
            		    var paramData = paramData;
            		    var addHtmlMthod = addhtml;
            		    var divid = divId;
            		    var tname = tname;
        		    layui.use(['element','form','carousel','laypage', 'layer','table'], function(){
        			var $ = layui.jquery
        			  ,element = layui.element//Tab的切换功能，切换事件监听等，需要依赖element模块
        			  ,laypage = layui.laypage
        			  ,carousel = layui.carousel
        			  ,layer = layui.layer
        			  ,form = layui.form
        			  ,laytable = layui.table;
            			  paramData.size = rows;
            			  paramData.current = pageIndex;
        		
        			var aj = $.ajax({
        			    type: "POST",
        			    url: tourl,
        	             	    data:JSON.stringify(paramData),
        	             	    dataType: "json",
        	             	    contentType: "application/json; charset=utf-8",
        	             	    success: function(json){
                	            	 if (json.errorcode == 0) {
                	            	  if(json.data !=null){
                        	            	  laypage.render({
                        	            	      elem: divid,
                        	            	      count: json.data.total, //数据总数
                        	            	      curr:json.data.current,
                        	            	      groups:3,
                        	            	      limit:json.data.size,
                        	            	      jump:function(obj,first) {
                        	            	      if(!first){
                        	            		    queryPage(divId,table,selectFrom,rows,obj.curr,tourl,addhtml,paramData,tname);
                        	            	      }
              				    }
              				});
                    	            	     var html = addHtmlMthod(json);
                    	            	     tableId.html(html);
                    	            	    
                    	            	     form.render();
                	            	  }
                	            	     
                			 }else{
                			      layer.alert(json.message);
                			}
                	            
        	             },
                	             error:function(){
                	        	 layer.alert('加载数据失败请重试!!');  
                	             }
        	         });
        		 });
        		}
        		
//        		innerPageHtml = function(divid,json){
//        		    layui.use(['laypage', 'layer'], function(){
//        		      var $ = layui.jquery
//        		       ,laypage = layui.laypage
//        		       ,layer = layui.layer
//                	      laypage.render({
//        	 		    elem: divid,
//        	 		    count: json.data.total, //数据总数
//        	 		    curr:json.data.current,
//        	 		    limit:json.data.size,
//        	 		    jump:function(obj,first) {
//        	 			if(!first){
//        	 			    queryPage(divId,table,selectFrom,rows,obj.curr,tourl,addhtml,paramData);
//        	 			}
//        	 		    }
//        	 		  });
//        		    });
//                	  }
//    		}
//	});
})


function Paging_index(){

		this.queryPage = function(divId,table,selectFrom,rows,pageIndex,tourl,addhtml,paramData,page_index){
		    
		    var tableId = $("#"+table+"");
		    var fromId = $("#"+selectFrom+"");
		    var url = url;
		    var totalUrl = totalUrl;
		    var pageIndex = pageIndex;
		    var rows = rows;
		    var count = 0;
		    var paramData = paramData;
		    var addHtmlMthod = addhtml;
		    var divid= divId;
		    layui.use(['element','form','carousel','laypage', 'layer'], function(){
			var $ = layui.jquery
			  ,element = layui.element//Tab的切换功能，切换事件监听等，需要依赖element模块
			  ,laypage = layui.laypage
			  ,carousel = layui.carousel
			  ,layer = layui.layer
			  ,form = layui.form
			
    			   paramData.size = rows;
    			   paramData.current = pageIndex;
		
			var aj = $.ajax({
			    type: "POST",
			    url: tourl,
	             	    data:JSON.stringify(paramData),
	             	    dataType: "json",
	             	    contentType: "application/json; charset=utf-8",
	             	    success: function(json){
        	            	 if (json.errorcode == 0) {
        	
        			      laypage.render({
        				        elem: divid,
        				        count: json.data.total, //数据总数
        				 	curr:json.data.current,
        				 	limit:json.data.size,
        				 	groups:5,
        				        jump:function(obj,first) {
        				 	if(!first){
        				 	   page_index.queryPage(divId,table,selectFrom,rows,obj.curr,tourl,addhtml,paramData,page_index);
        				 	}
        				    }
        				});
        	            	     var html = addHtmlMthod(json);
        	            	     tableId.html(html);
        	            	     form.render();
        			 }else{
        			      layer.alert(json.message);
        			}
	             },
        	             error:function(){
        	        	 layer.alert('加载数据失败请重试!!');  
        	             }
	         });
		 });
		   innerPageHtml = function(json){

	        	  };   
		}
};

function cancel() {
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

/**
 * 获取当前时间
 * @returns {String}
 */
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var hours = date.getHours();
    var minutes = date.getMinutes();
    var currentdate = year+"年" + month + "月" + strDate+"日  "+hours+":"+minutes;
    return currentdate;
}

/**
 * 时间戳转换方法
 */
function toTransTimeYMD(inputTime){
    var date = new Date(inputTime);  
    var y = date.getFullYear();    
    var m = date.getMonth() + 1;    
    m = m < 10 ? ('0' + m) : m;    
    var d = date.getDate();    
    d = d < 10 ? ('0' + d) : d;    
    return y + '-' + m + '-' + d;    
}

/** 
 * js截取字符串，中英文都能用 
 * @param str：需要截取的字符串 
 * @param len: 需要截取的长度 
 */
function cutstr(str, len) {
    var str_length = 0;
    var str_len = 0;
    str_cut = new String();
    str_len = str.length;
    for (var i = 0; i < str_len; i++) {
        a = str.charAt(i);
        str_length++;
        if (escape(a).length > 4) {
            //中文字符的长度经编码之后大于4  
            str_length++;
        }
        str_cut = str_cut.concat(a);
        if (str_length >= len) {
            str_cut = str_cut.concat("...");
            return str_cut;
        }
    }
    //如果给定字符串小于指定长度，则返回源字符串；  
    if (str_length < len) {
        return str;
    }
}

/**
 * 判断字符串为undefined 或者null的时候设置为-
 */
function isEmpt(paramter){
    if(paramter == undefined || paramter == null){
	return "-";
    }else{
	return paramter;
    }
}


function topSelect() {
	var selectType = $("#selectType").val();
	var selectName = $("#selectName").val();
    window.location.href=path + "/inquire/"+selectType+"?name="+encodeURI(selectName);
}