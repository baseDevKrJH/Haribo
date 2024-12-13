package com.jelly.www.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.jelly.www.vo.ProductVO;

public class ProductDAO {
    // DB 연결 정보
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/jelly";
    String user = "scott";
    String password = "tiger";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    StringBuffer sb = new StringBuffer();

    // 생성자
    public ProductDAO() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("MySQL DB 연결 성공");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL 드라이버 로드 실패");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("MySQL DB 연결 실패");
            e.printStackTrace();
        }
    }

 // 1. 전체 조회
    public List<ProductVO> selectAll() {
        List<ProductVO> list = new ArrayList<>();
        sb.setLength(0);
        sb.append("SELECT PRODUCT_ID, NAME, DESCRIPTION, BRAND, RELEASE_DATE, INITIAL_PRICE, ");
        sb.append("MODEL_NUMBER, CATEGORY_ID, IMAGE_URL, IS_ACTIVE, CREATED_AT, UPDATED_AT FROM PRODUCT");

        try {
            pstmt = conn.prepareStatement(sb.toString());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                ProductVO vo = new ProductVO(
                    rs.getInt("PRODUCT_ID"),
                    rs.getString("NAME"),
                    rs.getString("DESCRIPTION"),
                    rs.getString("BRAND"),
                    rs.getDate("RELEASE_DATE"),
                    rs.getInt("INITIAL_PRICE"),
                    rs.getString("MODEL_NUMBER"),
                    rs.getInt("CATEGORY_ID"),
                    rs.getString("IMAGE_URL"),
                    rs.getBoolean("IS_ACTIVE"),
                    rs.getDate("CREATED_AT"),
                    rs.getDate("UPDATED_AT")
                );
                list.add(vo);
                System.out.println("조회된 상품: " + vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    // 2. 상세 조회
    public ProductVO selectOne(int productId) {
        ProductVO vo = null;
        sb.setLength(0);
        sb.append("SELECT PRODUCT_ID, NAME, DESCRIPTION, BRAND, RELEASE_DATE, INITIAL_PRICE, ");
        sb.append("MODEL_NUMBER, CATEGORY_ID, IMAGE_URL, IS_ACTIVE, CREATED_AT, UPDATED_AT ");
        sb.append("FROM PRODUCT WHERE PRODUCT_ID = ?");

        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setInt(1, productId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                vo = new ProductVO(
                        rs.getInt("PRODUCT_ID"),
                        rs.getString("NAME"),
                        rs.getString("DESCRIPTION"),
                        rs.getString("BRAND"),
                        rs.getDate("RELEASE_DATE"),
                        rs.getInt("INITIAL_PRICE"),
                        rs.getString("MODEL_NUMBER"),
                        rs.getInt("CATEGORY_ID"),
                        rs.getString("IMAGE_URL"),
                        rs.getBoolean("IS_ACTIVE"),
                        rs.getDate("CREATED_AT"),
                        rs.getDate("UPDATED_AT")
                );
            }
            
            // 상품이 존재하면 사이즈 목록을 추가로 조회
            if (vo != null) {
                // 사이즈 목록 조회
                List<String> sizes = selectSizesByProductId(productId);
                vo.setSizes(sizes);  // ProductVO에 사이즈 목록 추가
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	close();
        }
        return vo;
    }

    // 3. 데이터 추가
    public void insertOne(ProductVO vo) {
        sb.setLength(0);
        sb.append("INSERT INTO PRODUCT (NAME, DESCRIPTION, BRAND, RELEASE_DATE, INITIAL_PRICE, ");
        sb.append("MODEL_NUMBER, CATEGORY_ID, IMAGE_URL, IS_ACTIVE, CREATED_AT, UPDATED_AT) ");
        sb.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())");

        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setString(1, vo.getName());
            pstmt.setString(2, vo.getDescription());
            pstmt.setString(3, vo.getBrand());
            pstmt.setDate(4, new java.sql.Date(vo.getReleaseDate().getTime()));
            pstmt.setInt(5, vo.getInitialPrice());
            pstmt.setString(6, vo.getModelNumber());
            pstmt.setInt(7, vo.getCategoryId());
            pstmt.setString(8, vo.getImageUrl());
            pstmt.setBoolean(9, vo.isActive());
            pstmt.executeUpdate();
            System.out.println("상품 추가 완료: " + vo);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	close();
        }
    }

    // 4. 데이터 삭제
    public void deleteOne(int productId) {
        sb.setLength(0);
        sb.append("DELETE FROM PRODUCT WHERE PRODUCT_ID = ?");

        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setInt(1, productId);
            pstmt.executeUpdate();
            System.out.println("상품 삭제 완료: ID = " + productId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	close();
        }
    }

    // 5. 데이터 수정
    public void updateOne(ProductVO vo) {
        sb.setLength(0);
        sb.append("UPDATE PRODUCT SET NAME = ?, DESCRIPTION = ?, BRAND = ?, RELEASE_DATE = ?, ");
        sb.append("INITIAL_PRICE = ?, MODEL_NUMBER = ?, CATEGORY_ID = ?, IMAGE_URL = ?, IS_ACTIVE = ?, ");
        sb.append("UPDATED_AT = NOW() WHERE PRODUCT_ID = ?");

        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setString(1, vo.getName());
            pstmt.setString(2, vo.getDescription());
            pstmt.setString(3, vo.getBrand());
            pstmt.setDate(4, new java.sql.Date(vo.getReleaseDate().getTime()));
            pstmt.setInt(5, vo.getInitialPrice());
            pstmt.setString(6, vo.getModelNumber());
            pstmt.setInt(7, vo.getCategoryId());
            pstmt.setString(8, vo.getImageUrl());
            pstmt.setBoolean(9, vo.isActive());
            pstmt.setInt(10, vo.getProductId());
            pstmt.executeUpdate();
            System.out.println("상품 수정 완료: " + vo);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	close();
        }
    }

    // 브랜드, 사이즈, 가격 필터링하는 메서드
    public List<ProductVO> filterByBrandsSizesAndPrice(List<String> brands, List<String> sizes, String priceRange) {
        List<ProductVO> list = new ArrayList<>();
        sb.setLength(0);
        sb.append("SELECT PRODUCT_ID, NAME, DESCRIPTION, BRAND, RELEASE_DATE, INITIAL_PRICE, ");
        sb.append("MODEL_NUMBER, CATEGORY_ID, IMAGE_URL, IS_ACTIVE, CREATED_AT, UPDATED_AT ");
        sb.append("FROM PRODUCT WHERE 1=1 ");

        // 브랜드 필터 추가
        if (!brands.isEmpty()) {
            sb.append("AND BRAND IN (");
            for (int i = 0; i < brands.size(); i++) {
                sb.append("?");
                if (i < brands.size() - 1) sb.append(", ");
            }
            sb.append(") ");
        }

        // 사이즈 필터 추가
        if (!sizes.isEmpty()) {
            sb.append("AND MODEL_NUMBER IN (");
            for (int i = 0; i < sizes.size(); i++) {
                sb.append("?");
                if (i < sizes.size() - 1) sb.append(", ");
            }
            sb.append(") ");
        }

        // 가격 필터 추가
        if (priceRange != null && !priceRange.isEmpty()) {
            switch (priceRange) {
                case "50000":
                    sb.append("AND INITIAL_PRICE <= 50000 ");
                    break;
                case "100000":
                    sb.append("AND INITIAL_PRICE > 50000 AND INITIAL_PRICE <= 100000 ");
                    break;
                case "200000":
                    sb.append("AND INITIAL_PRICE > 100000 AND INITIAL_PRICE <= 200000 ");
                    break;
                case "300000":
                    sb.append("AND INITIAL_PRICE > 200000 ");
                    break;
            }
        }

        try {
            pstmt = conn.prepareStatement(sb.toString());
            int paramIndex = 1;

            // 브랜드 값 설정
            for (String brand : brands) {
                pstmt.setString(paramIndex++, brand);
            }

            // 사이즈 값 설정
            for (String size : sizes) {
                pstmt.setString(paramIndex++, size);
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductVO vo = new ProductVO(
                    rs.getInt("PRODUCT_ID"),
                    rs.getString("NAME"),
                    rs.getString("DESCRIPTION"),
                    rs.getString("BRAND"),
                    rs.getDate("RELEASE_DATE"),
                    rs.getInt("INITIAL_PRICE"),
                    rs.getString("MODEL_NUMBER"),
                    rs.getInt("CATEGORY_ID"),
                    rs.getString("IMAGE_URL"),
                    rs.getBoolean("IS_ACTIVE"),
                    rs.getDate("CREATED_AT"),
                    rs.getDate("UPDATED_AT")
                );
                list.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }
    

    // 카테고리별 상품 조회
    public List<ProductVO> selectByCategory(String categoryName) {
        List<ProductVO> list = new ArrayList<>();
        sb.setLength(0);

        // 해당 카테고리와 하위 카테고리 포함 조회
        sb.append("SELECT p.PRODUCT_ID, p.NAME, p.DESCRIPTION, p.BRAND, p.RELEASE_DATE, ");
        sb.append("p.INITIAL_PRICE, p.MODEL_NUMBER, p.CATEGORY_ID, p.IMAGE_URL, p.IS_ACTIVE, ");
        sb.append("p.CREATED_AT, p.UPDATED_AT ");
        sb.append("FROM PRODUCT p ");
        sb.append("JOIN CATEGORY c ON p.CATEGORY_ID = c.CATEGORY_ID ");
        sb.append("WHERE c.NAME = ? OR c.PARENT_ID = (SELECT CATEGORY_ID FROM CATEGORY WHERE NAME = ?)");

        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setString(1, categoryName); // 상위 카테고리
            pstmt.setString(2, categoryName); // 하위 카테고리
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductVO vo = new ProductVO(
                    rs.getInt("PRODUCT_ID"),
                    rs.getString("NAME"),
                    rs.getString("DESCRIPTION"),
                    rs.getString("BRAND"),
                    rs.getDate("RELEASE_DATE"),
                    rs.getInt("INITIAL_PRICE"),
                    rs.getString("MODEL_NUMBER"),
                    rs.getInt("CATEGORY_ID"),
                    rs.getString("IMAGE_URL"),
                    rs.getBoolean("IS_ACTIVE"),
                    rs.getDate("CREATED_AT"),
                    rs.getDate("UPDATED_AT")
                );
                list.add(vo);
                System.out.println("카테고리별 조회 상품: " + vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }
    
    // 무한스크롤 메서드(페이지네이션)
    public List<ProductVO> selectByPage(int page, int limit) {
        List<ProductVO> list = new ArrayList<>();
        int offset = (page - 1) * limit; // 시작 위치 계산

        String sql = "SELECT * FROM PRODUCT ORDER BY RELEASE_DATE DESC LIMIT ? OFFSET ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, limit);
            pstmt.setInt(2, offset);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductVO vo = new ProductVO(
                    rs.getInt("PRODUCT_ID"),
                    rs.getString("NAME"),
                    rs.getString("DESCRIPTION"),
                    rs.getString("BRAND"),
                    rs.getDate("RELEASE_DATE"),
                    rs.getInt("INITIAL_PRICE"),
                    rs.getString("MODEL_NUMBER"),
                    rs.getInt("CATEGORY_ID"),
                    rs.getString("IMAGE_URL"),
                    rs.getBoolean("IS_ACTIVE"),
                    rs.getDate("CREATED_AT"),
                    rs.getDate("UPDATED_AT")
                );
                list.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }
    
    // 상품 사이즈 목록 조회
    public List<String> selectSizesByProductId(int productId) {
        List<String> sizes = new ArrayList<>();
        sb.setLength(0);
        sb.append("SELECT SIZE FROM SIZE WHERE PRODUCT_ID = ?");

        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setInt(1, productId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                sizes.add(rs.getString("SIZE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return sizes;
    }
    
    // 사이즈와 가격 조회
    public List<ProductVO> selectSizesAndPricesByProductId(int productId) {
        List<ProductVO> sizePriceList = new ArrayList<>();
        sb.setLength(0);
        sb.append("SELECT SIZE, PRICE FROM SIZE WHERE PRODUCT_ID = ?");

        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setInt(1, productId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductVO vo = new ProductVO();
                vo.setSize(rs.getString("SIZE")); // 사이즈 설정
                vo.setPrice(rs.getInt("PRICE")); // 가격 설정
                sizePriceList.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return sizePriceList;
    }
    
    // 자원 해제 메서드
    public void close() {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}