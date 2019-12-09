<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件列表</title>
</head>
<base href="<%=basePath%>">
<body>


<c:forEach var="fileItem" items="$(fileNameMap)">
	<c:url value="/servlet/Filelist" value="downurl">
		<c:param name="filename" value="${fileItem.key}"/>
	</c:url>
	${fileItem.value}<a href="$downurl">下载</a>
	<br>
</c:forEach>
</body>
</html>