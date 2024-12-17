package com.jelly.www.action;

import com.jelly.www.dao.UserDAO;
import com.jelly.www.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WishAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		String u = request.getParameter("user_id");
		
		if ( u != null) {
			int userId = Integer.parseInt(u);
			
			UserDAO uDao = new UserDAO();
			UserVO uVo = uDao.selectOne(userId);
			
			
			
		}
		
	    return "/views/wish/wish.jsp";
	}
}
