document.addEventListener("DOMContentLoaded", function() {
  // 사이즈 드롭다운 버튼 클릭 시 모달 열기
  document.getElementById("size-dropdown").addEventListener("click", function () {
    document.getElementById("size-modal").style.display = "flex";
  });

  // 모달 닫기 버튼 클릭 시 모달 닫기
  document.getElementById("modal-close").addEventListener("click", function () {
    document.getElementById("size-modal").style.display = "none";
  });
});