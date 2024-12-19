$(document).ready(function() {
	// 상품 태그 모달 열기
   $('#addProductButton').on('click', function () {
		$('.modal-overlay').css('display', 'flex');
   });

    // 모달 닫기
    $('.modal-close').on('click', function () {
        $('.modal-overlay').css('display', 'none');
    });

    // 모달 외부 클릭 시 닫기
    $(document).on('click', function (e) {
        if ($(e.target).hasClass('modal-overlay')) {
            $('.modal-overlay').css('display', 'none');
        }
    });
	
	
	function previewImages() {
	    const files = document.getElementById('imageInput').files;
	    const previewContainer = document.getElementById('imagePreview');
	    previewContainer.innerHTML = '';
	
	    if (files.length > 0) {
	        for (let i = 0; i < files.length; i++) {
	            const reader = new FileReader();
	            reader.onload = function(e) {
	                const img = document.createElemesnt('img');
	                img.src = e.target.result;
	                previewContainer.appendChild(img);
	            };
	            reader.readAsDataURL(files[i]);
	        }
	    }
	}
})
