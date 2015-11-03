<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibhead.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>场景告警弹出框-四川农信大数据日志分析平台</title>
<%-- <%@ include file="../../common/member.inc"%> --%>
<link rel="stylesheet" href="${ctx}/front/css/home.css" type="text/css"/>
<script type="text/javascript" src="${ctx}/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/layer.min.js"></script> 
<script type="text/javascript">
var InterValObj; //timer变量，控制时间
var count = 0; //起始值，1/4秒执行
var curCount;//当前秒数
$(document).ready(function() {
	curCount=count;
	//InterValObj = window.setInterval(setRemainTime, 250); //启动计时器，1/4秒执行一次
});
function setRemainTime(){
	var str = ".";
	curCount++;
	if(curCount%4==0){
		str=".";
	}
	if(curCount%4==1){
		str="..";
	}
	if(curCount%4==2){
		str="...";
	}
	if(curCount%4==3){
		str="....";
	}
	$(".jschange").html(str);
	if(curCount==21){
		$("#jschange_id_1").html("121");
		$("#jschange_id_1").removeClass("jschange"); 
	}
	if(curCount==22){
		$("#jschange_id_2").html("78");
		$("#jschange_id_2").removeClass("jschange");
		
		$("#jschange_id_3").html("34");
		$("#jschange_id_3").removeClass("jschange"); 
	}
	if(curCount==28){
		$("#jschange_id_4").html("78");
		$("#jschange_id_4").removeClass("jschange");
		
		$("#jschange_id_5").html("22");
		$("#jschange_id_5").removeClass("jschange"); 
		
		$("#jschange_id_6").html("56");
		$("#jschange_id_6").removeClass("jschange"); 
		
		$("#jschange_id_7").html("50");
		$("#jschange_id_7").removeClass("jschange");
		
		$("#jschange_id_8").html("正常");
		$("#jschange_id_8").removeClass("jschange"); 
		
		$("#jschange_id_9").html("正常");
		$("#jschange_id_9").removeClass("jschange"); 
	}
	if(curCount>30&&curCount%4==0){
		$("#jschange_id_3").html(Math.floor(Math.random()*20+30));
		$("#jschange_id_4").html(Math.floor(Math.random()*5+70));
		$("#jschange_id_5").html(Math.floor(Math.random()*10));
		$("#jschange_id_6").html(Math.floor(Math.random()*70+150));
		
	}
}
var respCode = "${sceneErrVO.respCode}"; // 响应码
var bizTrackNo = "${sceneErrVO.bizTrackNo }"; // 业务追踪号
var errorId = "${sceneErrVO.id}"; // 
$(document).ready(function() {
	// 获取错误码处理意见
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/alert/getErrorCodeTips",
		data:{respCode:respCode},
		dataType : "json",
		success : function(data) {
			if(data.flag==true){
				$("#errTips").html(data.errorCodeTips.errorTips);
				$("#addErrTips").val(data.errorCodeTips.errorTips);
			}else{
				$("#errTips").html("知识库里暂无内容，欢迎对此错误码进行解决方案的添加");
			}
		}
	});
	
	// 业务链分析
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/alert/getAnalyseListByBizTrackNo",
		data : {bizTrackNo:bizTrackNo},
		dataType : "json",
		success : function(data){
			var tagNode="";
			if(data.flag){
				tagNode = "总耗时:"+data.timeOut+"ms";
				for (var i = 0; i < data.analyseList.length; i++) {
					tagNode = tagNode +"</br>调用接口:"+ data.analyseList[i].interfaceName +" 状态:"+data.analyseList[i].respCode+" 耗时:"+data.analyseList[i].timeOut+"ms";
				}
				$("#tagNode").html(tagNode);
			}else{
				tagNode="未加载到数据";
				$("#tagNode").html(tagNode);
			}
		}
	});
	
	// 加载HBase里面的日志
	var resRowKey="${sceneErrVO.resRowKey}";
	var reqRowKey="${sceneErrVO.reqRowKey}";
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/alert/getLogs",
		data:{resRowKey:resRowKey,reqRowKey:reqRowKey},
		dataType : "json",
		success : function(data) {
			$("#reqRowValue").html(data.reqRowValue);
			$("#resRowValue").html(data.resRowValue);
		}
	});
});

