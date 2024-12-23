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
    
    // 구매가 평균 조회 메서드
    public int getAveragePurchasePrice(int productId) {
        String sql = "SELECT AVG(price) AS average_price " +
                     "FROM PRODUCT_SELLER " +
                     "WHERE product_id = ? AND price IS NOT NULL AND price > 0";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
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

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
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
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
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
        return 0; // 가격이 없으면 0 반환
    }
}