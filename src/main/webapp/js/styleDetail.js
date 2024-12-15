$(document).ready(function(){
	$("#like-btn").on("click", function() {
		const contextPath = $(this).data("context-path");
		const postId = $(this).data("post-id");
		const userId = $(this).data("user-id");
		const likeBtn = $(this);
		const likesCount = $("#likes-count");
		
		$.ajax({
			url: contextPath + '/likePost',
			type: "POST",
			data: {postId: postId, userId: userId },
			success: function(response){
				// 좋아요 상태 반전
				likeBtn.attr("src", response.isLike ? contextPath + '/img/after_like.png' : contextPath + '/img/before_like.png');
				// 좋아요 개수 업데이트
				likesCount.text(response.likesCount);
			},
			error: function(xhr, status, error) {
                console.error("Ajax 오류:", status, error);
                alert("서버 요청 중 오류가 발생했습니다.");
        	}
		})
	})
})