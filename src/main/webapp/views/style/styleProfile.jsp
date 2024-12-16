<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/views/home/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Style Detail</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/styleDetail.js"></script>
</head>
<body>
  <div class="container">
    	<h1>${userId}의 스타일프로필</h1>
  </div>

  <%@ include file="/views/home/footer.jsp" %>
</body>
</html>
