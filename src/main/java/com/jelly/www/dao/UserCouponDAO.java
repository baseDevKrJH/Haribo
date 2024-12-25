package com.jelly.www.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.jelly.www.vo.UserCouponVO;

public class UserCouponDAO {
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/jelly";
	private String user = "scott";
	private String password = "tiger";

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private StringBuilder sb = new StringBuilder();

	public UserCouponDAO() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("TadeDAO: MySQL DB 연결 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL 드라이버 로드 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("MySQL DB 연결 실패");
			e.printStackTrace();
		}
	}
	int userCoupon;
	int userId;
//	int couponId
	Boolean isUsed;
	Timestamp usedAt;
	
	int couponId;
	String couponCode;
	String description;
	String couponType;
	int discountAmount;
	int discountPercentage;
	int minimumOrderAmount;
	String expiryDate;
	Timestamp createdAt;
	Timestamp updatedAt;

	
	// 사용자가 가진 쿠폰 정보 조회(쿠폰 코드, 상세 설명,  
	public List<UserCouponVO> selectUserCoupons(int userId){
		List<UserCouponVO> userCouponList = new ArrayList<UserCouponVO>();
		
		sb.setLength(0);
		sb.append("SELECT couponCode, description, minimum_order_amount, expiry_date FROM COUPON C JOIN USER_COUPON UC WHERE ");
		
		
		return null;
	}
}
