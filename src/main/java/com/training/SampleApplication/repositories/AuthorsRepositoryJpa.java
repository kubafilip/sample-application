package com.training.SampleApplication.repositories;

import com.training.SampleApplication.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorsRepositoryJpa extends JpaRepository<Author, Integer> {
}
