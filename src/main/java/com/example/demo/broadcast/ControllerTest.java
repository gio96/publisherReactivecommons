package com.example.demo.broadcast;

import com.example.demo.broadcast.entities.TestEvent;
import com.example.demo.broadcast.entities.TestCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ControllerTest {

    private ManageTasksUseCase manageTasksUseCase;

    public ControllerTest(ManageTasksUseCase manageTasksUseCase) {
        this.manageTasksUseCase = manageTasksUseCase;
    }

    @GetMapping
    public Mono<TestEvent> testEvent(){
        return manageTasksUseCase.createNew("event1","testEvent1");
    }

    @PostMapping
    public Mono<Void> testCommand(){
        return manageTasksUseCase.emitCommandEvent(new TestCommand("pepito","perez"));
    }

    @PutMapping
    public Mono<String> testQuery(){
        return manageTasksUseCase.findSomethingQuery("testQuery");
    }
}
