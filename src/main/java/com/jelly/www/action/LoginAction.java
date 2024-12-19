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

        UserDAO dao = new UserDAO();
        UserVO vo = dao.findOneByEmailAndPw(email, password);
        dao.close();

        if (vo != null) {
            // 로그인 성공
            System.out.println("로그인 성공");

            // 세션에 사용자 정보 저장
            HttpSession session = req.getSession();
            session.setAttribute("user", vo); // 로그인한 사용자 정보 세션에 저장

            // 루트 페이지로 리다이렉트
            return "redirect:/haribo/jelly"; // 리다이렉트 URL 설정
        } else {
            // 로그인 실패
            System.out.println("로그인 실패");

            // 실패 메시지를 요청 속성에 저장
            req.setAttribute("errorMessage", "이메일 또는 비밀번호가 잘못되었습니다.");

            // 로그인 페이지로 이동
            return "/views/login/login.jsp";
        }
    }
}