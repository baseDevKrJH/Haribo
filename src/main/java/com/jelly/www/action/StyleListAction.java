package com.jelly.www.action;

import java.util.ArrayList;

import com.jelly.www.dao.FollowDAO;
import com.jelly.www.dao.PostDAO;
import com.jelly.www.dao.PostImageDAO;
import com.jelly.www.dao.PostLikeDAO;
import com.jelly.www.dao.PostSaveDAO;
import com.jelly.www.dao.UserDAO;
import com.jelly.www.vo.PostImageVO;
import com.jelly.www.vo.PostVO;
import com.jelly.www.vo.StylePostInfoVO;
import com.jelly.www.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class StyleListAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// set attribute of all posts
		String styleCode = request.getParameter("styleCode");
		String currentPage = request.getParameter("pageNum");
		System.out.println(styleCode);
		System.out.println(currentPage);
		if (styleCode == null) {
			// 껍데기를 출력하는 요청이
			return "/views/style/styleList.jsp";
		}
		int code = Integer.parseInt(styleCode);
		int pageNum = Integer.parseInt(currentPage);
		PostDAO dao = new PostDAO();
		ArrayList<PostVO> list = dao.selectByStyleCategory(code, pageNum);
		ArrayList<StylePostInfoVO> styleListInfo = new ArrayList<>();
		for(PostVO vo: list) {
			// get first image associated with post id
			boolean isLike = false;
			HttpSession session = request.getSession();
	        UserVO user = (UserVO) session.getAttribute("user");
			if(user != null) {
				PostLikeDAO postLikeDao = new PostLikeDAO();
				isLike = postLikeDao.checkLike(vo.getPostId(), user.getUserId());
			}
			
			String postImageUrl = vo.getThumbnailImageUrl();
			if(postImageUrl == null) {
				postImageUrl = "https://static.vecteezy.com/system/resources/previews/004/141/669/non_2x/no-photo-or-blank-image-icon-loading-images-or-missing-image-mark-image-not-available-or-image-coming-soon-sign-simple-nature-silhouette-in-frame-isolated-illustration-vector.jpg";
			}
			
			// get user information
			UserDAO userDAO = new UserDAO();
			UserVO userVO = userDAO.selectOne(vo.getUserId());
			String nickname = userVO.getNickname();
			String profileImageUrl = userVO.getProfileImage();
			
			StylePostInfoVO obj = new StylePostInfoVO(vo.getPostId(), vo.getUserId(), nickname, vo.getTitle(), postImageUrl, profileImageUrl, vo.getLikeCount(), isLike);
			styleListInfo.add(obj);
		}
		
		
		
		request.setAttribute("postList", styleListInfo);
		
		
		return "/views/style/stylePostList.jsp";
	}
}
