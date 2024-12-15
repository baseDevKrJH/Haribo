package com.jelly.www.dao;

import java.util.ArrayList;

import com.jelly.www.vo.PostImageVO;
import com.jelly.www.vo.PostTagVO;
import com.jelly.www.vo.PostVO;
import com.jelly.www.vo.ProductVO;
import com.jelly.www.vo.UserVO;

public class test {
	public static void main(String[] args) {

		PostDAO postDao = new PostDAO();
		ArrayList<PostVO> postList = postDao.getByUserId(2);
			
		System.out.println(postList.size());
		System.out.println(postDao.selectAll().size());
	}
}
