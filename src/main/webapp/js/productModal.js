
  document.addEventListener("DOMContentLoaded", function() {
    // 모든 사이즈 모달 열기/닫기
    document.getElementById("size-dropdown").addEventListener("click", function () {
      document.getElementById("size-modal").style.display = "flex";
    });
    document.getElementById("modal-close").addEventListener("click", function () {
      document.getElementById("size-modal").style.display = "none";
    });

    // 구매 모달 열기/닫기
    document.getElementById("buy-btn").addEventListener("click", function() {
      document.getElementById("buy-modal").style.display = "flex";
    });
    document.getElementById("buy-modal-close").addEventListener("click", function() {
      document.getElementById("buy-modal").style.display = "none";
    });

    // 판매 모달 열기/닫기
    document.getElementById("sell-btn").addEventListener("click", function() {
      document.getElementById("sell-modal").style.display = "flex";
    });
    document.getElementById("sell-modal-close").addEventListener("click", function() {
      document.getElementById("sell-modal").style.display = "none";
    });
  });
