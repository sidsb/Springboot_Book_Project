package org.book.service;

import org.book.beans.Book;
import org.book.beans.BookLite;

import java.util.List;

public interface BookService {
    String addBook(BookLite book);
    Book findBookById(int id);
    List<Book> findAllBooks();
    String updateBook(Book book);
    String deleteBook(int id);
    Book findBookByTitle(String title);
    List<Book> findBookByAuthor(String author);
    List<Book> findBookBySubject(String subject);
}
