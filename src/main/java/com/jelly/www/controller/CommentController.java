package com.jelly.www.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.jelly.www.dao.CommentDAO;
import com.jelly.www.dao.PostDAO;
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
		System.out.println("in controller comment");
		JSONObject jsonResponse = new JSONObject();
		String url = "";
    	
    	HttpSession session = req.getSession();
        UserVO user = (UserVO) session.getAttribute("user");
		
        if (user == null) { // 로그인이 되어 있지 않을 경우
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 401 상태
        } else {
        	// get parameters
        	int userId = user.getUserId();
    		PostDAO postDAO = new PostDAO();
    		CommentDAO commentDAO = new CommentDAO();
        	int postId = Integer.parseInt(req.getParameter("postId"));
        	String deleteCommentId = req.getParameter("deleteCommentId");
        	String comment = req.getParameter("comment");
        	
        	System.out.println(deleteCommentId);
        	
        	
        	if (deleteCommentId != null) {
        	    System.out.println("postId: " + postId);
        	    System.out.println("delete comment id is not null");
        	    int commentId = Integer.parseInt(deleteCommentId);
        	    System.out.println("commentId: " + commentId);
        	    commentDAO.deleteOne(commentId);
        	    postDAO.minusComment(postId);
        	}
    		
    		if(!comment.trim().equals("") && comment != null && deleteCommentId==null) {
    			System.out.println("inserting new comment");
    			commentDAO.insertOne(new CommentVO(postId, userId, comment));
    			postDAO.plusComment(postId);
    		}
    		
    		
    		UserDAO userDAO = new UserDAO();
    		// creating return values
    		ArrayList<CommentVO> list = commentDAO.getCommentOfPost(postId);
    		ArrayList<StyleCommentVO> styleCommentInfo = new ArrayList<>();
    		
    		
    		
    		
    		// loop through comment list to create returning value object
    		for(CommentVO vo: list) {
    			// get user profile picture and user nickname
    			UserVO userVO = userDAO.selectOne(vo.getUserId());
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
    			req.setAttribute("commentList", styleCommentInfo);
    			req.setAttribute("user", user);
    		}	
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
}
	
