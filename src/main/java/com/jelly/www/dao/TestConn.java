package com.jelly.www.dao;

public class TestConn {
	public static void main(String[] args) {
			ProductDAO dao = new ProductDAO();
			
			System.out.println(dao.selectAll().toString());
	}
}
