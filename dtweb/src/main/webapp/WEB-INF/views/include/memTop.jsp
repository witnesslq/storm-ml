<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="header">
	<div class="top">
    	<div class="logo"><a href="${ctx}/manage/alert/allInfo" style="margin-left: -4px;margin-top: -10px;"><img  width="411px" height="58px" title="力捷" src="${ctx}/front/images/logo_scnx.png"/></a></div>
        <ul class="nav">
<%--             <li><a href="${ctx}/" target="_blank">首页</a></li>
            <li><a href="${ctx}/api/voice" target="_blank">产品</a></li>
            <li><a href="${ctx}/solution/productFunction" target="_blank">解决方案</a></li>
            <li><a href="${ctx}/ability/toPriceTariff" class="na" target="_blank">价格</a></li>
            <li><a href="${ctx}/wiki/main" target="_blank">文档</a></li>
        	<li><a href="${bbs}" class="na" target="_blank">社区</a></li> --%>
        </ul>
        <div class="inform">
         <a href="${ctx}/manage/user/toModifyPwd" class="account">账号管理</a>
                <span class="line">|</span>
                <a href="javascript:void(0);" class="quit" onclick="javascript:location.replace('${ctx}/logout');event.returnValue=false;">退出</a>
        </div>
	</div>
</div>
<script type="text/javascript">
function msgCount(){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/alert/errCount?random="+Math.random(),
		dataType : "json",
		success : function(data) {
			if(data.flag){
				if(data.msgCount > 0){
					$('li .count').css('display','block');
					$("#msg_account_sum").html(data.msgCount);
				}else{
					$('li .count').css('display','none');
					layer.msg('未查询到今日交易数据', {
					    offset: 0,
					    shift: 6,
					    time: 58000
					});
				}
			}
		}
	});
}
msgCount();
// 定时更新场景错误数
var InterValObj; //timer变量，控制时间
var count = 0; //起始值，60秒执行
var curCount;//当前秒数
$(document).ready(function() {
	curCount=count;
	InterValObj = window.setInterval(msgCount, 60000); //启动计时器，1分钟执行一次
});
</script>