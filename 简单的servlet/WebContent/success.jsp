<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
   <h1>欢迎<%=request.getParameter("user") %></h1>
   <%
      Set all = (Set)this.getServletContext().getAttribute("onlineuser");
      Iterator iter = all.iterator(); //迭代输出
   %>
   <h1>当前在线人数：<%=all.size() %>人</h1>
   <h1>用户列表：</h1>
   <%
      while(iter.hasNext()){
   %>
   <%=iter.next() %>
   <br />
   <%
      }
   %>
   <h1 style="color:red">亲，请记得<a href="logout.jsp">注销！</a></h1>
</body>
</html>