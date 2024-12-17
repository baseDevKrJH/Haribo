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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleProfile.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/styleDetail.js"></script>
</head>
<body>
	<div class="container">
		<div class="user-info">
      		<div class="profile">
        		<img src="${userVo.profileImage}" alt="프로필 이미지" />
        		<div class="info">
          			<p><a href="<%= request.getContextPath() %>/jelly?page=styleProfile&userId=${userVo.userId}">${userVo.nickname}</a></p>
        		</div>
      		</div>
	        <c:choose>
				<c:when test="${sessionScope.user != null && sessionScope.user.userId == userVo.userId}">
			  		
				</c:when>
			<c:otherwise>
			  	<button id="follow-btn" class="follow-btn ${isFollow ? 'following' : '' }" 
			            data-context-path="${pageContext.request.contextPath}" 
			            data-user-id="${postVo.userId}">
			      ${isFollow ? '팔로잉' : '팔로우'}
			  	</button>
		    </c:otherwise>
		  	</c:choose>
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
        </div>

        <div class="tagged-products">
            <h3>태그 상품 ${productSet.size()}</h3>
        </div>

        <!-- 저장된 스타일 -->
        <div class="saved-styles">
            <h3>관심 스타일 ${savedPostList.size()}</h3>
        </div>

        <!-- 스타일 올리기 -->
        <div class="style-upload">
        	<a href="${pageContext.request.contextPath}/upload?page=stylePost">스타일 업로드</a>
        </div>
	</div>

	<%@ include file="/views/home/footer.jsp"%>
</body>
</html>
