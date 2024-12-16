package com.jelly.www.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jelly.www.dao.ProductDAO;
import com.jelly.www.vo.ProductVO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/filterProducts")
public class FilterController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // JSON 요청 데이터 읽기
        StringBuilder jsonBuffer = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            jsonBuffer.append(line);
        }
        String jsonString = jsonBuffer.toString();

        // JSON 데이터 파싱
        JSONParser parser = new JSONParser();
        List<String> brandList = new ArrayList<>();
        List<String> sizeList = new ArrayList<>();
        String price = null;

        try {
            JSONObject json = (JSONObject) parser.parse(jsonString);

            // 브랜드 추출함
            JSONArray brands = (JSONArray) json.get("brands");
            if (brands != null) {
                for (Object brand : brands) {
                    brandList.add(brand.toString());
                }
            }

            // 사이즈 추출함
            JSONArray sizes = (JSONArray) json.get("sizes");
            if (sizes != null) {
                for (Object size : sizes) {
                    sizeList.add(size.toString());
                }
            }

            // 가격 추출함
            price = json.get("price") != null ? json.get("price").toString() : null;

        } catch (ParseException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{error}");
            return;
        }

        // DAO 호출
        ProductDAO productDAO = new ProductDAO();
        List<ProductVO> filteredProducts = productDAO.filterByBrandsSizesAndPrice(brandList, sizeList, price);

        // JSON 응답 생성
        JSONArray responseArray = new JSONArray();
        for (ProductVO product : filteredProducts) {
            JSONObject productJson = new JSONObject();
            productJson.put("productId", product.getProductId());
            productJson.put("name", product.getProductName());
            productJson.put("description", product.getDescription());
            productJson.put("brand", product.getBrand());
            productJson.put("releaseDate", product.getReleaseDate() != null ? product.getReleaseDate().toString() : null);
            productJson.put("initialPrice", product.getInitialPrice());
            productJson.put("modelNumber", product.getModelNumber());
            productJson.put("categoryId", product.getCategoryId());
            productJson.put("imageUrl", product.getImageUrl());
            productJson.put("isActive", product.isActive());
            responseArray.add(productJson);
        }

        // 응답
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(responseArray.toJSONString());

        // 자원 해제
        productDAO.close();
    }
}