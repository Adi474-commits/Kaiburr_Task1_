package com.kaiburr.task1.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "tasks")
@Data
public class Task {
    @Id
    private String id;

    @NotEmpty(message = "Task name cannot be empty.")
    @Indexed(unique = true)
    private String name;
    
    @NotEmpty(message = "Owner cannot be empty.")
    private String owner;
    
    @NotEmpty(message = "Command cannot be empty.")
    private String command;

    private List<TaskExecution> taskExecutions = new ArrayList<>();
}