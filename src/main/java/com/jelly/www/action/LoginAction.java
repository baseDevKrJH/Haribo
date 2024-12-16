package com.jelly.www.action;

import com.jelly.www.dao.UserDAO;
import com.jelly.www.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        String emailError = null;
        String passwordError = null;

        // 이메일 유효성 검사
        if (email == null || !email.contains("@") || email.length() < 10) {
            emailError = "정확한 이메일 주소를 입력하세요.";
        }

        // 비밀번호 유효성 검사
        if (password == null || password.length() < 8 || password.length() > 16) {
            passwordError = "비밀번호는 8자 이상, 16자 이하로 입력하세요.";
        }

        if (emailError == null && passwordError == null) {
            UserDAO dao = new UserDAO();
            UserVO vo = dao.findOneByEmailAndPw(email, password);

            if (vo != null) {
                // 로그인 성공
                System.out.println("로그인 성공");

                // 세션에 사용자 정보 저장
                HttpSession session = req.getSession();
                session.setAttribute("user", vo); // 로그인한 사용자 정보 세션에 저장

                // 홈 페이지로 리다이렉트
                return "/views/home/home.jsp";
            } else {
                // 로그인 실패 메시지를 요청 속성에 저장
                req.setAttribute("errorMessage", "이메일 또는 비밀번호가 잘못되었습니다.");
                return "/views/login/login.jsp";
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
            return "/views/login/login.jsp";
        }
    }
}