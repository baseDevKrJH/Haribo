package com.jelly.www.action;

import java.text.DateFormat;
import java.text.DecimalFormat;

import com.jelly.www.dao.AddressDAO;
import com.jelly.www.dao.ProductDAO;
import com.jelly.www.dao.ProductSellerDAO;
import com.jelly.www.dao.UserAccountDAO;
import com.jelly.www.vo.AddressVO;
import com.jelly.www.vo.ProductSellerVO;
import com.jelly.www.vo.ProductVO;
import com.jelly.www.vo.UserAccountVO;
import com.jelly.www.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SellAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		// 세션에 있는 user 정보 가져오기
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		UserVO user = (UserVO) obj;
		int userId = user.getUserId();
		

		// 세션에 있는 userId가 0이 아니라면
		if (userId != 0) {

			// 요청 객체 파라미터 가져오기
			String productIdParam = request.getParameter("productId");
			String sizeParam = request.getParameter("size");
			int productId = Integer.parseInt(productIdParam);
			int size = Integer.parseInt(sizeParam);
			
			
			// int price = Integer.parseInt(formattedPriceParam.replace("[^//d]", ""));
			
			// product VO 가져오기
			ProductDAO productDAO = new ProductDAO();
			ProductVO product = productDAO.selectOne(productId);

			// address VO 가져오기
			AddressDAO addressDAO = new AddressDAO();
			AddressVO address = addressDAO.selectOne(userId);

			// userAccount VO 가져오기
			UserAccountDAO accountDAO = new UserAccountDAO();
			UserAccountVO defaultAccount = accountDAO.selectIsDefaultAccountByUserId(userId);
			
			// 사이즈별 가격 가져오기
			ProductVO sizeAndPrice = productDAO.selectPriceByProductIdAndSize(productId, size);
			int price = sizeAndPrice.getPrice(); // 사이즈별 가격 
			System.out.println("price : " + price);
			// 가격 포맷팅 처리
			DecimalFormat df = new DecimalFormat("#,###");
			String formattedPrice = df.format(price); // 사이즈별 가격 포맷팅 처리
			
			request.setAttribute("product", product); // 상품 정보
			request.setAttribute("address", address); // 사용자 주소 정보
			request.setAttribute("defaultAccount", defaultAccount); // 사용자의 기본 계좌 정보
			request.setAttribute("price", price); // 사이즈별 가격
			request.setAttribute("formattedPrice", formattedPrice); // 포맷팅처리한 가격
			request.setAttribute("size", size); // 사이즈 
		}
		return "/views/order/sell.jsp";
	}
	

	

}
