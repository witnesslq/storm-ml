<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibhead.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>场景详细信息-四川农信大数据日志分析平台</title>
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
		var brsHeight = $(window).height()+'px';
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
        var countArray=new Array();
        var tvarArray=new Array();
        
		<c:forEach items="${page.dataList}" var="sceneVar">
		 categorieArray.push('${sceneVar.interfaceName}');
		<c:choose>
		<c:when test="${empty sceneVar.timeOutMax}">
		allCountArray.push(parseFloat(0));
		</c:when >
		<c:otherwise>
		allCountArray.push(parseFloat('${sceneVar.timeOutMax}'));
		</c:otherwise>
		</c:choose>
		
		<c:choose>
		<c:when test="${empty sceneVar.timeOutMin}">
		successCountArray.push(parseFloat(0));
		</c:when >
		<c:otherwise>
		successCountArray.push(parseFloat('${sceneVar.timeOutMin}'));
		</c:otherwise>
		</c:choose>
		
		<c:choose>
		<c:when test="${empty sceneVar.timeOutAvg}">
		errorCountArray.push(parseFloat(0));
		</c:when >
		<c:otherwise>
		errorCountArray.push(parseFloat('${sceneVar.timeOutAvg}'));
		</c:otherwise>
		</c:choose>
		<c:choose>
		<c:when test="${empty sceneVar.count}">
		countArray.push(parseFloat(0));
		</c:when >
		<c:otherwise>
		countArray.push(parseFloat('${sceneVar.count}'));
		</c:otherwise>
		</c:choose>
		<c:choose>
		<c:when test="${empty sceneVar.tvar}">
		tvarArray.push(parseFloat(0));
		</c:when >
		<c:otherwise>
		tvarArray.push(parseFloat('${sceneVar.tvar}'));
		</c:otherwise>
		</c:choose>
		</c:forEach>
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
	            name: '响应最大耗时',
	            data: allCountArray
	        }, {
	            name: '响应最低耗时',
	            data: successCountArray
	        }, {
	            name: '响应平均耗时',
	            data: errorCountArray
	        }, {
	            name: '访问量',
	            data: countArray
	        }, {
	            name: '波动因子',
	            data: tvarArray
	        }]
	    });
	}); 
	

}); 

function viewOne(errorCodeId){
	//$.colorbox({href:"${ctx}/manage/alert/showOne?errorCodeId="+errorCodeId+"&m="+Math.random(),title:"查看详细"});
	layer.open({
		shift : 5,
		title: false,
		shade:[0.8, '#393D49'],
		shadeClose: false,
		maxmin: false,
		fix : true,  
		area: ['950px', '550px'],                     
		content : "${ctx}/manage/alert/showOneSceneCode?errorCodeId="+errorCodeId+"&m="+Math.random()
	});
}
</script>
<script src="${ctx}/js/highcharts/highcharts.js"></script>
<style type="text/css">
.t2{width:11%;}
</style>
</head>
<body>
    <div class="right">
             <form action="${ctx}/manage/alert/getScene" name="serachSubAccountForm" id="serachSubAccountForm" method="post">
	             <div class="search_line">
	                <input type="hidden" id="sceneNameEns" name="sceneNameEns" value="${alarmGroup.alarmGroupCode }"/>
	        		<input type="text" class="text" id="voip" name="voip" value="${voip }"  />
	                <input type="button" id="serachSubAccount" class="btn_search c2" value="查 询"/>
	             </div>
	              <div id="container" style="min-width: 400px; height: 320px; margin: 0 auto"></div>
             <div class="account_list">
                 <div class="th_title">
                 
                    <span class="t2">应用名称</span>
                    <span class="t2">主机名</span>
                    <span class="t2">服务名称</span>
                    <span class="t2">最大耗时</span>
                    <span class="t2">最小耗时</span>
                    <span class="t2">平均耗时</span>
                    <span class="t2">访问量</span>
                    <span class="t2">波动因子</span>
                 </div>
               <ul class="thlist clearfix">
                  <c:forEach items="${page.dataList}" var="sceneVar">
                	<li>
	                 	<%-- <span class="t1" style="height:40px;overflow: hidden" title="" >${sceneVar.sceneCode}</span> --%>
	                 	<span class="t2" style="height:40px;overflow: hidden" title="" >${sceneVar.appName}</span>
	                 	<span class="t2" style="height:40px;overflow: hidden" title="">${sceneVar.serverName}</span>
	                 	<span class="t2" style="height:40px;overflow: hidden" title="">${sceneVar.interfaceName}</span>
	                 	<span class="t2" style="height:40px;overflow: hidden" title="">${sceneVar.timeOutMax}</span>
	                 	<span class="t2" style="height:40px;overflow: hidden" title="">${sceneVar.timeOutMin}</span>
	                 	<span class="t2" style="height:40px;overflow: hidden" title="">${sceneVar.timeOutAvg}</span>
	                 	<span class="t2" style="height:40px;overflow: hidden" title="">${sceneVar.count}</span>
	                 	<span class="t2" style="height:40px;overflow: hidden" title="">${sceneVar.tvar}</span>
	                 	<%-- <span class="t6">
	                 	    <a href="javascript:void(0);" onclick="viewOne('${sceneVar.sceneCode}');" class="view_op" title="操作">查看</a>
	                 	</span> --%>
                	</li>
                   </c:forEach>
   				</ul>
		      <c:if test="${page.totalPages>1}">
                    <c:import url="../../include/page.jsp">
					<c:param name="totalPage" value="${page.totalPages}"/>
					<c:param name="curPage" value="${page.currentPage}"/>
					<c:param name="pageUrl" value="${ctx}/manage/alert/showOneSceneCode"/>
					<c:param name="pageParam" value="curPage"/>
					<c:param name="formId" value="serachSubAccountForm"/>
		        </c:import>
             </c:if>
             </div>
             </form>
    </div>
</body>
</html>
