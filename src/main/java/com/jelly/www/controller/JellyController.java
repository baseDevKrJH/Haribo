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
        resp.setContentType("text/html;charset=UTF-8");

        // 요청 파라미터
        String page = req.getParameter("page");
        String query = req.getParameter("query");
        String url = null;
        Action action = null;

        // 요청 처리
        if (query != null && !query.trim().isEmpty()) {
            action = new SearchAction(); // 검색 요청 처리
        } else if (page == null || page.equals("home")) {	
            action = new HomeAction(); // 홈 화면 처리
        } else if (page.equals("login")) {
            url = "/views/login/login.jsp"; // 로그인 페이지 처리
        } else if (page.equals("joinForm")) {
            url = "/views/join/joinForm.jsp"; // 회원가입 페이지 이동
        } else if (page.equals("logout")) {
            action = new LogoutAction(); // 로그아웃 처리
        } else if ("checkWishlist".equals(page)) {
                action = new CheckWishlistAction();  // 관심상품 상태를 확인하는 액션
        } else if (page.equals("bottoms")) {
            action = new BottomsAction(); // 하의 페이지 처리
        } else if (page.equals("brand")) {
            action = new BrandAction(); // 브랜드 페이지 처리
        } else if (page.equals("category")) {
            action = new CategoryAction(); // 카테고리 페이지 처리
        } else if (page.equals("wish")) {
            action = new WishPageAction(); // 장바구니 페이지 처리
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
            action = new ShoesAction(); // 신발 페이지
            req.setAttribute("currentPage", "shop"); // 현재 페이지 설정
        } else if (page.equals("bags")) {
            action = new BagsAction(); // 가방 페이지 처리
        } else if (page.equals("styleDetail")) {
        	action = new StyleDetailAction(); // 스타일 디테일 페이지 처리
        } else if (page.equals("styleList")) {
        	action = new StyleListAction(); // 스타일 list 처리
        } else if (page.equals("styleProfile")) {
        	action = new StyleProfileAction(); // 스타일 프로필 처리
        } else if (page.equals("styleModify")) {
        	action = new StyleModifyAction(); // 스타일 수정 처리
        } else if (page.equals("follower")) {
        	action = new FollowerAction(); // 팔로워 처리
        } else if (page.equals("following")) {
        	action = new FollowingAction(); // 팔로잉 처리	
        } else if (page.equals("popular")) {
            action = new ProductPopularAction(); // 인기상품 페이지 처리
        } else if (page.equals("productDetail")) {
            action = new ProductDetailAction(); // 상품 디테일 페이지 처리
        } else if (page.equals("event1")) {
            url = "/views/event/event1.jsp"; // Event1 페이지 처리
        } else if (page.equals("event2")) {
            url = "/views/event/event2.jsp"; // Event2 페이지 처리
        } else if (page.equals("notice")) {
            action = new NoticeAction(); // 공지사항 목록 페이지 처리
        } else if (page.equals("noticeWrite")) {
            url = "/views/notice/noticeWrite.jsp"; // 작성 페이지 이동
        } else if (page.equals("noticeEdit")) {
        	action = new NoticeEditAction();
        } else if (page.equals("noticeDetail")) {
        	action = new NoticeDetailAction();
        } else if (page.equals("noticeDelete")) {
            action = new NoticeDeleteAction(); // 공지사항 삭제 페이지 처리
        } else if (page.equals("faq")) {
        	action = new FaqAction(); // 자주묻는질문(FAQ) 페이지 처리
        } else if (page.equals("search")) {
            action = new SearchAction(); // 검색 요청 처리
        } else if (page.equals("viewProfileInfo")) {
            action = new MyProfileInfoViewAction(); // 프로필 정보 조회
        } else if (page.equals("mypage")) {
            if (isUserLoggedIn(req)) {
                action = new MypageAction(); // 마이 페이지 처리
            } else {
                url = "/views/login/login.jsp"; // 로그인 페이지로 리다이렉트
            }
        } else if (page.equals("purchaseHistory")) {
            // 구매내역 조회는 로그인한 사용자만 볼 수 있다고 가정
            if (isUserLoggedIn(req)) {
                action = new PurchaseHistoryAction(); // 구매내역 페이지 처리
            } else {
                url = "/views/login/login.jsp"; // 로그인 페이지로 리다이렉트
            }
        } else if (page.equals("salesHistory")) {
            if (isUserLoggedIn(req)) {
                action = new SalesHistoryAction(); // 판매내역 페이지 처리
            } else {
                url = "/views/login/login.jsp";
            }     
        } else if (page.equals("purchaseHistoryDetail")) {
            if (isUserLoggedIn(req)) {
                action = new PurchaseHistoryDetailAction(); // 구매내역 상세 페이지 처리
            } else {
                url = "/views/login/login.jsp";
            }
        } else if (page.equals("salesHistoryDetail")) {
            if (isUserLoggedIn(req)) {
                action = new SalesHistoryDetailAction(); // 판매내역 상세 페이지 처리
            } else {
                url = "/views/login/login.jsp";
            }
        } else if (page.equals("filter")) {
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET 요청은 허용되지 않습니다.");
            return;
        } else {
            url = "/views/error/404.jsp"; // 에러 페이지 처리
        }

        // Action 실행
        if (action != null) {
            url = action.execute(req, resp);

            // 리다이렉션 처리
            if (url != null && url.startsWith("redirect:")) {
                resp.sendRedirect(url.substring("redirect:".length()));
                return;
            }
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
        resp.setContentType("application/json;charset=UTF-8");

        // 요청 파라미터
        String page = req.getParameter("page");
        Action action = null;

        // POST 요청 처리
        if ("login".equals(page)) {
            action = new LoginAction(); // 로그인 요청 처리
        } else if ("joinOk".equals(page)) {
            action = new JoinOkAction(); // 회원가입 요청 처리
        } else if ("delete".equals(page)) {
        	action = new DeleteStyleAction();
            System.out.println("deleting");
        } else if (page.equals("updateProfileInfo")) {
            action = new MyProfileInfoUpdateAction(); // 프로필 정보 업데이트
        } else if ("noticeWrite".equals(page)) {
            action = new NoticeWriteAction(); // 공지사항 작성 처리    
        } else if ("noticeDelete".equals(page)) {
        	action = new NoticeDeleteAction(); // 공지사항 삭제 처리
        } else if (page.equals("noticeEditOk")) {
        	action = new NoticeEditOkAction();  // 공지사항 수정 처리
        } else if ("filter".equals(page)) {
            FilterController filterController = new FilterController();
            filterController.handleRequest(req, resp);
            return; // FilterController에서 응답을 직접 처ㄹ
        } else if ("wishlistToggle".equals(page)) {
            action = new WishlistToggleAction(); // 관심상품 토글 처리
        } else if ("checkWishlist".equals(page)) {
                action = new CheckWishlistAction(); // 관심상품 상태 확인 액션
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "요청 처리 대상이 없습니다.");
            return;
        }

        // Action 실행
        if (action != null) {
            String url = action.execute(req, resp);

            // 리다이렉션 처리
            if (url != null && url.startsWith("redirect:")) {
                resp.sendRedirect(url.substring("redirect:".length()));
            }
            // 페이지 이동
            if (url != null && !resp.isCommitted()) {
            	RequestDispatcher rd = req.getRequestDispatcher(url);
            	rd.forward(req, resp);
            }
        }
    }

    // 유저 로그인 상태 확인 메서드
    private boolean isUserLoggedIn(HttpServletRequest req) {
        return req.getSession().getAttribute("user") != null;
    }
}