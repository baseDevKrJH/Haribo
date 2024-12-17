<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/views/home/subHeader.jsp" %>    
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>mypage</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypageCommon.css"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profileInfo.css"
    />
  </head>
  <body>
    <!-- mypage-container 안에 mypageNavi.jsp 위치해야 함 -->
    <div class="mypage-container">
      <!-- mypageNavigation -->
      <%@ include file="/views/mypage/mypageNavi.jsp"%>
      <!-- mypage content 시작 -->
      <div class="mypage-content">
        <!-- (각자 부분) 시작 -->
        <div class="mypage-box">
          <div>
            <img
              src="${uVo.profileImage }"
              alt="프로필 사진"
              class="profile-image"
            />
          </div>
          <div class="profile-info">
            <span class="profile-user-nickname">${uVo.userName }</span> <br />
            <span class="profile-user-email">${uVo.email }</span>
          </div>
          <div class="profile-btns">
            <input type="button" value="프로필 관리" class="profile-info-btn" />
            <input type="button" value=" 내스타일 " class="profile-info-btn" />
          </div>
        </div>
        <!-- 각자 부분 끝 -->
      </div>
    </div>
  </body>
</html>
