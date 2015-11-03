<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibhead.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>失败接口详细-四川农信大数据日志分析平台</title>
<%@ include file="../../common/member.inc"%>
<link rel="stylesheet" href="${ctx}/front/css/home.css" type="text/css"/>
<link rel="stylesheet" href="${ctx}/front/css/sms_template.css" type="text/css" />
<link href="${ctx}/front/css/default.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/front/css/def.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/front/css/liebiao.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/layer/layer.min.js"></script> 
<style type="text/css">
.t1{width:6%; 
}
.t2{width:16%; 
}
.t3{width:10%; 
}
.t4{width:10%;
}
.t5{width:10%;
}
.t6{width:10%;
}
.t7{width:10%; 
}
.t8{width:10%; 
}
.t9{width:4%;
}
</style>
<script type="text/javascript">
var appIds ;
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
	window.onresize = function(){setHeight();};
	
	
	$("#serachSubAccount").click(function(){
		serachSubAccountForm.submit();
	});

}); 
var isIgnore = 0;
function isIgnoreFun(a){
	isIgnore = a;
}
function viewOne(errorCodeId){
	//$.colorbox({href:"${ctx}/manage/alert/showOne?errorCodeId="+errorCodeId+"&m="+Math.random(),title:"查看详细"});
	isIgnore=0;
	layer.open({
		shift : 5,
		type : 2,
		title: false,
		shade:[0.8, '#393D49'],
		shadeClose: false,
		maxmin: false,
		fix : true,  
		area: ['950px', '550px'],                     
		content: "${ctx}/manage/alert/showOne?errorCodeId="+errorCodeId+"&m="+Math.random(),
		end: function(){
			if(isIgnore==1){
				layer.msg('正在重新加载...');
				serachSubAccountForm.submit();
			}
		}
	});
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
		<div class="tit"><h1>【${listScene[0].sceneGroup }】接口调用失败详细</h1></div>
             <form action="${ctx}/manage/alert/getScene" name="serachSubAccountForm" id="serachSubAccountForm" method="post">
	             <div class="search_line">
	                <input type="hidden" id="sceneNameEns" name="sceneNameEns" value="${sceneNameEns }"/>
	        		<input type="hidden" id="appName" name="appName" value="${appName }"/>
	        		<input type="hidden" id="curPage" name="curPage" value="${page.currentPage}"/>
	        		<input type="hidden" value="${startDate }"  id="startDate" name="startDate"/>
	        		<input type="hidden" value="${endDate}"  id="endDate" name="endDate"/>
	        		    <span style="float: left;font-size: 17px">场景选择：</span>
	        		    <select id="sceneCode" name="sceneCode" style="width:180px; height: 30px;float: left;">
						  <option value ="">所有场景</option>
						  <c:forEach items="${listScene}" var="scene">
							  <option value ="${scene.sceneCode }" <c:if test="${sceneCode==scene.sceneCode }">selected="selected"</c:if>>${scene.sceneName }</option>
						  </c:forEach>

						</select>
	                <span style="float: left;">&nbsp;&nbsp;</span><input type="button" id="serachSubAccount" class="btn_search c2" value="查 询"/>
	             </div>
             
             <div class="account_list">
                 <div class="th_title">
                 	<span class="t1">时间</span>
                 	<span class="t2">业务追踪号</span>
                 	<span class="t3">场景名</span>
                    <span class="t4">交易名称</span>
                    <span class="t5">服务名</span>
                    <span class="t6">错误描述</span>
                    <span class="t7">业务描述</span>
                    <span class="t8">响应描述</span>
                    <span class="t9">操作</span>
                 </div>
               <ul class="thlist clearfix">
                  <c:forEach items="${page.dataList}" var="errorCode">
                	<li>
                		<span class="t1" style="height:40px;overflow: hidden"><fmt:formatDate value="${errorCode.recordTime}" pattern="HH:mm:ss"/></span>
                		<span class="t2" style="height:40px;overflow: hidden" title="${fn:length(errorCode.trackNo)<1?'无':errorCode.trackNo}">${fn:length(errorCode.trackNo)<1?'无':errorCode.trackNo}</span>
	                 	<span class="t3" style="height:40px;overflow: hidden" title="${fn:length(errorCode.sceneName)<1?'无':errorCode.sceneName}">${fn:length(errorCode.sceneName)<1?'无':errorCode.sceneName}</span>
	                 	<span class="t4" style="height:40px;overflow: hidden" title="${fn:length(errorCode.tradingName)<1?'无':errorCode.tradingName}">${fn:length(errorCode.tradingName)<1?'无':errorCode.tradingName}</span>
	                 	<span class="t5" style="height:40px;overflow: hidden" title="${fn:length(errorCode.serverName)<1?'无':errorCode.serverName}">${fn:length(errorCode.serverName)<1?'无':errorCode.serverName}</span>
	                 	<span class="t6" style="height:40px;overflow: hidden" title="${fn:length(errorCode.errorDes)<1?'无':errorCode.errorDes}">${fn:length(errorCode.errorDes)<1?'无':errorCode.errorDes}</span>
	                 	<span class="t7" style="height:40px;overflow: hidden" title="${fn:length(errorCode.businessDes)<1?'无':errorCode.businessDes}">${fn:length(errorCode.businessDes)<1?'无':errorCode.businessDes}</span>
	                 	<span class="t8" style="height:40px;overflow: hidden" title="${fn:length(errorCode.responseMsg)<1?'无':errorCode.responseMsg}">${fn:length(errorCode.responseMsg)<1?'无':errorCode.responseMsg}</span>
	                 	<span class="t9">
	                 	    <a href="javascript:void(0);" onclick="viewOne('${errorCode.id}');" class="view_op" title="操作">查看</a>
	                 	</span>
                	</li>
                   </c:forEach>
   				</ul>
		      <c:if test="${page.totalPages>1}">
                    <c:import url="../../include/page.jsp">
					<c:param name="totalPage" value="${page.totalPages}"/>
					<c:param name="curPage" value="${page.currentPage}"/>
					<c:param name="pageUrl" value="${ctx}/manage/alert/getScene"/>
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
