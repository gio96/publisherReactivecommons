package com.example.demo.broadcast;

import com.example.demo.broadcast.entities.TaskToDo;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskToDoRepository extends ReactiveCrudRepository<TaskToDo, String>, ReactiveQueryByExampleExecutor<TaskToDo> {
}
