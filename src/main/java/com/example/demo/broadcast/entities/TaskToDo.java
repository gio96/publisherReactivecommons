package com.example.demo.broadcast.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TaskToDo")
@Builder(toBuilder = true)
public class TaskToDo {

    @Id
    private String id;
    private String name;
    private String description;

    public TaskToDo(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public TaskToDo() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
