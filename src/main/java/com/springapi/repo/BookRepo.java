package com.springapi.repo;

import com.springapi.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepo extends MongoRepository<Book, String> {
    Book findByIsbn(String bookisbn);
}
