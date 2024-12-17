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
 <style>
 	div.name {
 		font: 14px;
 		font-color: #55555;
 	}
 </style>
</head>
<body class="main-style-popular-body">
  <div class="main-style-popular-container">
    <!-- STYLE 섹션 -->
    <div class="main-style-popular-section">
      <h2>
        STYLE<a href="<%= request.getContextPath() %>/jelly?page=style">&gt;</a>
      </h2>
      <div class="main-style-popular-style-items">
        <div class="main-style-popular-style-item">
          <a class="image-link" href="style-item1.html">
            <img src="#" alt="Style 1">
          </a>
          <div class="info">
            <div class="profile">
              <img src="profile1.jpg" alt="Profile Picture">
              <a class="name-link" href="profile1.html">JELLY</a>
            </div>
            <div title="Like" class="main-style-popular-heart-container">
              <input id="heart-1" class="checkbox" type="checkbox">
              <div class="svg-container">
                <svg xmlns="http://www.w3.org/2000/svg" class="svg-outline" viewBox="0 0 24 24">
                  <path d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Zm-3.585,18.4a2.973,2.973,0,0,1-3.83,0C4.947,16.006,2,11.87,2,8.967a4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,11,8.967a1,1,0,0,0,2,0,4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,22,8.967C22,11.87,19.053,16.006,13.915,20.313Z"></path>
                </svg>
                <svg xmlns="http://www.w3.org/2000/svg" class="svg-filled" viewBox="0 0 24 24">
                  <path d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Z"></path>
                </svg>
                <svg xmlns="http://www.w3.org/2000/svg" height="100" width="100" class="svg-celebrate">
                  <polygon points="10,10 20,20"></polygon>
                  <polygon points="10,50 20,50"></polygon>
                  <polygon points="20,80 30,70"></polygon>
                  <polygon points="90,10 80,20"></polygon>
                  <polygon points="90,50 80,50"></polygon>
                  <polygon points="80,80 70,70"></polygon>
                </svg>
              </div>
            </div>
          </div>
        </div>

        <div class="main-style-popular-style-item">
          <a class="image-link" href="style-item2.html">
            <img src="#" alt="Style 2">
          </a>
          <div class="info">
            <div class="profile">
              <img src="profile2.jpg" alt="Profile Picture">
              <a class="name-link" href="profile2.html">JELLY</a>
            </div>
            <div title="Like" class="main-style-popular-heart-container">
              <input id="heart-2" class="checkbox" type="checkbox">
              <div class="svg-container">
                <svg xmlns="http://www.w3.org/2000/svg" class="svg-outline" viewBox="0 0 24 24">
                  <path d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Zm-3.585,18.4a2.973,2.973,0,0,1-3.83,0C4.947,16.006,2,11.87,2,8.967a4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,11,8.967a1,1,0,0,0,2,0,4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,22,8.967C22,11.87,19.053,16.006,13.915,20.313Z"></path>
                </svg>
                <svg xmlns="http://www.w3.org/2000/svg" class="svg-filled" viewBox="0 0 24 24">
                  <path d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Z"></path>
                </svg>
                <svg xmlns="http://www.w3.org/2000/svg" height="100" width="100" class="svg-celebrate">
                  <polygon points="10,10 20,20"></polygon>
                  <polygon points="10,50 20,50"></polygon>
                  <polygon points="20,80 30,70"></polygon>
                  <polygon points="90,10 80,20"></polygon>
                  <polygon points="90,50 80,50"></polygon>
                  <polygon points="80,80 70,70"></polygon>
                </svg>
              </div>
            </div>
          </div>
        </div>

        <div class="main-style-popular-style-item">
          <a class="image-link" href="style-item3.html">
            <img src="#" alt="Style 3">
          </a>
          <div class="info">
            <div class="profile">
              <img src="profile3.jpg" alt="Profile Picture">
              <a class="name-link" href="profile3.html">JELLY</a>
            </div>
            <div title="Like" class="main-style-popular-heart-container">
              <input id="heart-3" class="checkbox" type="checkbox">
              <div class="svg-container">
                <svg xmlns="http://www.w3.org/2000/svg" class="svg-outline" viewBox="0 0 24 24">
                  <path d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Zm-3.585,18.4a2.973,2.973,0,0,1-3.83,0C4.947,16.006,2,11.87,2,8.967a4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,11,8.967a1,1,0,0,0,2,0,4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,22,8.967C22,11.87,19.053,16.006,13.915,20.313Z"></path>
                </svg>
                <svg xmlns="http://www.w3.org/2000/svg" class="svg-filled" viewBox="0 0 24 24">
                  <path d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Z"></path>
                </svg>
                <svg xmlns="http://www.w3.org/2000/svg" height="100" width="100" class="svg-celebrate">
                  <polygon points="10,10 20,20"></polygon>
                  <polygon points="10,50 20,50"></polygon>
                  <polygon points="20,80 30,70"></polygon>
                  <polygon points="90,10 80,20"></polygon>
                  <polygon points="90,50 80,50"></polygon>
                  <polygon points="80,80 70,70"></polygon>
                </svg>
              </div>
            </div>
          </div>
        </div>

        <div class="main-style-popular-style-item">
          <a class="image-link" href="style-item4.html">
            <img src="#" alt="Style 4">
          </a>
          <div class="info">
            <div class="profile">
              <img src="profile4.jpg" alt="Profile Picture">
              <a class="name-link" href="profile4.html">JELLY</a>
            </div>
            <div title="Like" class="main-style-popular-heart-container">
              <input id="heart-4" class="checkbox" type="checkbox">
              <div class="svg-container">
                <svg xmlns="http://www.w3.org/2000/svg" class="svg-outline" viewBox="0 0 24 24">
                                    <path d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Zm-3.585,18.4a2.973,2.973,0,0,1-3.83,0C4.947,16.006,2,11.87,2,8.967a4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,11,8.967a1,1,0,0,0,2,0,4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,22,8.967C22,11.87,19.053,16.006,13.915,20.313Z"></path>
                </svg>
                <svg xmlns="http://www.w3.org/2000/svg" class="svg-filled" viewBox="0 0 24 24">
                  <path d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Z"></path>
                </svg>
                <svg xmlns="http://www.w3.org/2000/svg" height="100" width="100" class="svg-celebrate">
                  <polygon points="10,10 20,20"></polygon>
                  <polygon points="10,50 20,50"></polygon>
                  <polygon points="20,80 30,70"></polygon>
                  <polygon points="90,10 80,20"></polygon>
                  <polygon points="90,50 80,50"></polygon>
                  <polygon points="80,80 70,70"></polygon>
                </svg>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 세로 구분선 -->
    <div class="main-style-popular-divider"></div>

     <!-- POPULAR 섹션 -->
    <div class="main-style-popular-section">
      <h2>
        POPULAR<a href="<%= request.getContextPath() %>/jelly?page=popular">&gt;</a>
      </h2>
      <div class="main-style-popular-popular-items">
        <!-- 인기 아이템 출력 반복 -->
        <c:forEach var="product" items="${popularProducts}" varStatus="status">
          <c:if test="${status.index < 4}"> <!-- 상위 4개 상품만 표시 -->
            <div class="main-style-popular-popular-item">
              <a class="image-link" href="<%= request.getContextPath() %>/jelly?page=productDetail&productId=${product.productId}">
                <img src="${product.imageUrl}" alt="${product.productName}">
              </a>
              <div class="info">
                <div class="name">${product.productName}</div>
                <div class="description">${product.description}</div>
                <div class="price">${product.initialPrice}원</div>    
              </div>
            </div>
          </c:if>
        </c:forEach>
      </div>
    </div>
  </div>
</body>
</html>