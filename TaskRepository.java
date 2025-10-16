package com.kaiburr.task1.repository;

import com.kaiburr.task1.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends MongoRepository<Task, String> {
    
    List<Task> findByNameContainingIgnoreCase(String name);
    
    Optional<Task> findByName(String name);
}