<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibhead.jsp"%>
<c:set var="menuFlag" value="${param.menuFlag}"/>
<ul class="tabs" id="tabs">
  <li><a href="${ctx}/manage/alert/allInfo"><div class="m1">1</div><span>场景监控</span><b class="count" style="display:none"><i id="msg_account_sum" ></i></b></a></li>
<%--    <li><a href="${ctx}/member/acc/toAccount"><div class="m2">2</div><span>财务</span></a></li>
  <li><a href="${ctx}/member/acc/billDetail"><div class="m6">4</div><span>详单</span></a></li>
   <li><a href="${ctx}/member/count/countPandect"><div class="m5">3</div><span>统计</span><b class="count" style="display:none"><i id="msg_account_sum" ></i></b></a></li> --%>
  <li class="on"><a href="${ctx}/manage/user/toModifyPwd"><div class="m3">3</div><span>我的帐号</span></a></li>
</ul>
<div class="con">
  <div class="nr" id="selectDiv1" style="display:block;">
    <ul class="listnav">  
     <li>
     	<h2>帐号管理</h2>
      	<div class="nav2">
      		<%-- <a href="${ctx}/manage/user/getOne" <c:if test="${menuFlag=='userInfo'}">class="cur"</c:if>>基础资料</a> --%>
      		<a href="${ctx}/manage/user/toModifyPwd" <c:if test="${menuFlag=='modifyPwd'}">class="cur"</c:if>>密码设置</a>
      	</div>
      </li>
<%--       <li>
      	<h2>消息通知</h2>
        <div class="nav2">
        	<a href="${ctx }/manage/user/msgList"  <c:if test="${menuFlag=='msgInfo'}">class="cur"</c:if>><span>系统消息<em id="msg_msg_sum">(0)</em></span></a>
        </div>
      </li> --%>
      </ul>
   </div>
</div>