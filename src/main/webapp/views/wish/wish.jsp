<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/views/home/subHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관심상품 목록</title>
<style>
    .wishlist-page {
        font-family: Arial, sans-serif;
        line-height: 1.6;
    }

    .wishlist-header {
        text-align: left;
        font-size: 24px;
        font-weight: bold;
        padding: 10px 0;
        border-bottom: 2px solid #333;
        margin: 0 auto 20px auto;
        max-width: 800px; /* wishlist-container와 동일한 가로 크기 */
    }

    .wishlist-container {
        max-width: 800px;
        margin: 0 auto;
        padding: 20px;
    }

    .wishlist-item {
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 10px 0;
        position: relative;
    }

    .wishlist-item:not(:last-child) {
        border-bottom: 1px solid #ddd; /* 하단 구분선 */
    }

    .wishlist-item img {
        width: 80px; /* 이미지 너비 */
        height: 80px; /* 이미지 높이 */
        object-fit: cover;
        border-radius: 5px;
        margin-right: 15px;
    }

    .wishlist-item .details {
        flex: 1;
    }

    .wishlist-item .details h3 {
        margin: 0;
        font-size: 13px; /* 상품명 크기 */
        font-weight: bold; /* 상품명 볼드 */
    }

    .wishlist-item .details p {
        margin: 5px 0;
        color: #666;
        font-size: 13px; /* 상세설명 크기 */
        font-weight: normal; /* 상세설명 일반체 */
    }

    .wishlist-item .actions {
        display: flex;
        flex-direction: column;
        align-items: center;
        position: relative;
    }

    .wishlist-item .actions .buy-button {
        display: flex;
        align-items: center;
        padding: 10px 15px;
        background-color: #FF7F50; /* 버튼 색상 */
        color: white;
        border: none;
        border-radius: 10px;
        font-size: 14px;
        cursor: pointer;
        width: 164px; /* 버튼 너비 */
        height: 52px; /* 버튼 높이 */
        text-align: left;
        position: relative;
    }

    .wishlist-item .actions .buy-button:hover {
        background-color: #FFA07A; /* 호버 시 연한 색상 */
    }

    .wishlist-item .actions .buy-button .title {
        width: 50px; /* 구매 텍스트 영역 크기 */
        font-size: 14px;
        font-weight: bold;
        margin-right: 5px; /* 구매 텍스트와 가격 간격 */
        text-align: left;
    }

    .wishlist-item .actions .buy-button .price {
        text-align: left; /* 왼쪽 정렬 */
    }

    .wishlist-item .actions .buy-button .price .text-lookup.num {
        font-weight: bold;
        color: #fff;
        font-size: 14px; /* 폰트 크기 동일화 */
        margin-bottom: 0; /* 즉시 구매가와 간격 없애기 */
        line-height: 1; /* 줄 간격 줄이기 */
    }

    .wishlist-item .actions .buy-button .price .text-lookup.desc {
        font-size: 11px;
        color: #ddd;
        margin-top: 2px; /* 추가 간격 제거 */
        line-height: 1; /* 줄 간격 줄이기 */
    }

    .wishlist-item .actions .delete-button {
        padding: 2px 5px;
        font-size: 12px;
        background: none;
        border: none;
        color: #666;
        cursor: pointer;
        text-decoration: underline;
        position: absolute;
        right: 6px;
        top: 50%;
        margin-top: 40px;
        transform: translateY(-50%);
    }

    .wishlist-item .actions .delete-button:hover {
        color: #333;
    }

    /* 페이지네이션 스타일 */
    .pagination {
        display: flex;
        justify-content: center;
        margin-top: 20px;
    }

    .pagination a {
        margin: 0 5px;
        padding: 5px 10px;
        border: 1px solid #333;
        border-radius: 5px;
        text-decoration: none;
        color: #333;
        font-size: 14px;
    }

    .pagination a:hover {
        background-color: #333;
        color: white;
    }

    .pagination .active {
        background-color: #333;
        color: white;
        pointer-events: none;
    }
</style>
</head>
<body class="wishlist-page">
    <div class="wishlist-header">관심</div>

    <div class="wishlist-container">
        <c:choose>
            <c:when test="${not empty wishlist}">
                <c:forEach var="item" items="${wishlist}">
                    <div class="wishlist-item">
                        <img src="${item.productImage}" alt="상품 이미지">
                        <div class="details">
                            <h3>${item.productName}</h3>
                            <p>${item.productDescription}</p>
                        </div>
                        <div class="actions">
                            <button class="buy-button" onclick="goToDetail(${item.productId})">
                                <span class="title">구매</span>
                                <div class="price">
                                    <p class="text-lookup num">
                                        <c:choose>
                                            <c:when test="${item.lowestPrice > 0}">
                                                ${item.lowestPrice}원
                                            </c:when>
                                            <c:otherwise>
                                                ${item.releasePrice}원
                                            </c:otherwise>
                                        </c:choose>
                                    </p>
                                    <p class="text-lookup desc">즉시 구매가</p>
                                </div>
                            </button>
                            <button class="delete-button" onclick="removeItem(${item.productId})">삭제</button>
                        </div>
                    </div>
                </c:forEach>

                <!-- 페이지네이션 -->
                <div class="pagination">
                    <c:if test="${currentPage > 1}">
                        <a href="${pageContext.request.contextPath}/jelly?page=wish&currentPage=${currentPage - 1}">이전</a>
                    </c:if>
                    <c:forEach begin="1" end="${totalPages}" var="page">
                        <c:choose>
                            <c:when test="${page == currentPage}">
                                <a class="active">${page}</a>
                            </c:when>
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/jelly?page=wish&currentPage=${page}">${page}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${currentPage < totalPages}">
                        <a href="${pageContext.request.contextPath}/jelly?page=wish&currentPage=${currentPage + 1}">다음</a>
                    </c:if>
                </div>

            </c:when>
            <c:otherwise>
                <p>관심상품이 없습니다.</p>
            </c:otherwise>
        </c:choose>
    </div>
<%@ include file="/views/home/footer.jsp" %>
    <script>
        // 관심상품 삭제
        function removeItem(productId) {
            if (confirm("이 상품을 관심상품에서 삭제하시겠습니까?")) {
                location.href = "${pageContext.request.contextPath}/jelly?page=wish&deleteProductId=" + productId;
            }
        }

        // 상세 페이지로 이동
        function goToDetail(productId) {
            location.href = "${pageContext.request.contextPath}/jelly?page=productDetail&productId=" + productId;
        }
    </script>
</body>
</html>