<%-- 
    Document   : Login
    Created on : May 29, 2025, 2:26:31 PM
    Author     : msi2k
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <a class="home" href="home.jsp">Home</a>
        <div class="login">
            <h2>LOGIN</h2>
            <form action="LoginController" method="POST">
                Email User <input type="text" name="email" value="" required=""/>*<br/>
                Password <input type="password" name="password" value="" required=""/>*<br/>
                <input class="loginbtn" type="submit" name="action" value="Login"/>
            </form>
            <a class="create" href="register.jsp">Create new account for you -></a><br/>
        
            <%
            String error = (String)request.getAttribute("ERROR");
            if(error == null) error = "";
            %>
            <%= error %>
        
        </div>


    </body>

    <style>
        body{
            background-image: url(./img/login.jpg);
            background-repeat: no-repeat;
            background-size: 100%;

        }

        h2{
            margin: 0;
            font-family: tahoma;
            color: dodgerblue;
        }
        .login{
            margin-left: 6rem;
            margin-top: 5rem;
            display: block;
            justify-items: center;
            text-align: center;
            padding: 2rem;
            width: 60vh;
            height: 60vh;
            background-image:url(./img/login_bg.jpg);
            background-size: 100% 100%;
            background-repeat: no-repeat;
            border-radius: 20px;
            box-shadow: gray 0px 0px 10px 1px;
        }

        form{
            text-align: left;
            display: inline-block;
            width: 100%;
            height: auto;
            font-family: arial;
        }

        input{
            border-radius: 5px;
            padding: 0.5rem;
            margin: 10px;
            width: 85%;
        }

        .loginbtn{
            color: white;
            font-style: normal;
            border: none;
            font-size: 18px;
            margin-left: 7rem;
            background-color: dodgerblue;
            width: 40%;
            padding: 0.6rem;
            margin-bottom:  20px;
            box-shadow: lightblue 0px 0px 5px 1px;

        }

        .create{

            text-align: center;
            text-decoration: none;
            color: dodgerblue;
            font-family: arial;
        }

        .home{
            margin-left: 70rem;
            background-color: white;
            padding: 10px;
            text-decoration: none;
            color: dodgerblue;
            font-family: arial;
        }
    </style>

</html>
