<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/views/home/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>TOPS</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/product.js"></script>
</head>
<body>
  <div class="container">
    <h1>상의 카테고리</h1>
    <div class="products" id="product-list">
      <c:forEach var="product" items="${productList}">
        <div class="product-card">
          <div class="image-wrapper">
            <img src="${product.imageUrl}" alt="${product.name}">
          </div>
          <div class="brand">${product.brand}</div>
          <div class="product-name">${product.name}</div>
          <div class="price">${product.initialPrice}원</div>
        </div>
      </c:forEach>
    </div>
  </div>
  <%@ include file="/views/home/footer.jsp" %>
</body>
</html>