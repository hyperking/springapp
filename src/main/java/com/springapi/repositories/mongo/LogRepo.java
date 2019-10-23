package com.springapi.repositories.mongo;
import com.springapi.models.Log;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepo extends MongoRepository<Log, String> {
    Optional<Log> findById(String id);
}
