<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Style and Popular</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/common.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/mainstylepopular.css">
</head>
<body class="main-style-popular-body">
  <div class="main-style-popular-container">
    <!-- STYLE 섹션 -->
    <div class="main-style-popular-section">
        <h2>
          STYLE<a href="<%= request.getContextPath() %>/jelly?page=styleList">&gt;</a>
        </h2>
      <div class="main-style-popular-style-items">
	      <c:forEach var="post" items="${postList}">
	      	<div class="main-style-popular-style-item">
	          <a href="${pageContext.request.contextPath}/jelly?page=styleDetail&postId=${post.postId}" class="post-card">
	            <div class="imageWrapper">
					<img src="${post.postImageUrl}" alt="${post.nickname}'s post">
				</div>
	          	<div class="underPost">
					<img src="${post.profileImageUrl}" alt="${post.nickname}'s profile picture" class="profilePic"/>
					<span class="username">${post.nickname}</span>
					<span class="likebtn">
					    <img 
					        id="like-btn-${post.postId}" 
					        class="like-btn" 
					        src="<%= request.getContextPath() %>/img/${post.like ? 'after_like.png' : 'before_like.png'}"
					        alt="좋아요 버튼" 
					        data-context-path="<%= request.getContextPath() %>" 
					        data-post-id="${post.postId}"
					        data-like-count = "${post.likeCount}"
					        onclick="return false;" />
	        
			    		<span id="like-count-${post.postId}" class="like-count">${post.likeCount}</span>
					</span>
				  </div>
				  <div class="title">
					  <p>${post.title}</p>
				  </div>
				</a>
        	 </div>
	       </c:forEach>
	     </div>
    <!-- 세로 구분선 -->
    <div class="main-style-popular-divider"></div>

    <!-- POPULAR 섹션 -->
      <h2>
        POPULAR<a href="<%= request.getContextPath() %>/jelly?page=popular">&gt;</a>
      </h2>
      <div class="main-style-popular-popular-items">
        <!-- 첫 번째 인기 아이템 -->
        <div class="main-style-popular-popular-item">
          <a class="image-link" href="popular-item1.html">
            <img src="#" alt="Shoe 1">
          </a>
          <div class="info">
            <div class="name">NIKE</div>
            <div class="description">Jordan x Travis Scott Jumpman Jack TR Black and Dark Mocha</div>
            <div class="price">500,000원</div>
          </div>
        </div>

        <!-- 두 번째 인기 아이템 -->
        <div class="main-style-popular-popular-item">
          <a class="image-link" href="popular-item2.html">
            <img src="#" alt="Shoe 2">
          </a>
          <div class="info">
            <div class="name">Adidas</div>
            <div class="description">Yeezy Boost 350 V2 'Zebra'</div>
            <div class="price">350,000원</div>
          </div>
        </div>

        <!-- 세 번째 인기 아이템 -->
        <div class="main-style-popular-popular-item">
          <a class="image-link" href="popular-item3.html">
            <img src="#" alt="Shoe 3">
          </a>
          <div class="info">
            <div class="name">PUMA</div>
            <div class="description">Suede Classic Mono Iced</div>
            <div class="price">100,000원</div>
          </div>
        </div>

        <!-- 네 번째 인기 아이템 -->
        <div class="main-style-popular-popular-item">
          <a class="image-link" href="popular-item4.html">
            <img src="#" alt="Shoe 4">
          </a>
          <div class="info">
            <div class="name">New Balance</div>
            <div class="description">327 'Castle Rock'</div>
            <div class="price">120,000원</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>