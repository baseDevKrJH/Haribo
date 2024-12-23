package com.jelly.www.action;

import java.text.DecimalFormat;

import com.jelly.www.dao.AddressDAO;
import com.jelly.www.dao.ProductDAO;
import com.jelly.www.dao.UserAccountDAO;
import com.jelly.www.dao.UserDAO;
import com.jelly.www.vo.AddressVO;
import com.jelly.www.vo.ProductVO;
import com.jelly.www.vo.UserAccountVO;
import com.jelly.www.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class BuyAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		// 요청 객체 파라미터 가져오기
		String productIdParam = request.getParameter("productId");
		String sizeParam = request.getParameter("size");
		String priceParam = request.getParameter("price"); // 판매입찰 시 가져오게 되는 상품 가격

		// 세션에 있는 user 정보 가져오기
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		UserVO user = (UserVO) obj;
		int userId = user.getUserId();

		// 전역 변수 선언
		int productId = 0;
		int size = 0;
		ProductVO product = null;
		ProductVO sizeAndPrice = null;
		AddressVO address = null;
		UserAccountVO defaultAccount = null;
		int price = 0;
		String formattedPrice = null;

		// 가격 포맷
		DecimalFormat df = new DecimalFormat("#,###");

		// 세션에 있는 userId가 0이 아니라면
		if (userId != 0 && priceParam == null) {
			// 요청객체 파라미터 가져오기
			productId = Integer.parseInt(productIdParam); // 상품 아이디
			size = Integer.parseInt(sizeParam); // 사이즈

			// ProductDAO로 상품정보 조회
			ProductDAO productDAO = new ProductDAO();
			product = productDAO.selectOne(productId);

			// userId로 주소 정보 조회
			AddressDAO addressDAO = new AddressDAO();
			address = addressDAO.selectOne(userId);

			// 가격 포맷팅 처리
			sizeAndPrice = productDAO.selectPriceByProductIdAndSize(productId, size);
			price = sizeAndPrice.getPrice(); // 사이즈별 가격
			formattedPrice = df.format(price);

		} else if (userId != 0 && priceParam != null) { // 상품가격이 null이 아니면(판매입찰이면)

			// 형변환
			productId = Integer.parseInt(productIdParam);
			size = Integer.parseInt(sizeParam);
			price = Integer.parseInt(priceParam);

			// product VO 가져오기
			ProductDAO productDAO = new ProductDAO();
			product = productDAO.selectOne(productId);

			// address VO 가져오기
			AddressDAO addressDAO = new AddressDAO();
			address = addressDAO.selectOne(userId);

			// userAccount VO 가져오기
			UserAccountDAO accountDAO = new UserAccountDAO();
			defaultAccount = accountDAO.selectIsDefaultAccountByUserId(userId);

			
			
		}
		// 상품정보, 사용자정보, 주소정보, 가격, 사이즈를 요청객체에 전달
		request.setAttribute("product", product); // 상품 정
		request.setAttribute("user", user); // 사용자 정보
		request.setAttribute("address", address); // 사용자의 주소 정보
		request.setAttribute("price", price); // 사이즈별 가격
		request.setAttribute("formattedPrice", formattedPrice); // 포맷팅 처리된 가
		request.setAttribute("size", size);
		return "/views/order/buy.jsp";

	}
}