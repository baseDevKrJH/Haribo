package com.jelly.www.dao;

import java.sql.*;
import java.util.ArrayList;

import com.jelly.www.vo.PostImageVO;

public class PostImageDAO {
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
	public PostImageDAO() {
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
	
	public ArrayList<PostImageVO> getByPostId(int postId) {
		ArrayList<PostImageVO> list = new ArrayList<PostImageVO>();
		sb.setLength(0);
        sb.append("select post_image_id, post_image_url, post_id, seq ");
        sb.append("from post_image ");
        sb.append("where post_id = ? ");
        sb.append("order by seq asc");

        try {
        	pstmt = conn.prepareStatement(sb.toString());
            pstmt.setInt(1, postId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                PostImageVO vo = new PostImageVO(
                    rs.getInt("post_image_id"),
                    rs.getString("post_image_url"),
                    rs.getInt("post_id"),
                    rs.getInt("seq")
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