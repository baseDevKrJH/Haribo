package com.jelly.www.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.jelly.www.dao.CommentDAO;
import com.jelly.www.dao.UserDAO;
import com.jelly.www.vo.CommentVO;
import com.jelly.www.vo.StyleCommentVO;
import com.jelly.www.vo.StylePostInfoVO;
import com.jelly.www.vo.UserVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/comment")
public class CommentController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		JSONObject jsonResponse = new JSONObject();
		String url = "";
    	
    	HttpSession session = req.getSession();
        UserVO user = (UserVO) session.getAttribute("user");
		
        if (user == null) { // 로그인이 되어 있지 않을 경우
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 401 상태
            jsonResponse.put("error", "로그인이 필요합니다.");
        }
        
		System.out.println("successfully post controller comment called");
		String comment = req.getParameter("comment");
		int postId = Integer.parseInt(req.getParameter("postId"));
		
		int userId = user.getUserId();
		
		
		CommentDAO commentDAO = new CommentDAO();
		UserDAO userDAO = new UserDAO();
		ArrayList<CommentVO> list = commentDAO.getCommentOfPost(postId);
		ArrayList<StyleCommentVO> styleCommentInfo = new ArrayList<>();
		for(CommentVO vo: list) {
			// get user profile picture and user nickname
			UserVO userVO = userDAO.selectOne(userId);
			userVO.getNickname();
			userVO.getProfileImage();

			
			StyleCommentVO obj = new StyleCommentVO(
					vo.getCommentId(),
					vo.getPostId(),
					vo.getUserId(),
					vo.getContent(),
					vo.getLikesCount(),
					vo.getCreatedAt(),
					vo.getUpdatedAt(),
					userVO.getProfileImage(),
					userVO.getNickname()
					);
			styleCommentInfo.add(obj);
			
			
		}
		req.setAttribute("commentList", styleCommentInfo);
		url = "/views/style/singleComment.jsp";

		// forward or redirect
        if (url != null && url.startsWith("redirect:")) {
            resp.sendRedirect(url.substring("redirect:".length()));
            return;
        }
	    if (url != null && !resp.isCommitted()) {
	        RequestDispatcher rd = req.getRequestDispatcher(url);
	        rd.forward(req, resp);
	    } 
		
	}
}
