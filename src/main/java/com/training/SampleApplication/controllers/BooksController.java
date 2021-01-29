package com.training.SampleApplication.controllers;

import com.training.SampleApplication.model.Book;
import com.training.SampleApplication.services.AuthorsService;
import com.training.SampleApplication.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class BooksController {
    BooksService booksService;
    AuthorsService authorsService;

    @Autowired
    public BooksController(BooksService booksService, AuthorsService authorsService) {
        this.booksService = booksService;
        this.authorsService = authorsService;
    }

    @GetMapping("/books")
    List<Book> getAllBooks(@RequestParam(value = "byTitle", required = false) String title) {
        if (title != null) {
            return booksService.getAllBooksByTitle(title);
        }
        return booksService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    Book getBookById(@PathVariable int id) {
        return booksService.findBookById(id);
    }

    @PostMapping("/books")
    ResponseEntity<Book> addBook(@RequestBody Book book) {
        if (book.getAuthor() != null && !authorsService.authorExists(book.getAuthor().getId())) {//tutaj wywali w ktoryms momencie
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(booksService.addBook(book));
    }

    @PutMapping("/books/{bookId}")
    Book assignAuthorToBook(@PathVariable int bookId ,@RequestParam(value = "author_id") int authorId) {
        return booksService.assignAuthorToBook(bookId,authorId);
    }

    @DeleteMapping("/books/{id}")
    void deleteBookById(@PathVariable int id) {
        booksService.deleteBookById(id);
    }

    @GetMapping("/hello")
    String printHello() {
        return "Hello";
    }
}
