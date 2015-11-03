<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibhead.jsp"%>
<c:set var="menuFlag" value="${param.menuFlag}"/>
<ul class="tabs">
  <li class="on"><a href="${ctx}/manage/alert/allInfo"><div class="m1">1</div><span>场景监控</span><b class="count" style="display:none"><i id="msg_account_sum" >332</i></b></a></li>
<%--    <li><a href="${ctx}/member/acc/toAccount"><div class="m2">2</div><span>财务</span></a></li>
  <li><a href="${ctx}/member/acc/billDetail"><div class="m6">4</div><span>详单</span></a></li>
   <li><a href="${ctx}/member/count/countPandect"><div class="m5">3</div><span>统计</span><b class="count" style="display:none"><i id="msg_account_sum" ></i></b></a></li> --%>
  <li><a href="${ctx}/manage/user/toModifyPwd"><div class="m3">3</div><span>我的帐号</span></a></li>
</ul>
<div class="con">
  <div class="nr" id="selectDiv1" style="display:block;">
    <ul class="listnav">
     <li>
        <h2>控制台</h2>
        <div class="nav2"><a href="${ctx}/manage/alert/allInfo"  <c:if test="${menuFlag=='allInfo'}">class="cur"</c:if>>全局概要</a></div>
      	<div class="nav2"><a href="${ctx}/manage/alert/list"  <c:if test="${menuFlag=='leftAlertList'}">class="cur"</c:if>>业务场景监控</a></div>
        <div class="nav2"><a href="${ctx}/manage/alert/timeOutTop10"  <c:if test="${menuFlag=='timeOutTop10'}">class="cur"</c:if>>失败耗时Top10</a></div>
        <div class="nav2"><a href="${ctx}/manage/alert/listAllSceneCode"  <c:if test="${menuFlag=='listAllSceneCode'}">class="cur"</c:if>>接口统计</a></div>
        <div class="nav2"><a href="${ctx}/manage/alert/listAllBusinessCode"  <c:if test="${menuFlag=='listAllBusinessCode'}">class="cur"</c:if>>业务量统计</a></div>
         <div class="nav2"><a href="${ctx}/manage/alert/listAllOutTime"  <c:if test="${menuFlag=='listAllOutTime'}">class="cur"</c:if>>接口耗时查询</a></div>
        <div class="nav2"><a href="${ctx}/manage/alert/showErrorList"  <c:if test="${menuFlag=='showErrorList'}">class="cur"</c:if>>错误日志</a></div>
<%--       	<sctt:SecurityTag userId="${web_user.authUser.userId}" resourceCode="AlertServiceImpl.getAllSceneErr">
			<div class="nav2"><a href="">权限通过检查</a></div>
		</sctt:SecurityTag> --%>
      	<c:if test=""></c:if>
      </li>
    </ul>
  </div> 
</div>