package com.jelly.www.action;

import java.io.IOException;

import java.lang.reflect.Parameter;

import com.jelly.www.dao.AddressDAO;
import com.jelly.www.dao.ProductDAO;
import com.jelly.www.dao.TradeDAO;
import com.jelly.www.vo.AddressVO;
import com.jelly.www.vo.ProductVO;
import com.jelly.www.vo.TradeVO;
import com.jelly.www.vo.UserVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/buyConfirm")
public class InsertBuyData extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tradeIdParam = req.getParameter("paymentId"); // 주문 번호
		String totalPriceParam = req.getParameter("totalAmount"); // 상품가격
		String productIdParam = req.getParameter("productId");
		String sizeParam = req.getParameter("size");

		int tradeId = Integer.parseInt(tradeIdParam);
		int totalPrice = Integer.parseInt(totalPriceParam);
		int productId = Integer.parseInt(productIdParam);
		int size = Integer.parseInt(sizeParam);

		ProductDAO productDAO = new ProductDAO();
		ProductVO product = productDAO.selectOne(productId);
		AddressDAO addressDAO = new AddressDAO();

		// 세션에 있는 사용자 id 가져오기
		HttpSession session = req.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		int userId = user.getUserId();
		AddressVO address = addressDAO.selectOne(userId);
		int addressId = address.getAddressId();

		if (userId != 0) {
			// TRADE 테이블에 데이터 삽입 (필요한것만 넣었는데.. 어케 될지 모름)
			TradeDAO tradeDAO = new TradeDAO();
			TradeVO trade = TradeVO.builder()
					.tradeId(tradeId)
					.productSellerId(1) // 이부분 not null 로 되어있어서 우선 0으로 함
					.buyerId(userId)
					.addressId(addressId)
					.couponId(1)
					.totalPrice(totalPrice)
					.tradeStatus(1)
					.build();
			tradeDAO.insertBuyOne(trade, productId);

		}
	}
}
