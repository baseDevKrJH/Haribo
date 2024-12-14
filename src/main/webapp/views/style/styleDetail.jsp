<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/views/home/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Style Detail</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
  	<!-- 작성자 정보 표시 -->
    <img src="${userVo.profileImage}" width=50px alt="프로필 이미지" />
    <p>작성자: <a href="<%= request.getContextPath() %>/profile?userId=${userVo.userId}">${userVo.nickname}</a></p>
    <button onclick="followUser(${userVo.userId})">팔로우버튼</button>
    
    <!-- 게시물 이미지 표시 -->
    <c:if test="${not empty postImageList}">
        <c:forEach var="postImage" items="${postImageList}">
            <div>
                <img src="${postImage.postImageUrl}" alt="게시물 이미지">
            </div>
        </c:forEach>
    </c:if>
    
  	<!-- 게시물 정보 표시 -->
    <h1>${postVo.title}</h1>
    <p>작성일: ${postVo.createdAt}</p>
    <p>${postVo.content}</p>
    <p>좋아요: ${postVo.likesCount} | 조회수: ${postVo.viewsCount}</p>
    
    <!-- 태그된 상품 표시 -->
    <c:choose>
        <c:when test="${not empty productList}">
            <h2>상품태그 ${productList.size()}개</h2>
            <c:forEach var="product" items="${productList}">
                <div>
                    <img src="${product.imageUrl}" alt="${product.name}">
                    <p><a href="${pageContext.request.contextPath}/jelly?page=productDetail&productId=${product.productId}">${product.name}</a></p>
                    <p>${product.initialPrice}</p>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <h2>상품태그 0개</h2>
        </c:otherwise>
    </c:choose>
    
    <button onclick="likePost(${postVo.postId})">좋아요 버튼</button>
    <button onclick="commentOnPost(${postVo.postId})">댓글 버튼</button>
    <button onclick="savePost(${postVo.postId})">저장 버튼</button>
    
    <!-- 작성자의 다른 게시물 표시 -->
    <h2>${userVo.nickname}님의 다른 스타일</h2>
    <p><a href="${pageContext.request.contextPath}/jelly?page=profile&userId=${userVo.userId}">더보기</a></p>
    <c:forEach var="post" items="${postList}">
    	<c:if test="${post.postId != postVo.postId}">
	        <div>
	            <img src="${post.postImageUrl}" alt="" />
	            <p>좋아요: ${post.likesCount}</p>
	        </div>
    	</c:if>
    	<div>
    		<img src="${post.postImageUrl}" alt="" />
    		<p>좋아요: ${post.likesCount}</p>
    	</div>
    </c:forEach>
    
    
    <%--
    <!-- 좋아요, 저장 Ajax -->
    <script>
        function likePost(postId) {
            fetch(`/likePost?postId=${postId}`, { method: 'POST' })
                .then(res => res.json())
                .then(data => alert(data.message))
                .catch(err => console.error(err));
        }

        function savePost(postId) {
            fetch(`/savePost?postId=${postId}`, { method: 'POST' })
                .then(res => res.json())
                .then(data => alert(data.message))
                .catch(err => console.error(err));
        }

        function followUser(userId) {
            fetch(`/followUser?userId=${userId}`, { method: 'POST' })
                .then(res => res.json())
                .then(data => alert(data.message))
                .catch(err => console.error(err));
        }
    </script>
    --%>
    
    
    
    <%@ include file="/views/home/footer.jsp" %>
</body>
</html>