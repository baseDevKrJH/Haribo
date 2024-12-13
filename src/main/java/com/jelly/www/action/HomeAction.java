package com.jelly.www.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomeAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // currentPage 설정
        request.setAttribute("currentPage", "home");

        // View 경로 반환
        return "/views/home/home.jsp";
    }
}