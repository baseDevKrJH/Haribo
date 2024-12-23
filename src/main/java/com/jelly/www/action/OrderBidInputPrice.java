package com.jelly.www.action;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/orderBid")
public class OrderBidInputPrice extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글처
		req.setCharacterEncoding("UTF-8");
		//resp.setContentType("text/html;charset=UTF-8");
		System.out.println("aaaa");
		
		// 요청객체 가져오기(희망 가격)
		String inputPriceParam = req.getParameter("inputPrice");
		
		// 형변환
		int inputPrice = Integer.parseInt(inputPriceParam);
		
		// 응답을 클라이언트에게 전송
//        resp.getWriter().write(""+inputPrice);
        resp.getWriter().print(inputPrice);
		
	}

}
