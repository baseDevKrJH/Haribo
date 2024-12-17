package com.jelly.www.dao;

import java.util.ArrayList;

import com.jelly.www.vo.FollowVO;
import com.jelly.www.vo.PostImageVO;
import com.jelly.www.vo.PostTagVO;
import com.jelly.www.vo.PostVO;
import com.jelly.www.vo.ProductVO;
import com.jelly.www.vo.UserVO;

public class test {
	public static void main(String[] args) {

		FollowDAO dao = new FollowDAO();
		
//		dao.insertOne(new FollowVO(1, 2));
//		System.out.println(dao.checkFollow(1, 2)); // true
//		System.out.println(dao.getFollowerIdList(2)); // 1
//		System.out.println(dao.getFollowingIdList(1)); // 2
//		dao.deleteOne(1, 2);
		
		dao.deleteOne(1, 5);
	}
}
