package com.example.demo.broadcast.entities;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TaskToDo")
@Builder(toBuilder = true)
public class TestEvent {

    @Id
    private String id;
    private String name;
    private String description;

    public TestEvent(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public TestEvent() {
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
