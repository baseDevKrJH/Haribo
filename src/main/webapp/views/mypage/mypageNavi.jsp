<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>mypageNavigation</title>
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/css/mypageNavi.css"
    />
  </head>
  <body>
    <div class="mypage-snb">
      <p class="mypage-sbn-title">마이페이지</p>
      <p class="mypage-sbn-subtitle">쇼핑정보</p>
      <p class="mypage-sbn-menu">
        <a href="./views/mypage/?.jsp">구매내역</a>
      </p>
      <p class="mypage-sbn-menu">
        <a href="./views/mypage/?.jsp">판매내역</a>
      </p>
      <p class="mypage-sbn-menu">
        <a href="<%= request.getContextPath()%>/jelly?page=wish">관심</a>
      </p>
      <p class="mypage-sbn-subtitle">내 정보</p>
      <p class="mypage-sbn-menu">
        <a href="./views/mypage/loginInfo.jsp">로그인 관리</a>
      </p>
      <p class="mypage-sbn-menu">
        <a href="./views/mypage/profileInfo.jsp">프로필 관리</a>
      </p>
      <p class="mypage-sbn-menu">
        <a href="./views/mypage/addressInfo.jsp">주소록</a>
      </p>
      <p class="mypage-sbn-menu">
        <a href="./views/mypage/paymentInfo.jsp">결제 정보</a>
      </p>
      <p class="mypage-sbn-menu">
        <a href="./views/mypage/?.jsp">판매 정산 계좌</a>
      </p>
      <p class="mypage-sbn-menu">
        <a href="./views/mypage/?.jsp">쿠폰</a>
      </p>
    </div>
  </body>
</html>
