<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- post list -->
<c:forEach var="post" items="${postList}">
	<a href= "${pageContext.request.contextPath}/jelly?page=styleDetail&postId=${post.postId}" class="post-card">
		<div class="imageWrapper">
			<!-- get image from imageDAO -->
			<img src="${post.postImageUrl}" alt="${post.nickname}'s post">
		</div>
		<div class="underPost">
			<img src="${post.profileImageUrl}" alt="${post.nickname}'s profile picture" class="profilePic"/>
			<span class="username">${post.nickname}</span>
			<span class="likebtn">
				<button class="heart" onclick="alert('i like this'); return false;">like</button>
				<span>${post.likesCount}</span>
			</span>
		</div>
		<div class="title">
			<p>${post.title}</p>
		</div>
	</a>
</c:forEach>

 
 
 

	
