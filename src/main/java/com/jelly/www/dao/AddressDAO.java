package com.jelly.www.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jelly.www.vo.AddressVO;
import com.jelly.www.vo.ProductVO;

public class AddressDAO {
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
	public AddressDAO() {
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

	// userId로 기본 주소지 조회
	public AddressVO selectDefaultAddressOne(int userId) {
		AddressVO vo = null;
		sb.setLength(0);
		sb.append(
				"SELECT address_id, postal_code, address_line1, address_line2 ");
		sb.append("FROM ADDRESS WHERE user_id = ? AND is_default = 1");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new AddressVO(
						rs.getInt("address_id"),
						rs.getString("postal_code"), 
						rs.getString("address_line1"),
						rs.getString("address_line2"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return vo;
	}

	
	// 사용자의 전체 주소 조회
	public List<AddressVO> selectAddressAll(int userId) {
        List<AddressVO> list = new ArrayList<>();
        sb.setLength(0);
		sb.append("SELECT address_id, postal_code, address_line1, address_line2 ");
		sb.append("FROM ADDRESS WHERE user_id = ? AND is_default = 1");

        try {
            pstmt = conn.prepareStatement(sb.toString());
            rs = pstmt.executeQuery();

            while (rs.next()) {
            	AddressVO vo = new AddressVO(
						rs.getInt("address_id"),
            			rs.getString("postal_code"), 
						rs.getString("address_line1"),
						rs.getString("address_line2")
                );
                list.add(vo);
                System.out.println("조회된 상품: " + vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return list;
    }
	
	// 주소 삭제
	public void deleteOne(int userId) {
		AddressVO vo = null;
		sb.setLength(0);
		sb.append("DELETE FROM ADDRESS WHERE user_id = ?");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, userId);
			pstmt.executeUpdate();
			System.out.println("주소 삭제 완료");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	// 자원 해제 메서드
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
