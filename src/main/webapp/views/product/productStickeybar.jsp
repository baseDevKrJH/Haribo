<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productStickey.css" />
</head>
<body>
  <!-- Sticky Bar 폼 -->
  <div class="sticky-container" id="stickyBar">
    <div class="product_area">
      <!-- 상품 이미지 -->
      <div class="thumbnail">
        <img src="${product.imageUrl}" alt="${product.name}">
      </div>
      <!-- 상품 텍스트 정보 -->
      <div class="product_list_info_summary">
        <p class="product_title">${product.name}</p>
        <p class="product_subtitle">${product.description}</p>
        <p class="product_model">${product.modelNumber}</p>
      </div>
    </div>
    <!-- 버튼 영역 -->
    <div class="btn_area">
      <a href="#" class="btn btn_wish2">관심등록</a>
      <button class="btn_action buy_button">
        <strong>구매</strong>
        <div class="price">${formattedPrice}원</div>
      </button>
      <button class="btn_action sell_button">
        <strong>판매</strong>
        <div class="price">250,000원</div>
      </button>
    </div>
  </div>
</body>
</html>