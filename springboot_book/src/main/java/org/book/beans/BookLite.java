package org.book.beans;

public class BookLite {
    private String title;
    private String author;

    public BookLite(String title, String author, String subject, Double price) {
        this.title = title;
        this.author = author;
        this.subject = subject;
        this.price = price;
    }

    public BookLite() {
    }

    private String subject;
    private Double price;

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
}
