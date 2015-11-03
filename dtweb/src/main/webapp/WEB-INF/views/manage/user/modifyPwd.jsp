<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibhead.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码-川农信大数据日志分析平台</title>
<%@ include file="../../common/member.inc"%>
<link rel="stylesheet" href="${ctx}/front/css/account_infor.css" type="text/css" />
<script type="text/javaScript" src="${ctx}/js/passwordStrength.js"></script>

<script type="text/javascript">

$(function(){
	function setHeight(){
		var nHeight = $('.container').height()+'px';
		var brsHeight = $(window).height()-176+'px';
		var mainHeight = parseInt(brsHeight)+'px';
		if(parseInt(nHeight) < parseInt(brsHeight)){
			$('.left,.left .tabs').css({'height':mainHeight});
		}else if(parseInt(nHeight) >= parseInt(brsHeight)){
			$('.left,.left .tabs').css({'height':nHeight});
		}
	}
	window.onload = function(){setHeight();};
window.onresize = function(){setHeight();};

});
function fn_focus(ele){
	var reg = /^[\s]*$/;
	ele.style.border = 'solid 1px rgb(2,185,239)';
	if(ele.value == ele.defaultValue){
		ele.value = '';
		ele.style.color = 'rgb(51,51,51)';
	}
}
function fn_blur(ele){
	var reg = /^[\s]*$/;
	ele.style.border = 'solid 1px rgb(213,213,213)';
	if( reg.test(ele.value) || ele.value == ele.defaultValue){
		ele.value = ele.defaultValue;
		ele.style.color = 'rgb(159,159,159)';
	}
}


$(document).ready(function() {
	$('#password').passwordStrength();
});
$(document).ready(function() {
	
	$("#passwordForm").validate({
		errorElement : "span",
		errorClass : "error",
		rules : {
			oldPassword : {
				required : true,
				rangelength : [ 6, 12 ],
				remote: {
					url: "${ctx}/manage/user/checkPassword",
					type: "get",
					cache: false,
					data: {
						oldPassword:function(){
							return $("#oldPassword").val();
						}
					}
				}
			},
			password : {
				required : true,
				rangelength : [ 6, 12 ]
			},
			rePassword : {
				required : true,
				equalTo : "#password"
			}
		},
		messages : {
			oldPassword : {
				required : "<b>请输入现在使用的密码</b>",
				rangelength : "<b>密码长度为6~12位</b>",
				remote : "<b>原始密码输入不正确</b>"
			},
			password : {
				required : "<b>如放弃修改请为空</b>",
				rangelength : "<b>密码长度为6~12位</b>"
			},
			rePassword : {
				required : "<b>确认密码不能为空</b>",
				equalTo : "<b>两次输入密码不一致</b>"
			}
		},
		errorPlacement : function(error, element) {
			error.appendTo(element.parent());
		}
	});
});

</script>
</head>
<body>
<div class="wrap">
  <%@ include file="../../include/memTop.jsp"%>
  <div class="container clearfix">
    <div class="left">
    <c:import url="../../include/leftUser.jsp">
		<c:param name="menuFlag" value="modifyPwd"/>
	</c:import>
    </div>
    <div class="right">
      <div class="tit">
        <h1>密码设置</h1>
      </div>
      <form action="${ctx}/manage/user/doSaveChangePwd" method="post" name="passwordForm" id="passwordForm">
      <div class="div1">
      	<dl><dt>原密码</dt><dd><input type="password" class="txt2" name="oldPassword" id="oldPassword"/><em class="ts">请输入现在使用的密码</em></dd></dl>
        <dl><dt>新密码</dt><dd><input type="password" class="txt2" name="password" id="password" /><em class="ts">如放弃修改请为空</em></dd></dl>
        <dl><dt>密码强度</dt><dd>
       	<div class="lenbox lenL" id="pwdPower"  >
       		<span class="s1">弱</span><span class="s2">中</span><span class="s3">强</span>
       	</div>
        </dd></dl>
        <dl><dt>再次输入密码</dt><dd><input type="password" name="rePassword" id="rePassword"  class="txt2" onfocus="fn_focus(this);" onblur="fn_blur(this);" /><em class="ts">请再次请入新密码</em></dd></dl>
        <dl><dt></dt><dd><input type="submit" value="确 定" class="btn_sure c2 ml0" /></dd></dl>
      </div>
      </form>
    </div>
  </div>
  <%@ include file="../../include/memBottom.jsp"%>
</div>
<c:if test="${msg == true }"> <script type="text/javascript"> alert("恭喜您，您的密码已修改成功!"); </script> </c:if>
</body>
</html>
