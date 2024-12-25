package com.jelly.www.action;

import java.text.DecimalFormat;
import java.util.List;

import com.jelly.www.dao.AddressDAO;
import com.jelly.www.dao.ProductDAO;
import com.jelly.www.vo.AddressVO;
import com.jelly.www.vo.ProductVO;
import com.jelly.www.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class BuyAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		// 요청 파라미터 가져오기
		String productIdParam = request.getParameter("productId");
		String sizeParam = request.getParameter("size");
		String priceParam = request.getParameter("price");

		// 세션에 있는 사용자 정보 가져오기
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		int userId = user.getUserId();

		if (userId != 0) {
			// 형변환
			int productId = Integer.parseInt(productIdParam);
			int size = Integer.parseInt(sizeParam);
			int price = Integer.parseInt(priceParam);

			// 상품정보 가져오기
			ProductDAO productDAO = new ProductDAO();
			ProductVO product = productDAO.selectOne(productId);
			
			// userId로 주소 정보 조회
			AddressDAO addressDAO = new AddressDAO();
			AddressVO defaultAddress = addressDAO.selectDefaultAddressOne(userId); // 사용자의 기본주소
			List<AddressVO> addressList = addressDAO.selectAddressAllExceptDefault(userId);
			
			// 가격 포맷팅
			DecimalFormat df = new DecimalFormat("#,###");
			String formattedPrice = df.format(price);
			
			// 사용자가 소지한 쿠폰 조회
			

			// 상품정보, 사용자정보, 주소정보, 가격, 사이즈를 요청객체에 전달
			request.setAttribute("product", product); // 상품 정보
			request.setAttribute("user", user); // 사용자 정보
			request.setAttribute("defaultAddress", defaultAddress); // 사용자의 기본주소 정보
			request.setAttribute("addressList", addressList); // 사용자의 전체 주소 정보
			request.setAttribute("price", price); // 사이즈별 가격
			request.setAttribute("formattedPrice", formattedPrice); // 포맷팅 처리된 가
			request.setAttribute("size", size);
			
		}
		return "/views/order/buy.jsp";
	}

}