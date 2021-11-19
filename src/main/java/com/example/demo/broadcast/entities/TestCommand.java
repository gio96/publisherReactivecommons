package com.example.demo.broadcast.entities;

public class TestCommand {
    private String name;
    private String description;

    public TestCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public TestCommand() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UserRegister{" +
                "name='" + name + '\'' +
                ", lastName='" + description + '\'' +
                '}';
    }
}
