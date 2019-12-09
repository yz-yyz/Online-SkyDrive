<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
        //获取验证消息
		request.setCharacterEncoding("UTF-8");
		String message = (String)request.getAttribute("message");
	%>
<div id="mess" style="color:red;"><%=message != null && !message.equals("")?message:"" %></div>
</body>
</html>