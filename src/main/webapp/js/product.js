$(document).ready(function () {
  const $filterMenu = $("#filter-menu");
  const $filterBtn = $("#filter-btn");
  const $closeBtn = $("#close-btn");
  const $resetBtn = $("#reset-filter");

  // 필터 메뉴 열기
  $filterBtn.on("click", function () {
    console.log("Filter menu opened");
    $filterMenu.addClass("open");
  });

  // 필터 메뉴 닫기
  $closeBtn.on("click", function () {
    console.log("Filter menu closed");
    $filterMenu.removeClass("open");
  });

  // 초기화 버튼 클릭 시 필터 초기화
  $resetBtn.on("click", function () {
    console.log("Filter options reset");
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

    console.log("Selected Filters:", {
      brands: selectedBrands,
      sizes: selectedSizes,
      price: selectedPrice,
    });

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
        console.log("Server response received:", response);

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

        console.log("Filtered products displayed successfully.");
      },
      error: function (xhr, status, error) {
        console.error("Error during filter request:", error);
        console.error("Response status:", status);
        console.error("XHR response:", xhr.responseText);
      },
    });

    // 필터 메뉴 닫기
    $filterMenu.removeClass("open");
  });
});