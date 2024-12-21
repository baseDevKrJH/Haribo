package com.jelly.www.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jelly.www.vo.ProductSellerVO;

public class ProductSellerDAO {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/jelly";
	private String user = "scott";
	private String password = "tiger";

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private StringBuilder sb = new StringBuilder();

	public ProductSellerDAO() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("ProductSellerDAO: MySQL DB 연결 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL 드라이버 로드 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("MySQL DB 연결 실패");
			e.printStackTrace();
		}

	}
	

	// 상품 아이디로 ProductSeller 한건 조회 (trade에서 product_seller_id 필요할 때 사용)
		public ProductSellerVO selectProductSellerByProductId(int productId) {
			ProductSellerVO productSellers = null;
			sb.setLength(0);
			sb.append("SELECT product_seller_id, product_id, seller_id, size_id, price, stock, created_at, updated_at FROM PRODUCT_SELLER WHERE product_id = ? ORDER BY created_at DESC, price ASC limit 1");
			System.out.println("sql : " + sb.toString());
			try {
				pstmt = conn.prepareStatement(sb.toString());
				pstmt.setInt(1, productId);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					productSellers = new ProductSellerVO(
							rs.getInt("product_seller_id"), 
							rs.getInt("product_id"),
							rs.getInt("seller_id"), 
							rs.getInt("size_id"), 
							rs.getInt("price"), 
							rs.getInt("stock"),
							rs.getTimestamp("created_at"), 
							rs.getTimestamp("updated_at")
					);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return productSellers;
		}

		// 구매가 평균 조회 메서드
	    public int getAveragePurchasePrice(int productId) {
	        String sql = "SELECT AVG(price) AS average_price " +
	                     "FROM PRODUCT_SELLER " +
	                     "WHERE product_id = ? AND price IS NOT NULL AND price > 0";

	        try (Connection conn = DriverManager.getConnection(url, user, password);
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, productId);

	            try (ResultSet rs = pstmt.executeQuery()) {
	                if (rs.next()) {
	                    int averagePrice = rs.getInt("average_price");
	                    // 평균가가 0일 경우 발매가를 반환
	                    if (averagePrice == 0) {
	                        return getInitialPrice(productId);  // 발매가 반환
	                    }
	                    return averagePrice;
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println("구매가 평균 조회 실패");
	            e.printStackTrace();
	        }
	        return 0; // 가격이 없으면 0 반환
	    }
	    
	    // 판매가 평균 조회 메서드
	    public int getAverageSellPrice(int productId) {
	        String sql = "SELECT AVG(price) AS average_price " +
	                     "FROM PRODUCT_SELLER " +
	                     "WHERE product_id = ? AND price IS NOT NULL AND price > 0";

	        try (Connection conn = DriverManager.getConnection(url, user, password);
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, productId);

	            try (ResultSet rs = pstmt.executeQuery()) {
	                if (rs.next()) {
	                    int averagePrice = rs.getInt("average_price");
	                    // 평균가가 0일 경우 발매가를 반환
	                    if (averagePrice == 0) {
	                        return getInitialPrice(productId);  // 발매가 반환
	                    }
	                    return averagePrice;
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println("판매가 평균 조회 실패");
	            e.printStackTrace();
	        }
	        return 0; // 가격이 없으면 0 반환
	    }

	    // 발매가 조회 메서드
	    private int getInitialPrice(int productId) {
	        String sql = "SELECT initial_price FROM PRODUCT WHERE product_id = ?";
	        try (Connection conn = DriverManager.getConnection(url, user, password);
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, productId);
	            try (ResultSet rs = pstmt.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getInt("initial_price"); // 발매가 반환
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println("발매가 조회 실패");
	            e.printStackTrace();
	        }
	        return 0; // 발매가가 없으면 0 반환
	    }

	    // 최저가 조회 메서드
	    public int getLowestPrice(int productId) {
	        String sql = "SELECT COALESCE(MIN(price), (SELECT initial_price FROM PRODUCT WHERE product_id = ?)) AS lowest_price " +
	                     "FROM PRODUCT_SELLER " +
	                     "WHERE product_id = ? AND price IS NOT NULL AND price > 0";

	        try (Connection conn = DriverManager.getConnection(url, user, password);
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, productId);
	            pstmt.setInt(2, productId);

	            try (ResultSet rs = pstmt.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getInt("lowest_price");
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println("최저가 조회 실패");
	            e.printStackTrace();
	        }
	        return 0; // 가격이 없으면 0 반환
	    }
	
	// 판매정보 추가
	    public int insertSellData(ProductSellerVO vo) {
	    	sb.setLength(0);
	    	sb.append("INSERT INTO PRODUCT_SELLER VALUES (?, ?, ?, ?, ?, ?, NOW(), NOW())");
	    	int result = 0;
	    	try {
				pstmt = conn.prepareStatement(sb.toString());
				pstmt.setInt(1, vo.getProductSellerId());
				pstmt.setInt(2, vo.getProductId());
				pstmt.setInt(3, vo.getSellerId());
				pstmt.setInt(4, vo.getSizeId());
				pstmt.setInt(5, vo.getPrice());
				pstmt.setInt(6, vo.getStock());
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return result; // insert 안됐으면 0
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
