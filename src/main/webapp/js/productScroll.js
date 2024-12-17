$(document).ready(function () {
    let currentPage = 1;
    const $productContainer = $("#product-container");
    const $loading = $("#loading");
    let isLoading = false;

    $(window).on("scroll", function () {
        if (!isLoading && $(window).scrollTop() + $(window).height() >= $(document).height() - 100) {
            loadMoreProducts();
        }
    });

    function loadMoreProducts() {
        isLoading = true;
        currentPage++;
        $loading.fadeIn();

        $.ajax({
            url: "<%= request.getContextPath() %>/jelly",
            type: "GET",
            data: { page: "shoes", pageNo: currentPage },
            success: function (data) {
                if ($.trim(data) === "") {
                    console.log("No more data to load.");
                } else {
                    setTimeout(function () {
                        $productContainer.append(data); // 상품 리스트 HTML 추가
                    }, 700); // 0.7초 로딩
                }
            },
            error: function (xhr, status, error) {
                console.error("Error loading products:", error);
            },
            complete: function () {
                setTimeout(function () {
                    $loading.fadeOut();
                }, 1000); // 1초 후 로딩 숨김
                isLoading = false;
            }
        });
    }
});