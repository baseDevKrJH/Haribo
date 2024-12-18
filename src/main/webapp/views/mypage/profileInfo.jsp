<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/home/subHeader.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>프로필 관리</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/mypageCommon.css" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/loginInfo.css" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/profileInfo.css" />
  </head>
<body>

	<!-- 마이페이지 공통부분 시작 -->
	<div class="mypage-container">
		<%@ include file="/views/mypage/mypageNavi.jsp"%>
		<!-- 마이페이지 컨텐츠 시작 -->
		<div class="mypage-content">
			<div class="mypageSubtitle">프로필 관리</div>
			<div class="profile-box">
				<div>
					<img
						src="<%= request.getContextPath() %>/img/profile2.png"
						alt="프로필 사진" class="profile-image" />
				</div>
				<div class="profile-info">
					<span class="profile-user-nickname">${user.userName}</span> <br /> <span
						class="profile-user-email">${user.email}</span>
				</div>
				<div class="profile-btns">
					<input type="button" value="이미지 변경" class="profile-info-btn" /> <input
						type="button" value="삭제" class="profile-info-btn" />
				</div>
			</div>
			<div class="loginInfo-content">
				<h3>프로필 정보</h3>
				<div class="unit">
					<span class="unit-title">프로필 이름</span>

					<div class="inputBox">
						<span class="detailInfo">사용자 프로필명</span>
						<button class="modifybtn">변경</button>
					</div>
				</div>
				<div class="unit">
					<span class="unit-title">이름</span>
					<div class="inputBox">
						<span class="detailInfo">사용자 이름?</span>
						<button class="modifybtn">변경</button>
					</div>
				</div>
				<div class="unit">
					<span class="unit-title">소개</span>
					<div class="inputBox">
						<span class="detailInfo">프로필 소개</span>
						<button class="modifybtn">변경</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
