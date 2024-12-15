$(document).ready(function(){
	$("#like-btn").on("click", function() {
		const isLike = $(this).data("is-like");
		const contextPath = $(this).data("context-path");
		const postId = $(this).data("post-id");
		const userId = $(this).data("user-id");
		const likeBtn = $(this);
		
		$.ajax({
			url: contextPath + `/likePost`,
			type: "POST",
			data: { postId: postId, userId: userId },
			success: function(response){
				likeBtn.attr("src", 'img/after_like.png');
			}
		})
		
		
		/*likeBtn.attr("src", isLike?'img/after_like.png':'img/before_like.png');
		likeBtn.data("is-like", !isLike);*/
	})
})