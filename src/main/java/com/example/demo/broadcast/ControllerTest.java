package com.example.demo.broadcast;

import com.example.demo.broadcast.entities.TaskToDo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ControllerTest {

    private ManageTasksUseCase manageTasksUseCase;

    public ControllerTest(ManageTasksUseCase manageTasksUseCase) {
        this.manageTasksUseCase = manageTasksUseCase;
    }

    @GetMapping
    public Mono<TaskToDo> testEvent(){
        return manageTasksUseCase.createNew("event1","testEvent1");
    }
}
