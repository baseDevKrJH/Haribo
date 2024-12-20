package com.jelly.www.action;

import com.jelly.www.dao.ProductDAO;
import com.jelly.www.vo.ProductVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BuyConfirmAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String productIdParam = request.getParameter("productId");
		int productId = Integer.parseInt(productIdParam);
		
		ProductDAO productDAO = new ProductDAO();
		ProductVO product = productDAO.selectOne(productId);
		
		request.setAttribute("product", product);
		
		return "/views/order/buyConfirm.jsp";
	}

}
