package com.springapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springapi.models.Author;
import com.springapi.repo.AuthorsRepo;

/**
 * AuthorService
 */
@Service
public class AuthorService {

    @Autowired
    AuthorsRepo authorsDB;

    public List<Author> getDBauthors() {
        return authorsDB.findAll();
    }

    public Optional<Author> getAuthor(String id) {
        return authorsDB.findById(id);
    }
}