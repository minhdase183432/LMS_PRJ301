<%-- 
    Document   : newjsp
    Created on : Jun 1, 2025, 8:37:20 AM
    Author     : msi2k
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dto.UserDto"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        
        <% 
                UserDto loginUser = (UserDto) session.getAttribute("LOGIN_USER");
                if (loginUser != null) {
            %>
            <h1>Hello <%=loginUser.getName()%> </h1>

            <%
                
                }
            %>
    </body>
</html>
