<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/views/home/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Post New Post</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/postNewStyle.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/postNewStyle.js"></script>
</head>
<body>
	<div id=container>
	    <div id="formContainer">
	        <form action="<%= request.getContextPath() %>/upload" method="post" enctype="multipart/form-data" onsubmit="alert('posted')">
	            <input type="hidden" name="uploadType" value="stylePost" />
	            
	            <label for="titleInput">제목</label>
	            <input type="text" name="title" id="titleInput">
	
	            <label for="contentInput">설명</label>
	            <textarea name="content" id="contentInput"></textarea>
	
	            <label for="imageInput">이미지 선택</label>
	            <input type="file" name="postImages" id="imageInput" multiple="multiple" onchange="previewImages()">
	            
	            <!-- 이미지 미리 보기 -->
	            <div id="imagePreview"></div>
	            <br />
	
	            <label for="styleCategoryInput">Style Category:</label>
	            <select name="styleCategory" id="styleCategoryInput">
	                <option value="1" selected>캐주얼</option>
	                <option value="2">스트릿</option>
	                <option value="3">모던</option>
	                <option value="4">빈티지</option>
	                <option value="5">미니멀</option>
	                <option value="6">포멀</option>
	            </select>
	            
	            <!-- 상품 태그 추가 버튼 -->
                <button type="button" id="tag-button" onclick="openProductModal()">상품 태그 추가</button>
	            
	            <input type="submit" id="submitButton" value="Post">
	        </form>
	    </div>
	    
		 <!-- 상품 태그 모달 -->
	    <div id="product-modal" class="modal-overlay">
	        <div class="modal-content">
	            <h3>상품 검색</h3>
	            <form class="search-form" action="<%= request.getContextPath() %>/jelly?page=search" method="get">
			        <button>
			          	<svg width="17" height="16" fill="none" xmlns="http://www.w3.org/2000/svg" role="img" aria-labelledby="search">
			            	<path d="M7.667 12.667A5.333 5.333 0 107.667 2a5.333 5.333 0 000 10.667zM14.334 14l-2.9-2.9" stroke="currentColor" stroke-width="1.333" stroke-linecap="round" stroke-linejoin="round"></path>
			          	</svg>
			        </button>
			        <input class="search-input" placeholder="검색어를 입력해주세요" name="query" required="" type="text">
			        <button class="search-reset" type="reset">
			          	<svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
			            	<path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12"></path>
			          	</svg>
			        </button>
			    </form>
	            <div id="product-results">
	                <!-- 검색 결과가 여기에 표시 -->
	            </div>
	            <button class="modal-close" id="modal-close">&times;</button>
	        </div>
	    </div>
	</div>
	
	<%@ include file="/views/home/footer.jsp" %>
</body>
</html>

