<%-- 
    Document   : user
    Created on : Jun 1, 2025, 8:40:29 AM
    Author     : msi2k
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dto.UserDto"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Profile</title>
    </head>
    <body>
        <%
            UserDto loginUser = (UserDto) session.getAttribute("LOGIN_USER");
            if (loginUser == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            // Kiểm tra xem có đang ở chế độ chỉnh sửa không
            String editMode = request.getParameter("edit");
            boolean isEditMode = "true".equals(editMode);
        %>
        <a class="home" href="home.jsp">Home</a>
        <div class="profile">
            <h2><%= loginUser.getName() %>'s Profile</h2>
            
            <% 
                // Hiển thị thông báo lỗi nếu có
                String errorMessage = (String) session.getAttribute("ERROR_MESSAGE");
                if (errorMessage != null) { 
            %>
                <p style="color: red; text-align: center;"><%= errorMessage %></p>
            <% 
                session.removeAttribute("ERROR_MESSAGE"); 
                } 
            %>

            <% if (!isEditMode) { %>
                <!-- Hiển thị thông tin người dùng -->
                <div class="info">
                    <p><strong>Name:</strong> <%= loginUser.getName() %></p>
                    <p><strong>Email:</strong> <%= loginUser.getEmail() %></p>
                    <p><strong>Password:</strong> ****</p>
                    <div class="button-group">
                        <form action="user.jsp" method="GET" style="display: inline;">
                            <input type="hidden" name="edit" value="true"/>
                            <input class="edit-btn" type="submit" value="Edit Profile"/>
                        </form>
                        <a href="LogoutController" class="logout-btn">Logout</a>
                    </div>
                </div>
            <% } else { %>
                <!-- Form chỉnh sửa thông tin -->
                <form action="UpdateProfileController" method="POST">
                    <label>Name</label>
                    <input type="text" name="name" value="<%= loginUser.getName() %>" required/><br/>
                    <label>Email</label>
                    <input type="email" name="email" value="<%= loginUser.getEmail() %>" required/><br/>
                    <label>Password</label>
                    <input type="password" name="password" value="<%= loginUser.getPassword() %>" required/><br/>
                    <div class="button-group">
                        <input class="save-btn" type="submit" value="Save Changes"/>
                        <a class="cancel-btn" href="user.jsp">Cancel</a>
                    </div>
                </form>
            <% } %>
        </div>
    </body>
    <style>
        body {
            background-image: url(./img/bg.jpg);
            background-repeat: no-repeat;
            background-size: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .profile {
            margin: 0 auto;
            display: block;
            justify-items: center;
            text-align: center;
            padding: 2rem;
            width: 60vh;
            height: auto;
            background-color: white;
            border-radius: 20px;
            box-shadow: gray 0px 0px 10px 1px;
        }

        h2 {
            margin: 0 0 20px 0;
            padding: 0;
            font-family: tahoma;
            color: dodgerblue;
            text-align: center;
        }

        .info {
            text-align: left;
            font-family: arial;
            padding: 1rem;
        }

        .info p {
            margin: 10px 0;
            text-align: left;
        }

        form {
            text-align: left;
            display: inline-block;
            width: 100%;
            font-family: arial;
        }

        label {
            font-family: arial;
            display: block;
            margin: 10px 0 5px 10px;
            text-align: left;
        }

        input {
            border-radius: 5px;
            padding: 0.4rem;
            margin: 10px;
            width: 85%;
        }

        .button-group {
            text-align: center;
            margin-top: 20px;
        }

        .edit-btn, .save-btn {
            background-color: dodgerblue;
            padding: 0.6rem 1.5rem;
            border: none;
            color: white;
            font-size: 18px;
            cursor: pointer;
            border-radius: 5px;
            margin: 5px;
            display: inline-block;
        }

        .logout-btn {
            background-color: red;
            padding: 0.6rem 1.5rem;
            border: none;
            color: white;
            font-size: 18px;
            cursor: pointer;
            border-radius: 5px;
            margin: 5px;
            text-decoration: none;
            display: inline-block;
        }

        .cancel-btn {
            display: inline-block;
            text-decoration: none;
            color: dodgerblue;
            font-family: arial;
            font-size: 16px;
            padding: 0.6rem 1.5rem;
            border-radius: 5px;
            background-color: white;
            border: 1px solid dodgerblue;
            margin: 5px;
        }

        a {
            text-decoration: none;
            color: dodgerblue;
            font-family: arial;
        }

        .home {
            position: absolute;
            top: 10px;
            right: 10px;
            background-color: white;
            padding: 10px;
            text-decoration: none;
            color: dodgerblue;
            font-family: arial;
            border-radius: 5px;
        }
    </style>
</html>