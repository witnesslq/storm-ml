<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibhead.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>业务量统计-四川农信大数据日志分析平台</title>
<%@ include file="../../common/member.inc"%>
<link rel="stylesheet" href="${ctx}/front/css/home.css" type="text/css"/>
<link rel="stylesheet" href="${ctx}/front/css/sms_template.css" type="text/css" />
<link href="${ctx}/front/css/default.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/front/css/def.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/front/css/liebiao.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/layer/layer.min.js"></script> 
<script type="text/javascript">
$(document).ready(function() { 
	function setHeight(){
		var nHeight = $('.container').height()+'px';
		var brsHeight = $(window).height()-176+'px';
		var mainHeight = parseInt(brsHeight)+'px';
		if(parseInt(nHeight) < parseInt(brsHeight)){
			$('.left,.left .tabs').css({'height':mainHeight});
		}else if(parseInt(nHeight) >= parseInt(brsHeight)){
			$('.left,.left .tabs').css({'height':nHeight});
		}
		/*公告下面图片大小设置*/
		var W = $('.noticeDiv').width()+'px';
		var oImg = $('.activity_logo').find('img');
		oImg.css("width",W);
	}
	window.onload = function(){setHeight();};
	window.onresize = function(){setHeight();}
	
	//== highChart
	
	var onedaycount=new Array();
	var onedayerrorcount=new Array();
	var ebcpArray=new Array();
	var ebgbArray=new Array();
	var ebmpArray=new Array();
	var successRate=new Array();
	//
	var sceneGroupX=new Array();
	var sceneGroupSuccY=new Array();
	var sceneGroupErrY=new Array();
	
	
	for(var i=0;i<10;i++){
		onedaycount[i]=0;
		onedayerrorcount[i]=0;
		ebcpArray[i]=0;
		ebgbArray[i]=0;
		ebmpArray[i]=0;
		successRate[i]=0.0;
	}
	var index=0;
	
	
	<c:forEach items="${oneDayEerrorBusinessCounts}" var="oneDayEerrorBusinessCount">
	onedayerrorcount['${oneDayEerrorBusinessCount.dtime}']=parseFloat('${oneDayEerrorBusinessCount.count}');
	</c:forEach >
	
	<c:forEach items="${oneDayBusinessCounts}" var="oneDayBusinessCount">
	onedaycount['${oneDayBusinessCount.dtime}']=parseFloat('${oneDayBusinessCount.count}');
	if(onedaycount['${oneDayBusinessCount.dtime}']>0){
		successRate['${oneDayBusinessCount.dtime}']=(onedaycount['${oneDayBusinessCount.dtime}']-onedayerrorcount['${oneDayBusinessCount.dtime}'])/onedaycount['${oneDayBusinessCount.dtime}'];
	}
	</c:forEach >
	<c:forEach items="${ebcp}" var="ebcpVar">
	ebcpArray['${ebcpVar.dtime}']=parseFloat('${ebcpVar.count}');
	</c:forEach >
	<c:forEach items="${ebgb}" var="ebgbVar">
	ebgbArray['${ebgbVar.dtime}']=parseFloat('${ebgbVar.count}');
	</c:forEach >
	<c:forEach items="${ebmp}" var="ebmpVar">
	ebmpArray['${ebmpVar.dtime}']=parseFloat('${ebmpVar.count}');
	</c:forEach >
	
	var i=0;
	<c:forEach items="${sceneGroup}" var="sceneGroupVar">
	if(i<14){
		sceneGroupX[i]='${sceneGroupVar.sceneGroup}';
		sceneGroupErrY[i]=parseInt('${sceneGroupVar.err}');
		sceneGroupSuccY[i++]=parseInt('${sceneGroupVar.succ}');
	}
	</c:forEach>

	$(function () {
	//===统一门户
	  $('#container').highcharts({
		  credits:{
	    	     enabled:false // 禁用版权信息
	    	},
	    	chart: {
	            type: 'column'
	        },
	        title: {
	            text: '统一门户 访问量',
	            x: -20 //center
	        },
	        subtitle: {
	            text: '',
	            x: -20
	        },
	        xAxis: {
	        	categories: ['0', '1', '2', '3', '4', '5',
	     	                '6', '7', '8', '9', '10', '11','12','13','14','15','16','17','18','19','20','21','22','23']
	        },
	        yAxis: {
	            title: {
	                text: '次数'
	            },
	            plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }]
	        },
	        tooltip: {
	            valueSuffix: ''
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'middle',
	            borderWidth: 0
	        },
	        series: [{
	            name: '统一门户',
	            data: ebcpArray
	        }]
	    });
	});
	
	$(function () {
		//===统一门户
		  $('#container2').highcharts({
			  credits:{
		    	     enabled:false // 禁用版权信息
		    	},
		    	chart: {
		            type: 'column'
		        },
		        title: {
		            text: '移动门户',
		            x: -20 //center
		        },
		        subtitle: {
		            text: '',
		            x: -20
		        },
		        xAxis: {
		        	categories: ['0', '1', '2', '3', '4', '5',
		     	                '6', '7', '8', '9', '10', '11','12','13','14','15','16','17','18','19','20','21','22','23']
		        },
		        yAxis: {
		            title: {
		                text: '次数'
		            },
		            plotLines: [{
		                value: 0,
		                width: 1,
		                color: '#808080'
		            }]
		        },
		        tooltip: {
		            valueSuffix: ''
		        },
		        legend: {
		            layout: 'vertical',
		            align: 'right',
		            verticalAlign: 'middle',
		            borderWidth: 0
		        },
		        series: [{
		            name: '移动门户',
		            data: ebmpArray
		        }]
		    });
		});
	
	 $('#container1').highcharts({
		 credits:{
    	     enabled:false // 禁用版权信息
    	   },
	    	chart: {
	            type: 'column'
	        },
	        title: {
	            text: '标配',
	            x: -20 //center
	        },
	        subtitle: {
	            text: '',
	            x: -20
	        },
	        xAxis: {
	        	categories: ['0', '1', '2', '3', '4', '5',
	     	                '6', '7', '8', '9', '10', '11','12','13','14','15','16','17','18','19','20','21','22','23']
	        },
	        yAxis: {
	            title: {
	                text: '次数'
	            },
	            plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }]
	        },
	        tooltip: {
	            valueSuffix: ''
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'middle',
	            borderWidth: 0
	        },
	        series: [ {
	            name: '标配',
	            data: ebgbArray
	        }]
	    });
	//=========当天交易量统计
	
	$(function () {
    $('#container3').highcharts({
    	credits:{
   	     enabled:false // 禁用版权信息
      	},
        chart: {
            type: 'area'
        },
        title: {
            text: '当天的访问量统计'
        },
        subtitle: {
            text: ''
        },
        xAxis: {
        	categories: ['0', '1', '2', '3', '4', '5',
	     	                '6', '7', '8', '9', '10', '11','12','13','14','15','16','17','18','19','20','21','22','23','24']
        },
        yAxis: {
            title: {
                text: '交易量'
            },
            labels: {
                formatter: function() {
                    return this.value ;
                }
            }
        },
        tooltip: {
            pointFormat: ' 交易量 <b>{point.y:,.0f}</b><br>  {point.x}'
        },
        plotOptions: {
            area: {
                pointStart: 0,
                marker: {
                    enabled: false,
                    symbol: 'circle',
                    radius: 2,
                    states: {
                        hover: {
                            enabled: true
                        }
                    }
                }
            }
        },
        series: [{
            name: '总交易量',
            data: onedaycount
            
        }, {
            name: '失败交易量',
            data: onedayerrorcount
        }]
    });
}); 
	
	//按场景组统计交易量 
	$(function () {
    $('#container4').highcharts({
    	credits:{
    	     enabled:false // 禁用版权信息
    	},
        chart: {
            type: 'column'
        },
        title: {
            text: 'top10场景组 '
        },
        xAxis: {
            categories: sceneGroupX
        },
        yAxis: {
            min: 0,
            title: {
                text: '交易量 '
            },
            stackLabels: {
                enabled: false,
                style: {
                    fontWeight: 'bold',
                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                }
            }
        },
        legend: {
            align: 'right',
            x: 0,
            verticalAlign: 'top',
            y: 20,
            floating: true,
            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
            borderColor: '#CCC',
            borderWidth: 1,
            shadow: false
        },
        tooltip: {
            formatter: function() {
                return '<b>'+ this.x +'</b><br>'+
                    this.series.name +': '+ this.y +'<br>'+
                    'Total: '+ this.point.stackTotal;
            }
        },
        plotOptions: {
            column: {
                stacking: 'normal',
                dataLabels: {
                    enabled: true,
                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
                }
            }
        },
        series: [ {
            name: '成功',
            data: sceneGroupSuccY
        }, {
            name: '失败',
            data: sceneGroupErrY
        }]
    });
});
	//====
	
	
	
});
	



