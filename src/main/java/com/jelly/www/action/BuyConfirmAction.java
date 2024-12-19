package com.jelly.www.action;


import java.io.IOException;
import java.lang.reflect.Parameter;

import com.jelly.www.dao.TradeDAO;
import com.jelly.www.vo.TradeVO;
import com.jelly.www.vo.UserVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/buyConfirm")
public class BuyConfirmAction extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tradeIdParam = req.getParameter("paymentId"); // 주문 번호
		String txId = req.getParameter("txId"); // 트랜잭션 아이디 (굳이 db에 넣을 필요 없음)
												// -> 거래 플랫폼에서 임의로 만들어주는 유니크값 이걸로 결제 플랫폼에서 조회하는 것
		String productName = req.getParameter("orderName"); // 상품명
		String totalPriceParam = req.getParameter("totalAmount"); // 상품가격

		int tradeId = Integer.parseInt(tradeIdParam);
		int totalPrice = Integer.parseInt(totalPriceParam);

		// 세션에 있는 사용자 id 가져오기
		HttpSession session = req.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		int userId = user.getUserId();

		if (userId != 0) {
			// TRADE 테이블에 데이터 삽입 (필요한것만 넣었는데.. 어케 될지 모름)
			TradeDAO tradeDAO = new TradeDAO();
			TradeVO trade = TradeVO.builder().tradeId(tradeId).productSellerId(0) // 이부분 not null 로 되어있어서 우선 0으로 함
					.buyerId(userId).totalPrice(totalPrice).build();
			tradeDAO.insertBuyOne(trade);
		} else {
			resp.sendRedirect("/views/login/login.jsp");
		}

		// 여기는 일단 해놓고 나중에 jsp 파일만들고 추가하기
		req.setAttribute("tradeId", tradeId);
		req.setAttribute("productName", productName);
		// resp.sendRedirect("/views/order/buyConfirm.jsp"); 여기는 db에 넣고 끝! redirect 하면 안됨 -> ajax success에서 해줘야

	}
}
