package com.jelly.www.action;

import com.jelly.www.dao.ProductDAO;
import com.jelly.www.dao.ProductSellerDAO;
import com.jelly.www.vo.ProductVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.*;

public class ProductDetailAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // productId 가져오기
        String productIdParam = request.getParameter("productId");
        int productId;

        try {
            productId = Integer.parseInt(productIdParam);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "유효하지 않은 상품 ID입니다.");
            return "/views/error/404.jsp";
        }

        // DAO 객체 생성
        ProductDAO productDAO = new ProductDAO();
        ProductSellerDAO productSellerDAO = new ProductSellerDAO();

        // 상품 상세 정보 조회
        ProductVO product = productDAO.selectOne(productId);

        if (product == null) {
            request.setAttribute("error", "해당 상품을 찾을 수 없습니다.");
            return "/views/error/404.jsp";
        }

        // ProductSellerDAO의 메서드를 통해 최저가 조회
        int lowestPrice = productSellerDAO.getLowestPrice(productId);

        // 모델 번호 포맷팅
        String formattedModelNumber = productDAO.getFormattedModelNumber(productId);
        request.setAttribute("formattedModelNumber", formattedModelNumber);
        
        // 가격 포맷팅 처리
        DecimalFormat df = new DecimalFormat("#,###");
        String formattedPrice = df.format(product.getInitialPrice()); // 발매가
        String formattedLowestPrice = lowestPrice > 0 ? df.format(lowestPrice) : "가격 정보 없음";

        // 고정 사이즈 목록 (210~290)
        List<String> sizeList = Arrays.asList("210", "220", "230", "240", "250", "260", "270", "280", "290");

        // ProductDAO의 메서드를 통해 사이즈별 가격 조회
        Map<String, Integer> sizePriceMap = new HashMap<>();
        for (ProductVO sizePrice : productDAO.selectSizesAndPricesByProductId(productId)) {
            Integer price = sizePrice.getPrice();
            if (price == null || price == 0) {
                sizePriceMap.put(sizePrice.getSize(), -1); // 가격이 없으면 -1로 설정
            } else {
                sizePriceMap.put(sizePrice.getSize(), price); // 실제 가격 
            }
        }

        // 각 사이즈에 대해 버튼 텍스트 설정 (가격이 없으면 구매 입찰)
        Map<String, String> sizeButtons = new HashMap<>();
        for (String size : sizeList) {
            int price = sizePriceMap.getOrDefault(size, -1);
            if (price == -1) {
                sizeButtons.put(size, "구매 입찰");
            } else {
                sizeButtons.put(size, String.format("%,d원", price));
            }
        }

        // JSP로 전달할 데이터 설정
        request.setAttribute("product", product); // 상품 정보
        request.setAttribute("formattedPrice", formattedPrice); // 발매가
        request.setAttribute("formattedLowestPrice", formattedLowestPrice); // 최저가
        request.setAttribute("sizeList", sizeList); // 고정 사이즈 리스트
        request.setAttribute("sizeButtons", sizeButtons); // 사이즈별 버튼 텍스트

        return "/views/product/productDetail.jsp";
    }
}