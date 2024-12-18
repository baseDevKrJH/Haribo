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

        // DAO를 이용하여 사용자 정보 조회
        UserDAO dao = new UserDAO();
        UserVO vo = dao.findOneByEmailAndPw(email, password);

        if (vo != null) {
        	System.out.println("로그인 성공!");
            // 세션에 사용자 정보 저장
            HttpSession session = req.getSession();
            session.setAttribute("user", vo); // 사용자 전체 정보 저장
            session.setAttribute("user_id", vo.getUserId()); // user_id만 별도로 저장 (구매내역에서 사용함)

            // 리다이렉트
            return "redirect:/haribo/jelly";
        } else {
            // 로그인 실패
            System.out.println("로그인 실패: 이메일 또는 비밀번호 불일치");

            // 실패 메시지 저장
            req.setAttribute("errorMessage", "이메일 또는 비밀번호가 잘못되었습니다.");

            // 로그인 페이지로 이동
            return "/views/login/login.jsp";
        }
    }
}