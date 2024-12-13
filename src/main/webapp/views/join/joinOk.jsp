<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 성공</title>
<script>
    // 회원가입 성공했으면 팝업으로 알려주고 홈페이지로 이동
    window.onload = function() {
        alert("회원가입에 성공하셨습니다!");
        window.location.href = "<%= request.getContextPath() %>/jelly?page=home"; // 홈으로 리다이렉트처리
    };
</script>
</head>
<body>
</body>
</html>