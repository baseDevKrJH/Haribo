package com.jelly.www.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jelly.www.vo.AddressVO;

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

	// 주소 한건 조회
	public AddressVO selectOne(int userId) {
		AddressVO vo = null;
		sb.setLength(0);
		sb.append(
				"SELECT address_id, user_id, address_line1, address_line2, postal_code, is_default, created_at, updated_at ");
		sb.append("FROM ADDRESS WHERE user_id = ?");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new AddressVO(rs.getInt("address_id"), rs.getInt("user_id"), rs.getString("address_line1"),
						rs.getString("address_line2"), rs.getString("postal_code"), rs.getBoolean("is_default"),
						rs.getTimestamp("created_at"), rs.getTimestamp("updated_at"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
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
		} finally {
			close();
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
