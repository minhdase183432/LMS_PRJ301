/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dao.UserDao;
import dao.UserError;
import dto.UserDto;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author msi2k
 */
public class RegisterController extends HttpServlet {

    private static final String ERROR = "register.jsp";
    private static final String SUCCESS = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError userError = new UserError();
        UserDao dao = new UserDao();
        boolean check = true;

        try {
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String password = request.getParameter("pass");
            String confirmpass = request.getParameter("confpass");

            // Kiểm tra hợp lệ
            if (name == null || name.trim().isEmpty()) {
                userError.setNameError("Tên không được để trống!");
                check = false;
            }
            if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                userError.setEmailError("Email không hợp lệ!");
                check = false;
            }
            if (password == null || password.length() < 6) {
                userError.setPasswordError("Mật khẩu phải có ít nhất 6 ký tự!");
                check = false;
            }
            if (!password.equals(confirmpass)) {
                userError.setConfirmPassError("Hai mật khẩu không giống nhau!");
                check = false;
            }

            // Kiểm tra email trùng lặp
            if (check) {
                boolean checkDuplicate = dao.checkDuplicate(email);
                if (checkDuplicate) {
                    userError.setEmailError("Email đã tồn tại!");
                    check = false;
                }
            }

            // Thêm người dùng nếu không có lỗi
            if (check) {
                UserDto user = new UserDto(0, name, email, password, "user", "active");
                boolean checkInsert = dao.insert(user);
                if (checkInsert) {
                    url = SUCCESS;
                } else {
                    userError.setError("Lỗi khi thêm người dùng vào cơ sở dữ liệu!");
                    request.setAttribute("USER_ERROR", userError);
                }
            }

            // Đặt lỗi vào request nếu có
            if (!check || url.equals(ERROR)) {
                request.setAttribute("USER_ERROR", userError);
            }

        } catch (Exception e) {
            log("Error at RegisterController: " + e.toString());
            userError.setError("Lỗi hệ thống: " + e.getMessage());
            request.setAttribute("USER_ERROR", userError);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
