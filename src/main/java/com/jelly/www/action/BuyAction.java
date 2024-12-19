package com.jelly.www.action;

import java.text.DecimalFormat;

import com.jelly.www.dao.AddressDAO;
import com.jelly.www.dao.ProductDAO;
import com.jelly.www.dao.UserDAO;
import com.jelly.www.vo.AddressVO;
import com.jelly.www.vo.ProductVO;
import com.jelly.www.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BuyAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		// productId 파라미터 가져오기
		String productIdParam = request.getParameter("productId");
		String userIdParam = request.getParameter("userId");
		String sizeParam = request.getParameter("size");
		int productId = Integer.parseInt(productIdParam);
		int userId = Integer.parseInt(userIdParam);
		int size = Integer.parseInt(sizeParam);
		
		// ProductDAO로 상품정보 조회
		ProductDAO productDAO = new ProductDAO();
		ProductVO product = productDAO.selectOne(productId);
		
		// UserDAO로 사용자 정보 조회
		UserDAO userDAO = new UserDAO();
		UserVO user = userDAO.selectOne(userId);
		
		// userId로 주소 정보 조회
		AddressDAO addressDAO = new AddressDAO();
		AddressVO address = addressDAO.selectOne(userId);
		
		// 가격 포맷팅 처리
		DecimalFormat df = new DecimalFormat("#,###");
		String formattedPrice = df.format(product.getInitialPrice());
		
		
		// 상품정보, 사용자정보, 주소정보, 가격, 사이즈를 요청객체에 전달
		request.setAttribute("product", product);
		request.setAttribute("user", user);
		request.setAttribute("address", address);
		request.setAttribute("formattedPrice", formattedPrice);
//		request.setAttribute("size", size);
		
		
		return "/views/order/buy.jsp";
	

}
}