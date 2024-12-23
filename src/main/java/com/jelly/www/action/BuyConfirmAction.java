package com.jelly.www.action;

import com.jelly.www.dao.ProductDAO;
import com.jelly.www.vo.ProductVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BuyConfirmAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 요청 객체 가져오기 & 형변환
		int productId = Integer.parseInt(request.getParameter("productId"));
		
		ProductDAO productDAO = new ProductDAO();
		ProductVO product = productDAO.selectOne(productId);
		
		request.setAttribute("product", product);
		
		return "/views/order/buyConfirm.jsp";
	}

}
