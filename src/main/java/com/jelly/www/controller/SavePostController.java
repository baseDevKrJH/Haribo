package com.jelly.www.controller;

import java.io.IOException;

import org.json.simple.JSONObject;

import com.jelly.www.dao.PostDAO;
import com.jelly.www.dao.PostSaveDAO;
import com.jelly.www.vo.PostSaveVO;
import com.jelly.www.vo.PostVO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/savePost")
public class SavePostController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isSave = false;
    	
    	// 요청 데이터 읽기
    	int postId = Integer.parseInt(request.getParameter("postId"));
    	int userId = Integer.parseInt(request.getParameter("userId"));

        // DAO 호출
    	PostDAO postDao = new PostDAO();
    	PostSaveDAO postsaveDao = new PostSaveDAO();
    	
    	if(postsaveDao.checkSave(postId, userId)) {
    		// 저장 중이라면
    		postsaveDao.deleteOne(postId, userId);
    		postDao.minusSave(postId);
    	}
    	else {
    		// 저장 중이 아니라면
    		PostSaveVO postSaveVo = new PostSaveVO(postId, userId);
    		postsaveDao.insertOne(postSaveVo);
    		postDao.plusSave(postId);
    		isSave = true;
    	}
    	
    	PostVO postVo = postDao.selectOne(postId);

        // JSON 응답 생성
    	JSONObject jsonResponse = new JSONObject();
    	jsonResponse.put("saveCount", postVo.getSaveCount());
    	jsonResponse.put("isSave", isSave);

        // 응답
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(jsonResponse.toJSONString());
    }
}