<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibhead.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hbase错误日志记录查阅-四川农信大数据日志分析平台</title>
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
<script type="text/javascript" src="${ctx }/js/My97DatePicker/WdatePicker.js"></script>
<script src="${ctx}/js/highcharts/highcharts.js"></script>
</head>
<body>
<div class="wrap">
  <%@ include file="../../include/memTop.jsp"%>
  <div class="container clearfix">
    <div class="left">
    <c:import url="../../include/leftAlert.jsp">
		<c:param name="menuFlag" value="showErrorList"/>
	</c:import>
    </div>
    <div class="right">
		<div class="tit"><h1>Hbase错误日志记录查阅</h1></div>
             <form action="${ctx}/manage/alert/showErrorList" name="serachSubAccountForm" id="serachSubAccountForm" method="post">
	             <div class="search_line">
	                <%-- <input type="hidden" id="sceneNameEns" name="sceneCode" value="${alarmGroup.alarmGroupCode }"/> --%>
	        		<span style="height: 30px;float: left; font-size: 17px">开始时间：</span><input type="text" value="${startRow }"  id="txtStartDate" name="startRow" style="width:180px; height: 30px;float: left;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'}) " />
	        		<span style="height: 30px;float: left; font-size: 17px">结束时间：</span><input type="text" value="${endRow}"  id="txtEndDate" name="endRow" style="width:180px; height: 30px;float: left;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'}) " />
	                <span style="height: 30px;float: left; font-size: 17px">&nbsp;&nbsp;</span><input type="submit" id="serachSubAccount"  class="btn_search c2" value="查 询"/>
	             </div>
             <div class="account_list">
              
               <ul class="thlist clearfix">
                  <c:forEach items="${errorList}" var="row">
                  <c:forEach items="${row}" var="map">
                	<li>
                	   
	                 	<span title="" >${map.value}</span>
                	</li>
                	</c:forEach>
                   </c:forEach>
   				</ul>
		      
             </div>
             </form>
    </div>
  </div>
  <%@ include file="../../include/memBottom.jsp"%>
</div>
</body>
</html>
