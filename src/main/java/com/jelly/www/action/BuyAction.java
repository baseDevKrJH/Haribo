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
import jakarta.servlet.http.HttpSession;

public class BuyAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		// 세션에 있는 user 정보 가져오기
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		UserVO userVO = (UserVO) obj;
		int userId = userVO.getUserId();

		// 세션에 있는 userId가 0이 아니라면
		if (userId != 0) {
			// 요청객체 파라미터 가져오기
			String productIdParam = request.getParameter("productId");
			String userIdParam = request.getParameter("userId");
			String sizeParam = request.getParameter("size");
			int productId = Integer.parseInt(productIdParam); // 상품 아이디
			int size = Integer.parseInt(sizeParam); // 사이즈

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
			ProductVO sizeAndPrice = productDAO.selectPriceByProductIdAndSize(productId, size);
			int price = sizeAndPrice.getPrice(); // 사이즈별 가격
			DecimalFormat df = new DecimalFormat("#,###");
			String formattedPrice = df.format(price);

			// 상품정보, 사용자정보, 주소정보, 가격, 사이즈를 요청객체에 전달
			request.setAttribute("product", product); // 상품 정
			request.setAttribute("user", user); // 사용자 정보
			request.setAttribute("address", address); // 사용자의 주소 정보
			request.setAttribute("price", price); // 사이즈별 가격
			request.setAttribute("formattedPrice", formattedPrice); // 포맷팅 처리된 가
			request.setAttribute("size", size);
		}
		return "/views/order/buy.jsp";

	}
}