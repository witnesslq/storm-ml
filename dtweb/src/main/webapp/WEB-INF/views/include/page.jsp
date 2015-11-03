<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibhead.jsp"%>

<!--接收参数total,总记录数-->
<c:set var="total" value="${param.totalPage}"/>
<!--接收参数curPage,当前页数-->
<c:set var="curPage" value="${param.curPage}"/>
<!--接收参数pageUrl,要分页的页面URL-->
<c:set var="pageUrl" value="${param.pageUrl}"/>
<c:set var="paramName" value="${param.pageParam}"/>
<c:set var="formId" value="${param.formId}"/>

<script type="text/javascript">
<!--
page=function(url){
	<c:choose>
	<c:when test="${empty formId||formId==''}">
	location.href=url;
	</c:when>
    <c:otherwise>
	    var form = document.getElementById('${formId}');
		form.action=url;
		form.submit();
    </c:otherwise>
	</c:choose>
}


//-->
function toPage(pageNum){
	var url = '${pageUrl}?${paramName}=';
	page(url+pageNum);
}
</script>

<div class="page">
<%-- <a href="javascript:void(0);" onclick="page('${pageUrl}?${paramName}=1')" class="num">首页</a> --%>
<c:choose>
	<c:when test="${curPage+4 > total}">
		<c:set var="endPage" value="${total}"/>
	</c:when>
    <c:otherwise>
		<c:set var="endPage" value="${curPage+4}"/>
    </c:otherwise>
</c:choose>
<c:choose>
<c:when test="${total>5&&total-curPage<5}">                           
	<c:forEach  var="p" begin="${total-4}" end="${total}" step="1">  
	<c:if test="${curPage>1&&p==total-4}">
		<a href="javascript:void(0);" onclick="page('${pageUrl}?${paramName}=${curPage-1}')" class="more">&lt;&lt;</a>
	</c:if>
		<a href="javascript:void(0);" onclick="page('${pageUrl}?${paramName}=${p}')" class='<c:if test="${curPage ==p}"> class="active" </c:if>'> ${p} </a>
	</c:forEach> 
</c:when>
<c:otherwise>
	<c:forEach  var="p" begin="${curPage}" end="${endPage}" step="1">  
	<c:if test="${p<=total&&curPage-p<=4&&curPage-p>=0&&curPage>1}">
		<a href="javascript:void(0);" onclick="page('${pageUrl}?${paramName}=${p-1}')" class="more">&lt;&lt;</a>
	</c:if>
		<a href="javascript:void(0);" onclick="page('${pageUrl}?${paramName}=${p}')" class="num" <c:if test="${curPage ==p}"> class="active" </c:if>> ${p} </a>
	</c:forEach> 
</c:otherwise>
</c:choose>        
<c:if test="${curPage*1<total}">
	<a href="javascript:void(0);" onclick="page('${pageUrl}?${paramName}=${curPage+1}')" class="more">&gt;&gt;</a>
</c:if>
<select class="select" onchange="toPage(this.value)">
	<option value="">跳至</option>	
	<c:forEach  var="p" begin="1" end="${total}" step="1">  
		<option  <c:if test="${p == curPage }">selected="selected"</c:if> value="${p }">${p }</option>
	</c:forEach> 
</select>
<%-- <a href="javascript:void(0);" onclick="page('${pageUrl}?${paramName}=${total}')" class="num">尾页</a> --%>
<span class="totalpage">共${total}页</span>
</div>





