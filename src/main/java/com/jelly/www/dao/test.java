package com.jelly.www.dao;

import java.util.ArrayList;

import com.jelly.www.vo.PostImageVO;
import com.jelly.www.vo.PostTagVO;
import com.jelly.www.vo.ProductVO;

public class test {
	public static void main(String[] args) {
		
		PostImageDAO postImageDao = new PostImageDAO();
		ArrayList<PostImageVO> postImageList = postImageDao.getByPostId(2);
		
		
		
		System.out.println(postImageList);
	}
}
