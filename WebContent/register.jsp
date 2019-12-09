<%@ page language="java"  pageEncoding="UTF-8"%>
<html>
	<head>
		<title>用户注册</title>
		<script type="text/javascript" src="js/news.js"></script>
	</head>
	<%
                //获取验证消息
		request.setCharacterEncoding("UTF-8");
		String message = (String)request.getAttribute("message");
	%>
	<body>
	<div id="mess" style="color:red;"><%=message != null && !message.equals("")?message:"" %></div>
	<form name="form1" method="post" action="ZhuCe" >
		<table>
			<tr>	
				<td>用户名：</td>
				<td> <input type="text" name="userName" id="userName" value='<%=request.getParameter("userName")==null?"":request.getParameter("userName")%>' ></td>
			</tr>
	        <tr>	
		 		<td>输入登录密码：</td>
				<td><input type="password" name="pwd" id="pwd" value='<%=request.getParameter("pwd")==null?"":request.getParameter("pwd")%>'></td>
			</tr>
			<tr>	
				<td>再次输入密码：</td>
				<td><input type="password" name="validatepwd" id="ckpwd" value='<%=request.getParameter("validatepwd")==null?"":request.getParameter("validatepwd")%>' ></td>
			</tr>
			<tr>	
				<td colspan="2"><input type="submit" value="注册"></td>
			</tr>
		</table>
	</form>
	</body>
</html>
