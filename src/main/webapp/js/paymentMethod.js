$(() => {
	let paymentMethod = '';

	function setPaymentMethod(method) {
		paymentMethod = method;
		$(".payment-submit-btn").css("background-color", "rgba(255, 127, 80, 1)");
	}

	$("#credit-card").on("click", async () => {
		setPaymentMethod("creditCard");
	});

	$("#naver-pay").on("click", async () => {
		setPaymentMethod("naverPay");
	});

	$("#kakao-pay").on("click", async () => {
		setPaymentMethod("kakaoPay");
	});

})