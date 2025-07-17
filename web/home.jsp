<%-- 
    Document   : home
    Created on : Jun 1, 2025, 2:46:30 PM
    Author     : msi2k
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dto.UserDto"%>
<%@page import="dto.BookDto"%>
<%@page import="dao.BookDao"%>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <h1>RANG DONG BOOKSTORE</h1>
        <div class="menubar">
            <div class="col-md-6 menu1">
                <a href="home.jsp">Home</a>
                <a href="home.jsp">About Us</a>
                <a href="home.jsp">Borrow Book</a>
            </div>
            <div class="col-md-6 menu2">
                <form class="search" action="SearchController" method="get">
                    <input class="box" type="text" name="txtsearch" value="">            
                    <input class="submit" type="submit" value="search">
                </form>


                <% 
                    // Kiểm tra xem người dùng đã đăng nhập hay chưa
                    UserDto loginUser = (UserDto) session.getAttribute("LOGIN_USER");
                    if (loginUser != null) {
                %>
                <div class="user-info">
                    <a href="user.jsp"><%= loginUser.getName() %></a>
                </div>
                <a href="LogoutController">Logout</a>

                <% 
                    } else {
                %>
                <a href="login.jsp">Login/Register</a>
                <% 
                    }
                %>
            </div>
        </div>

        <div class="book-list">
            <% 
                BookDao bookDao = new BookDao();
                List<BookDto> bookList = bookDao.getAllBooks();
                if (bookList != null && !bookList.isEmpty()) {
                    for (BookDto book : bookList) {
            %>
            <div class="book-card">
                <h3><%= book.getTitle() %></h3>
                <p><strong>Year:</strong> <%= book.getPublishedYear() %></p>
                <p><strong>Available:</strong> <%= book.getAvailableCopies() %></p>
                <p><strong>Author:</strong> <%= book.getAuthor() %></p>
            </div>
            <% 
                    }
                } else {
            %>
            <p>No books available.</p>
            <% 
                }
            %>
        </div>
    </body>
    <style>
        h1
        {
            text-align: center;
            font-family: sans-serif;
            font-weight: bold;
            margin: 20px;
        }

        .menubar{
            display: flex;
            justify-content: center;
            background-color: dodgerblue;
            padding: 5px;
        }

        .menu1
        {
            display: flex;
            align-items:  center;

        }

        .menu2
        {
            display: flex;
            align-items:  center;

        }

        .menu1 a{
            margin-left: 3rem;
        }

        .menu2 a{
            margin-left: 3rem;
        }

        .search
        {
            display: flex;
            justify-content: center;
            align-items:  center;
            width: 20rem;
        }

        .box
        {
            padding: 0.3rem;
            width: 100%;
        }

        .submit
        {
            padding: 0.3rem;
            color: darkblue;
            background-color: gold;
            border: none;
        }

        a
        {
            text-decoration: none;
            font-family: arial;
            color: white;
        }

        .user-info{
            font-family: tahoma;
            color:brown;
        }

        .book-list {
            display: flex;
            justify-content: space-around;
            padding: 20px;
        }

        .book-card {
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 15px;
            margin: 10px;
            width: 200px;
            text-align: center;
            background-color: white;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }

        .book-card h3 {
            margin: 10px 0;
            font-size: 18px;
            color: dodgerblue;
        }

        .book-card p {
            margin: 5px;
            text-align: left;
            font-family: arial;
        }
    </style>
</html>
