<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/views/home/subHeader.jsp" %>    
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>mypage</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/mypage.css" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/mypageCommon.css" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/profileInfo.css" />
  </head>
  <body>
    <div class="mypage-container">
      <!-- mypageNavigation -->
      <%@ include file="/views/mypage/mypageNavi.jsp" %>
      <!-- mypage content 시작 -->
      <div class="mypage-content">
        <div class="mypage-box">
          <div>
            <img src="${user.profileImage}" alt="프로필 사진" class="profile-image" />
          </div>
          <div class="profile-info">
            <span class="profile-user-nickname">${user.userName}</span> <br />
            <span class="profile-user-email">${user.email}</span>
          </div>
          <div class="profile-btns">
            <input type="button" value="프로필 관리" class="profile-info-btn" />
            <input type="button" value="내스타일" class="profile-info-btn" />
          </div>
        </div>
      </div>
    </div>
  </body>
</html>