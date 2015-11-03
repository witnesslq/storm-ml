<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibhead.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>接口统计-四川农信大数据日志分析平台</title>
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
	
	$(function () {
		
        var categorieArray=new Array();
        var allCountArray=new Array();
        var successCountArray=new Array();
        var errorCountArray=new Array();
        
		<c:forEach items="${page.dataList}" var="sceneVar">
		categorieArray.push('${sceneVar.sceneName}');
		
		<c:choose>
		<c:when test="${empty sceneVar.allCount}">
		allCountArray.push(parseFloat(0));
		</c:when >
		<c:otherwise>
		allCountArray.push(parseFloat('${sceneVar.allCount}'));
		</c:otherwise>
		</c:choose>
		
		<c:choose>
		<c:when test="${empty sceneVar.successCount}">
		successCountArray.push(parseFloat(0));
		</c:when >
		<c:otherwise>
		successCountArray.push(parseFloat('${sceneVar.successCount}'));
		</c:otherwise>
		</c:choose>
		
		<c:choose>
		<c:when test="${empty sceneVar.errorCount}">
		errorCountArray.push(parseFloat(0));
		</c:when >
		<c:otherwise>
		errorCountArray.push(parseFloat('${sceneVar.errorCount}'));
		</c:otherwise>
		</c:choose>
		
		</c:forEach>
		//allCountArray.push(parseFloat('${sceneVar.allCount}'));
		
		
	    $('#container').highcharts({
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
	            categories: categorieArray
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
	            name: '总调用次数',
	            data: allCountArray
	        }, {
	            name: '响应码非全零',
	            data: errorCountArray
	        }, {
	            name: '响应码全零',
	            data: successCountArray
	        }]
	    });
	});
	

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
		content: "${ctx}/manage/alert/showOneSceneCode?sceneCode="+errorCodeId+"&m="+Math.random()
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
		<c:param name="menuFlag" value="listAllSceneCode"/>
	</c:import>
    </div>
    <div class="right">
		<div class="tit"><h1>接口统计</h1></div>
             <form action="${ctx}/manage/alert/listAllSceneCode" name="serachSubAccountForm" id="serachSubAccountForm" method="post">
	             <div class="search_line">
	                <%-- <input type="hidden" id="sceneNameEns" name="sceneCode" value="${alarmGroup.alarmGroupCode }"/> --%>
	        		<input type="text" class="text" id="sceneCode" name="sceneCode" value="${sceneCode }"  />
	                <input type="button" id="serachSubAccount" class="btn_search c2" value="查 询"/>
	             </div>
	              <div id="container" style="min-width: 400px; height: 320px; margin: 0 auto"></div>
             <div class="account_list">
                 <div class="th_title">
                    <span class="t1">场景码</span>
                    <span class="t2">总调用次数</span>
                    <span class="t4">执行成功数</span>
                    <span class="t4">执行失败数</span>
                    <span class="t6">操作</span>
                 </div>
               <ul class="thlist clearfix">
                  <c:forEach items="${page.dataList}" var="sceneVar">
                	<li>
	                 	<span class="t1" style="height:40px;overflow: hidden" title="" >${sceneVar.sceneName}</span>
	                 	<span class="t2" style="height:40px;overflow: hidden" title="" >${sceneVar.allCount}</span>
	                 	<span class="t2" style="height:40px;overflow: hidden" title="">${sceneVar.successCount}</span>
	                 	<span class="t2" style="height:40px;overflow: hidden" title="">${sceneVar.errorCount}</span>
	                 	<span class="t6">
	                 	    <a href="javascript:void(0);" onclick="viewOne('${sceneVar.sceneCode}');" class="view_op" title="操作">查看</a>
	                 	</span>
                	</li>
                   </c:forEach>
   				</ul>
		      <c:if test="${page.totalPages>1}">
                    <c:import url="../../include/page.jsp">
					<c:param name="totalPage" value="${page.totalPages}"/>
					<c:param name="curPage" value="${page.currentPage}"/>
					<c:param name="pageUrl" value="${ctx}/manage/alert/listAllSceneCode"/>
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
