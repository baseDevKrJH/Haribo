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

      <!-- 구매/판매 버튼 -->
      <div class="button-group">
        <a href="<%= request.getContextPath() %>/views/product/buy.jsp" class="buy-button">
          <div class="buy-text">구매</div>
          <div class="buy-info">
            <span class="price">250,000원</span>
            <span class="subtext">즉시 구매가</span>
          </div>
        </a>
        <a href="<%= request.getContextPath() %>/views/product/sell.jsp" class="sell-button">
          <div class="sell-text">판매</div>
          <div class="sell-info">
            <span class="price">250,000원</span>
            <span class="subtext">즉시 판매가</span>
          </div>
        </a>
      </div>
      
<!-- 관심저장 버튼 -->
<div class="additional-button">
  <a href="<%= request.getContextPath() %>/views/product/wish.jsp" class="btn btn_wish outlinegrey large">
    <div class="wishlist-icon">
      <img src="<%= request.getContextPath() %>/img/bookmark-icon.png" alt="Bookmark">
    </div>
    <span class="wishlist-text">관심상품</span> 
    <span class="wishCount">210</span><!-- 이건 나중에 관심상품 DB 동해서 숫자 받아올거임 -->
  </a>
</div>

<!-- 배송 정보 섹션 -->
<div class="delivery_way_wrap">
  <h3 class="delivery_title">배송 정보</h3>
  <ul class="delivery_options">
    <li class="delivery_option">
      <div class="delivery_icon">
        <img src="<%= request.getContextPath() %>/img/deliveryicon.png" alt="일반배송">
      </div>
      <div class="delivery_details">
        <p class="delivery_type">일반배송 <span class="delivery_price">3,000원</span></p>
        <p class="delivery_desc">검수 후 배송 · 5-7일 내 도착 예정</p>
      </div>
    </li>
    <li class="delivery_option">
      <div class="delivery_icon">
        <img src="<%= request.getContextPath() %>/img/storageicon.png" alt="창고보관">
      </div>
      <div class="delivery_details">
        <p class="delivery_type">창고보관 <span class="delivery_price">첫 30일 무료</span></p>
        <p class="delivery_desc">배송 없이 창고에 보관 · 빠르게 판매 가능</p>
      </div>
    </li>
  </ul>
</div>


<!-- 정품 보증 섹션 -->
<div class="authenticity-info-container">
  <ul class="authenticity-list">
    <!-- 100% 정품 보증 -->
    <li class="authenticity-item">
      <div class="authenticity-icon">
        <img src="<%= request.getContextPath() %>/img/x3.png" alt="정품 보증">
      </div>
      <div class="authenticity-details">
        <p class="authenticity-title">100% 정품 보증</p>
        <p class="authenticity-desc">Jelly에서 검수한 상품이 정품이 아닐 경우, 구매가의 3배를 보상합니다.</p>
      </div>
    </li>
    <!-- 엄격한 다중 검수 -->
    <li class="authenticity-item">
      <div class="authenticity-icon">
        <img src="<%= request.getContextPath() %>/img/check.png" alt="다중 검수">
      </div>
      <div class="authenticity-details">
        <p class="authenticity-title">엄격한 다중 검수</p>
        <p class="authenticity-desc">모든 상품은 검수센터에 도착한 후, 상품별 전문가 그룹의 체계적인 시스템을 거쳐 검수를 진행합니다.</p>
      </div>
    </li>
    <!-- 정품 인증 패키지 -->
    <li class="authenticity-item">
      <div class="authenticity-icon">
        <img src="<%= request.getContextPath() %>/img/check2.png" alt="정품 인증">
      </div>
      <div class="authenticity-details">
        <p class="authenticity-title">정품 인증 패키지</p>
        <p class="authenticity-desc">검수에 합격한 경우에 한하여 Jelly의 정품 인증 패키지가 포함된 상품이 배송됩니다.</p>
      </div>
    </li>
  </ul>
</div>


<!-- 모달 창 -->
<div class="modal-overlay" id="size-modal">
  <div class="modal-content">
    <button class="modal-close" id="modal-close">&times;</button>
    <h3 class="modal-title">사이즈</h3>
    <div class="size-grid">
      <c:forEach var="sizePrice" items="${sizePriceList}">
        <div class="size-item">
          <a href="${pageContext.request.contextPath}/views/product/buy.jsp?size=${sizePrice.size}" class="size-button">
            <!-- 사이즈 -->
            <span>${sizePrice.size}</span>
            <!-- 가격 -->
            <c:choose>
              <c:when test="${sizePrice.price == -1}">
                <span class="price bid-label">구매 입찰</span>
              </c:when>
              <c:otherwise>
                <span class="price">${sizePrice.price}원</span>
              </c:otherwise>
            </c:choose>
          </a>
        </div>
      </c:forEach>
    </div>
  </div>
</div>
    </div>
  </div>

<!-- 사이즈 정보 섹션 -->
<div class="size-info-container">
  <h3 class="size-title">사이즈 정보</h3>
  <table class="size-table">
    <thead>
      <tr>
        <th>KR</th>
        <th>US (M)</th>
        <th>US (W)</th>
        <th>UK</th>
        <th>JP</th>
        <th>EU</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>225</td>
        <td>3.5</td>
        <td>5</td>
        <td>3</td>
        <td>22.5</td>
        <td>35.5</td>
      </tr>
      <tr>
        <td>230</td>
        <td>4</td>
        <td>5.5</td>
        <td>3.5</td>
        <td>23</td>
        <td>36</td>
      </tr>
      <tr>
        <td>235</td>
        <td>4.5</td>
        <td>6</td>
        <td>4</td>
        <td>23.5</td>
        <td>36.5</td>
      </tr>
      <tr>
        <td>240</td>
        <td>5</td>
        <td>6.5</td>
        <td>4.5</td>
        <td>24</td>
        <td>37.5</td>
      </tr>
      <tr>
        <td>245</td>
        <td>5.5</td>
        <td>7</td>
        <td>5</td>
        <td>24.5</td>
        <td>38</td>
      </tr>
      <tr>
        <td>250</td>
        <td>6</td>
        <td>7.5</td>
        <td>5.5</td>
        <td>25</td>
        <td>39</td>
      </tr>
      <tr>
        <td>255</td>
        <td>6.5</td>
        <td>8</td>
        <td>6</td>
        <td>25.5</td>
        <td>40</td>
      </tr>
      <tr>
        <td>260</td>
        <td>7</td>
        <td>8.5</td>
        <td>6.5</td>
        <td>26</td>
        <td>41</td>
      </tr>
      <tr>
        <td>265</td>
        <td>7.5</td>
        <td>9</td>
        <td>7</td>
        <td>26.5</td>
        <td>42</td>
      </tr>
      <tr>
        <td>270</td>
        <td>8</td>
        <td>9.5</td>
        <td>7.5</td>
        <td>27</td>
        <td>42.5</td>
      </tr>
    </tbody>
  </table>
  <p class="size-note">
    ※ 해당 사이즈 정보는 고객 이해를 위한 참고용이며, 브랜드마다 또는 상품 카테고리마다 차이가 있을 수 있습니다. 
    정확한 내용은 실물 상품 기준으로 공식 제조사/유통사에 확인해 주시기 바랍니다.
  </p>
</div>

  <%@ include file="/views/home/footer.jsp" %>
</body>
</html>