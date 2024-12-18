package com.jelly.www.action;

import com.google.gson.JsonObject;
import com.jelly.www.dao.WishlistDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WishlistToggleAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 사용자 ID와 상품 ID를 요청에서 가져옴
            int userId = Integer.parseInt(request.getParameter("userId"));
            int productId = Integer.parseInt(request.getParameter("productId"));

            // userId productId 출력
            // System.out.println("userId: " + userId + ", productId: " + productId);

            WishlistDAO dao = new WishlistDAO();
            boolean isExists = dao.isWishlistExists(userId, productId);

            JsonObject jsonResponse = new JsonObject();

            // 관심상품이 이미 존재하면 삭제, 존재하지 않으면 추가
            if (isExists) {
                // 관심상품 삭제
                dao.removeWishlist(userId, productId);
                // System.out.println("Wishlist userId: " + userId + ", productId: " + productId);
                jsonResponse.addProperty("status", "removed");
            } else {
                // 관심상품 추가
                dao.addWishlist(userId, productId);
                // System.out.println("Wishlist userId: " + userId + ", productId: " + productId);
                jsonResponse.addProperty("status", "added"); 
            }

            // 응답을 JSON 형식으로 반환
            response.setContentType("application/json");
            response.getWriter().write(jsonResponse.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}