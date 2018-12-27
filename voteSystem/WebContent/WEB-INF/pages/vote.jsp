<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="sym/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>参与投票</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<script>!function(e){var c={nonSecure:"8123",secure:"8124"},t={nonSecure:"http://",secure:"https://"},r={nonSecure:"127.0.0.1",secure:"gapdebug.local.genuitec.com"},n="https:"===window.location.protocol?"secure":"nonSecure";script=e.createElement("script"),script.type="text/javascript",script.async=!0,script.src=t[n]+r[n]+":"+c[n]+"/codelive-assets/bundle.js",e.getElementsByTagName("head")[0].appendChild(script)}(document);</script></head>
<body>

<%@ include file="top.jsp" %>
<div id="vote" class="wrap">
	<h2>参与投票</h2>
	<ul class="list">
		<li>
			<h4>${subject.title}(<c:if test="${subject.number==1 }">单选</c:if><c:if test="${subject.number==2 }">多选</c:if>)</h4>
			<p class="info">共有 ${subject.optionCount}个选项，已有${subject.userCount}个网友参与了投票。</p>
			<form method="post" action="${pageContext.request.contextPath}/m/doVote.do">
			    <input type="hidden" name="subjectId" value="${subject.id }"/> 
				<ol>
				  
				     <c:forEach items="${subject.options}" var="option" varStatus="status"> 
					     <li><input <c:if test="${subject.number==2}">type="checkbox"</c:if><c:if test="${subject.number==1}">type="radio"</c:if> name="options"  value="${option.id }"/>${option.content}</li>
				     </c:forEach>
				 				 
				</ol>
				<p class="voteView"><input type="image" src="${pageContext.request.contextPath}/images/button_vote.gif" /><a href="${pageContext.request.contextPath}/m/view.do?id=${subject.id}"><img src="${pageContext.request.contextPath}/images/button_view.gif" /></a></p>
			</form>
			<div class="error">${message }</div>		
		<c:remove  var="message"  scope="session"  />
		</li>
	</ul>
</div>
<div id="footer" class="wrap">
	艾特优软件 &copy; 版权所有
</div>
</body>
</html>
         