// 提交解决方法到知识库
function addErrTips(){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/alert/addErrTips",
		data:{respCode:respCode,errorTips:$("#addErrTips").val()},
		dataType : "json",
		success : function(data) {
			if(data.flag==true){
				layer.alert('添加成功', {icon: 6});
				$("#errTips").html($("#addErrTips").val());
			}else{
				layer.alert('添加失败', {icon: 6});
			}
		}
	});
}
// 忽略错误
function ignoreError(){
	layer.confirm('真的忽略次错误吗？', {
	    btn: ['确定','取消'] //按钮
	}, function(){
		parent.isIgnoreFun(1);//子父页面传值
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/alert/ignoreError",
			data:{errorId:errorId},
			dataType : "json",
			success : function(data) {
				if(data.flag==true){
					layer.msg('忽略成功', {icon: 1,time: 1200});
				}
			}
		});
	});

}
</script>
</head>
<body>
<div style="float: left; width: 220px;"><font color="#26b77b" style="font-size: 20px"><b>场景信息简介</b></font>
    <div class="pop_con_div">
    	<p><font color="#26b77b">场景名称：</font><span>${sceneErrVO.sceneName }</span></p>
		<p><font color="#26b77b">交易名称：</font><span>${sceneErrVO.tradingName }</span></p>
		<p><font color="#26b77b">主机：</font><span>${sceneErrVO.hostName }</span></p>
		<p><font color="#26b77b">主机名：</font><span>${fn:split(sceneErrVO.hostName, '.')[0]}</span></p>
		<p><font color="#26b77b">异常返回码：</font><span>${sceneErrVO.respCode }</span></p>
		<p><font color="#26b77b">返回码描述：</font><span>${sceneErrVO.errorDes }</span></p>
		&nbsp;&nbsp;<input type="button" onclick="ignoreError();" value="忽略该错误"/>
	</div>
</div>
<div style='float: left; width: 430px;border-right:2px solid #eee;border-left:2px solid #eee;margin-left: 5px;margin-right: 5px;padding: 2px'>
  <font color="#26b77b" style="font-size: 20px"><b>主机连接情况</b></font>
  <div id='pop_reset' class="popbox1">
  <form id="restTokenForm" name="restTokenForm" action="${ctx}/member/restToken" method="post">
    <div class="pop_con_div">
		<p>【${fn:split(sceneErrVO.hostName, '.')[0]}】当前网关延时： <span class="jschange" id="jschange_id_1">正常</span> </p>
		<p>【${fn:split(sceneErrVO.hostName, '.')[0]}】日志平台延时： <span class="jschange" id="jschange_id_2">正常</span></p>
		<p>【${fn:split(sceneErrVO.hostName, '.')[0]}】当前CPU情况 ：<span class="jschange" id="jschange_id_3">正常</span> </p>
		<p>【${fn:split(sceneErrVO.hostName, '.')[0]}】内存实时情况 ：<span class="jschange" id="jschange_id_4">正常</span> </p>
		<p>【${fn:split(sceneErrVO.hostName, '.')[0]}】IOPS实时情况 ：<span class="jschange" id="jschange_id_5">正常</span> </p>
		<p>【${fn:split(sceneErrVO.hostName, '.')[0]}】网卡流量情况： <span class="jschange" id="jschange_id_6">正常</span> </p>
		<p>【${fn:split(sceneErrVO.hostName, '.')[0]}】存储容量信息：<span class="jschange" id="jschange_id_7">正常</span> </p>
		<p>【${fn:split(sceneErrVO.hostName, '.')[0]}】服务检测结果：<span class="jschange" id="jschange_id_8">正常</span></p>
		<p>【${fn:split(sceneErrVO.hostName, '.')[0]}】交易经过的服务器ip及延时 ${sceneErrVO.timeOut } ms <span class="jschange" id="jschange_id_9"> ${sceneErrVO.timeOut>30000?"过长":"正常" }</span></p>
		
        <p><font color="#26b77b">负载均衡状态：</font><em>未获取数据</em></p>
        <p><font color="#26b77b">中间件状态：</font><em>未获取数据</em></p> 
		<p><font color="#26b77b">业务链分析：</font><em>业务码：${sceneErrVO.bizTrackNo } <em id="tagNode">正在分析加载中...</em></em></p>
    </div>
    </form>
  </div>
</div>
<div style="float: left; width: 220px"><font color="#26b77b" style="font-size: 20px"><b>知识库</b></font>
	<p><font color="#26b77b">常见解决方法:</font></p>
	<p id="errTips">加载中...</p>
	<p><font color="#26b77b">更新知识库：</font></p>
	<p><textarea id="addErrTips" style="width: 210px; height: 221px;"></textarea></p>
	<p><input type="button" onclick="addErrTips();" value="提交"/>
	</p>
</div>
<div style="float: left; width: 920px;border-top:2px solid #eee"><font color="#26b77b" style="font-size: 20px"><b>故障登记及处理情况</b></font>
	<p><font color="#26b77b">故障联系人：</font><em>未登记</em></p>
    <p><font color="#26b77b">故障原因：</font><em>${sceneErrVO.errorDes }</em></p>
    <p><font color="#26b77b">故障解决情况：</font><em>未登记</em></p><br>
</div>

<div style="float: left; width: 920px;border-top:2px solid #eee"><font color="#26b77b" style="font-size: 20px"><b>错误日志详细信息</b></font>
	<p><font color="#26b77b">请求日志：</font><span id="reqRowValue">正在从HBase加载...</span></p>
    <p><font color="#26b77b">响应日志：</font><span id="resRowValue">正在从HBase加载...</span></p>
</div>

</body>
</html>
