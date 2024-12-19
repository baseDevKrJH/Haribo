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
        sb.append("SELECT product_id, product_name, description, brand, release_date, initial_price ");
        sb.append("model_number, category_id, image_url, is_active, created_at, updated_at FROM PRODUCT");

        try {
            pstmt = conn.prepareStatement(sb.toString());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductVO vo = new ProductVO(
                    rs.getInt("product_id"),
                    rs.getString("product_name"),  // name을 productName으로 수정
                    rs.getString("description"),
                    rs.getString("brand"),
                    rs.getDate("release_date"),
                    rs.getInt("initial_price"),
                    rs.getString("model_number"),
                    rs.getInt("category_id"),
                    rs.getString("image_url"),
                    rs.getBoolean("is_active"),
                    rs.getDate("created_at"),
                    rs.getDate("updated_at")
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
        sb.append("SELECT product_id, product_name, description, brand, release_date, initial_price ");
        sb.append("model_number, category_id, image_url, is_active, created_at, updated_at ");
        sb.append("FROM PRODUCT WHERE product_id = ?");

        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setInt(1, productId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                vo = new ProductVO(
                		 rs.getInt("product_id"),
                         rs.getString("product_name"),  // name을 productName으로 수정
                         rs.getString("description"),
                         rs.getString("brand"),
                         rs.getDate("release_date"),
                         rs.getInt("initial_price"),
                         rs.getString("model_number"),
                         rs.getInt("category_id"),
                         rs.getString("image_url"),
                         rs.getBoolean("is_active"),
                         rs.getDate("created_at"),
                         rs.getDate("updated_at")
                );
            }
            
            // 상품이 존재하면 사이즈 목록을 추가로 조회
            if (vo != null) {
                // 사이즈 목록 조회
                List<String> sizes = selectSizesByProductId(productId);
                vo.setSizes(sizes);              }
            
            
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
        sb.append("INSERT INTO PRODUCT (product_name, description, brand, release_date, initial_price, ");
        sb.append("model_number, category_id, image_url, is_active, created_at, updated_at) ");
        sb.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())");

        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setString(1, vo.getProductName());  // name을 productName으로 수정
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
        sb.append("DELETE FROM PRODUCT WHERE product_id = ?");

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
        sb.append("UPDATE PRODUCT SET product_name = ?, description = ?, brand = ?, release_date = ?, ");
        sb.append("initial_price = ?, model_number = ?, category_id = ?, image_url = ?, is_active = ?, ");
        sb.append("updated_at = NOW() WHERE product_id = ?");

        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setString(1, vo.getProductName());  // name을 productName으로 수정
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
    
    // 필터
    public List<ProductVO> filterByBrandsCategoriesAndPrice(List<String> brands, List<Integer> categories, String priceRange) {
        List<ProductVO> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        // 기본 SQL 작성
        sb.append("SELECT p.product_id, p.product_name, p.description, p.brand, p.release_date, ");
        sb.append("p.initial_price, p.image_url ");
        sb.append("FROM PRODUCT p ");
        sb.append("JOIN CATEGORY c on p.category_id = c.category_id ");
        sb.append("WHERE 1=1 ");

        // 브랜드 필터 조건 추가
        if (brands != null && !brands.isEmpty()) {
            sb.append("AND p.BRAND IN (");
            sb.append(String.join(",", brands.stream().map(b -> "?").toArray(String[]::new)));
            sb.append(") ");
        }

        // 카테고리 필터 조건 추가
        if (categories != null && !categories.isEmpty()) {
            sb.append("AND (p.category_id IN (");
            sb.append(String.join(",", categories.stream().map(c -> "?").toArray(String[]::new)));
            sb.append(") OR c.parent_in IN (");
            sb.append(String.join(",", categories.stream().map(c -> "?").toArray(String[]::new)));
            sb.append(")) ");
        }

        // 가격 필터 조건 추가
        if (priceRange != null) {
            switch (priceRange) {
                case "50000":
                    sb.append("AND p.initial_price <= 50000 ");
                    break;
                case "100000":
                    sb.append("AND p.initial_price > 50000 AND p.initial_price <= 100000 ");
                    break;
                case "200000":
                    sb.append("AND p.initial_price > 100000 AND p.initial_price <= 200000 ");
                    break;
                case "300000":
                    sb.append("AND p.initial_price > 200000 ");
                    break;
            }
        }

        try {
            PreparedStatement pstmt = conn.prepareStatement(sb.toString());
            int paramIndex = 1;

            // 브랜드 필터 값 설정
            if (brands != null && !brands.isEmpty()) {
                for (String brand : brands) {
                    pstmt.setString(paramIndex++, brand);
                }
            }

            // 카테고리 필터 값 설정
            if (categories != null && !categories.isEmpty()) {
                for (Integer category : categories) {
                    pstmt.setInt(paramIndex++, category); // CATEGORY_ID
                }
                for (Integer category : categories) { // PARENT_ID
                    pstmt.setInt(paramIndex++, category);
                }
            }

            // 가격 필터 값은 쿼리에서 이미 추가되었음

            // SQL 실행 및 결과 처리
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ProductVO product = new ProductVO(
                    rs.getInt("product_id"),
                    rs.getString("product_name"),  // `PRODUCT_NAME` 사용
                    rs.getString("description"),
                    rs.getString("brand"),
                    rs.getDate("release_date"),
                    rs.getInt("initial_price"),
                    rs.getString("image_url")
                );
                list.add(product);
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
        sb.append("SELECT p.PRODUCT_ID, p.PRODUCT_NAME, p.DESCRIPTION, p.BRAND, p.RELEASE_DATE, ");
        sb.append("p.INITIAL_PRICE, p.MODEL_NUMBER, p.CATEGORY_ID, p.IMAGE_URL, p.IS_ACTIVE, ");
        sb.append("p.CREATED_AT, p.UPDATED_AT ");
        sb.append("FROM PRODUCT p ");
        sb.append("JOIN CATEGORY c ON p.CATEGORY_ID = c.CATEGORY_ID ");
        sb.append("WHERE c.NAME = ? OR c.PARENT_ID = (SELECT CATEGORY_ID FROM CATEGORY WHERE NAME = ?)");

        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setString(1, categoryName); 
            pstmt.setString(2, categoryName);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductVO vo = new ProductVO(
                    rs.getInt("PRODUCT_ID"),
                    rs.getString("PRODUCT_NAME"),  // `NAME`을 `PRODUCT_NAME`으로 변경
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
                // System.out.println("카테고리별 조회 상품: " + vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }
    
    // 무한스크롤 메서드(페이지네이션)....인데 구현안됨 어떻게함?
//    public List<ProductVO> selectByPage(int page, int limit) {
//        List<ProductVO> list = new ArrayList<>();
//        int offset = (page - 1) * limit; // 시작 위치 계산
//
//        String sql = "SELECT * FROM PRODUCT ORDER BY RELEASE_DATE DESC LIMIT ? OFFSET ?";
//        try {
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, limit);
//            pstmt.setInt(2, offset);
//            rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                ProductVO vo = new ProductVO(
//                    rs.getInt("PRODUCT_ID"),
//                    rs.getString("NAME"),
//                    rs.getString("DESCRIPTION"),
//                    rs.getString("BRAND"),
//                    rs.getDate("RELEASE_DATE"),
//                    rs.getInt("INITIAL_PRICE"),
//                    rs.getString("MODEL_NUMBER"),
//                    rs.getInt("CATEGORY_ID"),
//                    rs.getString("IMAGE_URL"),
//                    rs.getBoolean("IS_ACTIVE"),
//                    rs.getDate("CREATED_AT"),
//                    rs.getDate("UPDATED_AT")
//                );
//                list.add(vo);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            close();
//        }
//        return list;
//    }
    
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

    // 사이즈와 가격 조회 (상품 모든 사이즈버튼, 구매, 판매 버튼 누르면 나오는 모달창에 사용)
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
                vo.setSize(rs.getString("SIZE"));
                vo.setPrice(rs.getInt("PRICE"));
                sizePriceList.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return sizePriceList;
    }

    // 검색어 관련 메서드
    public List<ProductVO> searchProducts(String query) {
        List<ProductVO> list = new ArrayList<>();
        sb.setLength(0);
        sb.append("SELECT PRODUCT_ID, PRODUCT_NAME, DESCRIPTION, BRAND, RELEASE_DATE, INITIAL_PRICE, ");
        sb.append("MODEL_NUMBER, CATEGORY_ID, IMAGE_URL, IS_ACTIVE, CREATED_AT, UPDATED_AT ");
        sb.append("FROM PRODUCT WHERE PRODUCT_NAME LIKE ? OR MODEL_NUMBER LIKE ? OR BRAND LIKE ?");

        try {
            pstmt = conn.prepareStatement(sb.toString());
            String searchKeyword = "%" + query + "%";
            pstmt.setString(1, searchKeyword);
            pstmt.setString(2, searchKeyword);
            pstmt.setString(3, searchKeyword);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductVO vo = new ProductVO(
                    rs.getInt("PRODUCT_ID"),
                    rs.getString("PRODUCT_NAME"),
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
                System.out.println("검색된 상품: " + vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
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
