<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibhead.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>全局概览-四川农信大数据日志分析平台</title>
<%@ include file="../../common/member.inc"%>
<link rel="stylesheet" href="${ctx}/front/css/home.css" type="text/css"/>
<script src="${ctx}/js/Highstock-2.0.4/js/highstock.js"></script>
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
	getPstnStatAccList();
});
function getPstnStatAccList(){
	var loading=layer.load(1, {
	    	shade: [0.1,'#fff'] //0.1透明度的白色背景
		});
	var lastTime=0;//储存最后查询时间点
	var hasData = 0;
	var nullData = 0;
	    var seriesOptions = [],
	    seriesCounter = 0,
	    names = ['总交易量', '成功量'];
	    // create the chart when all data is loaded
		Highcharts.setOptions({
			global:{useUTC : false},//设置为当地时区
			lang: {
				months: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
		        shortMonths: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一', '十二'],
		        weekdays: ['星期天', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
				rangeSelectorFrom: '显示范围 从',
				rangeSelectorTo: '至',
				rangeSelectorZoom: '时间区间'
			},
			colors: ['#FF2D2D', '#007500', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'] 
		});
	    createChart = function () {
	        $('#div_box').highcharts('StockChart', {
	        	title:{text:"交易监控"},
	            rangeSelector: {
	                inputEnabled: $('#div_box').width() > 480,
	                selected: 4,
	    				inputDateFormat: '%Y-%m-%d',
	    				inputEditDateFormat: '%Y-%m-%d',
	    				inputStyle: {
	    		    		color: '#000'
	    		    	},
	    				labelStyle: {
	    		    		color: '#000'
	    		    	},
	    				buttons: [
							{//定义一组buttons,下标从0开始
								type: 'hour',
								count: 0.1,
								text: '5分钟'
							},
	  						{//定义一组buttons,下标从0开始
								type: 'hour',
								count: 0.5,
								text: '30分钟'
							},
							{//定义一组buttons,下标从0开始
								type: 'hour',
								count: 1,
								text: '1小时'
							},
							{//定义一组buttons,下标从0开始
								type: 'hour',
								count: 3,
								text: '3小时'
							},
	    				    {//定义一组buttons,下标从0开始
	    						type: 'day',
	    						count: 1,
	    						text: '1天'
	    					},{
	    						type: 'all',
	    						text: '全部'
	    				}],
	    				selected: 0//表示以上定义button的index,从0开始
	            },
	            yAxis: {
	                plotLines: [{
	                    value: 0,
	                    width: 2,
	                    color: 'silver'
	                }],
	            },
	        	plotOptions: {
	        		line: {
	        			dataGrouping: {
	        				enabled: false
	        			}
	        		}
	        	},
	            tooltip: {
	                pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b><br/>',
	                valueDecimals: 0,
	                valueSuffix:" 条",
	                xDateFormat: '%Y-%m-%d %H:%M:%S'
	            },
	            series: seriesOptions,
				//动态加载                                                        
		        chart : {
		            events : {
		                marginRight: 10, 
		                load : function () {
		                    var seriesUsed = this.series[0];
		                    var seriesSucc = this.series[1];
		                    setInterval(function () {
		                    	$.ajax({
			            			async: false,
			            			type : "POST",
			            			url : "${ctx}/manage/alert/getDynamicData?random="+Math.random(),
			            			data:{lastTime:lastTime},
			            			dataType : "json",
			            			success : function(data) {
			            				if(data.flag){
			            					nullData=0;
	 				                        lastTime = data.lastTime,
		 				                    timeLineArr=data.timeLine.split(";"),
		 				                    numUsedLineArr=data.numUsedLine.split(";"),
		 				                    numSuccLineArr=data.numSuccLine.split(";");
	 				                       	for (var i = 0; i < timeLineArr.length; i++) {
							                    seriesUsed.addPoint([eval(timeLineArr[i]), eval(numUsedLineArr[i])], true, true);
							                    seriesSucc.addPoint([eval(timeLineArr[i]), eval(numSuccLineArr[i])], true, true);      					
	 				                       	}
			            				}else{
			            					nullData++;
			            					if(nullData>=3){
			            						//layer.msg('3分钟未加载到新交易数据', {icon: 1,time: 56000});
			            						layer.msg('3分钟内未加载到新交易数据', {
			            						    offset: 0,
			            						    shift: 6,
			            						    time: 58000
			            						});
			            					}
			            				}
			            			}
		                    	});
		                    }, 60000);
		                }
		            }
		        }
	        });
	    };
	
	    $.getJSON('${ctx}/manage/alert/getPointsData',
	    	function (data) {
		    	if(data.lastTime!=0){
		    		hasData=hasData+1;
		    	}
		    	lastTime = data.lastTime;
		    	var callback = new Array();
		    	callback = data.msg.split(";");
		    	$.each(names, function (i, name) {
			        seriesOptions[i] = {
			            name: name,
			            type: 'area',
			            data: eval(callback[i])
			        };
			
			        // As we're loading the data asynchronously, we don't know what order it will arrive. So
			        // we keep a counter and create the chart when all the data is loaded.
			        seriesCounter += 1;
			
			        if (seriesCounter === names.length) {
			        	layer.close(loading);
			            createChart();
			            console.log("hasData:"+hasData);
			        	if(hasData==0){
				            $(".highcharts-title").html("数据为空");
			        	}else{
			        		$(".highcharts-title").html("交易监控");
			        	}
			        }
			    });
		    });
}
// 定时更新总交易情况
var InterValObj; // timer变量，控制时间
var count = 0; // 起始值，60秒执行
var curCount; // 当前秒数
$(document).ready(function() {
	getAllInfo();
	getInterfaceVariance();
	curCount=count;
	InterValObj = window.setInterval(getAllInfo, 60000); //启动计时器，1分钟执行一次
	window.setInterval(getInterfaceVariance, 60000); 
});
function getAllInfo(){
	console.log("getAllInfo");
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/alert/getDynamicAllInfo?random="+Math.random(),
		dataType : "json",
		success : function(data) {
			if(data.flag){
				$("#bizSumCount").html(data.allInfoVO.allSumCount);
				$("#bizExceptionNum").html(Math.round((data.allInfoVO.allSumCount-data.allInfoVO.allExceptionNum)/data.allInfoVO.allSumCount*100)+" %");
				$("#timeout").html(data.allInfoVO.timeout+" ms");
				doView(data.allInfoVO.allSumCount,data.allInfoVO.allSumCount-data.allInfoVO.allExceptionNum,data.allInfoVO.allExceptionNum);
			}
		}
	});
}
function getInterfaceVariance(){
	console.log("getInterfaceVariance");
    var interfaceNameArray=new Array();
    var callSumArray=new Array();
    var maxTimeoutArray=new Array();
    var minTimeoutArray=new Array();
    var varianceArray=new Array();
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/alert/getInterfaceVariance?random="+Math.random(),
		dataType : "json",
		success : function(data) {
			if(data.flag){
				for (var i = 0; i < data.interfaceVarianceVOList.length; i++) {
					var name=data.interfaceVarianceVOList[i].serverName==null?data.interfaceVarianceVOList[i].interfaceName:data.interfaceVarianceVOList[i].serverName;
				    interfaceNameArray.push(name);
				    callSumArray.push(data.interfaceVarianceVOList[i].callSum);
				    maxTimeoutArray.push(data.interfaceVarianceVOList[i].maxTimeout);
				    minTimeoutArray.push(data.interfaceVarianceVOList[i].minTimeout);
				    varianceArray.push(data.interfaceVarianceVOList[i].variance);
				}
				
				$('#container').highcharts({
					title:{text:"接口访问量Top10"},
			    	chart: {
			            type: 'column'
			        },
			        title: {
			            text: '',
			            x: -20 //center
			        },
			        subtitle: {
			            text: '',
			            x: -20
			        },
			        xAxis: {
			            categories: interfaceNameArray,
			            labels: {
			                rotation : -20  //控制斜放
			            }
			        },
			        yAxis: {
			            title: {
			                text: '次数/ms'
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
			            name: '接口调用次数',
			            data: callSumArray
			        }, {
			            name: '响应最大耗时',
			            data: maxTimeoutArray
			        }, {
			            name: '响应最小耗时',
			            data: minTimeoutArray
			        }, {
			            name: '波动因子',
			            data: varianceArray
			        }]
			    });
			}
		}
	});
}

