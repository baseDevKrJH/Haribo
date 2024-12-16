package com.jelly.www.action;

import com.jelly.www.dao.UserDAO;
import com.jelly.www.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JoinOkAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        // 사용자 입력값 가져오기
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phone_number");
        String password = req.getParameter("password");
        String emailError = null;
        String passwordError = null;

        // UserVO 객체 생성
        UserVO user = new UserVO();
        user.setUsername(username);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);

        // DAO 생성
        UserDAO dao = new UserDAO();

        // 이메일 유효성 검사
        if (email == null || !email.contains("@") || email.length() < 10) {
        	System.out.println("이메일 양식 에러");
            emailError = "정확한 이메일 주소를 입력하세요.";
        }

        // 비밀번호 유효성 검사
        if (password == null || password.length() < 8 || password.length() > 16) {
        	System.out.println("비밀번호 글자 수 에러");
            passwordError = "비밀번호는 8자 이상, 16자 이하로 입력하세요.";
        }
        
        // 중복 회원 체크
        if (dao.isDuplicate(email, phoneNumber)) {
            System.out.println("중복된 회원 존재");
            req.setAttribute("error", "이미 가입된 이메일 또는 전화번호입니다.");
            }
        
        if (emailError == null && passwordError == null) {
        	
        	// 중복이 없을 경우 사용자 데이터 삽입
        	int result = dao.insertOne(user);
        	
        	// 결과에 따라 적절한 페이지로 이동
        	if (result > 0) {
        		System.out.println("회원가입 성공");
        		req.setAttribute("success", true);
        		return "/views/join/joinOk.jsp"; // 성공 -> 결과 페이지로 
        	} else {
        		System.out.println("회원가입 실패");
        		req.setAttribute("error", "회원가입에 실패했습니다. 다시 시도해주세요.");
        		return "/views/join/joinForm.jsp"; // 실패 -> 다시 회원가입 폼으로 보냄
        	}
        } else {
            // 이메일이나 비밀번호가 유효하지 않은 경우, 에러 메시지를 요청 속성에 추가
            if (emailError != null) {
                req.setAttribute("emailError", emailError);
            }
            if (passwordError != null) {
                req.setAttribute("passwordError", passwordError);
            }

            // 로그인 페이지로 이동
            return "/views/join/joinForm.jsp";
        }
        
    }
}