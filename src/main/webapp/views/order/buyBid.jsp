<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/views/home/subHeader.jsp" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>즉시 판매하기</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/orderBid.css">
<!-- jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

</head>
<body>
	<div class="order-container">
		<!-- 헤더 -->
		<h2>구매 입찰</h2>

		<div class="order-content">
			<!-- 상품 정보 섹션 -->
			<div class="order-info">
				<img src="${product.imageUrl }" alt="상품 이미지" />
				<div class="order-details">
					<span class="span-bold">${product.modelNumber }</span> <span>${product. productName}</span>
					<span class="span-gray">${product.description }</span> <span
						class="span-bold">${size }</span>
				</div>
			</div>

			<!-- 가격 정보 섹션 -->
			<div class="price-detail">
				<div class="orderNow">
					<div>
						<span class="span-bold">즉시 구매가</span> <span>-</span>
					</div>
					<div>
						<c:choose>
							<c:when test="${price != 0}">
								<span class="span-bold">즉시 판매가</span>
								<span>${formattedPrice }원</span>
							</c:when>
							<c:otherwise>
								<span class="span-bold">즉시 판매가</span>
								<span>-</span>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<!-- 구매 희망가 입력 -->
			<div class="tapList">
				<div>
					<h4>구매 희망가</h4>
					<button class="registPrice">등록</button>
				</div>
				<span id="alertMessage"></span><br />
				<div class="inputPriceBox">
					<input type="text" name="inputPrice" id="inputPrice"
						placeholder="금액을 입력하세요" /> <span>원</span>
				</div>
			</div>

			<!-- 정산 금액 -->
			<div class="total-price">
				<span>구매 희망가</span> <span id="totalAmount"></span>
			</div>

			<!-- 판매 버튼 -->
			<a
				href="<%=request.getContextPath()%>/jelly?page=buy&productId=${product.productId}&size=${size}&price=90000"
				class="action-button">판매 입찰 계속</a>
		</div>
	</div>
	<jsp:include page="/views/home/footer.jsp" />
	<script>
$(() => {
	  $(".registPrice").on("click", () => {
		 // 등록버튼 누르면 action-button 활성화 
	    $(".action-button").css("background-color","black");
		    
	    let inputPrice = $("#inputPrice").val().trim();
	    $.ajax({
	      url: '/haribo/orderBid',
	      method: 'get', 
	      data: {
	        inputPrice: inputPrice
	      },
	      success: (response) => {
	        console.log("입력한 가격 전송 완료");
	        console.log(JSON.stringify(response));
	        if (response > 20000) {
	        	$("#totalAmount").text(inputPrice+"원");
	        	
	        	let productId = ${product.productId};
	        	let sizeVal = ${size};
	        	let url = '${pageContext.request.contextPath}/jelly?page=buy&productId=' + productId + '&size=' + sizeVal + '&price=' + 30000;
	        	
	        	$(".action-button").attr("href", url);
	       	
	        } else {
	        	$("#alertMessage").html("2만원부터 천원 단위로 입력하세요");
	        }
	      }
	    });
	  });
	});

</script>
</body>

</html>
