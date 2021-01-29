package com.training.SampleApplication.services;

import com.training.SampleApplication.model.Author;
import com.training.SampleApplication.repositories.AuthorsRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorsService {

    AuthorsRepositoryJpa authorsRepository;

    @Autowired
    public AuthorsService(AuthorsRepositoryJpa authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    public Author addAuthor(Author author) {
        return authorsRepository.save(author);
    }

    public Author getAuthorById(int id) {
        return authorsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author with id: " + id + " not found"));
    }

    public void deleteAuthorById(int id) {
        authorsRepository.deleteById(id);
    }

    public boolean authorExists(int authorId) {
        return authorsRepository.existsById(authorId);
    }


    public List<Author> getAllAuthors() {
        return authorsRepository.findAll();
    }
}
