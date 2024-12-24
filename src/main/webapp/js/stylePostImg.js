$(document).ready(function () {
  const $slider = $(".slider-wrapper");
  const $slides = $(".slider-image");
  const $prevBtn = $("#prev-btn");
  const $nextBtn = $("#next-btn");
  let currentIndex = 0;
  const totalSlides = $slides.length;

  function updateSlider() {
    const offset = -currentIndex * $slides.parent().width();
    $slider.css("transform", `translateX(${offset}px)`);
  }

  $prevBtn.on("click", function () {
    currentIndex = (currentIndex > 0) ? currentIndex - 1 : totalSlides - 1;
    updateSlider();
  });

  $nextBtn.on("click", function () {
    currentIndex = (currentIndex < totalSlides - 1) ? currentIndex + 1 : 0;
    updateSlider();
  });

  $(window).on("resize", function () {
    updateSlider(); // 창 크기 변경 할 때 슬라이드 위치 변경
  });

  // 초기화
  updateSlider();
});