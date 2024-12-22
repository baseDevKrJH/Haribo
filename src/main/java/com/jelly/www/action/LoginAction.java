package com.jelly.www.action;

import java.util.List;

import com.jelly.www.dao.UserDAO;
import com.jelly.www.dao.WishlistDAO;
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

        if (vo != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", vo);
            session.setAttribute("user_id", vo.getUserId());
            session.setAttribute("userEmail", email); // 이메일 저장 추가 -- 형윤

            // 관리자 계정 확인 -- 형윤
            if ("admin@gmail.com".equals(email) && "1234".equals(password)) {
                session.setAttribute("isAdmin", true); 
            } else {
                session.setAttribute("isAdmin", false); 
            }

            // 관심목록 다시 세션에 저장 -- 형윤
            WishlistDAO wishlistDAO = new WishlistDAO();
            List<Integer> wishlist = wishlistDAO.getWishlistByUserId(vo.getUserId());
            session.setAttribute("wishlist", wishlist);

            // 로그인 후 리다이렉트할 URL 처리
            String redirectUrl = (String) session.getAttribute("redirectUrl");
            if (redirectUrl != null && !redirectUrl.isEmpty()) {
                // 세션에 저장된 URL로 리다이렉트
                return "redirect:" + redirectUrl;
            } else {
                // 세션에 URL이 없으면 홈으로 리다이렉트
                return "redirect:/haribo/jelly";
            }
        } else {
            req.setAttribute("errorMessage", "이메일 또는 비밀번호가 잘못되었습니다.");
            return "/views/login/login.jsp";
        }
    }
}