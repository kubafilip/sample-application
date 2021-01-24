package repository;

import model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorsRepositoryJpa extends JpaRepository<Author, Integer> {
}
