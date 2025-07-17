/*
 * Click nfs://.netbeans.org/templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author msi2k
 */
public class BookDto {

    private int id;
    private String title;   // Tên sách
    private int publishedYear; // Năm xuất bản
    private int availableCopies;  // Số lượng còn lại
    private String author; // Tên tác giả

    public BookDto() {
    }

    public BookDto(int id, String title, int publishedYear, int availableCopies, String author) {
        this.id = id;
        this.title = title;
        this.publishedYear = publishedYear;
        this.availableCopies = availableCopies;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
