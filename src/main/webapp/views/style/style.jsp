<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>스타일</h2>
	<a href="<%= request.getContextPath() %>/jelly?page=styleList">list
	<a href="<%= request.getContextPath() %>/jelly?page=styleDetail">detail
	<a href="<%= request.getContextPath() %>/jelly?page=styleWrite">write
	<a href="<%= request.getContextPath() %>/jelly?page=styleModify">modify
</body>
</html>