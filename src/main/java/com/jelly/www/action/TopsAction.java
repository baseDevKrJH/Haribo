package com.jelly.www.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.jelly.www.dao.ProductDAO;
import com.jelly.www.vo.ProductVO;

import java.util.List;

public class TopsAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 상의 카테고리 상품 조회
        ProductDAO productDAO = new ProductDAO();
        List<ProductVO> productList = productDAO.selectByCategory("상의");

        // 상품 리스트를 request 속성에 저장
        request.setAttribute("productList", productList);
        request.setAttribute("currentCategory", "상의");

        // 이동할 JSP 경로 반환
        return "/views/product/productTops.jsp";
    }
}