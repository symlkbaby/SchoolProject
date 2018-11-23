<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
   <form action="hello" method="post">
   <table>
   <tr>
       <td> 用户名:</td>
       <td><input type="text" name="user" /></td>
   </tr>
   <tr>
        <td align="right">密 码:</td>
        <td><input type="password" name="pass" /></td>
   </tr>
   <tr>
        <td></td>
        <td ><input type = "submit" value = "提交" style="width:60px"/></td>
   </tr>
   </table>
   </form>
</body>
</html>