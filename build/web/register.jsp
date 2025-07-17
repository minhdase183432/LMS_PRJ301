<%-- 
    Document   : register
    Created on : May 29, 2025, 2:40:08 PM
    Author     : msi2k
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dao.UserError"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <a class="home" href="home.jsp">Home</a>
        <div class="regis">
            <% UserError userError = (UserError) request.getAttribute("USER_ERROR");
            if (userError == null) userError = new UserError();
            %>
            <h2>REGISTER</h2>
            <form action="RegisterController" method="POST">
                Name <input type="text" name="name" value="" required=""/>*<br/>
                <span style="color: red;"><%= userError.getNameError() %></span><br/>
                Email <input type="text" name="email" value="" required=""/>*<br/>
                <span style="color: red;"><%= userError.getEmailError() %></span><br/>
                Password <input type="password" name="pass" value="" required=""/>*<br/>
                <span style="color: red;"><%= userError.getPasswordError() %></span><br/>
                Confirm Password <input type="password" name="confpass" value="" required=""/>*<br/>
                <span style="color: red;"><%= userError.getConfirmPassError() %></span><br/>
                <input class="dangky" type="submit" name="action" value="Create Account"/><br/>
            </form>
            <a href="login.jsp">You have already account! Let's login now</a>
            <br/><span style="color: red;"><%= userError.getError() %></span>
        </div>
    </body>

    <style>
        body {
            background-image: url(./img/bg.jpg);
            background-repeat: no-repeat;
            background-size: 100%;
        }

        h2 {
            margin: 0;
            margin-bottom: 10px;
            padding: 0;
            font-family: tahoma;
            color: dodgerblue;
        }

        .regis {
            margin-left: 25rem;
            margin-top: 1.5rem;
            display: block;
            justify-items: center;
            text-align: center;
            padding: 2rem;
            width: 60vh;
            height: 75vh;
            background-color: white;
            border-radius: 20px;
            box-shadow: gray 0px 0px 10px 1px;
        }

        form {
            text-align: left;
            display: inline-block;
            width: 100%;
            height: auto;
            font-family: arial;
        }

        input {
            border-radius: 5px;
            padding: 0.4rem;
            margin: 10px;
            width: 85%;
        }

        .dangky {
            margin-left: 6rem;
            background-color: dodgerblue;
            width: 50%;
            padding: 0.6rem;
            border: none;
            color: white;
            font-size: 18px;
        }

        a {
            text-align: center;
            text-decoration: none;
            color: dodgerblue;
            font-family: arial;
        }

        .home {
            margin-left: 70rem;
            background-color: white;
            padding: 10px;
            text-decoration: none;
            color: dodgerblue;
            font-family: arial;
        }
    </style>
</html>