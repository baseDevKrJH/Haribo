package com.jelly.www.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import com.jelly.www.action.Action;
import com.jelly.www.action.BottomsAction;
import com.jelly.www.action.BrandAction;
import com.jelly.www.action.CategoryAction;
import com.jelly.www.action.HatsAction;
import com.jelly.www.action.HomeAction;
import com.jelly.www.action.LogoutAction;
import com.jelly.www.action.LuxuryAction;
import com.jelly.www.action.MypageAction;
import com.jelly.www.action.NoticeAction;
import com.jelly.www.action.OuterAction;
import com.jelly.www.action.SearchAction;
import com.jelly.www.action.WishAction;
import com.jelly.www.dao.PostDAO;
import com.jelly.www.dao.PostImageDAO;
import com.jelly.www.vo.PostImageVO;
import com.jelly.www.vo.PostVO;
import com.jelly.www.vo.UserVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 3,       // 3MB
        maxRequestSize = 1024 * 1024 * 60    // 60MB
)
public class UploadImageController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글처리 요청 인코딩
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession();
	    UserVO user = (UserVO) session.getAttribute("user");
	    String url = null;

	    if (user != null) {
	        // Access user properties
	        String username = user.getUserName();
	        String password = user.getPassword();
	        System.out.println(username + password);
	        
	        // 요청 파라미터
	        String page = req.getParameter("page");

	        // 요청 처리
	        if (page.equals("stylePost")) {
	            url = "/views/style/postNewStyle.jsp";
	        } else {
	            url = "/views/error/404.jsp"; // 에러 페이지 처리
	        }    
	    } else {
	    	// goto login page if user not logged in
	    	url = "/views/login/login.jsp";
	    }
	    // forward or redirect
        if (url != null && url.startsWith("redirect:")) {
            resp.sendRedirect(url.substring("redirect:".length()));
            return;
        }
	    if (url != null && !resp.isCommitted()) {
	        RequestDispatcher rd = req.getRequestDispatcher(url);
	        rd.forward(req, resp);
	    }        
	}
	
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get user session
		HttpSession session = request.getSession();
	    UserVO user = (UserVO) session.getAttribute("user");

	    if (user != null) {
	        // Access user properties
	        String username = user.getUserName();
	        String password = user.getPassword();
	        System.out.println(username + password);
	    } 
		
		
		
		// in case we use file upload somewhere else
		String uploadType = request.getParameter("uploadType");
		
		// set where you need to redirect to
		String url = null;
		
		//set these values to where you want to save in the if statements
		String upload_directory = null;
		String uploadPath = null;
        if ("stylePost".equals(uploadType)) {
        	upload_directory = "postImages";
        	uploadPath = request.getServletContext().getRealPath("") + upload_directory;
        	System.out.println(request.getContextPath());
        	System.out.println(request.getServletContext());
        } else {
        	response.getWriter().println("Invalid upload type.");
        }
         
        // create directory if it doesn't exist
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
        
        
        try {
        	int seq = 1;
            for (Part part : request.getParts()) {
            	String submittedFileName = part.getSubmittedFileName();
                if (submittedFileName != null && !submittedFileName.isEmpty()) {
                    // Sanitize file name
                    String fileName = cleanFileName(submittedFileName);
                	
                	// unique file name use UUID 
                    String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;

                    // create the file path
                    String filePath = uploadPath + File.separator + uniqueFileName;

                    // create fileURL
                    String baseUrl = "http://localhost:8080";
                    String fileURL = baseUrl + request.getContextPath() + "/" + upload_directory + "/" + uniqueFileName;

                    System.out.println("File Path: " + filePath);
                    System.out.println("File URL: " + fileURL);
                    
                    // save to database
                    if(uploadType.equals("stylePost")) {
                    	// create new post first 
                    	PostDAO dao = new PostDAO();
                    	PostVO vo = new PostVO();
                    	vo.setUserId(user.getUserId());
                    	vo.setTitle(request.getParameter("title"));
                    	vo.setContent(request.getParameter("content"));
                    	vo.setStyleCategory(Integer.parseInt(request.getParameter("styleCategory")));
                    	vo.setThumbnailImageUrl(fileURL);
                    	dao.createNewPost(vo);
                    	
                    	// get post number  
                    	PostVO newVO = dao.getUsersNewPost(user.getUserId());
                    	int postId = newVO.getPostId();
             
                    	// save image to post_img table
                    	PostImageDAO imageDAO = new PostImageDAO();
                    	PostImageVO imageVO = new PostImageVO();
                    	imageVO.setPostId(postId);
                    	imageVO.setPostImageOrder(1);
                    	imageVO.setPostImageUrl(fileURL);
                    	imageDAO.insertOne(imageVO);
                    	
                    	url = "/views/style/style.jsp";
                    }

                    part.write(filePath);
                    response.getWriter().println("File uploaded successfully: " + fileURL);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error uploading file.");
        }
        
        
        
        if (url != null && url.startsWith("redirect:")) {
            response.sendRedirect(url.substring("redirect:".length()));
            return;
        }
	    // 페이지 이동
	    if (url != null && !response.isCommitted()) {
	        RequestDispatcher rd = request.getRequestDispatcher(url);
	        rd.forward(request, response);
	    }
    }
    
	
	private String cleanFileName(String fileName) {
	    return new File(fileName).getName().replaceAll("[^a-zA-Z0-9._-]", "_");
	}
}