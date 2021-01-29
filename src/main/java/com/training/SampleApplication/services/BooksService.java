package com.training.SampleApplication.services;

import com.training.SampleApplication.model.Author;
import com.training.SampleApplication.model.Book;
import com.training.SampleApplication.repositories.BookRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class BooksService {

    BookRepositoryJpa bookRepository;
    AuthorsService authorsService;

    @Autowired
    public BooksService(BookRepositoryJpa bookRepository, AuthorsService authorsService) {
        this.bookRepository = bookRepository;
        this.authorsService = authorsService;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getAllBooksByTitle(String title) {
        return bookRepository.getAllBooksByTitle(title);
    }

    public Book findBookById(int id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with id: " + id + "not found"));
    }

    public void deleteBookById(int id) {
        bookRepository.deleteById(id);
    }

    public Book assignAuthorToBook(int bookId, int authorId) {
        Book book = findBookById(bookId);
        Author author = authorsService.getAuthorById(authorId);
        book.setAuthor(author);
        return addBook(book);
    }
}
