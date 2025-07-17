/*
 * Click nfs://.netbeans.org/templates/Classes/Class.java to edit this template
 */
package dao;

import dto.BookDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lib.DBUtils;

/**
 *
 * @author msi2k
 */
public class BookDao {

    private static final String GET_ALL_BOOKS = "SELECT id, title, published_year, available_copies, author FROM books";

    public List<BookDto> getAllBooks() throws SQLException, ClassNotFoundException {
        List<BookDto> bookList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(GET_ALL_BOOKS);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    int publishedYear = rs.getInt("published_year");
                    int availableCopies = rs.getInt("available_copies");
                    String author = rs.getString("author");
                    bookList.add(new BookDto(id, title, publishedYear, availableCopies, author));
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
        return bookList;
    }
}
