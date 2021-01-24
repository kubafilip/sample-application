package controller;

import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import service.AuthorsService;
import service.BooksService;

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
        if (book.getAuthorId() != 0 && !authorsService.authorExists(book.getAuthorId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(booksService.addBook(book));
    }

    @DeleteMapping("/books/{id}")
    void deleteBookById(@PathVariable int id) {
        booksService.deleteBookById(id);
    }
}
