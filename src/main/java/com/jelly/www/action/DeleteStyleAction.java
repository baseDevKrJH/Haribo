package com.jelly.www.action;

import com.jelly.www.dao.PostDAO;
import com.jelly.www.dao.PostImageDAO;
import com.jelly.www.dao.PostTagDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteStyleAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String pId = request.getParameter("postId");
		System.out.println("postId: " + pId);

		// Delete관련해서는 항상 잘 고민해볼것!
		// 실제로 지우는것 or flag값 처리 (ex. delete_yn) -> 운영적 측면 및 사이드임팩트 체크
		if(pId != null) {
			int postId = Integer.parseInt(pId);
			PostDAO postDAO = new PostDAO();
			PostImageDAO imageDAO = new PostImageDAO();
			PostTagDAO postTagDao = new PostTagDAO();
			imageDAO.deleteImageOfPost(postId);
			postTagDao.deleteTagOfPost(postId);
			postDAO.deleteOne(postId);
			
			postDAO.close();
			imageDAO.close();
		}
		
		
		return "/views/style/styleList.jsp";
	}
}
