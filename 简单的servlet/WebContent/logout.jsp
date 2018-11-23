<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%
    session.invalidate();
	response.setHeader("refresh","2;login.jsp");
%>
<center>
<h1 style="color:red ;font-size:50px">注销成功！</h1>
<h2>两秒后自动跳转到登录页面</h2>
</center>
</body>
</html>