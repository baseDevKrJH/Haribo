package com.jelly.www.action;

import com.jelly.www.dao.ProductDAO;
import com.jelly.www.vo.ProductVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.*;

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

        // 고정 사이즈 목록 (210~290)
        List<String> sizeList = Arrays.asList("210", "220", "230", "240", "250", "260", "270", "280", "290");
        
        // 사이즈별 가격을 저장 (사이즈 -> 가격)
        Map<String, Integer> sizePriceMap = new HashMap<>();
        for (ProductVO sizePrice : productDAO.selectSizesAndPricesByProductId(productId)) {
            Integer price = sizePrice.getPrice();
            if (price == null || price == 0) { 
                sizePriceMap.put(sizePrice.getSize(), -1); // 가격이 null 또는 0인 경우 -1로 설정
            } else {
                sizePriceMap.put(sizePrice.getSize(), price);
            }
        }

        // 각 사이즈에 대해 버튼 텍스트 설정 (가격이 없으면 구매 입찰)
        Map<String, String> sizeButtons = new HashMap<>();
        for (String size : sizeList) {
            int price = sizePriceMap.getOrDefault(size, -1); // 가격이 없으면 -1로 설정
            if (price == -1) {
                sizeButtons.put(size, "구매 입찰");
            } else {
                sizeButtons.put(size, String.format("%,d원", price));
            }
        }

        // JSP로 전달할 데이터 설정
        request.setAttribute("product", product); // 상품 정보
        request.setAttribute("formattedPrice", formattedPrice); // 포맷팅된 가격
        request.setAttribute("sizeList", sizeList); // 고정 사이즈 리스트
        request.setAttribute("sizeButtons", sizeButtons); // 각 사이즈별 버튼 텍스트

        return "/views/product/productDetail.jsp";
    }
}