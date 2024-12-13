package com.jelly.www.action;

import com.jelly.www.dao.ProductDAO;
import com.jelly.www.vo.ProductVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.List;

public class ProductDetailAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 요청 파라미터에서 productId 가져오기
        String productIdParam = request.getParameter("productId");
        int productId;

        try {
            productId = Integer.parseInt(productIdParam);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "유효하지 않은 상품 ID입니다.");
            return "/views/error/404.jsp";
        }

        // DAO를 통해 상품 상세 정보 조회
        ProductDAO productDAO = new ProductDAO();
        ProductVO product = productDAO.selectOne(productId);

        if (product == null) {
            request.setAttribute("error", "해당 상품을 찾을 수 없습니다.");
            return "/views/error/404.jsp";
        }

        // 가격 포맷팅 처리
        DecimalFormat df = new DecimalFormat("#,###");
        String formattedPrice = df.format(product.getInitialPrice());

        // 사이즈 및 가격 정보 가져오기
        List<ProductVO> sizePriceList = productDAO.selectSizesAndPricesByProductId(productId);

        // 가격이 0인 경우 -1로 변환
        for (ProductVO sizePrice : sizePriceList) {
            if (sizePrice.getPrice() == 0) {
                sizePrice.setPrice(-1); // 0 대신 -1로 설정
            }
        }

        // JSP로 전달할 데이터 설정
        request.setAttribute("product", product);
        request.setAttribute("formattedPrice", formattedPrice);
        request.setAttribute("sizePriceList", sizePriceList);

        return "/views/product/productDetail.jsp";
    }
}