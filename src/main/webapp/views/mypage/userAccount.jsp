<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/home/subHeader.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>프로필 관리</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/userAccount.css" />
  </head>
<body>

	<!-- 마이페이지 공통부분 시작 -->
	<div class="mypage-container">
		<%@ include file="/views/mypage/mypageNavi.jsp"%>
		<!-- 마이페이지 컨텐츠 시작 -->
		<div class="mypage-content">
			<div class="mypageSubtitle">판매 정산 계좌</div>
			<div class="loginInfo-content">
				<div class="unit">
				  <span class="unit-title">은행명</span>
				  <div class="inputBox">
				    <select name="bankname" id="bankname" class="selectstyle">
				      <option value="default">선택하세요</option>
				      <option value="ourBank">우리은행</option>
				      <option value="kookminBank">국민은행</option>
				      <!-- 추가된 은행 추가 시 여기 옵션을 추가하세요 -->
				    </select>
				  </div>
				</div>
				<div class="unit">
					<span class="unit-title">계좌번호</span>
					<div class="inputBox">
						<input type="text" name="accountnum" id="accountnum" placeholder="-없이 입력하세요" /> &nbsp;
					</div>
				</div>
				<div class="unit">
					<span class="unit-title">예금주</span>
					<div class="inputBox">
						<input type="text" name="accountname" id="accountname" placeholder="예금주명을 정확히 입력하세요" /> &nbsp;
					</div>
				</div>
			<button type="submit" class="submit-btn">저장하기</button>				
			</div>
		</div>
	</div>
</body>
</html>
