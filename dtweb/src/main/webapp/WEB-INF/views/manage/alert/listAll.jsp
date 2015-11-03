<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibhead.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>场景告警-四川农信大数据日志分析平台</title>
<%@ include file="../../common/member.inc"%>
<link rel="stylesheet" href="${ctx}/front/css/home.css" type="text/css"/>
<script src="${ctx}/js/highcharts/highcharts.js"></script>
<script type="text/javascript" src="${ctx}/js/engine.js"></script>
<script type="text/javascript" src="${ctx}/dwr/util.js"></script>
<script type="text/javascript" src="${ctx}/dwr/interface/MessagePush.js"></script>

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

});
function getNewSceneList(){
	var appName = $("#appName").val();
	var startDate = $("#startDate").val();
	var endDate =  $("#endDate").val();
	var orderField = $("#orderField").val();
	window.location.href = "${ctx}/manage/alert/list?appName="+appName+"&orderField="+orderField+"&startDate="+startDate+"&endDate="+endDate;
}
$(function () {
	<c:forEach items="${listOfSceneGroupVO }" var="sceneGroupVO">
		var sumNum = '${sceneGroupVO.sumNum}';
		var rightNum = '${sceneGroupVO.rightNum}';
		var errNum = '${sceneGroupVO.errNum}';
		var sceneGroupName = '${sceneGroupVO.sceneGroupName}';
		var sceneNameEns = '${sceneGroupVO.sceneNameEns}';
		doView(sumNum,rightNum,errNum,sceneGroupName,sceneNameEns);
	</c:forEach>
});
function doView(sumNum,rightNum,errNum,sceneGroupName,sceneNameEns){
    $('#view_'+sceneNameEns).highcharts({
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
            text: sceneGroupName,
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
function getScene(sceneNameEns,appName){
	var startDate = $("#startDate").val();
	var endDate =  $("#endDate").val();
	window.parent.location.href="${ctx}/manage/alert/getScene?sceneNameEns="+sceneNameEns+"&appName="+appName+"&startDate="+startDate+"&endDate="+endDate;
}
$(document).ready(function() {
	//加载dwr引擎，设置反推
	dwr.engine.setActiveReverseAjax(true);
	dwr.engine.setNotifyServerOnPageUnload(true);
	MessagePush.register("${web_user.authUser.userId}");
});
function alert_msg(sceneCode,message){
	alert("收到警告："+sceneCode+" 消息："+message);
}
</script>
</head>
<body>
<div class="wrap">
  <%@ include file="../../include/memTop.jsp"%>
  <div class="container clearfix">
    <div class="left">
    <c:import url="../../include/leftAlert.jsp">
		<c:param name="menuFlag" value="leftAlertList"/>
	</c:import>
    </div>
    <div class="right">
 		<!-- <div class="activity_logo" style="margin-top:38px; margin-bottom:44px;"><a href="${bbs}/viewthread.php?tid=1431" target="_blank"><img src="${ctx}/mem/images/activity_logo.jpg" alt=""/></a></div>-->

      <div class="tit">
        <h1>场景信息</h1>
        <div class="dep" style="font-size: 17px"><span>
	        <span style="height: 30px;float: left; font-size: 12px">开始：</span><input type="text" value="${startDate }"  id="startDate" name="startDate" style="width:120px; height: 22px;float: left;font-size:12px" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'}) " />
	        <span style="height: 30px;float: left; font-size: 12px">&nbsp;结束：</span><input type="text" value="${endDate}"  id="endDate" name="endDate" style="width:120px; height: 22px;float: left;font-size:12px" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'}) " />
        	<select id="appName">
        	  <option value ="">查看所有</option>
			  <option value ="EBCC" <c:if test="${appName=='EBCC' }">selected="selected"</c:if>>交互平台</option>
			  <option value ="ebcp" <c:if test="${appName=='ebcp' }">selected="selected"</c:if>>门户</option>
			  <option value="EBGB" <c:if test="${appName=='EBGB' }">selected="selected"</c:if>>标配门户</option>
			  <option value="ebmp" <c:if test="${appName=='ebmp' }">selected="selected"</c:if>>移动门户</option>
			  <option value="EBUI" <c:if test="${appName=='EBUI' }">selected="selected"</c:if>>统一认证</option>
			</select>
			<select id="orderField">
			  <option value ="sumNum" <c:if test="${orderField=='sumNum' }">selected="selected"</c:if>>总数降序</option>
			  <option value ="errNum" <c:if test="${orderField=='errNum' }">selected="selected"</c:if>>错误降序</option>
			</select>
			&nbsp;&nbsp;<em><a href="javascript:void(0)" onclick="getNewSceneList();">筛选</a></em>
		</span></div>
      </div>
      <div class="div1 clearfix">
      <c:forEach items="${listOfSceneGroupVO }" var="sceneGroupVO">
      <c:if test="${not empty sceneGroupVO.sceneNameEns}">
      	<a href="javascript:getScene('${sceneGroupVO.sceneNameEns}','${appName}')"  style="min-width:300px;height:300px">
      		<div id="view_${sceneGroupVO.sceneNameEns}" style="min-width:300px;height:300px"></div>
      	</a>
      </c:if>
      </c:forEach>
      </div>
     
    </div>
  </div>
  <%@ include file="../../include/memBottom.jsp"%>
</div>
</body>
</html>
