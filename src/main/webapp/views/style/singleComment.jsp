<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Post list -->
<c:forEach var="comment" items="${commentList}">
    <!-- Wrap profile picture and nickname -->
    <div class="comment-info">
        <a href="<%= request.getContextPath() %>/jelly?page=styleProfile&userId=${comment.userId}" class="comment-profile-link">
            <img src="${comment.profileImage}" alt="Profile Picture" class="comment-profile-picture">
            <span class="comment-nickname">${comment.nickname}</span>
        </a>
        <span class="comment-contents">${comment.content}</span>
        
        <c:if test="${user.userId == comment.userId}">
            <button class="delete-comment-btn" data-comment-id="${comment.commentId}" data-context-path="${pageContext.request.contextPath}" data-post-id="${comment.postId}">Delete</button>
        </c:if>
    </div>
</c:forEach>
