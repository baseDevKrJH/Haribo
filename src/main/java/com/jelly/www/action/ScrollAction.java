package com.jelly.www.action;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.jelly.www.dao.ProductDAO;
import com.jelly.www.vo.ProductVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ScrollAction {

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int page = Integer.parseInt(req.getParameter("page"));
        int limit = 10;

        ProductDAO dao = new ProductDAO();
        List<ProductVO> productList = dao.selectByPage(page, limit);

        // JSON 배열 생성
        JSONArray jsonArray = new JSONArray();
        for (ProductVO product : productList) {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("imageUrl", product.getImageUrl());
            jsonObj.put("name", product.getProductName());
            jsonObj.put("brand", product.getBrand());
            jsonObj.put("initialPrice", product.getInitialPrice());
            jsonArray.add(jsonObj);
        }

        // JSON 응답
        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().write(jsonArray.toJSONString());
    }
}