package com.training.SampleApplication.controllers;

import com.training.SampleApplication.model.Author;
import com.training.SampleApplication.services.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorsController {

    private AuthorsService authorsService;

    @Autowired
    public AuthorsController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @GetMapping("/authors")
    List<Author> getAllAuthors() {
        return authorsService.getAllAuthors();
    }

    @GetMapping("/authors/{id}")
    Author getAuthorById(@PathVariable int id) {
        return authorsService.getAuthorById(id);
    }

    @PostMapping("/authors")
    ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorsService.addAuthor(author));
    }

    @DeleteMapping("/authors/{id}")
    void deleteAuthorById(@PathVariable int id) {
        authorsService.deleteAuthorById(id);
    }
}
