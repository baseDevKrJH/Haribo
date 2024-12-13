package com.jelly.www.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WishAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
	    return "/views/wish/wish.jsp";
	}
}
