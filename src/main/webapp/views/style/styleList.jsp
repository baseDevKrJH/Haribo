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
	    setActiveLeft(document.getElementById("filter-all"));
	    setActiveRight(document.getElementById("filter-recent"));
	});
	
	
	function setActiveLeft(element) {
        const links = document.querySelectorAll('.filter-left .filter-link');
        links.forEach(link => link.classList.remove('active'));
        element.classList.add('active');
    }
	function setActiveRight(element) {
        const links = document.querySelectorAll('.filter-right .filter-link');
        links.forEach(link => link.classList.remove('active'));
        element.classList.add('active');
    }


	</script>
<body>
    <div class="filter-navbar">
        <!-- Filter links on the left -->
        <div class="filter-left" id="filter-container">
		    <a href="javascript:void(0);" onclick="_Style.getStyleList(0); setActiveLeft(this);" id="filter-all" class="filter-link">All</a>
		    <a href="javascript:void(0);" onclick="_Style.getStyleList(99); setActiveLeft(this);" id="filter-following" class="filter-link">Following</a>
		    <a href="javascript:void(0);" onclick="_Style.getStyleList(1); setActiveLeft(this);" id="filter-casual" class="filter-link">Casual</a>
		    <a href="javascript:void(0);" onclick="_Style.getStyleList(2); setActiveLeft(this);" id="filter-street" class="filter-link">Street</a>
		    <a href="javascript:void(0);" onclick="_Style.getStyleList(3); setActiveLeft(this);" id="filter-modern" class="filter-link">Modern</a>
		    <a href="javascript:void(0);" onclick="_Style.getStyleList(4); setActiveLeft(this);" id="filter-vintage" class="filter-link">Vintage</a>
		    <a href="javascript:void(0);" onclick="_Style.getStyleList(5); setActiveLeft(this);" id="filter-minimal" class="filter-link">Minimal</a>
		    <a href="javascript:void(0);" onclick="_Style.getStyleList(6); setActiveLeft(this);" id="filter-formal" class="filter-link">Formal</a>
		</div>
        
        <!-- Filter links on the right -->
        <div class="filter-right">
            <a href="javascript:void(0);" onclick="orderBy(0); setActiveRight(this);" id="filter-recent" class="filter-link">Most Recent</a>
            <a href="javascript:void(0);" onclick="orderBy(1); setActiveRight(this);" id="filter-likes" class="filter-link">Most Likes</a>
        </div>
    </div>
    
    
    <div class="posts" id="divStyleList">
    	
    </div>


</body>
</html>
<%@ include file="/views/home/footer.jsp" %>