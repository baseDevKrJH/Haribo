<%@page import="com.jelly.www.vo.UserVO"%>
<%@page import="com.jelly.www.dao.UserDAO"%>
<%@page import="com.jelly.www.vo.PostImageVO"%>
<%@page import="com.jelly.www.dao.PostImageDAO"%>
<%@page import="com.jelly.www.vo.PostVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/views/style/styleHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleList.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<!-- post list -->
	<div class="posts">
		<c:forEach var="post" items="${postList}">
			<%-- <%
				Object postObject = pageContext.getAttribute("post");
	        	PostVO post = (PostVO) postObject;
	        	
	        	// get information from postVO
	        	int postId = post.getPostId();
	        	int userId =  post.getUserId();
	        	
				// get first image associated with post id
				PostImageDAO imageDAO = new PostImageDAO();
				PostImageVO imageVO = imageDAO.getFirstImageByPostId(postId);
				request.setAttribute("imageVO", imageVO);
				
				// get user information
				UserDAO userDAO = new UserDAO();
				UserVO userVO = userDAO.selectOne(userId);
				request.setAttribute("userVO", userVO);
			%> --%>
			
			
			
			<a href= "${pageContext.request.contextPath}/jelly?page=styleDetail&postId=${post.postId}" class="post-card">
				<div class="imageWrapper">
					<!-- get image from imageDAO -->
					<img src="${post.postImageUrl}" alt="${post.nickname}'s post">
				</div>
				<div class="underPost">
					<img src="${post.profileImageUrl}" alt="${post.nickname}'s profile picture" class="profilePic"/>
					<span class="username">${post.nickname}</span>
					<span class="likebtn">
						<button class="heart">likebtn</button>
						<span>${post.likesCount}</span>
					</span>
				</div>
				<div class="title">
					<p>${post.title}</p>
				</div>
			</a>
		</c:forEach>
	</div>

</body>
</html>
<%@ include file="/views/home/footer.jsp" %>