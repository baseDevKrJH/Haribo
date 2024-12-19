<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/views/home/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
  	<style>
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
                <option value="2">Street</option>
                <option value="3">Modern</option>
                <option value="4">Vintage</option>
                <option value="5">Minimal</option>
                <option value="6">Formal</option>
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

