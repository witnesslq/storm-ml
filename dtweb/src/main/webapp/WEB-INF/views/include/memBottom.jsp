<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy");
		java.util.Date currentTime = new java.util.Date();//得到当前系统时间
		String year = formatter.format(currentTime); //将日期时间格局化
		%>
<div class="footer">
	<p><span class="copy_con">© Copyright <%=year%> 四川农信大数据日志分析平台&nbsp;&nbsp;蜀ICP备06888888-7&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;All Rights Reserved 成都迪思普纳软件有限责任公司</span>
		<span class="contact">
			<em class="con_info">在线咨询：</em>
			<!-- <em class="qq"><script charset="utf-8" type="text/javascript" src="http://wpa.b.qq.com/cgi/wpa.php?key=XzkzODA1MDYzMV8yMDY2MjNfNDAwNjEwMTAxOV8"></script></em> -->
			<em class="con_info">客服电话：</em><i class="tel">400-400-8000</i>
		</span>
	</p>
    <p class="p1"><em></em></p>
</div>