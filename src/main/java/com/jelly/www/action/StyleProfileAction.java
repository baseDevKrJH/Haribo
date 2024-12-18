package com.jelly.www.action;

import java.util.ArrayList;
import java.util.HashSet;

import com.jelly.www.dao.FollowDAO;
import com.jelly.www.dao.PostDAO;
import com.jelly.www.dao.PostSaveDAO;
import com.jelly.www.dao.PostTagDAO;
import com.jelly.www.dao.ProductDAO;
import com.jelly.www.dao.UserDAO;
import com.jelly.www.vo.PostSaveVO;
import com.jelly.www.vo.PostTagVO;
import com.jelly.www.vo.PostVO;
import com.jelly.www.vo.ProductVO;
import com.jelly.www.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class StyleProfileAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String uId = request.getParameter("userId");

		if (uId != null) {
			int userId = Integer.parseInt(uId);
			
			// 유저 가져오기
			UserDAO userDao = new UserDAO();
			UserVO userVo = userDao.selectOne(userId);
		
			// 팔로우 중인지 조회
			boolean isFollow = false;
			HttpSession session = request.getSession();
	        UserVO user = (UserVO) session.getAttribute("user");
			
	        FollowDAO followDao = new FollowDAO();
			if(user != null) {
				isFollow = followDao.checkFollow(user.getUserId(), userVo.getUserId());
			}
			
			// 팔로워 리스트 가져오기
			ArrayList<Integer> followerList = followDao.getFollowerIdList(userId);
			
			// 팔로잉 리스트 가져오기
			ArrayList<Integer> followingList = followDao.getFollowingIdList(userId);
			
			// 유저의 스타일 리스트 가져오기
			PostDAO postDao = new PostDAO();
			ArrayList<PostVO> postList = postDao.getByUserId(userId);
			
			// 태그 상품 리스트 가져오기
			PostTagDAO postTagDao = new PostTagDAO();
			ProductDAO productDao = new ProductDAO();
			HashSet<ProductVO> productSet = new HashSet<ProductVO>();
			for(PostVO post : postList) {
				ArrayList<PostTagVO> postTagList = postTagDao.getByPostId(post.getPostId());
				for(PostTagVO postTag : postTagList) {
					productSet.add(productDao.selectOne(postTag.getProductId()));
				}
			}
			
			// 저장된 스타일 가져오기
			PostSaveDAO postSaveDao = new PostSaveDAO();
			ArrayList<PostSaveVO> postSaveList = postSaveDao.getByUserId(userId);
			ArrayList<PostVO> savedPostList = new ArrayList<PostVO>();
			for(PostSaveVO postSave : postSaveList) {
				savedPostList.add(postDao.selectOne(postSave.getPostId()));
			}
			
			// 요청 파라미터 설정
			request.setAttribute("userVo", userVo);
			request.setAttribute("isFollow", isFollow);
			request.setAttribute("followerList", followerList);
			request.setAttribute("followingList", followingList);
			request.setAttribute("postList", postList);
			request.setAttribute("productSet", productSet);
			request.setAttribute("savedPostList", savedPostList);
			
		}

		return "/views/style/styleProfile.jsp";
	}

}
