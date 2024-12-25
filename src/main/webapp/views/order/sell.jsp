<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/views/home/subHeader.jsp" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>즉시판</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sell.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
$(()=>{
	$(".payment-submit-btn").on("click", async function requestPayment(){
		const now = new Date(); 
 		const year = now.getFullYear() % 100;
 		const month = ("0" + (now.getMonth() + 1)).slice(-2); // 월 앞자리에 0을 추가 (예: 01, 02)
 		const date = ("0" + now.getDate()).slice(-2); // 일 앞자리에 0을 추가 (예: 01, 02)
 		const rnd = Math.floor(Math.random()*100);
		const tradeId = year + month + date + rnd + "0"

		$.ajax({
		    url: '/haribo/sellData', // 판매 정보를 DAO에 넣는 서블릿으로 이동
		    method: "GET", // HTTP 메서드는 대문자로 작성
		    data: {
		    	tradeId: tradeId,
		        productName: "${product.productName}", 
		        totalPrice: ${price - 10000}, // 쿠폰까지 먹인 최종가 나중에 변경해야 함
		        price : {price}, // 사용자가 입력한 판매 입찰가
		        productId: "${product.productId}",
		        size: "${size}"
		    },
		    success: function (response) {
		        console.log("결제 성공");
		        window.location.href = "${pageContext.request.contextPath}/jelly?page=sellConfirm&productId=${product.productId}&price=${price}"; // 성공 시 페이지 이동
		    },
		    error: function(xhr, status, error) { 
		        console.error("결제 실패:", error); // console.log 대신 console.error 사용
		        console.error("응답 상태:", status);
		        console.error("서버 응답:", xhr.responseText);
		    }
		});

	});
});


</script>
</head>
<body>
	<div class="order-container">
		<h2>주문/정산</h2>
		<!-- 주문 상품 -->
		<div class="order-content">
			<div class="order-title">
				<span>주문 상품</span>
			</div>
			<div class="order-info">
				<img src="${product.imageUrl}" alt="상품사진" />
				<div class="order-subInfo">
					<div class="productSubInfo">
						<span style="font-weight: bold">${product.modelNumber }</span><br />
						<span>${product.productName }</span>
						<div class="fontGray">
							<span>${product.productName } </span>
						</div>
						<span>${size }</span>
					</div>
				</div>
			</div>
		</div>

		<!-- 판매 정산계좌 -->
		<div class="order-content">
			<div class="order-title">
				<span>판매 정산 계좌</span>
			</div>
			<div class="order-info">
				<div class="order-subInfo">
					<div class="fontGray">
						<span>계좌</span><br /> <span>예금주</span>
					</div>
					<div>
						<span>${defaultAccount.accountHolder}</span><br /> <span>${defaultAccount.accountNumber }</span><br />
					</div>
				</div>
			</div>
		</div>
		<!-- 배송 주소 -->
		<div class="order-content">
			<div class="order-title">
				반송 주소
				<button class="modalOpen">주소 변경</button>
			</div>
			<div class="order-info">
				<div class="order-subInfo">
					<div class="fontGray">
						<span>받는 분</span><br /> <span>연락처</span><br /> <span>주소</span>
					</div>
					<div>
						<span>${user.userName}</span><br /> <span>${user.phoneNumber }</span><br />
						<span>[${defaultAddress.postalCode}] ${defaultAddress.addressLine1 }
							${defaultAddress.addressLine2 }</span>
					</div>
				</div>
			</div>
			<select name="요청사항" id="" class="order-selection">
				<option value="op1">요청사항 없음</option>
				<option value="op2">문 앞에 놓아주세요</option>
				<option value="op3">경비실에 맡겨 주세요</option>
				<option value="op4">파손 위험 상품입니다. 배송 시 주의해주세요</option>
				<option value="op5">직접 입력</option>
			</select>
		</div>

		<!-- 결제 방법 -->
		<div class="order-content">
			<div class="order-title">
				<span>결제방법</span>
			</div>
			<div class="order-info">
				<div class="paymentSubinfo">
					<span>카드 간편결제</span> <a href="/views/mypage/구매내역.jsp"><button
							class="addCard">+ 새 카드 추가하기</button></a>
					<div class="account-btn">${defaultAccount.bankName }&nbsp;
						${defaultAccount.accountNumber }</div>
					<span>페널티는 일시불만 지원합니다. 체결 후 결제 정보 변경은 불가하며 분할 납부 변경은 카드사 문의
						바랍니다.단, 카드사별 정책에 따라 분할 납부 변경 시 수수료가 발생할 수 있습니다.</span>
				</div>
			</div>
		</div>

		<!-- 결제 금액 -->
		<div class="order-content">
			<div class="order-title">
				<span>결제금액</span>
			</div>
			<div class="order-info">
				<div class="order-price">
					<div class="price-detail">
						<div>
							<span>즉시 판매가</span> <span>${formattedPrice }원</span>
						</div>
						<div>
							<span>검수비</span> <span>무료</span>
						</div>
						<div>
							<span>수수료</span> <span>10,000</span>
						</div>
						<div>
							<span>배송비</span> <span>선불.판매자 부담</span>
						</div>
					</div>
					<div class="total-price">
						<span>정산금액</span> 
						<span> <fmt:formatNumber value="${price -10000 }" type="number" />
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 결제 푸터 -->
	<div class="payment-footer">
		<button class="payment-submit-btn">
		<fmt:formatNumber value="${price - 10000 }" type="number"/>원 판매하기
		</button> 
			
	</div>
</body>
<jsp:include page="/views/home/footer.jsp" />
</html>
