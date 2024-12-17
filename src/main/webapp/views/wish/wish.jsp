<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/views/home/subHeader.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>관심상품</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mypageCommon.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/wish.css" />
</head>
<body>
	<!-- 마이페이지 공통부분 시작 -->
	<div class="mypage-container">
		<!-- 마이페이지 네비바 시작 -->
		<%@ include file="/views/mypage/mypageNavi.jsp"%>

		<!-- 마이페이지 컨텐츠 시작 -->
		<div class="mypage-content">
			<div class="mypageSubtitle">관심</div>
			<div class="wish-section">
				<button class="wish-btn" id="wish-products-btn">상품</button>
				<button class="wish-btn" id="wish-style-btn">스타일</button>
				<button class="wish-btn" id="wish-brand-btn">브랜드</button>
				<!-- 관심페이지 공통부분 끝 -->
			</div>
			<span class="wish-header-contents">전체 5</span>
			<div class="wish-products">
				<!-- 관심 상품 1개 폼 시작 -->
				<div class="wish-items">
					<img src="../img/productimg.png" alt="상품 사진" />
					<div class="wish-item-details">
						<span class="wish-item-name">Nike Air Force 1 '07 WB Flax </span>
						<br /> <span class="wish-item-description">나이키 에어포스 1 '07
							WB 플랙스</span> <br /> <br /> <span class="wish-item-size">240</span>
					</div>
					<div class="wish-item-rightsection">
						<a href="" class="buy-button">
							<div class="buy-text">구매</div>
							<div class="buy-info">
								<span class="price">250,000원</span> <span class="subtext">즉시
									구매가</span>
							</div>
						</a>
						<button class="wish-item-delete">삭제</button>
					</div>
				</div>
				<!-- 관심 상품 1개 폼 끝 -->
				<div class="wish-items">
					<img src="../img/productimg.png" alt="상품 사진" />
					<div class="wish-item-details">
						<span class="wish-item-name">Nike Air Force 1 '07 WB Flax </span>
						<br /> 
						<span class="wish-item-description">나이키 에어포스 1 '07
							WB 플랙스</span> <br /> <br /> <span class="wish-item-size">240</span>
					</div>
					<div class="wish-item-rightsection">
						<a href="" class="sell-button">
							<div class="sell-text">판매</div>
							<div class="sell-info">
								<span class="price">250,000원</span> <span class="subtext">즉시
									판매가</span>
							</div>
						</a>
						<button class="wish-item-delete">삭제</button>
					</div>
				</div>
			</div>
			<div class="wish-style"></div>
			<div class="wish-brand"></div>
		</div>
	</div>
</body>
</html>
