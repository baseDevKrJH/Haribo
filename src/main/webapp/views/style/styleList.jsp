<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/views/home/header.jsp" %>
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
		pageNum : 1,
		styleCode : 0,
		orderBy: 0,
		getStyleList: function(styleCode, pageNum) {
			if (_Style.isSubmit) {
				return;
			}
			if (pageNum === undefined) {
				pageNum = 1;
			}
			// Clear the list and reset pageNum if the styleCode changes
		    if (_Style.styleCode != styleCode) {
		        _Style.pageNum = 1;
		        $("#divStyleList").html("");
		    } else if (pageNum === 1 && _Style.styleCode === styleCode) {
		        // Reload the first page for the same styleCode
		        $("#divStyleList").html("");
		    }

		    _Style.pageNum = pageNum; // Update the pageNum
			_Style.styleCode = styleCode;
			_Style.isSubimt = true;
			$.ajax({
			    type: 'GET',
			    url: _MainPath + '/jelly?page=styleList&styleCode=' + styleCode + "&pageNum=" + _Style.pageNum + "&orderBy=" + _Style.orderBy,
			    data: {},
			    dataType: 'html',
			    cache: false,
			    complete: function () {
			    	_Style._isSubmit = false;
			    },
			    success: function (html) {
		            if (!html.trim()) {
		                console.log("No more content to load.");
		                return;
		            }
		            console.log(html.includes('<title>Login</title>'));
		            
		         	// Check if the response is the login page
		            if (html.includes('<title>Login</title>') || html.includes('id="login-form"')) {
		                console.log("Login page detected. Redirecting...");
		                window.location.href = _MainPath + '/views/login/login.jsp'; // 로그인 페이지로 이동
		                return;
		            }

		            if ($("#divStyleList").html().includes(html.trim())) {
		                console.log("Duplicate content, not appending.");
		                return;
		            }

		            $("#divStyleList").append(html);
			    },
			    error: function (request, status, error) {
			        console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
			    }
			});
		},
		
		nextPage : function(){
			_Style.getStyleList(_Style.styleCode, _Style.pageNum + 1);
		}
	};
	

	function orderBy(order) {
	    _Style.orderBy = order;
	    _Style.getStyleList(_Style.styleCode, 1);
	}
	
	// init event handlers
	$(document).ready(function() {
		_Style.getStyleList(0);
		
		let isLoading = false; 

	    $(window).scroll(function () {
	        if (isLoading) return;

	        if ($(window).scrollTop() + $(window).height() >= $(document).height() - 100) {
	            isLoading = true; 

	            _Style.nextPage(); 

	            setTimeout(function () {
	                isLoading = false; 
	            }, 1000);
	        }
	    });

	});
	
	

	</script>
<body>
	<div class="filter">
		<!-- setAttribute("style", "street") to determine active page -->
		<!-- javascript:void(0) to not go back to top of page -->
		<a href="javascript:void(0);" onclick="_Style.getStyleList(0);" class="${style == 'classic' ? 'active' : ''}">All</a>
		<a href="javascript:void(0);" onclick="_Style.getStyleList(99);" class="${style == 'classic' ? 'active' : ''}">Following</a>
	    <a href="javascript:void(0);" onclick="_Style.getStyleList(1);" class="${style == 'classic' ? 'active' : ''}">Casual</a>
	    <a href="javascript:void(0);" onclick="_Style.getStyleList(2);" class="${style == 'street' ? 'active' : ''}">Street</a>
	    <a href="javascript:void(0);" onclick="_Style.getStyleList(3);" class="${style == 'modern' ? 'active' : ''}">Modern</a>
	    <a href="javascript:void(0);" onclick="_Style.getStyleList(4);" class="${style == 'vintage' ? 'active' : ''}">Vintage</a>
	    <a href="javascript:void(0);" onclick="_Style.getStyleList(5);" class="${style == 'minimal' ? 'active' : ''}">Minimal</a>
	    <a href="javascript:void(0);" onclick="_Style.getStyleList(6);" class="${style == 'casual' ? 'active' : ''}">Formal</a>
	    <a href="javascript:void(0);" onclick="orderBy(0);" class="${style == 'casual' ? 'active' : ''}">Most Recent</a>
	    <a href="javascript:void(0);" onclick="orderBy(1);" class="${style == 'casual' ? 'active' : ''}">Most Likes</a>
	    
    </div>
    
    
    <div class="posts" id="divStyleList">
    	
    </div>


</body>
</html>
<%@ include file="/views/home/footer.jsp" %>