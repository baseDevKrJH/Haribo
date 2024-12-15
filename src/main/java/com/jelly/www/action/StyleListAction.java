package com.jelly.www.action;

import java.util.ArrayList;

import com.jelly.www.dao.PostDAO;
import com.jelly.www.dao.PostImageDAO;
import com.jelly.www.dao.UserDAO;
import com.jelly.www.vo.PostImageVO;
import com.jelly.www.vo.PostVO;
import com.jelly.www.vo.StylePostInfoVO;
import com.jelly.www.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StyleListAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// set attribute of all posts
		PostDAO dao = new PostDAO();
		ArrayList<PostVO> list = dao.selectAll();
		ArrayList<StylePostInfoVO> styleListInfo = new ArrayList<>();
		for(PostVO vo: list) {
			// get first image associated with post id
			PostImageDAO imageDAO = new PostImageDAO();
			PostImageVO imageVO = imageDAO.getFirstImageByPostId(vo.getPostId());
			String postImageUrl = imageVO.getPostImageUrl();
			
			// get user information
			UserDAO userDAO = new UserDAO();
			UserVO userVO = userDAO.selectOne(vo.getUserId());
			String nickname = userVO.getNickname();
			String profileImageUrl = userVO.getProfileImage();
			
			StylePostInfoVO obj = new StylePostInfoVO(vo.getPostId(), vo.getUserId(), nickname, vo.getTitle(), postImageUrl, profileImageUrl, vo.getLikesCount());
			styleListInfo.add(obj);
		}
		request.setAttribute("postList", styleListInfo);
		
		
		return "/views/style/styleList.jsp";
	}
}
