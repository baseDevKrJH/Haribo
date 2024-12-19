<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/productStickey.css" />
  <!-- Modal.css -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productModal.css" />
</head>
<body>
  <!-- Sticky Bar 폼 -->
  <div class="sticky-container" id="stickyBar">
    <div class="product_area">
      <!-- 상품 이미지 -->
      <div class="thumbnail">
        <img src="${product.imageUrl}" alt="${product.productName}">
      </div>
      <!-- 상품 텍스트 정보 -->
      <div class="product_list_info_summary">
        <p class="product_title">${product.productName}</p>
        <p class="product_subtitle">${product.description}</p>
        <p class="product_model">${product.modelNumber}</p>
      </div>
    </div>
    <!-- 버튼 영역 -->
    <div class="btn_area">
      <a href="#" class="btn btn_wish2">관심등록</a>
      <button class="btn_action buy_button" id="sticky-buy-btn">
        <strong>구매</strong>
        <div class="price">${formattedPrice}원</div>
      </button>
      <button class="btn_action sell_button" id="sticky-sell-btn">
        <strong>판매</strong>
        <div class="price">250,000원</div>
      </button>
    </div>
  </div>

<!-- Sticky Bar의 구매 모달 -->
<div class="modal-overlay" id="sticky-buy-modal">
  <div class="modal-content">
    <button class="modal-close" id="sticky-buy-close">&times;</button>
    <h3 class="modal-title">구매 사이즈 선택</h3>
    <div class="size-grid">
      <c:forEach var="size" items="${sizeList}">
        <div class="size-item">
          <a href="${pageContext.request.contextPath}/views/product/buy.jsp?size=${size}" class="size-button">
            <span>${size}</span>
            <c:choose>
              <c:when test="${sizeButtons[size] == '구매 입찰'}">
                <span class="price bid-label">구매 입찰</span>
              </c:when>
              <c:otherwise>
                <span class="price">${sizeButtons[size]}</span>
              </c:otherwise>
            </c:choose>
          </a>
        </div>
      </c:forEach>
    </div>
  </div>
</div>

  <!-- 판매 모달 -->
  <div class="modal-overlay" id="sticky-sell-modal">
    <div class="modal-content">
      <button class="modal-close" id="sticky-sell-close">&times;</button>
      <h3 class="modal-title">판매 사이즈 선택</h3>
      <div class="size-grid">
        <c:forTokens var="sizeVal" items="210,220,230,240,250,260,270,280,290" delims=",">
          <div class="size-item">
            <a href="${pageContext.request.contextPath}/views/product/sell.jsp?size=${sizeVal}" class="size-button">
              <span>${sizeVal}</span>
              <span class="price">판매하기</span>
            </a>
          </div>
        </c:forTokens>
      </div>
    </div>
  </div>

  <!-- stickeyBarModal.js -->
  <script src="<%= request.getContextPath() %>/js/stickeyBarModal.js"></script>
</body>
</html>