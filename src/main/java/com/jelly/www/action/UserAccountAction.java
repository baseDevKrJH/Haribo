package com.jelly.www.action;

import com.jelly.www.dao.UserAccountDAO;
import com.jelly.www.vo.UserAccountVO;
import com.jelly.www.vo.UserVO;
import com.mysql.cj.Session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserAccountAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");
        String bankname = req.getParameter("bankname");
        String accountnum = req.getParameter("accountnum");
        String accountname = req.getParameter("accountname");

        HttpSession session = req.getSession(true);
        Object obj = session.getAttribute("user");
        UserVO user = (UserVO) obj;
        System.out.println(user);
        int sessionuserid = user.getUserId();

        UserAccountDAO dao = new UserAccountDAO();

        if ("modify".equals(action)) {
            // 입력된 데이터를 UserAccountVO 객체로 설정
            UserAccountVO user1 = new UserAccountVO();
            user1.setBankName(bankname);
            user1.setAccountNumber(accountnum);
            user1.setAccountHolder(accountname);
            user1.setUserId(sessionuserid);

            // 테이블 데이터를 수정하는 메서드 호출
            dao.updateAccount(user1);
            
            session.setAttribute("bankname", user1.getBankName());
            session.setAttribute("accountnum", user1.getAccountNumber());
            session.setAttribute("accountname", user1.getAccountHolder());
            
            System.out.println(user1.getBankName());
    

            req.setAttribute("successMessage", "계좌 정보가 수정되었습니다.");
            System.out.println("계좌 정보 수정 완료");
            return "views/mypage/userAccount.jsp";
        } else {
            String numError = null;

            if (accountnum == null || accountnum.length() <= 10) {
                numError = "계좌번호 형식이 잘못되었습니다.";
            }

            if (numError == null && sessionuserid != 0) {
                UserAccountVO user2 = new UserAccountVO();
                user2.setBankName(bankname);
                user2.setAccountNumber(accountnum);
                user2.setAccountHolder(accountname);
                user2.setUserId(sessionuserid);

                dao.insertAccount(user2);
                
                session.setAttribute("bankname", user2.getBankName());
                session.setAttribute("accountnum", user2.getAccountNumber());
                session.setAttribute("accountname", user2.getAccountHolder());

                req.setAttribute("successMessage", "판매 정산 계좌가 저장되었습니다.");
                System.out.println("계좌 정보 추가 완료");
            } else {
                req.setAttribute("errorMessage", "계좌번호를 정확히 입력하세요");
            }

            return "views/mypage/userAccount.jsp";
        }
    }
}