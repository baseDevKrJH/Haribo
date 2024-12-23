package com.jelly.www.action;

import java.io.IOException;

import com.jelly.www.dao.AddressDAO;
import com.jelly.www.dao.ProductDAO;
import com.jelly.www.dao.ProductSellerDAO;
import com.jelly.www.dao.SizeDAO;
import com.jelly.www.dao.TradeDAO;
import com.jelly.www.vo.AddressVO;
import com.jelly.www.vo.ProductSellerVO;
import com.jelly.www.vo.ProductVO;
import com.jelly.www.vo.SizeVO;
import com.jelly.www.vo.TradeVO;
import com.jelly.www.vo.UserVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/sellConfirm")
public class InsertSellData extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tradeIdParam = req.getParameter("tradeId"); // 주문 번호
		String productName = req.getParameter("productName"); // 상품명
		String totalPriceParam = req.getParameter("totalPrice"); // 상품가격
		String productIdParam = req.getParameter("productId");
		String sizeParam = req.getParameter("size");

		int tradeId = Integer.parseInt(tradeIdParam); // sellconfirm.jsp 만들기
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
		
		// userId로 주소정보 가져오기
		AddressVO address = addressDAO.selectOne(userId);
		int addressId = address.getAddressId();
		
		// sizeId 가져오기
		SizeDAO sizeDAO = new SizeDAO();
		SizeVO sizeVO = sizeDAO.selectSizeIdByProductIdAndSize(size, productId);
		int sizeId = sizeVO.getSizeId();
		
		if (userId != 0) {
			// product_seller 테이블에 데이터 삽입
			ProductSellerDAO productSellerDAO = new ProductSellerDAO();
			ProductSellerVO productSeller = ProductSellerVO.builder()
					.productSellerId(0)
					.productId(productId)
					.sellerId(userId)
					.sizeId(sizeId)
					.price(totalPrice)
					.stock(1)
					.build();
			productSellerDAO.insertSellData(productSeller);
			System.out.println("productSeller : " + productSeller);

		} 

			
	}

}
