package com.jelly.www.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.jelly.www.action.*;

import java.io.IOException;

@WebServlet("/jelly")
public class JellyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
		// 한글처리 요청 인코딩
		req.setCharacterEncoding("UTF-8");
		// 응답의 컨텐츠 타입 지정
		resp.setContentType("text/html;charset=UTF-8");	
		
        // 요청 파라미터
        String page = req.getParameter("page");
        String url = null;
        Action action = null;

        // GET 요청 처리
        if (page == null || page.equals("home")) {
            action = new HomeAction(); // 홈 화면 처리
        } else if (page.equals("login")) {
            url = "/views/login/login.jsp"; // 로그인 페이지 처리
        } else if (page.equals("joinForm")) {
            url = "/views/join/joinForm.jsp"; // 회원가입 페이지 이동
        } else if (page.equals("logout")) {                              
            action = new LogoutAction(); // 로그아웃 처리
        } else if (page.equals("wish")) {
            if (isUserLoggedIn(req)) {
                action = new WishAction(); // 관심 페이지 처리
            } else {
                url = "/views/login/login.jsp"; // 로그인 페이지로 리다이렉트
            }
        } else if (page.equals("mypage")) {
            if (isUserLoggedIn(req)) {
                action = new MypageAction(); // 마이 페이지 처리
            } else {
                url = "/views/login/login.jsp"; // 로그인 페이지로 리다이렉트
            }
        } else if (page.equals("notice")) {
            action = new NoticeAction(); // 고객센터 페이지 처리
        } else if (page.equals("bottoms")) {
            action = new BottomsAction(); // 하의 페이지 처리
        } else if (page.equals("brand")) {
            action = new BrandAction(); // 브랜드 페이지 처리
        } else if (page.equals("category")) {
            action = new CategoryAction(); // 카테고리 페이지 처리
        } else if (page.equals("hats")) {
            action = new HatsAction(); // 모자 페이지 처리
        } else if (page.equals("luxury")) {
            action = new LuxuryAction(); // 럭셔리 페이지 처리
        } else if (page.equals("outer")) {
            action = new OuterAction(); // 아우터 페이지 처리
        } else if (page.equals("tops")) {
            action = new TopsAction(); // 상의 페이지 처리
        } else if (page.equals("wallets")) {
            action = new WalletsAction(); // 지갑 페이지 처리
        } else if (page.equals("shoes")) {
            action = new ShoesAction(); // 신발 페이지 처리
        } else if (page.equals("bags")) {
            action = new BagsAction(); // 가방 페이지 처리
        } else if (page.equals("style")) {
            action = new StyleAction(); // 스타일 페이지 처리
        } else if (page.equals("event")) {
            action = new EventAction(); // 이벤트 페이지 처리
        } else if (page.equals("popular")) {
            action = new PopularAction(); // 인기상품 페이지 처리
        } else if (page.equals("productDetail")) {
        	action = new ProductDetailAction(); // 상품 디테일 페이지 처리
        } else if (page.equals("event1")) {
            url = "/views/event/event1.jsp"; // Event1 페이지 처리
        } else if (page.equals("event2")) {
            url = "/views/event/event2.jsp"; // Event2 페이지 처리
        } else if (page.equals("faq")) {
        	url = "/views/notice/faq.jsp"; // 자주묻는질문(FAQ) 페이지 처리
        } else if (page.equals("notice")) {
            url = "/views/notice/notice.jsp"; // 공지사항 페이지로 이동 -> 이거 url 아니고 NoticeAction으로 줄건데 일단 임시로 해놓음
        } else if (page.equals("findEmail")) {
            action = new FindEmailAction(); // 이메일 찾기 페이지 처리
        } else if (page.equals("findPw")) {
            action = new FindPwAction(); // 비밀번호 찾기 페이지 처리
        } else if (page.equals("userAccount")) {
            url = "/views/mypage/userAccount.jsp"; // 판매 정산 계좌 이동
        } else if (page.equals("profileInfo")) {
            url = "/views/mypage/profileInfo.jsp"; // 프로필 관리 계좌 이동
        }  else {
            url = "/WEB-INF/views/error/404.jsp"; // 에러 페이지 처리
        } 

        // Action 실행
        if (action != null) {
            url = action.execute(req, resp);
        }

        // 페이지 이동
        if (url != null && !resp.isCommitted()) {
            RequestDispatcher rd = req.getRequestDispatcher(url);
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
		// 한글처리 요청 인코딩
		req.setCharacterEncoding("UTF-8");
		// 응답의 컨텐츠 타입 지정
		resp.setContentType("text/html;charset=UTF-8");	
		

        // 요청 파라미터
        String page = req.getParameter("page");
        String url = null;
        Action action = null;

        // POST 요청 처리
        if ("login".equals(page)) {
            action = new LoginAction(); // 로그인 요청 처리
        } else if ("joinNum".equals(page)) {
            action = new JoinNumAction(); // 인증 코드 처리
        }  else if ("joinOk".equals(page)) {
            action = new JoinOkAction(); // 인증코드 입력 후 회원가입 요청 처리
        }  else if ("findoutEmail".equals(page)) {
            action = new FindOutEmailAction();
        }   else if ("confirmPw".equals(page)) {
            action = new ConfirmPwAction(); // 비밀번호 찾기 페이지 처리
        } else {
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "메서드 확인");
            return;
        }

        // Action 실행
        if (action != null) {
            url = action.execute(req, resp);
        }

        // 페이지 이동
        if (url != null && !resp.isCommitted()) {
            RequestDispatcher rd = req.getRequestDispatcher(url);
            rd.forward(req, resp);
        }
    }

    // 유저 로그인 상태 확인 메서드
    private boolean isUserLoggedIn(HttpServletRequest req) {
        return req.getSession().getAttribute("user") != null;
    }
}