package com.jelly.www.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.jelly.www.vo.AddressVO;
import com.jelly.www.vo.ProductSellerVO;
import com.jelly.www.vo.TradeVO;

public class TradeDAO {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/jelly";
	private String user = "scott";
	private String password = "tiger";

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private StringBuilder sb = new StringBuilder();

	public TradeDAO() {
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

	// 1. 구매내역 전체 조회
	public List<TradeVO> selectBuyAll(int userId) {
		List<TradeVO> tradeList = new ArrayList<>();
		sb.setLength(0);
		sb.append("SELECT * FROM TRADE WHERE buyer_id = ?");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				TradeVO trade = new TradeVO(rs.getInt("trade_id"), rs.getInt("product_seller_id"),
						rs.getInt("buyer_id"), rs.getInt("address_id"), rs.getInt("coupon_id"),
						rs.getInt("total_price"), rs.getInt("trade_status"), rs.getTimestamp("trade_date"),
						rs.getTimestamp("completed_at"), rs.getTimestamp("created_at"), rs.getTimestamp("updated_at"));
				tradeList.add(trade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return tradeList;
	}

	// 2. 판매내역 전체 조회
	public List<TradeVO> selectSellAll(int userId) {
		List<TradeVO> tradeList = new ArrayList<>();
		sb.setLength(0);
		sb.append("SELECT * FROM TRADE WHERE product_seller_id = ?");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				TradeVO trade = new TradeVO(rs.getInt("trade_id"), rs.getInt("product_seller_id"),
						rs.getInt("buyer_id"), rs.getInt("address_id"), rs.getInt("coupon_id"),
						rs.getInt("total_price"), rs.getInt("trade_status"), rs.getTimestamp("trade_date"),
						rs.getTimestamp("completed_at"), rs.getTimestamp("created_at"), rs.getTimestamp("updated_at"));
				tradeList.add(trade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return tradeList;
	}

	// 2. 구매 주문 정보 1건 조회 (구매자 아이디와 주문번호로 조회)
	public TradeVO selectBuyOne(int userId, int tradeId) {
		TradeVO trade = null;
		sb.setLength(0);
		sb.append("SELECT * FROM TRADE WHERE buyer_id = ? AND trade_id=?");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, userId);
			pstmt.setInt(2, tradeId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				trade = new TradeVO(rs.getInt("trade_id"), rs.getInt("product_seller_id"), rs.getInt("buyer_id"),
						rs.getInt("address_id"), rs.getInt("coupon_id"), rs.getInt("total_price"),
						rs.getInt("trade_status"), rs.getTimestamp("trade_date"), rs.getTimestamp("completed_at"),
						rs.getTimestamp("created_at"), rs.getTimestamp("updated_at"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return trade;
	}

	// 2-2. 판매 주문 정보 1건 조회 (구매자 아이디와 주문번호로 조회)
	public TradeVO selectSellOne(int userId, int tradeId) {
		TradeVO trade = null;
		sb.setLength(0);
		sb.append("SELECT * FROM TRADE WHERE buyer_id = ? AND trade_id=?");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, userId);
			pstmt.setInt(2, tradeId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				trade = new TradeVO(rs.getInt("trade_id"), rs.getInt("product_seller_id"), rs.getInt("buyer_id"),
						rs.getInt("address_id"), rs.getInt("coupon_id"), rs.getInt("total_price"),
						rs.getInt("trade_status"), rs.getTimestamp("trade_date"), rs.getTimestamp("completed_at"),
						rs.getTimestamp("created_at"), rs.getTimestamp("updated_at"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return trade;
	}

	// 구매한 상품 insert
	public int insertBuyOne(TradeVO trade) {
		
		sb.setLength(0);
		sb.append(
				"INSERT INTO TRADE VALUES (?, ?, ?, ?, ?, ?, ?,  NOW(), NOw(), NOW(), NOW())");
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, trade.getTradeId());
			pstmt.setInt(2, trade.getProductSellerId());
			pstmt.setInt(3, trade.getBuyerId());
			pstmt.setInt(4, trade.getAddressId());
			pstmt.setInt(5, trade.getCouponId());
			pstmt.setInt(6, trade.getTotalPrice());
			pstmt.setInt(7, trade.getTradeStatus());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return result;
	}
	
	// 구매한 상품 PAYMENT테이블에 insert
	public int insertPayment(int tradeId, String paymentMethod, int totalPrice) {
		sb.setLength(0);
		sb.append("INSERT INTO PAYMENT (trade_id, payment_method, amount) VALUES (?, ?, ?)");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, tradeId);
			pstmt.setString(2, paymentMethod);
			pstmt.setInt(3, totalPrice);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("insertPayment result : " + result);
		return result;
	}
	// 자원 해제
	private void close() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
