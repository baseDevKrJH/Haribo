package com.jelly.www.dao;

import java.sql.*;
import java.util.ArrayList;

import com.jelly.www.vo.PostImageVO;
import com.jelly.www.vo.PostVO;

public class PostDAO {
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
	public PostDAO() {
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
	
	public PostVO selectOne(int postId) {
		PostVO postVO = null;
		sb.setLength(0);
		sb.append("select * from POST where post_id = ?");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, postId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				postVO = new PostVO(
					rs.getInt("post_id"), 
					rs.getInt("user_id"), 
					rs.getInt("style_category"),
					rs.getString("title"), 
					rs.getString("content"), 
					rs.getInt("likes_count"),
					rs.getInt("comments_count"), 
					rs.getInt("views_count"), 
					rs.getTimestamp("created_at"),
					rs.getTimestamp("updated_at")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return postVO;
	}
	
	public ArrayList<PostVO> selectAll(){
		ArrayList<PostVO> list = new ArrayList<PostVO>();
		sb.setLength(0);
        sb.append("select * from POST");

        try {
        	pstmt = conn.prepareStatement(sb.toString());
            rs = pstmt.executeQuery();

            while(rs.next()) {
            	PostVO vo = new PostVO(
            			rs.getInt("post_id"), 
    					rs.getInt("user_id"), 
    					rs.getInt("style_category"),
    					rs.getString("title"), 
    					rs.getString("content"), 
    					rs.getInt("likes_count"),
    					rs.getInt("comments_count"), 
    					rs.getInt("views_count"), 
    					rs.getTimestamp("created_at"),
    					rs.getTimestamp("updated_at")
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
	
	public ArrayList<PostVO> getByUserId(int userId){
		ArrayList<PostVO> list = new ArrayList<PostVO>();
		sb.setLength(0);
        sb.append("select * from POST ");
        sb.append("where user_id = ? ");
        sb.append("order by created_at desc");

        try {
        	pstmt = conn.prepareStatement(sb.toString());
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
            	PostVO vo = new PostVO(
            			rs.getInt("post_id"), 
    					rs.getInt("user_id"), 
    					rs.getInt("style_category"),
    					rs.getString("title"), 
    					rs.getString("content"), 
    					rs.getInt("likes_count"),
    					rs.getInt("comments_count"), 
    					rs.getInt("views_count"), 
    					rs.getTimestamp("created_at"),
    					rs.getTimestamp("updated_at")
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
	
	// 조회수 증가 메서드
	public void plusView(int postId) {
		sb.setLength(0);
        sb.append("update post ");
        sb.append("set views_count = views_count + 1 ");
        sb.append("where post_id = ?");
        
        try {
        	pstmt = conn.prepareStatement(sb.toString());
            pstmt.setInt(1, postId);
            pstmt.executeUpdate();
            System.out.println("조회수 증가 완료");
        } catch (SQLException e) {
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