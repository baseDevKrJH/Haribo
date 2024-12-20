package com.jelly.www.dao;

import com.jelly.www.vo.ProductSellerVO;

public class TestMain {
	public static void main(String[] args) {
//		UserDAO dao = new UserDAO();
//		System.out.println(dao);

		TradeDAO dao1 = new TradeDAO();
		System.out.println(dao1.selectProductSellerByProductId(419));
		
		

	}
}
