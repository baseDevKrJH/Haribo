<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Sticky Bar</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Stickeybar.css">
<script src="${pageContext.request.contextPath}/js/Stickeybar.js"></script>
</head>
<body>
  <div class="sticky-container">
    <div class="floating_price" id="stickyBar">
      <div class="inner_box">
        <!-- 상품 이미지 및 정보 -->
        <div class="product_area">
          <div class="thumbnail">
            <img src="${product.imageUrl}" alt="${product.name}">
          </div>
          <div class="product_list_info_summary">
            <p class="product_title">${product.name}</p>
            <p class="product_subtitle">뉴발란스 2002R 그레이</p>
            <p class="product_model">ML2002RC</p>
          </div>
        </div>
        <!-- 버튼 영역 -->
        <div class="btn_area">
          <a href="#" class="btn btn_wish">관심</a>
          <button class="btn_action buy_button">
            <strong>구매</strong>
            <div class="price">115,000원</div>
          </button>
          <button class="btn_action sell_button">
            <strong>판매</strong>
            <div class="price">140,000원</div>
          </button>
        </div>
      </div>
    </div>
  </div>
</body>
</html>