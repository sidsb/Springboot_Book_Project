package org.book.controller;

import org.book.beans.Book;
import org.book.beans.BookLite;
import org.book.exception.BookException;
import org.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/query")
public class BookController {

    public static Logger logger = Logger.getLogger(BookController.class.getName());

    @Autowired
    private BookService bookService;

    @GetMapping("/findAllBooks")
    public ResponseEntity<String> findAllBooks() {
        logger.info("inside controller findAllBooks method");
        List<Book> books = bookService.findAllBooks();
        return books != null ? new ResponseEntity<>(books.stream().map(Book::toString).collect(Collectors.joining("\n")), HttpStatus.OK)
                : new ResponseEntity<>("No books found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findBookById/{bookId}")
    public ResponseEntity<String> findBookById(@PathVariable("bookId") int bookId) {
        Book book = bookService.findBookById(bookId);
//        return book != null ? new ResponseEntity<>(book.toString(), HttpStatus.OK)
//                : new ResponseEntity<>("No book found with id = " + bookId, HttpStatus.NOT_FOUND);
        if(book == null){
            throw new BookException("No book found with id " + bookId + " found");
        }
        return new ResponseEntity<>(book.toString(), HttpStatus.OK);
    }

    @GetMapping("/findBookByTitle/{title}")
    public ResponseEntity<String> findBookByTitle(@PathVariable("title") String title) {
        Book book = bookService.findBookByTitle(title);
        return book != null ? new ResponseEntity<>(book.toString(), HttpStatus.OK)
                : new ResponseEntity<>("No book found with title = " + title, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findBookByAuthor/{author}")
    public ResponseEntity<String> findBookByAuthor(@PathVariable("author") String author) {
        List<Book> books = bookService.findBookByAuthor(author);
        return books != null ? new ResponseEntity<>(books.stream().map(Book::toString).collect(Collectors.joining("\n")), HttpStatus.OK)
                : new ResponseEntity<>("No books found with author " + author, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findBookBySubject/{subject}")
    public ResponseEntity<String> findBookBySubject(@PathVariable("subject") String subject) {
        List<Book> books = bookService.findBookBySubject(subject);
        return books != null ? new ResponseEntity<>(books.stream().map(Book::toString).collect(Collectors.joining("\n")), HttpStatus.OK)
                : new ResponseEntity<>("No books found with subject " + subject, HttpStatus.NOT_FOUND);    }


    @PostMapping("/addBook")
    public ResponseEntity<String> addBook(@RequestBody BookLite book) {
        String result = bookService.addBook(book);
        if(result.contains("already exists")){
            throw new BookException(result);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/editBook")
    public ResponseEntity<String> editBook(@RequestBody Book book) {
        String result = bookService.updateBook(book);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/deleteBook/{bookId}")
    public ResponseEntity<String> deleteBookById(@PathVariable("bookId") int bookId) {
        return new ResponseEntity<>(bookService.deleteBook(bookId), HttpStatus.OK);
    }
}