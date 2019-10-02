package com.springapi.repo;

import com.springapi.models.Author;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorsRepo extends MongoRepository<Author, String> {
    Optional<Author> findById(String id);
}
