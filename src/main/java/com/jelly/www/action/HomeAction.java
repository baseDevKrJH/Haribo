package com.jelly.www.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.jelly.www.dao.ProductDAO;
import com.jelly.www.vo.ProductVO;

import java.util.List;

public class HomeAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // currentPage 설정
        request.setAttribute("currentPage", "home");

        // 인기 상품 데이터 조회
        ProductDAO productDAO = new ProductDAO();
        List<ProductVO> popularProducts = productDAO.getPopularProducts(); // 인기 상품 데이터 조회
        request.setAttribute("popularProducts", popularProducts); // 인기 상품 데이터 저장


        return "/views/home/home.jsp"; // 홈 페이지로 이동
    }
}