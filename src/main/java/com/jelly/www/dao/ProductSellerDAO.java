package com.jelly.www.dao;

import java.sql.*;

public class ProductSellerDAO {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/jelly?useSSL=false&serverTimezone=UTC";
    private static final String USER = "scott";
    private static final String PASSWORD = "tiger";

    public ProductSellerDAO() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL 드라이버 로드 실패");
            e.printStackTrace();
        }
    }

    // 최저가 조회 메서드
    public int getLowestPrice(int productId) {
        String sql = "SELECT COALESCE(" +
                     "    (SELECT MIN(price) " +
                     "     FROM PRODUCT_SELLER " +
                     "     WHERE product_id = ? AND price IS NOT NULL AND price > 0), " +
                     "    (SELECT initial_price " +
                     "     FROM PRODUCT " +
                     "     WHERE product_id = ?)) AS lowest_price";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
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
        return 0;
    }
}