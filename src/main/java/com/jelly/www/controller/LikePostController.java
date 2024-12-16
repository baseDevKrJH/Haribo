package com.jelly.www.controller;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.jelly.www.dao.PostDAO;
import com.jelly.www.dao.PostLikeDAO;
import com.jelly.www.vo.PostLikeVO;
import com.jelly.www.vo.PostVO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/likePost")
public class LikePostController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isLike = false;
    	
    	// 요청 데이터 읽기
    	int postId = Integer.parseInt(request.getParameter("postId"));
    	int userId = Integer.parseInt(request.getParameter("userId"));

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
    	JSONObject jsonResponse = new JSONObject();
    	jsonResponse.put("likeCount", postVo.getLikeCount());
    	jsonResponse.put("isLike", isLike);

        // 응답
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(jsonResponse.toJSONString());
    }
}