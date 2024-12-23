package com.jelly.www.action;

import com.jelly.www.vo.UserVO;
import com.mysql.cj.Session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SellBidAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		UserVO user = (UserVO) obj;
		int userId = user.getUserId();

		if (userId != 0) {
			// 요청객체 가져오기
			String productIdParam = request.getParameter("productId");
			String sizeParam = request.getParameter("size");

			// 형변환
			int productId = Integer.parseInt(productIdParam);
			int size = Integer.parseInt(sizeParam);
			
			

		}
		return "/views/order/sellPrep.jsp";
	}

}
