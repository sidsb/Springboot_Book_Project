package org.book.dao;

import org.book.beans.Book;
import org.book.beans.BookLite;
import org.book.exception.BookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao{
    @Autowired
    JdbcTemplate template;

    @Override
    public List<Book> findAllBooks() {
        String qry = "select * from book";
        List<Book> bookList = template.query(qry, new BeanPropertyRowMapper<>(Book.class));
        return bookList;
    }

    @Override
    public Book findBookById(int id) {
        String qry = "select * from book where bookId=?";
        List<Book> books = template.query(qry, new BeanPropertyRowMapper<Book>(Book.class), id);
        if(books.isEmpty())
            throw new BookException("No books found with id = " + id );
        return books.get(0);
    }

    @Override
    public String addBook(BookLite book) {
        String add = "insert into book (title, author, subject, price) values(?,?,?,?)";
        Object args[] = {book.getTitle(),book.getAuthor(),book.getSubject(),book.getPrice()};
        try {
            template.update(add, args);
        } catch (DataAccessException e) {
            return null;
        }
        return "Book Inserted Successfully";
    }

    @Override
    public int editBook(Book book) {
            String update = "update book set title=?,author=?,subject=?,price=? where bookId=?";
            Object args[] = {book.getTitle(), book.getAuthor(), book.getSubject(), book.getPrice(), book.getBookId()};
            return template.update(update, args);
    }

    @Override
    public int deleteBook(int id) {
        String delqry = "delete from book where bookId=?";
        int rows = template.update(delqry, id);
        return rows;
    }

    @Override
    public Book findBookByTitle(String title) {
        String qry = "select * from book where title=?";
        List<Book> books = template.query(qry, new BeanPropertyRowMapper<Book>(Book.class), title);
        if(books.isEmpty())
            throw new BookException("No books found with title " + title);
        return books.get(0);
    }

    @Override
    public List<Book> findBookByAuthor(String author) {
        String qry = "select * from book where author=?";
        List<Book> books = template.query(qry, new BeanPropertyRowMapper<Book>(Book.class), author);
        if(books.isEmpty())
            throw new BookException("No books found");
        return books;
    }

    @Override
    public List<Book> findBookBySubject(String subject) {
        String qry = "select * from book where subject=?";
        List<Book> books = template.query(qry, new BeanPropertyRowMapper<Book>(Book.class), subject);
        if(books.isEmpty())
            throw new BookException("No books found with subject " + subject);
        return books;
    }

}
