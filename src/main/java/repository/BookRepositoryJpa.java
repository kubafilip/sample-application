package repository;

import model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepositoryJpa extends JpaRepository<Book, Integer> {
    List<Book> getAllBooksByTitle(String title);
}
