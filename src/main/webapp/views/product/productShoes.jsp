<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/views/home/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Product Shoes</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/product.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="<%= request.getContextPath() %>/js/product.js"></script>
</head>
<body>
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
      <div class="filter-icon">
        <img src="<%= request.getContextPath() %>/img/filter.png" alt="Filter" id="filter-btn">
      </div>
    </div>

<!-- 상품 리스트 -->
<div class="products" id="product-list">
  <c:forEach var="product" items="${productList}">
    <a href="${pageContext.request.contextPath}/jelly?page=productDetail&productId=${product.productId}" class="product-card">
      <div class="image-wrapper">
        <img src="${product.imageUrl}" alt="${product.name}">
      </div>
      <div class="brand">${product.brand}</div>
      <div class="product-name">${product.name}</div>
      <div class="price">${product.initialPrice}원</div>
    </a>
  </c:forEach>
</div>
<!--    <div id="loading-spinner" style="text-align: center; margin-top: 20px; display: none;">
      <p>로딩 중...</p>
    </div> -->
  </div>

  <!-- 필터 슬라이드 메뉴 -->
  <div class="filter-menu" id="filter-menu">
    <button class="close-btn" id="close-btn">&times;</button>
    <h2>필터</h2>
    <div class="filter-options">
      <h3>브랜드</h3>
      <label><input type="checkbox" name="brand" value="NIKE"> NIKE</label>
      <label><input type="checkbox" name="brand" value="ADIDAS"> ADIDAS</label>
      <label><input type="checkbox" name="brand" value="PUMA"> PUMA</label>

      <h3>사이즈</h3>
      <label><input type="checkbox" name="size" value="210"> 210</label>
      <label><input type="checkbox" name="size" value="220"> 220</label>
      <label><input type="checkbox" name="size" value="230"> 230</label>
      <label><input type="checkbox" name="size" value="240"> 240</label>
      <label><input type="checkbox" name="size" value="250"> 250</label>
      <label><input type="checkbox" name="size" value="260"> 260</label>
      <label><input type="checkbox" name="size" value="270"> 270</label>
      <label><input type="checkbox" name="size" value="280"> 280</label>
      <label><input type="checkbox" name="size" value="290"> 290</label>

      <h3>가격</h3>
      <label><input type="radio" name="price" value="50000"> 5만원 이하</label>
      <label><input type="radio" name="price" value="100000"> 5~10만원</label>
      <label><input type="radio" name="price" value="200000"> 10~20만원</label>
      <label><input type="radio" name="price" value="300000"> 20만원 이상</label>
    </div>
    <button class="apply-btn" id="apply-filter">적용</button>
    <button class="reset-btn" id="reset-filter">초기화</button>
  </div>
  <%@ include file="/views/home/footer.jsp" %>
</body>
</html>