<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/views/home/subHeader.jsp" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>즉시판</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/sell.css">

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
				<img src="../img/productimg.png" alt="" />
				<div class="order-subInfo">
					<div class="productSubInfo">
						<span>Nike Air Force 1 '07 WB Flax</span>
						<div class="fontGray">
							<span>나이키 에어포스 1 '07 WB 플랙스 CJ9179-200</span>
						</div>
						<span>240</span>
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
						<span>천세진</span><br /> <span>010-9289-7875</span><br /> <span>[13536]
							경기 성남시 분당구 판교역로 4 (백현동) 10층 </span>
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
					<span>카드 간편결제</span><br />
					<button class="account-btn">현대카드 &nbsp;
						****-****-****-****</button>
					<button class="addCard">+ 새 카드 추가하기</button>
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
							<span>상품금액</span> <span>129,000원</span>
						</div>
						<div>
							<span>배송비</span> <span>5,000원</span>
						</div>
						<div>
							<span>쿠폰</span> <span>-10,000원</span>
						</div>
					</div>
					<div class="total-price">
						<span>총 결제금액</span> <span>124,000원</span>
					</div>
				</div>
			</div>
		</div>
	</div>
    <!-- 결제 푸터 -->
    <div class="payment-footer">
      <input
        type="submit"
        value="134,000원 판매하기"
        class="payment-submit-btn"
      />
    </div>
  </body>
  	<jsp:include page="/views/home/footer.jsp" />
</html>
