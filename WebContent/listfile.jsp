<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":" + request.getServerPort() + path + "/";
	ArrayList<String> fileMap = (ArrayList<String>)session.getAttribute("filelist");
	//Map<String, String>fileNameMap =   (HashMap<String,String>)session.getAttribute("fileNameMap");
	//Map<String,String> filelist = HashMap<String,String>session.getAttribute("filelist");
	/*<c:forEach var="fileItem" items="$(fileNameMap)">
	
	${fileItem.value}<a href="">下载</a>
	<br>  
</c:forEach>*/
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件列表</title>
</head>
<base href="<%=basePath%>">
<body>

<%
	for(int i=0;i<fileMap.size();i++){
		%><%=fileMap.get(i) %><% 
	}
%>

</body>
</html>