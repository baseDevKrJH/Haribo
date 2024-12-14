package com.jelly.www.dao;

import java.sql.*;
import java.util.ArrayList;

import com.jelly.www.vo.PostTagVO;

public class PostTagDAO {
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
	public PostTagDAO() {
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
	
	public ArrayList<PostTagVO> getByPostId(int postId) {
		ArrayList<PostTagVO> list = new ArrayList<PostTagVO>();
		sb.setLength(0);
        sb.append("select post_tag_id, post_id, product_id, created_at ");
        sb.append("from post_tag ");
        sb.append("where post_id = ?");

        try {
        	pstmt = conn.prepareStatement(sb.toString());
            pstmt.setInt(1, postId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                PostTagVO vo = new PostTagVO(
                    rs.getInt("post_tag_id"),
                    rs.getInt("post_id"),
                    rs.getInt("product_id"),
                    rs.getTimestamp("created_at")
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