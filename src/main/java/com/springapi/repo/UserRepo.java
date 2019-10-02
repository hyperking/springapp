package com.springapi.repo;

import com.springapi.models.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
    Optional<User> findById(String id);
    User findByEmail(String email);
}
