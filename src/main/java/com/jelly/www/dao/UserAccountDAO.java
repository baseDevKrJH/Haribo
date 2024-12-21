package com.jelly.www.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jelly.www.vo.UserAccountVO;

public class UserAccountDAO {
	// DB 연결 정보
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/jelly";
    String user = "scott";
    String password = "tiger";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    StringBuffer sb = new StringBuffer();

    public UserAccountDAO() {
    	try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("UserAccountDAO: MySQL DB 연결 성공");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL 드라이버 로드 실패");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("MySQL DB 연결 실패");
            e.printStackTrace();
        }
    }
	
	
	
	// userId로 사용자 기본 account 정보 조회
    public UserAccountVO selectIsDefaultAccountByUserId(int userId) {
    	UserAccountVO vo = null;
    	sb.setLength(0);
    	sb.append("SELECT account_id, user_id, bank_name, account_number, account_holder, is_default, created_at FROM USER_ACCOUNT WHERE user_id = ? and is_default = 1");
    	
    	try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
			vo = new UserAccountVO(
					rs.getInt("account_id"),
					rs.getInt("user_id"),
					rs.getString("bank_name"),
					rs.getString("account_number"),
					rs.getString("account_holder"),
					rs.getBoolean("is_default"),
					rs.getTimestamp("created_at")
					);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return vo;
    }

}
