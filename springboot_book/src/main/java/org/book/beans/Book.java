package org.book.beans;

public class Book {
    private int bookId;
    private String title;
    private String author;

    public Book() {
    }

    private String subject;
    private Double price;

    public Book(int bookId, String title, String author, String subject, Double price) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.subject = subject;
        this.price = price;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    // toString() method to represent the Book object as a string
    @Override
    public String toString() {
        return "Book{" +
                "id=" + bookId+
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", subject=" + subject +
                ", price='" + price + '\'' +
                '}';
    }
}
