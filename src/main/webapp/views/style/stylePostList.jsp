<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${pageContext.request.contextPath}/js/styleDetail.js"></script>


<!-- post list -->
<c:forEach var="post" items="${postList}">
	<a href="${pageContext.request.contextPath}/jelly?page=styleDetail&postId=${post.postId}" class="post-card">
		<div class="imageWrapper">
			<!-- get image from imageDAO -->
			<img src="${post.postImageUrl}" alt="${post.nickname}'s post">
		</div>
		<div class="underPost">
			<img src="${post.profileImageUrl}" alt="${post.nickname}'s profile picture" class="profilePic"/>
			<span class="username">${post.nickname}</span>
			<span class="likebtn">
			    <img 
			        id="like-btn-${post.postId}" 
			        class="like-btn" 
			        src="<%= request.getContextPath() %>/img/${isLike ? 'after_like.png' : 'before_like.png'}"
			        alt="좋아요 버튼" 
			        data-context-path="<%= request.getContextPath() %>" 
			        data-post-id="${post.postId}" 
			        onclick="return false;" />
			    <span id="like-count-${post.postId}" class="like-count">${post.likeCount}</span>
			</span>
		</div>
		<div class="title">
			<p>${post.title}</p>
		</div>
	</a>
</c:forEach>


 
 
 

	
