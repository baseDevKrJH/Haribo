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
        int productId = 0;

        // 디버깅 요청된 productId 확인 출
        System.out.println("요청된 productId: " + productIdParam);

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

        // 디버깅 상품 상세 정보 확인 출력
        if (product == null) {
            System.out.println("해당 productId에 대한 상품을 찾을 수 없습니다: " + productId);
            request.setAttribute("error", "해당 상품을 찾을 수 없습니다.");
            return "/views/error/404.jsp";
        } else {
            System.out.println("조회된 상품: " + product);
        }

        // 가격 포맷팅 처리
        DecimalFormat df = new DecimalFormat("#,###");
        String formattedPrice = df.format(product.getInitialPrice());

        // 디버깅 포맷팅된 가격 확인출력
        System.out.println("포맷팅된 가격: " + formattedPrice);

        // 사이즈 및 가격 정보 가져오기
        List<ProductVO> sizePriceList = productDAO.selectSizesAndPricesByProductId(productId);

        // 디버깅 사이즈와 가격 목록 확인 출려ㅕㄱ
        System.out.println("사이즈 및 가격 정보:");
        for (ProductVO sizePrice : sizePriceList) {
            System.out.println("Size: " + sizePrice.getSize() + ", Price: " + sizePrice.getPrice());
        }

        // 상품 정보를 요청 객체에 저장
        request.setAttribute("product", product);
        request.setAttribute("formattedPrice", formattedPrice);
        request.setAttribute("sizePriceList", sizePriceList);

        // JSP로 전달된 데이터 확인 출력
        System.out.println("JSP로 전달된 데이터:");
        System.out.println("Product: " + product);
        System.out.println("Formatted Price: " + formattedPrice);
        System.out.println("SizePriceList: " + sizePriceList);

        // 상품 상세 페이지로 이동
        return "/views/product/productDetail.jsp";
    }
}