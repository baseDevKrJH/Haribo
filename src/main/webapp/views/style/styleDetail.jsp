<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/views/home/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Style Detail</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleDetail.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/styleDetail.js"></script>
</head>
<body>
  <div class="container">
    <!-- 작성자 정보 표시 -->
    <div class="author-info">
      <div class="profile">
        <img src="${userVo.profileImage}" alt="프로필 이미지" />
        <div class="info">
          <p><a href="<%= request.getContextPath() %>/jelly?page=styleProfile&userId=${userVo.userId}">${userVo.nickname}</a></p>
          <p class="date">작성일: ${postVo.createdAt}</p>
        </div>
      </div>
      <c:choose>
		<c:when test="${sessionScope.user != null && sessionScope.user.userId == postVo.userId}">
		  <div class="edit-btn" data-context-path="${pageContext.request.contextPath}" data-post-id="${postVo.postId}">
		  	<a href="<%= request.getContextPath() %>/jelly?page=styleModify&postId=${postVo.postId}">스타일 수정하기</a>
		  </div>
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

    <!-- 게시물 이미지 표시 -->
    <c:if test="${not empty postImageList}">
      <div class="post-images">
        <c:forEach var="postImage" items="${postImageList}">
          <img src="${postImage.postImageUrl}" alt="게시물 이미지">
        </c:forEach>
      </div>
    </c:if>

    <!-- 게시물 정보 표시 -->
    <div class="post-info">
      <h1>${postVo.title}</h1>
      <p>${postVo.content}</p>
      <div class="stats">
        <span>
          <img src="<%= request.getContextPath() %>/img/view.png" alt="조회수" />
          <span>${postVo.viewCount}</span>
        </span>
        <span>
          <img id="like-btn" class="like-btn" src="<%= request.getContextPath() %>/img/${isLike ? 'after_like.png' : 'before_like.png'}" alt="좋아요 버튼" data-context-path="${pageContext.request.contextPath}" data-post-id="${postVo.postId}">
          <span id="like-count">${postVo.likeCount}</span>
        </span>
        <span>
          <img id="comment-btn" src="<%= request.getContextPath() %>/img/comment.png" alt="댓글 버튼" />
          <span>${postVo.commentCount}</span>
        </span>
        <span>
          <img id="save-btn" src="<%= request.getContextPath() %>/img/${isSave ? 'after_save.png' : 'before_save.png'}" alt="저장 버튼" data-context-path="${pageContext.request.contextPath}" data-post-id="${postVo.postId}">
          <span id="save-count">${postVo.saveCount}</span>
        </span>
      </div>
    </div>

    <!-- 태그된 상품 표시 -->
    <div class="tagged-products">
      <c:choose>
        <c:when test="${not empty productList}">
          <h2>상품태그 ${productList.size()}개</h2>
          <div class="product-grid">
            <c:forEach var="product" items="${productList}" end="4">
              <a href="${pageContext.request.contextPath}/jelly?page=productDetail&productId=${product.productId}" class="product">
                <img src="${product.imageUrl}" alt="${product.productName}" />
                <p>${product.productName}</p>
                <p>${product.initialPrice}</p>
              </a>
            </c:forEach>
          </div>
        </c:when>
        <c:otherwise>
          <h2>상품태그 0개</h2>
        </c:otherwise>
      </c:choose>
    </div>

    <hr class="divider" />

    <!-- 작성자의 다른 게시물 표시 -->
    <div class="other-posts">
      <div class="header">
        <h2>@${userVo.nickname}님의 다른 스타일</h2>
        <p><a href="${pageContext.request.contextPath}/jelly?page=styleProfile&userId=${userVo.userId}" class="more-btn">더보기</a></p>
      </div>
      <div class="post-grid">
        <c:forEach var="post" items="${postList}" end="4">
          <c:if test="${post.postId != postVo.postId}">
            <a href="${pageContext.request.contextPath}/jelly?page=styleDetail&postId=${post.postId}" class="post">
              <img src="${post.thumbnailImageUrl}" alt="게시물 썸네일" />
              <p>좋아요: ${post.likeCount}</p>
            </a>
          </c:if>
        </c:forEach>
      </div>
    </div>
  </div>

  <%@ include file="/views/home/footer.jsp" %>
</body>
</html>