<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/home/subHeader.jsp"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>로그인 정보</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypageCommon.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginInfo.css" />
  </head>

  <body>
    <div class="mypage-container">
      <!-- 마이페이지 네비바 시작 -->
		<%@ include file="/views/mypage/mypageNavi.jsp"%>
      <!-- 마이페이지 컨텐츠 시작 -->
      <div class="mypage-content">
        <div class="mypageSubtitle">로그인 정보</div>
        <!-- 관심페이지 공통부분 끝 -->
        <div class="loginInfo-content">
          <h3>내 계정</h3>
          <div class="unit">
            <span class="unit-title"></span>
            <div class="inputBox">
              <span class="detailInfo">사용자 이메일 주소</span>
              <button class="modifybtn">변경</button>
            </div>
          </div>
          <div class="unit">
            <span class="unit-title">비밀번호</span>
            <div class="inputBox">
              <span class="detailInfo">사용자 비밀번호</span>

              <button class="modifybtn">변경</button>
            </div>
          </div>
          <br />
          <br />
          <h3>개인 정보</h3>
          <div class="unit">
            <span class="unit-title">휴대폰 번호</span>
            <div class="inputBox">
              <span class="detailInfo">사용자 휴대번호</span>
              <button class="modifybtn">변경</button>
            </div>
          </div>
          <div class="unit">
            <span class="unit-title">신발 사이즈</span>
            <div class="inputBox">
              <span class="detailInfo">사용자 신발 사이즈</span>
              <button class="modifybtn">변경</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
