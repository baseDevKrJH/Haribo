<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/views/home/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>OUTER</title>
  <!-- CSS 연결 -->
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/product.css">
</head>
<body>
  <!-- 전체 컨테이너 -->
  <div class="container">
    <!-- 카테고리 링크 -->
    <div class="categories">
      <a href="<%= request.getContextPath() %>/jelly?page=shoes" class="${currentCategory == '신발' ? 'active' : ''}">신발</a>
      <a href="<%= request.getContextPath() %>/jelly?page=outer" class="${currentCategory == '아우터' ? 'active' : ''}">아우터</a>
      <a href="<%= request.getContextPath() %>/jelly?page=tops" class="${currentCategory == '상의' ? 'active' : ''}">상의</a>
      <a href="<%= request.getContextPath() %>/jelly?page=bottoms" class="${currentCategory == '하의' ? 'active' : ''}">하의</a>
      <a href="<%= request.getContextPath() %>/jelly?page=bags" class="${currentCategory == '가방' ? 'active' : ''}">가방</a>
      <a href="<%= request.getContextPath() %>/jelly?page=hats" class="${currentCategory == '모자' ? 'active' : ''}">모자</a>
      <a href="<%= request.getContextPath() %>/jelly?page=wallets" class="${currentCategory == '지갑' ? 'active' : ''}">지갑</a>
      <a href="<%= request.getContextPath() %>/jelly?page=luxury" class="${currentCategory == '럭셔리' ? 'active' : ''}">럭셔리</a>
    </div>


    <!-- 상품 리스트 -->
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

  <!-- 푸터 -->
  <%@ include file="/views/home/footer.jsp" %>
</body>
</html>