$(document).ready(function () {
  const $commentMenu = $("#comment-menu"); // 필터 메뉴
  const $openCommentBtn = $("#open-comment-btn"); // 필터 열기 버튼
  const $closeBtn = $("#close-btn"); // 닫기 버튼
  const $applyBtn = $("#postComment");
  const $comments = $("#comments"); // Container for comments
  let rep = 1;
  let deleteRep = 1;
  

  // 필터 메뉴 열기
  $openCommentBtn.on("click", function () {
    // console.log("[Filter] 필터 메뉴 열림");
    $commentMenu.addClass("open");
	let comment = $("#myComment").val();
	
	const contextPath = $(this).data("context-path");
	const postId = $(this).data("post-id");
	console.log(contextPath);
	console.log(postId);
	console.log(comment);
   


    console.log("[comment] 서버로 Ajax 요청 시작");
    $.ajax({
      url: contextPath + '/comment', // 필터 데이터를 처리할 서버 URL
      type: "POST",
	  data: {postId: postId, comment: comment},
	  dataType: 'html',

      beforeSend: function () {
        console.log("[comment] Ajax 요청 전: 로딩 메시지 표시");
      },
      success: function (html) {
		console.log("success");
		$("#comments").html(html);
        
      },
      error: function (xhr, status, error) {
		if (xhr.status === 401) { // 로그인 필요
            alert("로그인이 필요합니다.");
            window.location.href = contextPath + '/views/login/login.jsp'; // 로그인 페이지로 이동
        } else {
            console.error("Ajax 오류:", status, error);
            alert("서버 요청 중 오류가 발생했습니다.");
        }
      },
      complete: function () {
        // console.log("[Filter] Ajax 요청 완료");
        // $commentMenu.removeClass("open");
        // console.log("[Filter] 필터 메뉴 닫힘");
      },
    });
  });

  // 필터 메뉴 닫기
  $closeBtn.on("click", function () {
    // console.log("[Filter] 필터 메뉴 닫힘");
    $commentMenu.removeClass("open");
	const likeCount = $("#like-count");
	likeCount.html()
  });
  
  document.addEventListener("click", function (event) {
      const commentMenu = document.getElementById("comment-menu");
      if (commentMenu && !commentMenu.contains(event.target) && event.target.id !== "open-comment-btn") {
			$commentMenu.removeClass("open");
			const likeCount = $("#like-count");
			likeCount.html()
      }
  });



  // 필터 적용 버튼 클릭 이벤트
  $applyBtn.on("click", function () {
    // console.log("[Filter] 필터 적용 버튼 클릭됨");

	// let this is the comment from the form
    let comment = $("#myComment").val();
	let commentCount = parseInt($(this).data("comment-count"), 10) || 0;
	const count = $("#comment-count");
	
	
	
	const contextPath = $(this).data("context-path");
	const postId = $(this).data("post-id");
	console.log(contextPath);
	console.log(postId);
	console.log(comment);
   


    console.log("[comment] 서버로 Ajax 요청 시작");
    $.ajax({
      url: contextPath + '/comment', // 필터 데이터를 처리할 서버 URL
      type: "POST",
	  data: {postId: postId, comment: comment},
	  dataType: 'html',

      beforeSend: function () {
        console.log("[comment] Ajax 요청 전: 로딩 메시지 표시");
      },
      success: function (html) {
		console.log("success");
		$("#comments").html(html);
		if(comment != ""){
			console.log("something was written");
			console.log("current comment count: " + commentCount);
			commentCount += rep;
			rep++;
			console.log("after comment count: " + commentCount);
			console.log("count: " + rep)
			count.text(commentCount);
		}
		document.getElementById('myComment').value = '';
		
      },
      error: function (xhr, status, error) {
		if (xhr.status === 401) { // 로그인 필요
            alert("로그인이 필요합니다.");
            window.location.href = contextPath + '/views/login/login.jsp'; // 로그인 페이지로 이동
        } else {
            console.error("Ajax 오류:", status, error);
            alert("서버 요청 중 오류가 발생했습니다.");
        }
      },
      complete: function () {
        // console.log("[Filter] Ajax 요청 완료");
        // $commentMenu.removeClass("open");
        // console.log("[Filter] 필터 메뉴 닫힘");
      },
    });
  });
  
  $comments.on("click", ".delete-comment-btn", function () {
	  const commentNum = $("#comment-count");
	
      const deleteCommentId = $(this).data("comment-id");
	  const countOfComment = $(this).data("comment-count");
	  const contextPath = $(this).data("context-path");
	  const postId = $(this).data("post-id");
	  console.log("postId:" + postId);
	  console.log("commendId: " + deleteCommentId);
	  console.log("countOfComment: " + countOfComment);
	  let comment = $("#myComment").val();
	  $.ajax({
	    url: contextPath + '/comment',
	    type: "POST",
	    data: { deleteCommentId: deleteCommentId, postId: postId, comment: comment},
	    beforeSend: function () {
	      console.log("[comment] Ajax 요청 전: 로딩 메시지 표시");
	    },
	    success: function (html) {
	      console.log("success");
	      $("#comments").html(html);
		  commentNum.text(countOfComment - 1);
	    },
	    error: function (xhr, status, error) {
	      if (xhr.status === 401) {
	        alert("로그인이 필요합니다.");
	        window.location.href = contextPath + '/views/login/login.jsp';
	      } else {
	        console.error("Ajax 오류:", status, error);
	        alert("서버 요청 중 오류가 발생했습니다.");
	      }
	    }
	  });
	  

    });
});