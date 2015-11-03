<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibhead.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>接口耗时查询-四川农信大数据日志分析平台</title>
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
		content: "${ctx}/manage/alert/showInfo?id="+errorCodeId+"&yyyyMMdd=${yyyyMMdd}&m="+Math.random()
	});
}
</script>
<script type="text/javascript" src="${ctx }/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<div class="wrap">
  <%@ include file="../../include/memTop.jsp"%>
  <div class="container clearfix">
    <div class="left">
    <c:import url="../../include/leftAlert.jsp">
		<c:param name="menuFlag" value="listAllOutTime"/>
	</c:import>
    </div>
    <div class="right">
		<div class="tit"><h1>接口耗时查询</h1></div>
             <form action="${ctx}/manage/alert/listAllOutTime" name="serachSubAccountForm" id="serachSubAccountForm" method="post">
	             <div class="search_line" style="margin-top: 0px;padding-bottom: 0px;">
	                <span style="height: 30px;float: left; font-size: 17px">开始时间：</span><input type="text" value="${yyyyMMdd}"  id="yyyyMMdd" name="yyyyMMdd" style="width:100px; height:30px;float: left;" onclick="WdatePicker({dateFmt:'yyyyMMdd'}) " />
	                <%-- <input type="hidden" id="sceneNameEns" name="sceneCode" value="${alarmGroup.alarmGroupCode }"/> --%>
	        		<span style="height: 30px;float: left; font-size: 17px"> 应用 ：</span> <input type="text" style="width:100px; height: 30px;float: left;" class="text" id="appName" name="appName" value="${appName}"  /> 
	        		<span style="height: 30px;float: left; font-size: 17px"> 请求序列号：</span> <input type="text" style="width:100px; height: 30px;float: left;" class="text" id="respCode" name="respCode" value="${respCode}"  />
	        		<span style="height: 30px;float: left; font-size: 17px"> 业务跟踪号：</span> <input type="text" style="width:100px; height: 30px;float: left;" class="text" id="bizTrackNo" name="bizTrackNo" value="${bizTrackNo}"  />
	           
	             </div>
	             <div class="search_line" style="margin-top: 0px;padding-bottom: 0px;">
	            
	                <%-- <input type="hidden" id="sceneNameEns" name="sceneCode" value="${alarmGroup.alarmGroupCode }"/> --%>
	        		<span style="height: 30px;float: left; font-size: 17px"> 服务名称 :</span><input type="text" style="width:100px; height: 30px;float: left;" class="text" id="interfaceName" name="interfaceName" value="${interfaceName}"  /> 
	        		<span style="height: 30px;float: left; font-size: 17px"> 服务器：</span> <input type="text" style="width:100px; height: 30px;float: left;" class="text" id="serverName" name="serverName" value="${serverName}"  />
	        		<span style="height: 30px;float: left; font-size: 17px"> 场景码：</span> <input type="text" style="width:100px; height: 30px;float: left;" class="text" id="sceneCode" name="sceneCode" value="${sceneCode}"  />
	                <input type="submit" style="width: 80px;" id="serachSubAccount" class="btn_search c2" value="查 询"/>
	             </div>
	             
             <div class="account_list">
                 <div class="th_title">
                    <span class="t1" style="width:50px;">应用</span>
                    <span class="t2" style="width:180px;">服务器</span>
                    <span class="t4" style="width:150px;">服务名称</span>
                    <span class="t4" style="width:150px;">场景码</span>
                    <span class="t4" style="width:100px;">请求序列号</span>
                    <span class="t4" style="width:100px;">业务跟踪号</span>
                    <span class="t4"style="width:100px;">响应码</span>
                    <span class="t4"style="width:50px;">耗时</span>
                    <span class="t6"style="width:50px;">操作</span>
                 </div>
               <ul class="thlist clearfix">
                  <c:forEach items="${page.dataList}" var="sceneVar">
                	<li>
	                 	<span class="t1" style="height:40px;overflow: hidden;width:50px;" title="" >${sceneVar.appName}</span>
	                 	<span class="t2" style="height:40px;overflow: hidden;width:180px;" title="" >${sceneVar.serverName}</span>
	                 	<span class="t2" style="height:40px;overflow: hidden;width:150px;" title="">${sceneVar.interfaceName}</span>
	                 	<span class="t2" style="height:40px;overflow: hidden;width:150px;" title="">${sceneVar.sceneCode}</span>
	                 	<span class="t2" style="height:40px;overflow: hidden;width:100px;" title="">${sceneVar.trackNo}</span>
	                 	<span class="t2" style="height:40px;overflow: hidden;width:100px;" title="">${sceneVar.bizTrackNo}</span>
	                 	<span class="t2" style="height:40px;overflow: hidden;width:100px;" title="">${sceneVar.respCode}</span>
	                 	<span class="t2" style="height:40px;overflow: hidden;width:50px;" title="">${sceneVar.timeout}</span>
	                 	<span class="t6" style="height:40px;overflow: hidden;width:50px;" title="">
	                 	    <a href="javascript:void(0);" onclick="viewOne('${sceneVar.id}');" class="view_op" title="操作">查看</a>
	                 	</span>
                	</li>
                   </c:forEach>
   				</ul>
		      <c:if test="${page.totalPages>1}">
                    <c:import url="../../include/page.jsp">
					<c:param name="totalPage" value="${page.totalPages}"/>
					<c:param name="curPage" value="${page.currentPage}"/>
					<c:param name="pageUrl" value="${ctx}/manage/alert/listAllOutTime"/>
					<c:param name="pageParam" value="curPage"/>
					<c:param name="formId" value="serachSubAccountForm"/>
		        </c:import>
             </c:if>
             </div>
             </form>
    </div>
  </div>
  <%@ include file="../../include/memBottom.jsp"%>
</div>
</body>
</html>
