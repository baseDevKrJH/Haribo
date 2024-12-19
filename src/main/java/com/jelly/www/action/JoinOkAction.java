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

        // UserVO 객체 생성
        UserVO user = new UserVO();
        user.setUserName(username);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);

        // DAO 생성
        UserDAO dao = new UserDAO();

        // 중복 회원 체크
        if (dao.isDuplicate(email, phoneNumber)) {
            System.out.println("중복된 회원 존재");
            req.setAttribute("error", "이미 가입된 이메일 또는 전화번호입니다.");
            return "/views/join/joinForm.jsp"; // 중복 -> 다시 회원가입폼으로 보냄
        }

        // 중복이 없을 경우 사용자 데이터 삽입
        int result = dao.insertOne(user);
        dao.close();

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
    }
}