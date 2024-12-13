/* 무한스크롤 js */

$(document).ready(function () {
  let currentPage = 1; // 현재 페이지
  let isLoading = false; // 데이터 로딩 상태

  const loadProducts = () => {
    if (isLoading) return; // 로딩 중이면 중복 요청 방지
    isLoading = true;

    $.ajax({
      url: "/jelly/filterProducts", // 서버 요청 URL
      type: "GET",
      data: { page: currentPage }, // 현재 페이지 정보 전달
      success: function (response) {
        const $productList = $("#product-list");
        if (response.length > 0) {
          response.forEach((product) => {
            $productList.append(`
              <div class="product-card">
                <div class="image-wrapper">
                  <img src="${product.imageUrl}" alt="${product.name}">
                </div>
                <div class="brand">${product.brand}</div>
                <div class="product-name">${product.name}</div>
                <div class="price">${product.initialPrice.toLocaleString()}원</div>
              </div>
            `);
          });
          currentPage++; // 페이지 번호 증가시킴
        } else {
          $(window).off("scroll"); // 더 이상 데이터가 없을 경우 스크롤 이벤트 제거하고
          $productList.append("<p>더 이상 상품이 없습니다.</p>");
        }
        isLoading = false; // 로딩 상태 초기화시킴
      },
      error: function () {
        alert("상품을 불러오는 중 문제가 발생했습니다.");
        isLoading = false;
      },
    });
  };

  // 스크롤 이벤트 처리
  $(window).on("scroll", function () {
    if ($(window).scrollTop() + $(window).height() >= $(document).height() - 100) {
      loadProducts(); // 페이지 하단 도달 시 데이터 요청함
    }
  });

  // 초기 데이터를 로드해줌
  loadProducts();
});