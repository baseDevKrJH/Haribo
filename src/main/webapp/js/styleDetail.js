$(document).ready(function() {
	$("#like-btn").on("click", function() {
		const contextPath = $(this).data("context-path");
		const postId = $(this).data("post-id");
		const userId = $(this).data("user-id");
		const likeBtn = $(this);
		const likeCount = $("#like-count");

		$.ajax({
			url: contextPath + '/likePost',
			type: "POST",
			data: {postId: postId, userId: userId },
			success: function(response) {
				// 좋아요 상태 반전
				likeBtn.attr("src", response.isLike ? contextPath + '/img/after_like.png' : contextPath + '/img/before_like.png');
				// 좋아요 개수 업데이트
				likeCount.text(response.likeCount);
			},
			error: function(xhr, status, error) {
				console.error("Ajax 오류:", status, error);
				alert("서버 요청 중 오류가 발생했습니다.");
			}
		})
	})

	$("#save-btn").on("click", function() {
		const contextPath = $(this).data("context-path");
		const postId = $(this).data("post-id");
		const userId = $(this).data("user-id");
		const saveBtn = $(this);
		const saveCount = $("#save-count");

		$.ajax({
			url: contextPath + '/savePost',
			type: "POST",
			data: {postId: postId, userId: userId },
			success: function(response) {
				// 저장 상태 반전
				saveBtn.attr("src", response.isSave ? contextPath + '/img/after_save.png' : contextPath + '/img/before_save.png');
				// 저장 개수 업데이트
				saveCount.text(response.saveCount);
			},
			error: function(xhr, status, error) {
				console.error("Ajax 오류:", status, error);
				alert("서버 요청 중 오류가 발생했습니다.");
			}
		})
	})
})