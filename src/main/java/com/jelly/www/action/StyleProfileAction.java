package com.jelly.www.action;

import com.jelly.www.dao.UserDAO;
import com.jelly.www.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StyleProfileAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String uId = request.getParameter("userId");

		if (uId != null) {
			int userId = Integer.parseInt(uId);
			
			// 유저 가져오기
			UserDAO userDao = new UserDAO();
			UserVO userVo = userDao.selectOne(userId);
			
			
			
			
			
			
			// 요청 파라미터 설정
			request.setAttribute("UserVo", userVo);
		}

		return "/views/style/styleProfile.jsp";
	}

}
