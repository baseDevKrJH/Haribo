<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/views/home/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
  	<style>
  		/* Basic reset for the page */
		* {
		    margin: 0;
		    padding: 0;
		    box-sizing: border-box;
		}
		
		body {
		    font-family: Arial, sans-serif;
		    background-color: #f4f4f4;
		    color: #333;
		    line-height: 1.6;
		}
		
		#formContainer {
		    width: 60%;
		    margin: 30px auto;
		    background-color: #fff;
		    padding: 20px;
		    border-radius: 8px;
		    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
		}
		
		form {
		    display: flex;
		    flex-direction: column;
		}
		
		label {
		    font-size: 16px;
		    margin-bottom: 8px;
		    font-weight: bold;
		}
		
		input[type="text"],
		textarea,
		select {
		    width: 100%;
		    padding: 10px;
		    margin-bottom: 15px;
		    border: 1px solid #ddd;
		    border-radius: 4px;
		}
		
		input[type="file"] {
		    margin-bottom: 15px;
		}
		
		input[type="submit"] {
		    padding: 10px 15px;
		    background-color: #333;
		    color: #fff;
		    border: none;
		    border-radius: 4px;
		    cursor: pointer;
		    transition: background-color 0.3s;
		}
		
		input[type="submit"]:hover {
		    background-color: #555;
		}
		
		#imagePreview {
		    display: flex;
		    flex-wrap: wrap;
		    gap: 10px;
		}
		
		#imagePreview img {
		    max-width: 100px;
		    max-height: 100px;
		    border-radius: 4px;
		    object-fit: cover;
		}
		
		textarea {
		    resize: vertical;
		    min-height: 100px;
		}
		
		select {
		    padding: 10px;
		}
		  		
  	
  	</style>
</head>
<body id="body">
    <div id="formContainer">
        <form action="<%= request.getContextPath() %>/upload" method="post" enctype="multipart/form-data" onsubmit="alert('posted')">
            <input type="hidden" name="uploadType" value="stylePost" />
            
            <label for="titleInput">Title:</label>
            <input type="text" name="title" id="titleInput">

            <label for="contentInput">Caption:</label>
            <textarea name="content" id="contentInput"></textarea>

            <label for="imageInput">Select Image:</label>
            <input type="file" name="postImages" id="imageInput" multiple="multiple" onchange="previewImages()">
            
            <!-- Image preview section -->
            <div id="imagePreview"></div>
            <br />

            <label for="styleCategoryInput">Style Category:</label>
            <select name="styleCategory" id="styleCategoryInput">
                <option value="1" selected>Casual</option>
                <option value="2">Formal</option>
                <option value="3">Athletic</option>
                <option value="4">Vintage</option>
                <option value="5">Street</option>
            </select>
            
            <input type="submit" id="submitButton" value="Post">
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
</body>
</html>

