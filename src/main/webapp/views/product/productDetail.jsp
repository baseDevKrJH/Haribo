<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>상품 상세 정보</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productDetail.css">
  <script src="<%= request.getContextPath() %>/js/size.js"></script>
</head>
<body>
  <div class="product-detail-container">
    <!-- 왼쪽: 상품 이미지 -->
    <div class="product-image-section">
      <div class="product-image">
        <img src="${product.imageUrl}" alt="${product.name}">
      </div>
    </div>

    <!-- 가운데 구분선 -->
    <div class="divider"></div>

    <!-- 오른쪽: 상품 정보 -->
    <div class="product-info-section">
      <!-- 즉시 구매가 -->
      <div class="instant-buy">
        <p class="instant-buy-label">즉시 구매가</p>
        <p class="instant-buy-price">${formattedPrice}원</p>
      </div>

      <!-- 상품명 및 상세설명 -->
      <div class="product-name">${product.name}</div>
      <div class="product-description">${product.description}</div>
		
      <!-- 사이즈 버튼 -->
      <div class="size-selector">
        <button class="size-dropdown" id="size-dropdown">
          <span>모든 사이즈</span>
          <svg width="16" height="16" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
            <path d="M7 10l5 5 5-5H7z" fill="currentColor" />
          </svg>
        </button>
      </div>

      <!-- 상품 세부 정보 -->
      <dl class="product-details">
        <div class="detail">
          <div class="text">모델번호</div>
          <span>${product.modelNumber}</span>
        </div>
        <div class="detail">
          <div class="text">출시일</div>
          <span>${product.releaseDate}</span>
        </div>
        <div class="detail">
          <div class="text">발매가</div>
          <span>${formattedPrice}원</span>
        </div>
      </dl>

      <div class="button-group">
        <!-- 구매 버튼 -->
        <a href="<%= request.getContextPath() %>/views/product/buy.jsp" class="buy-button">
          <div class="buy-text">구매</div>
          <div class="buy-info">
            <span class="price">250,000원</span> <!-- 임시 텍스트 -->
            <span class="subtext">즉시 구매가</span>
          </div>
        </a>

        <!-- 판매 버튼 -->
        <a href="<%= request.getContextPath() %>/views/product/sell.jsp" class="sell-button">
          <div class="sell-text">판매</div>
          <div class="sell-info">
            <span class="price">250,000원</span> <!-- 임시 텍스트 -->
            <span class="subtext">즉시 판매가</span>
          </div>
        </a>
      </div>

      <!-- 모달 창 -->
      <div class="modal-overlay" id="size-modal">
        <div class="modal-content">
          <button class="modal-close" id="modal-close">&times;</button>
          <h3 class="modal-title">사이즈</h3>
          <div class="size-grid">
            <c:choose>
              <c:when test="${not empty sizePriceList}">
                <c:forEach var="sizePrice" items="${sizePriceList}">
                  <div class="size-item">
                    <c:choose>
                      <c:when test="${sizePrice.price != null}">
                        <!-- 가격이 있는 경우 -->
                        <a href="${pageContext.request.contextPath}/views/product/buy.jsp?size=${sizePrice.size}" 
                           class="size-button">
                          <span>${sizePrice.size}</span>
                          <span class="price">${sizePrice.price}원</span>
                        </a>
                      </c:when>
                      <c:otherwise>
                        <!-- 가격이 없는 경우 -->
                        <a href="#" class="size-button disabled">
                          <span>${sizePrice.size}</span>
                          <span class="price">구매 입찰</span>
                        </a>
                      </c:otherwise>
                    </c:choose>
                  </div>
                </c:forEach>
              </c:when>
            </c:choose>
          </div>
        </div>
      </div>
    </div>
  </div>
    <%@ include file="/views/home/footer.jsp" %>
</body>
</html>