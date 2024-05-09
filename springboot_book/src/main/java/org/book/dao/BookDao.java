package org.book.dao;

import org.book.beans.Book;
import org.book.beans.BookLite;

import java.util.List;

public interface BookDao {
    List<Book> findAllBooks();
    Book findBookById(int id);
    String addBook(BookLite book);
    int editBook(Book book);
    int deleteBook(int id);
    Book findBookByTitle(String title);
    List<Book> findBookByAuthor(String author);
    List<Book> findBookBySubject(String subject);
}