function viewOne(errorCodeId){
	//$.colorbox({href:"${ctx}/manage/alert/showOne?errorCodeId="+errorCodeId+"&m="+Math.random(),title:"查看详细"});
	layer.open({
		shift : 5,
		type : 2,
		title: false,
		shade:[0.8, '#393D49'],
		shadeClose: false,
		maxmin: false,
		fix : true,  
		area: ['950px', '550px'],                     
		content : "${ctx}/manage/alert/showOneSceneCode?sceneCode="+errorCodeId+"&m="+Math.random()
	});
}
</script>
<script src="${ctx}/js/highcharts/highcharts.js"></script>
</head>
<body>
<div class="wrap">
  <%@ include file="../../include/memTop.jsp"%>
  <div class="container clearfix">
    <div class="left">
    <c:import url="../../include/leftAlert.jsp">
		<c:param name="menuFlag" value="listAllBusinessCode"/>
	</c:import>
    </div>
    <div class="right">
		<div class="tit"><h1>业务量统计</h1></div>
        
        <div id="container3" style="min-width: 400px; height: 320px; margin: 0 auto"></div>
        <div id="container4" style="min-width: 400px; height: 320px; margin: 0 auto"></div>
	              <div id="container" style="min-width: 400px; height: 320px; margin: 0 auto"></div>
	               <div id="container2" style="min-width: 400px; height: 320px; margin: 0 auto"></div>
	               <div id="container1" style="min-width: 400px; height: 320px; margin: 0 auto"></div> 
	              
	               
             
    </div>
  </div>
  <%@ include file="../../include/memBottom.jsp"%>
</div>
</body>
</html>
