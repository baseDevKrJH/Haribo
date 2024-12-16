package com.jelly.www.dao;

import java.util.ArrayList;

import com.jelly.www.vo.PostImageVO;
import com.jelly.www.vo.PostTagVO;
import com.jelly.www.vo.PostVO;
import com.jelly.www.vo.ProductVO;
import com.jelly.www.vo.UserVO;

public class test {
	public static void main(String[] args) {

		int postId = 2;
		
		// 조회수 증가
		PostDAO postDao = new PostDAO();
		postDao.plusView(postId);

		// 게시물 정보 조회
		PostVO postVo = postDao.selectOne(postId);
		
		System.out.println(postVo);
	}
}
