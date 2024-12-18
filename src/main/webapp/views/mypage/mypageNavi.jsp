<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

      <!-- 구매내역 -->
      <p class="mypage-sbn-menu">
      <c:choose>
            <c:when test="${param.page == 'purchaseHistory'}">
              <a href="<%=request.getContextPath()%>/jelly?page=" style="color:black; font-weight: bold">구매내역</a>
            </c:when>
            <c:otherwise>
              <a href="<%=request.getContextPath()%>/jelly?page=">구매내역</a>
            </c:otherwise>
      </c:choose>
      </p>
      <!-- 판매내역 -->
      <p class="mypage-sbn-menu">
      <c:choose>
            <c:when test="${param.page == ''}">
              <a href="<%=request.getContextPath()%>/jelly?page=" style="color:black; font-weight: bold">판매내역</a>
            </c:when>
            <c:otherwise>
              <a href="<%=request.getContextPath()%>/jelly?page=">판매내역</a>
            </c:otherwise>
      </c:choose>
      </p>
      <!-- 관심 -->
      <p class="mypage-sbn-menu">
       <c:choose>
            <c:when test="${param.page == 'wish'}">
              <a href="<%=request.getContextPath()%>/jelly?page=wish" style="color:black; font-weight: bold">관심</a>
            </c:when>
            <c:otherwise>
              <a href="<%=request.getContextPath()%>/jelly?page=wish">관심</a>
            </c:otherwise>
      </c:choose>
      </p>

      <p class="mypage-sbn-subtitle">내 정보</p>

      <!-- 로그인 관리 -->
      <p class="mypage-sbn-menu">
       <c:choose>
            <c:when test="${param.page == ''}">
              <a href="<%=request.getContextPath()%>/jelly?page=" style="color:black; font-weight: bold">로그인 관리</a>
            </c:when>
            <c:otherwise>
              <a href="<%=request.getContextPath()%>/jelly?page=">로그인 관리</a>
            </c:otherwise>
      </c:choose>
      </p>

      <!-- 프로필 관리 -->
      <p class="mypage-sbn-menu">
       <c:choose>
            <c:when test="${param.page == 'profileInfo'}">
              <a href="<%=request.getContextPath()%>/jelly?page=profileInfo" style="color:black; font-weight: bold">프로필 관리</a>
            </c:when>
            <c:otherwise>
              <a href="<%=request.getContextPath()%>/jelly?page=profileInfo">프로필 관리</a>
            </c:otherwise>
      </c:choose>
      </p>

      <!-- 주소록 -->
      <p class="mypage-sbn-menu">
       <c:choose>
            <c:when test="${param.page == ''}">
              <a href="<%=request.getContextPath()%>/jelly?page=" style="color:black; font-weight: bold">주소록</a>
            </c:when>
            <c:otherwise>
              <a href="<%=request.getContextPath()%>/jelly?page=">주소록</a>
            </c:otherwise>
      </c:choose>
      </p>

      <!-- 정보 -->
      <p class="mypage-sbn-menu">
       <c:choose>
            <c:when test="${param.page == ''}">
              <a href="<%=request.getContextPath()%>/jelly?page=" style="color:black; font-weight: bold">정보</a>
            </c:when>
            <c:otherwise>
              <a href="<%=request.getContextPath()%>/jelly?page=">정보</a>
            </c:otherwise>
      </c:choose>
      </p>

      <!-- 판매 정산 계좌 -->
      <p class="mypage-sbn-menu">
       <c:choose>
            <c:when test="${param.page == 'userAccount'}">
              <a href="<%=request.getContextPath()%>/jelly?page=userAccount" style="color:black; font-weight: bold">판매 정산 계좌</a>
            </c:when>
            <c:otherwise>
              <a href="<%=request.getContextPath()%>/jelly?page=userAccount">판매 정산 계좌</a>
            </c:otherwise>
      </c:choose>
      </p>

      <!-- 쿠폰 -->
      <p class="mypage-sbn-menu">
       <c:choose>
            <c:when test="${param.page == 'myCoupon'}">
              <a href="<%=request.getContextPath()%>/jelly?page=myCoupon" style="color:black; font-weight: bold">쿠폰</a>
            </c:when>
            <c:otherwise>
              <a href="<%=request.getContextPath()%>/jelly?page=myCoupon">쿠폰</a>
            </c:otherwise>
      </c:choose>
      </p>
    </div>
  </body>
</html>