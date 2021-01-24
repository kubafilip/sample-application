package service;

import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import repository.BookRepositoryJpa;

import java.util.List;

@Service
public class BooksService {

    BookRepositoryJpa bookRepository;

    @Autowired
    public BooksService(BookRepositoryJpa bookRepository) {
        this.bookRepository = bookRepository;
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
        return bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void deleteBookById(int id) {
        bookRepository.deleteById(id);
    }
}
