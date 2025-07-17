/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dao.UserDao;
import dto.UserDto;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author msi2k
 */
public class UpdateProfileController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDto loginUser = (UserDto) session.getAttribute("LOGIN_USER");
        if (loginUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy thông tin từ form
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // Kiểm tra định dạng email
            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                session.setAttribute("ERROR_MESSAGE", "Email không hợp lệ!");
                response.sendRedirect("user.jsp");
                return;
            }

            // Kiểm tra email mới có trùng với người dùng khác không (trừ chính mình)
            UserDao userDAO = new UserDao();
            if (!loginUser.getEmail().equals(email) && userDAO.checkDuplicate(email)) {
                session.setAttribute("ERROR_MESSAGE", "Email đã được sử dụng bởi người dùng khác!");
                response.sendRedirect("user.jsp");
                return;
            }

            // Cập nhật thông tin người dùng
            UserDto updatedUser = new UserDto(0, name, email, password, name, email);
            updatedUser.setName(name);
            updatedUser.setEmail(email);
            updatedUser.setPassword(password);
            updatedUser.setRole(loginUser.getRole()); // Giữ nguyên role
            updatedUser.setStatus(loginUser.getStatus()); // Giữ nguyên status

            boolean success = userDAO.updateUser(loginUser.getEmail(), updatedUser);
            if (success) {
                // Cập nhật session với thông tin mới
                updatedUser.setId(loginUser.getId()); // Giữ nguyên ID
                session.setAttribute("LOGIN_USER", updatedUser);
                response.sendRedirect("user.jsp");
            } else {
                session.setAttribute("ERROR_MESSAGE", "Cập nhật thông tin thất bại!");
                response.sendRedirect("user.jsp");
            }
        } catch (Exception e) {
            session.setAttribute("ERROR_MESSAGE", "Lỗi hệ thống: " + e.getMessage());
            response.sendRedirect("user.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
