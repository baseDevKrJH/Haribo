$(document).ready(function () {
  const $filterMenu = $("#filter-menu");
  const $filterBtn = $("#filter-btn");
  const $closeBtn = $("#close-btn");
  const $resetBtn = $("#reset-filter");

  // 필터 메뉴 열기
  $filterBtn.on("click", function () {
    $filterMenu.addClass("open");
  });

  // 필터 메뉴 닫기
  $closeBtn.on("click", function () {
    $filterMenu.removeClass("open");
  });

  // 초기화 버튼 클릭 시 필터 초기화
  $resetBtn.on("click", function () {
    $(".filter-options input").prop("checked", false);
  });

  // 필터 적용 버튼 클릭 시
  $("#apply-filter").on("click", function () {
    const selectedBrands = [];
    const selectedSizes = [];
    let selectedPrice = null;

    // 선택된 필터 값 수집
    $(".filter-options input[name='brand']:checked").each(function () {
      selectedBrands.push($(this).val());
    });
    $(".filter-options input[name='size']:checked").each(function () {
      selectedSizes.push($(this).val());
    });
    selectedPrice = $(".filter-options input[name='price']:checked").val();

    // Ajax로 서버에 필터 데이터 전송
    $.ajax({
      url: "filterProducts", // 필터링된 상품 데이터를 받는 서버 URL
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify({
        brands: selectedBrands,
        sizes: selectedSizes,
        price: selectedPrice,
      }),
      success: function (response) {
        const $productList = $("#product-list");
        $productList.empty(); // 기존 상품 리스트를 비움

        // 필터링된 상품 리스트 추가
        response.forEach((product) => {
          $productList.append(`
            <a href="${window.location.origin}/haribo/jelly?page=productDetail&productId=${product.productId}" class="product-card">
              <div class="image-wrapper">
                <img src="${product.imageUrl}" alt="${product.name}">
              </div>
              <div class="brand">${product.brand}</div>
              <div class="product-name">${product.name}</div>
              <div class="price">${product.initialPrice}원</div>
            </a>
          `);
        });
      },
      error: function (xhr, status, error) {
        console.error("Error:", error);
      },
    });

    // 필터 메뉴 닫기
    $filterMenu.removeClass("open");
  });
});