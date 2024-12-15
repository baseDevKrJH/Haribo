package com.jelly.www.controller;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/likePost")
public class LikePostController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 요청 데이터 읽기
    	int postId = Integer.parseInt(request.getParameter("postId"));
    	int userId = Integer.parseInt(request.getParameter("userId"));

        // DAO 호출

        // JSON 응답 생성
        JSONArray responseArray = new JSONArray();
        JSONObject json = new JSONObject();
        responseArray.add(json);

        // 응답
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(responseArray.toJSONString());
    }
}