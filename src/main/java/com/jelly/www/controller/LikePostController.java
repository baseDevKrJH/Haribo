package com.jelly.www.controller;

import java.io.IOException;

import org.json.simple.JSONObject;

import com.jelly.www.dao.PostDAO;
import com.jelly.www.dao.PostLikeDAO;
import com.jelly.www.vo.PostLikeVO;
import com.jelly.www.vo.PostVO;
import com.jelly.www.vo.UserVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/likePost")
public class LikePostController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	JSONObject jsonResponse = new JSONObject();
    	
    	HttpSession session = request.getSession();
        UserVO user = (UserVO) session.getAttribute("user");
		
        if (user == null) { // 로그인이 되어 있지 않을 경우
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 401 상태
            jsonResponse.put("error", "로그인이 필요합니다.");
        }
        else {
			int userId = user.getUserId();
	    	
	        boolean isLike = false;
	    	
	    	// 요청 데이터 읽기
	    	int postId = Integer.parseInt(request.getParameter("postId"));
	
	        // DAO 호출
	    	PostDAO postDao = new PostDAO();
	    	PostLikeDAO postLikeDao = new PostLikeDAO();
	    	
	    	if(postLikeDao.checkLike(postId, userId)) {
	    		// 좋아요 중이라면
	    		postLikeDao.deleteOne(postId, userId);
	    		postDao.minusLike(postId);
	    	}
	    	else {
	    		// 좋아요 중이 아니라면
	    		PostLikeVO postLikeVo = new PostLikeVO(postId, userId);
	    		postLikeDao.insertOne(postLikeVo);
	    		postDao.plusLike(postId);
	    		isLike = true;
	    	}
	    	
	    	PostVO postVo = postDao.selectOne(postId);
	
	        // JSON 응답 생성
	    	jsonResponse.put("likeCount", postVo.getLikeCount());
	    	jsonResponse.put("isLike", isLike);
	
	        // 응답
	        response.setContentType("application/json;charset=UTF-8");
	        response.getWriter().write(jsonResponse.toJSONString());
        }
    }
}