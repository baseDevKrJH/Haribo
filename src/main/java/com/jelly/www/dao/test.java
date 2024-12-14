package com.jelly.www.dao;

import java.util.ArrayList;

import com.jelly.www.vo.PostImageVO;
import com.jelly.www.vo.PostTagVO;
import com.jelly.www.vo.PostVO;
import com.jelly.www.vo.ProductVO;
import com.jelly.www.vo.UserVO;

public class test {
	public static void main(String[] args) {

		String pId = "2";

		if (pId != null) {
			int postId = Integer.parseInt(pId);

			// 게시물 정보 조회
			PostDAO postDao = new PostDAO();
			PostVO postVo = postDao.selectOne(postId);

			// 게시물 이미지 조회
			PostImageDAO postImageDao = new PostImageDAO();
			ArrayList<PostImageVO> postImageList = postImageDao.getByPostId(postId);

			// 유저 정보 조회
			UserDAO userDao = new UserDAO();
			UserVO userVo = userDao.selectOne(postVo.getUserId());

			// 태그 상품 조회
			PostTagDAO postTagDao = new PostTagDAO();
			ArrayList<PostTagVO> postTagList = postTagDao.getByPostId(postId);
			ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
			ProductDAO productDao = new ProductDAO();
			for (PostTagVO postTagVo : postTagList) {
				ProductVO productVo = productDao.selectOne(postTagVo.getProductId());
				productList.add(productVo);
			}

			// 작성자의 다른 게시물 조회
			ArrayList<PostVO> postList = postDao.getByUserId(postVo.getUserId());
			
			System.out.println(postList);
		}
	}
}