function doView(sumNum,rightNum,errNum){
    $('#view').highcharts({
        chart: {
            plotBackgroundColor: "#FBFBFF",
            plotBorderWidth: null,
            plotShadow: true
        },
        colors:[
                '#28b779',//第一个颜色
                '#f24143',//第二个颜色
              ],
        title: {
            text: "交易成功率分布",
            style: {fontSize:"19px"} 
        },
        tooltip: {
    	    pointFormat: '<b>{point.name}率</b>: {point.percentage:.1f}%'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '{point.name}: <b>{point.sum}次</b>',
                    style: {fontSize:"13px"} 
                }
            }
        },
        series: [{
            type: 'pie',
            name: '百分比',
            data: [
                {
                    name: '成功',
                    y: rightNum/sumNum*100,
                    sum:rightNum
                },
                {
                    name: '失败',
                    y: errNum/sumNum*100,
                    sum:errNum,
                    sliced: true,
                    selected: true
                },
            ]
        }]
    });
};
// 去除highcharts的logo
$(document).ready(function () {
   $('svg > text[zIndex=8]').html(" ");
});
</script>
</head>
<body>
<div class="wrap">
  <%@ include file="../../include/memTop.jsp"%>
  <div class="container clearfix">
    <div class="left">
    <c:import url="../../include/leftAlert.jsp">
		<c:param name="menuFlag" value="allInfo"/>
	</c:import>
    </div>
    <div class="right">
 		<!-- <div class="activity_logo" style="margin-top:38px; margin-bottom:44px;"><a href="${bbs}/viewthread.php?tid=1431" target="_blank"><img src="${ctx}/mem/images/activity_logo.jpg" alt=""/></a></div>-->

      <div class="tit">
        <h1>总览</h1>
      </div>
      <div class="div1 clearfix" style="font-size: 20px">
		总交易量：<span id="bizSumCount"></span>　　
		交易成功率：<span id="bizExceptionNum"></span>　　
		平均耗时：<span id="timeout"></span>
      </div>
      <div id="div_box" style="width: 650px; height: 350px; float: left;">正在查询，请稍候...</div>
        <a href="${ctx}/manage/alert/list"  style="min-width:300px;height:300px">
      		<div id="view" style="min-width:300px;height:350px;float: left;"></div>
      	</a>
      <div id="container" style="width: 950px; height: 320px; margin: 0 auto ;float: left;"></div>
    </div>
  </div>
  <%@ include file="../../include/memBottom.jsp"%>
</div>
</body>
</html>
