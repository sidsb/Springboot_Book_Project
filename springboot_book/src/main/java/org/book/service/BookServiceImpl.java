package org.book.service;

import org.book.beans.Book;
import org.book.beans.BookLite;
import org.book.dao.BookDao;
import org.book.exception.BookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
public class BookServiceImpl implements BookService {
    @Autowired
    BookDao bookDao;
    List<Book> books = new ArrayList<>();

    public BookServiceImpl() {
        books.add(new Book(101,  "Harry Potter and the Chamber of Secrets", "JK Rowling", "Fantasy", 900.00));
        books.add(new Book(102,  "Harry Potter and the Prisoner of Azkaban", "JK Rowling", "Fantasy", 1200.50));
        books.add(new Book(103,  "JAVA EE", "Donald Smith", "Technology", 1599.99));
        books.add(new Book(104,  "Theory of Everything", "Stephen Hawking", "Science", 700.00));
        books.add(new Book(105,  "Oath of the Vayuputras", "Amish", "Mythology", 899.99));
        books.add(new Book(106,  "Go down with truth", "Vawn King", "Reality", 900.00));
        books.add(new Book(107,  "Mathematics Class 10", "RD Sharma", "Education", 1200.00));

    }

    @Override
    public String addBook(BookLite book) {
//        Optional<Book> foundBook = books.stream().filter(book1 -> book1.getBookId() == book.getBookId()).findFirst();
//
//        String res = "Book with id " + book.getBookId() +" already exists";
//        if (!foundBook.isPresent()) {
//            books.add(book);
//            res = "Book added successfully";
//        }
//        return res;
        String res = bookDao.addBook(book);
        if(res == null){
            throw new BookException("Book not added");
        }
        return res;

    }

    @Override
    public Book findBookById(int id) {
//        return books.stream().filter(book -> book.getBookId() == id).findFirst().orElse(null);
        Book book = bookDao.findBookById(id);
        if(book == null) {
            throw  new BookException("Book with id = " + id + " not found");
        }
        return book;
    }


    @Override
    public List<Book> findAllBooks() {
//        return books.isEmpty() ? null : books;
        List<Book> res = bookDao.findAllBooks();
        if(res.isEmpty()){
            throw  new BookException("No Books Found");
        }
        return res;
    }

    @Override
    public String updateBook(Book newBook) {
//        Optional<Book> foundBook = books.stream().filter(book -> book.getBookId() == newBook.getBookId()).findFirst();
//
//        String res = "Book with id " + newBook.getBookId() +" not found";
//        if (foundBook.isPresent()) {
//            int index = books.indexOf(foundBook.get());
//            books.set(index, newBook);
//            res = "Book with id " + newBook.getBookId() + " updated successfully";
//        }
//        return res;

        //will throw exception if book not found
        findBookById(newBook.getBookId());
        int rows =  bookDao.editBook(newBook);
        if(rows == 0){
            return "Book not updated";
        } else {
            return "Book updated";
        }
    }

    @Override
    public String deleteBook(int id) {
//        Optional<Book> foundBook = books.stream().filter(book -> book.getBookId() == id).findFirst();
//        String res = "Book with id " + id + " not found";
//        if (foundBook.isPresent()) {
//            books.remove(foundBook.get());
//            res = "Book with id " + id + " deleted successfully";
//        }
//
//        return res;

        //will throw exception if book not found
        findBookById(id);
        int rows =  bookDao.deleteBook(id);
        if(rows == 0){
            return "Book not deleted";
        } else {
            return "Book deleted";
        }
    }

    @Override
    public Book findBookByTitle(String title) {
//        return books.stream().filter(book -> Objects.equals(book.getTitle(), title)).findFirst().orElse(null);
        return bookDao.findBookByTitle(title);
    }

    @Override
    public List<Book> findBookByAuthor(String author) {
//        List<Book> bookList =  books.stream().filter(book -> Objects.equals(book.getAuthor(), author)).collect(Collectors.toList());
//        return !bookList.isEmpty() ? bookList : null;
        return bookDao.findBookByAuthor(author);
    }

    @Override
    public List<Book> findBookBySubject(String subject) {
//        List<Book> bookList =  books.stream().filter(book -> Objects.equals(book.getSubject(), subject)).collect(Collectors.toList());
//        return !bookList.isEmpty() ? bookList : null;
        return bookDao.findBookBySubject(subject);
    }
}
