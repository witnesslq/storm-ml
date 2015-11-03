<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./common/taglibhead.jsp" %>
<%@ include file="./common/resource.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录-川农信大数据日志分析平台</title>
<script type="text/javascript" src="${ctx }/js/jquery-1.10.2.min.js"></script>
<link rel="stylesheet" href="${ctx }/front/css/login.css" type="text/css"/>
<script type="text/javascript">
function refreshRandCode() {
	$('#randCodeImg').hide().attr('src','${ctx}/jcaptcha?' + Math.floor(Math.random()*100)).fadeIn();
}
$(document).ready(function() {
	$("#loginBtn").click(function() {
		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath }/loginSubmit",
			data:{
				loginName:$("#loginName").val(),
				loginPwd:$("#loginPwd").val(),
				randCode:$("#loginCode").val()
			},
			dataType : "json",
			cache: false,
			success : function(data) {
				if(data.flag){
					window.parent.location.href = "${pageContext.request.contextPath }"+data.url;
				}else{
					refreshRandCode();
					alert(data.msg);
				}
			}
		});
	});
	//回车提交
 	 $(".login_div_mid").keydown(function(e){ 
        var curKey = e.which; 
        if(curKey == 13){ 
            $("#loginBtn").click();
        }
    }); 
    var msg="${msg}";
    if(msg.length>0){
    	alert(msg);
    }
    $("#way").change(function(){
    	if(this.value==1){
    		$("#tel").show();
    	}else{
    		$("#tel").hide();
    	}
    });
});
</script>
</head>
<body>
	<div class="login_div_box">
    	<div class="login_div_top"><img src="${ctx}/front/images/login_box_topbg.png"/></div>
        <div class="login_div_mid">
        	<div class="titDiv"><span class="t1" style="margin-left: -16px;margin-top: -9px;"><img src="${ctx}/front/images/logo_scnx.png" width="384px" height="55px"/></span><!-- <span class="t2">呼叫中心管理系统登录</span> --></div>
            <div class="rows"><span class="base_tit">帐号</span><input name="loginName" id="loginName" type="text" class="input_text" /><div class="div_tishi"><!-- <span class="error">用户名不能为空</span> --></div></div>
            <div class="rows"><span class="base_tit">密码</span><input name="loginPwd" id="loginPwd" type="password" class="input_text" /><div class="div_tishi"><!--<span class="error">密码不能为空</span>--></div></div>
            <div class="rows"><span class="base_tit">验证码</span>
            	<input name="loginCode" id="loginCode" type="text" class="input_text bgshort" />
            	<div class="check_box">
            		<img  src="${ctx}/jcaptcha" id="randCodeImg" name="randCodeImg" onclick="refreshRandCode();" height="37"/>
            	</div>
            </div>
            <p class="btns"><input id="loginBtn" type="submit" class="btn_submit" value="登&nbsp;&nbsp;&nbsp;&nbsp;录" /></p>
        </div>
        <div class="login_div_bot"><img src="${ctx}/front/images/login_box_botbg.png" /></div>
    </div>
</body>
</html>