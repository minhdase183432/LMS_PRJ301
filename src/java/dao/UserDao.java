/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.UserDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lib.DBUtils;

/**
 *
 * @author msi2k
 */
public class UserDao {

    private static final String LOGIN = "select id, name, role, status from dbo.users where email=? and password =?";
    private static final String ADD = "INSERT INTO dbo.users (name, email, password, role, status) VALUES (?, ?, ?, ?, ?)";
    private static final String CHECK_DUPLICATE = "select name from dbo.users where email=  ?";
    private static final String UPDATE_USER = "UPDATE dbo.users SET name = ?, email = ?, password = ?, role = ?, status = ? WHERE id = ?";

    public UserDto checkLogin(String email, String password) throws SQLException, ClassNotFoundException {
        UserDto user = null;
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(LOGIN);
                ptm.setString(1, email);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String role = rs.getString("role");
                    String status = rs.getString("status");
                    if ("active".equalsIgnoreCase(status)) {
                        return new UserDto(id, name, email, "***", role, status);
                    } else {
                        throw new SQLException("Your account is not active. Please contact the administrator.");
                    }
                } else {
                    throw new SQLException("Email or password is incorrect.");
                }
            }
        } catch (SQLException e) {
            throw e; // Ném lại ngoại lệ để LoginController xử lý
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return user;
    }

    public boolean checkDuplicate(String email) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, email);
                rs = ptm.executeQuery();
                return rs.next();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean insert(UserDto user) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ptm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(ADD);
                ptm.setString(1, user.getName());
                ptm.setString(2, user.getEmail());
                ptm.setString(3, user.getPassword());
                ptm.setString(4, user.getRole());
                ptm.setString(5, user.getStatus());
                return ptm.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateUser(String oldEmail, UserDto user) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ptm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                // Lấy ID của người dùng dựa trên email cũ
                int userId = getUserIdByEmail(oldEmail);
                if (userId == -1) {
                    return false; // Không tìm thấy người dùng
                }
                ptm = con.prepareStatement(UPDATE_USER);
                ptm.setString(1, user.getName());
                ptm.setString(2, user.getEmail());
                ptm.setString(3, user.getPassword());
                ptm.setString(4, user.getRole());
                ptm.setString(5, user.getStatus());
                ptm.setInt(6, userId);
                int rowsAffected = ptm.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    private int getUserIdByEmail(String email) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT id FROM dbo.users WHERE email = ?";
                ptm = con.prepareStatement(sql);
                ptm.setString(1, email);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return -1; // Trả về -1 nếu không tìm thấy
    }
}
