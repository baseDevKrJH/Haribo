package com.jelly.www.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PopularAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

        return "/views/popular/popular.jsp";
	}

}
