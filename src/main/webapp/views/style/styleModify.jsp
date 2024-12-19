<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/views/home/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
  	<style>
  		/* Black & White Themed Form Styles */
		#formContainer {
		    max-width: 600px;
		    margin: 50px auto;
		    padding: 20px;
		    border: 1px solid #000; /* Black border for definition */
		    border-radius: 10px;
		    background-color: #fff; /* White background */
		    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); /* Subtle shadow */
		    color: #000; /* Black text for contrast */
		}
		
		/* Form Labels */
		#formContainer label {
		    display: block;
		    margin-top: 15px;
		    font-weight: bold;
		    color: #000; /* Black labels */
		}
		
		/* Input Fields, Textarea, Select, and File Upload */
		#formContainer input[type="text"],
		#formContainer textarea,
		#formContainer select,
		#formContainer input[type="file"] {
		    width: calc(100% - 20px);
		    margin-top: 5px;
		    padding: 10px;
		    border: 1px solid #000; /* Black borders */
		    border-radius: 5px;
		    font-size: 14px;
		    box-sizing: border-box;
		    background-color: #fff; /* White input background */
		    color: #000; /* Black text */
		    outline: none;
		}
		
		#formContainer input[type="text"]:focus,
		#formContainer textarea:focus,
		#formContainer select:focus,
		#formContainer input[type="file"]:focus {
		    border-color: #000; /* Black border on focus */
		    box-shadow: 0 0 5px rgba(0, 0, 0, 0.3); /* Subtle black glow */
		}
		
		/* Textarea Specific */
		#formContainer textarea {
		    height: 80px;
		    resize: vertical; /* Allow vertical resizing */
		}
		
		/* Submit Button */
		#formContainer input[type="submit"] {
		    display: block;
		    width: 100%;
		    margin-top: 20px;
		    padding: 10px;
		    background-color: #000; /* Black button */
		    color: #fff; /* White text */
		    font-weight: bold;
		    border: none;
		    border-radius: 5px;
		    cursor: pointer;
		    font-size: 16px;
		    text-transform: uppercase;
		}
		
		#formContainer input[type="submit"]:hover {
		    background-color: #333; /* Slightly lighter black on hover */
		}
		
		/* Image Preview Section */
		#formContainer #imagePreview {
		    display: flex;
		    flex-wrap: wrap;
		    gap: 10px;
		    margin-top: 15px;
		}
		
		#formContainer #imagePreview img {
		    max-width: 100px;
		    max-height: 100px;
		    object-fit: cover;
		    border: 1px solid #000; /* Black border for preview images */
		    border-radius: 5px;
		    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Subtle shadow */
		}
		
		/* Mobile Responsiveness */
		@media (max-width: 768px) {
		    #formContainer {
		        padding: 15px;
		    }
		
		    #formContainer input[type="text"],
		    #formContainer textarea,
		    #formContainer select,
		    #formContainer input[type="file"] {
		        width: calc(100% - 10px);
		    }
		}
  	
		
  	</style>
</head>
<body id="body">
    <div id="formContainer">
    <form action="<%= request.getContextPath() %>/upload" method="post" enctype="multipart/form-data" onsubmit="alert('updated')">
        <input type="hidden" name="uploadType" value="styleModify" />
        <input type="hidden" name="modifyingPostId" value="${postVo.postId }" />

        <!-- Title Input -->
        <label for="titleInput">Title:</label>
        <input type="text" name="title" id="titleInput" 
               value="<c:out value='${postVo.title}' />">

        <!-- Caption (Content) -->
        <label for="contentInput">Caption:</label>
        <textarea name="content" id="contentInput"><c:out value="${postVo.content}" /></textarea>

        <!-- Image Input -->
        <label for="imageInput">Select Image:</label>
        <input type="file" name="postImages" id="imageInput" multiple="multiple" onchange="previewImages()">
        
        <!-- Image Preview Section -->
        <div id="imagePreview"></div>
        <br />

        <!-- Style Category Dropdown -->
        <label for="styleCategoryInput">Style Category:</label>
        <select name="styleCategory" id="styleCategoryInput">
            <option value="1" <c:if test="${postVo.styleCategory == 1}">selected</c:if>>Casual</option>
            <option value="2" <c:if test="${postVo.styleCategory == 2}">selected</c:if>>Street</option>
            <option value="3" <c:if test="${postVo.styleCategory == 3}">selected</c:if>>Modern</option>
            <option value="4" <c:if test="${postVo.styleCategory == 4}">selected</c:if>>Vintage</option>
            <option value="5" <c:if test="${postVo.styleCategory == 5}">selected</c:if>>Minimal</option>
            <option value="6" <c:if test="${postVo.styleCategory == 6}">selected</c:if>>Formal</option>
        </select>

        <!-- Submit Button -->
        <input type="submit" id="submitButton" value="Update">
    </form>
</div>
    

    <script>
        function previewImages() {
            const files = document.getElementById('imageInput').files;
            const previewContainer = document.getElementById('imagePreview');
            previewContainer.innerHTML = '';

            if (files.length > 0) {
                for (let i = 0; i < files.length; i++) {
                    const reader = new FileReader();
                    reader.onload = function(e) {
                        const img = document.createElement('img');
                        img.src = e.target.result;
                        previewContainer.appendChild(img);
                    };
                    reader.readAsDataURL(files[i]);
                }
            }
        }
    </script>
    <%@ include file="/views/home/footer.jsp" %>
</body>
</html>