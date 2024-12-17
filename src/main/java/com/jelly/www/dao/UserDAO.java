package com.jelly.www.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.jelly.www.vo.UserVO;

public class UserDAO {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/jelly";
    private String user = "scott";
    private String password = "tiger";

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private StringBuilder sb = new StringBuilder();

    public UserDAO() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("MySQL DB 연결 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL 드라이버 로드 실패");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("MySQL DB 연결 실패");
            e.printStackTrace();
        }
    }

    // 1. 사용자 전체 조회
    public List<UserVO> selectAll() {
        List<UserVO> userList = new ArrayList<>();
        sb.setLength(0);
        sb.append("SELECT * FROM USER");

        try {
            pstmt = conn.prepareStatement(sb.toString());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                UserVO user = new UserVO(
                    rs.getInt("user_id"),
                    rs.getString("user_name"),
                    rs.getString("nickname"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("phone_number"),
                    rs.getString("birth_date"),
                    rs.getString("kakao_id"),
                    rs.getString("naver_id"),
                    rs.getString("profile_image"),
                    rs.getInt("follower_count"),                   
                    rs.getInt("following_count"),                   
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at")
                );
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return userList;
    }

    // 2. 특정 사용자 조회 (ID로)
    public UserVO selectOne(int userId) {
        UserVO user = null;
        sb.setLength(0);
        sb.append("SELECT * FROM USER WHERE user_id = ?");

        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new UserVO(
                    rs.getInt("user_id"),
                    rs.getString("user_name"),
                    rs.getString("nickname"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("phone_number"),
                    rs.getString("birth_date"),
                    rs.getString("kakao_id"),
                    rs.getString("naver_id"),
                    rs.getString("profile_image"),
                    rs.getInt("follower_count"),                   
                    rs.getInt("following_count"),
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return user;
    }

    // 3. 사용자 추가
    public int insertOne(UserVO user) {
        sb.setLength(0);
        sb.append("INSERT INTO USER (user_name, nickname, email, password, phone_number, birth, kakao_id, naver_id, profile_image, created_at, updated_at) ");
        sb.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())");

        int result = 0;
        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getNickname());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getPhoneNumber());
            pstmt.setString(6, user.getBirthDate());
            pstmt.setString(7, user.getKakaoId());
            pstmt.setString(8, user.getNaverId());
            pstmt.setString(9, user.getProfileImage());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return result;
    }

    // 4. 사용자 삭제
    public int deleteOne(int userId) {
        sb.setLength(0);
        sb.append("DELETE FROM USER WHERE user_id = ?");

        int result = 0;
        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setInt(1, userId);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return result;
    }

    // 5. 사용자 정보 업데이트
    public int updateOne(UserVO user) {
        sb.setLength(0);
        sb.append("UPDATE USER SET user_name = ?, nickname = ?, email = ?, password = ?, phone_number = ?, ");
        sb.append("birth_date = ?, kakao_id = ?, naver_id = ?, profile_image = ?, updated_at = NOW() ");
        sb.append("WHERE user_id = ?");

        int result = 0;
        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getNickname());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getPhoneNumber());
            pstmt.setString(6, user.getBirthDate());
            pstmt.setString(7, user.getKakaoId());
            pstmt.setString(8, user.getNaverId());
            pstmt.setString(9, user.getProfileImage());
            pstmt.setInt(10, user.getUserId());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return result;
    }

    // 6. 이메일 및 비밀번호로 사용자 조회
    public UserVO findOneByEmailAndPw(String email, String password) {
        UserVO user = null;
        sb.setLength(0);
        sb.append("SELECT * FROM USER WHERE email = ? AND password = ?");

        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new UserVO(
                    rs.getInt("user_id"),
                    rs.getString("user_name"),
                    rs.getString("nickname"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("phone_number"),
                    rs.getString("birth_date"),
                    rs.getString("kakao_id"),
                    rs.getString("naver_id"),
                    rs.getString("profile_image"),
                    rs.getInt("follower_count"),                   
                    rs.getInt("following_count"),    
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return user;
    }
    
    // 7. 회원가입할 때 중복 회원 체크하는 메서드
    public boolean isDuplicate(String email, String phoneNumber) {
        sb.setLength(0);
        sb.append("SELECT COUNT(*) FROM USER WHERE email = ? OR phone_number = ?");
        
        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setString(1, email);
            pstmt.setString(2, phoneNumber);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0; // 중복이 있으면 true
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        
        return false; // 중복이 없으면 false 
    }
    
    
    // 자원 해제
    private void close() {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}