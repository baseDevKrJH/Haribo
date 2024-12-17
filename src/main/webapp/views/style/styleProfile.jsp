<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/views/home/header.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Style Detail</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/styleDetail.js"></script>
</head>
<body>
	<div class="container">
		<!-- 프로필 이미지 -->
        <div class="profile-image">
            <img src="${userVo.profileImage}" alt="유저 프로필 이미지">
        </div>

        <!-- 닉네임 -->
        <div class="profile-nickname">
            <h2>${userVo.nickname}</h2>
        </div>

        <!-- 팔로우 버튼 -->
        <div class="follow-button">
            <button id="follow-btn">팔로우</button>
        </div>

        <!-- 팔로워 수 -->
        <div class="follower-count">
            <span id="followers">팔로워: ${userVo.followerCount}</span>
            <button id="view-follower">팔로워 보기</button>
        </div>

        <!-- 팔로잉 수 -->
        <div class="following-count">
            <span id="following">팔로잉: ${userVo.followingCount}</span>
            <button id="view-following">팔로잉 보기</button>
        </div>
        
		<!-- 스타일 -->
        <div class="style-section">
            <h3>스타일 ${postList.size()}</h3>
            <button id="view-style">스타일 보기</button>
        </div>

        <div class="tagged-products">
            <h3>태그 상품 ${productLIst.size()}</h3>
            <button id="view-tagged-products">태그 상품 보기</button>
        </div>

        <!-- 저장된 스타일 -->
        <div class="saved-styles">
            <h3>관심 스타일</h3>
            <button id="view-saved-styles">관심 스타일 보기</button>
        </div>

        <!-- 스타일 올리기 -->
        <div class="style-upload">
            <button onclick="${pageContext.request.contextPath}/jelly?page=postNewStyle">스타일 업로드</button>
        </div>
	</div>

	<%@ include file="/views/home/footer.jsp"%>
</body>
</html>
