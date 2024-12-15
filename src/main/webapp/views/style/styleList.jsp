<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/views/style/styleHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleList.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<script>
	var _MainPath = "<%= request.getContextPath() %>";
	var _Style = {
		isSubmit : false,	
		getStyleList: function(styleCode) {
			if (_Style.isSubmit) {
				return;
			}
			_Style.isSubimt = true;
			$.ajax({
			    type: 'GET',
			    url: _MainPath + '/jelly?page=styleList&styleCode=' + styleCode,
			    data: {},
			    dataType: 'html',
			    cache: false,
			    complete: function () {
			    	_Style._isSubmit = false;
			    },
			    success: function (html) {
			        $("#divStyleList").html(html);
			    },
			    error: function (request, status, error) {
			        console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
			    }
			});
		}
	};
	
	$(document).ready(function() {
		_Style.getStyleList(0);
	});
	
		
	</script>
<body>
	<div class="filter">
		<!-- setAttribute("style", "street") to determine active page -->
		<!-- javascript:void(0) to not go back to top of page -->
		<a href="javascript:void(0);" onclick="_Style.getStyleList(0);" class="${style == 'classic' ? 'active' : ''}">all</a>
	    <a href="javascript:void(0);" onclick="_Style.getStyleList(1);" class="${style == 'classic' ? 'active' : ''}">classic</a>
	    <a href="javascript:void(0);" onclick="_Style.getStyleList(2);" class="${style == 'street' ? 'active' : ''}">street</a>
	    <a href="javascript:void(0);" onclick="_Style.getStyleList(3);" class="${style == 'modern' ? 'active' : ''}">modern</a>
	    <a href="javascript:void(0);" onclick="_Style.getStyleList(4);" class="${style == 'vintage' ? 'active' : ''}">vintage</a>
	    <a href="javascript:void(0);" onclick="_Style.getStyleList(5);" class="${style == 'minimal' ? 'active' : ''}">minimal</a>
    </div>
    
    <div class="posts" id="divStyleList">
    	
    </div>


</body>
</html>
<%@ include file="/views/home/footer.jsp" %>