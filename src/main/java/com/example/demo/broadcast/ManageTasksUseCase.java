package com.example.demo.broadcast;

import com.example.demo.broadcast.entities.TaskToDo;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.api.domain.DomainEvent;
import org.reactivecommons.api.domain.DomainEventBus;
import reactor.core.publisher.Mono;

import java.util.UUID;


public class ManageTasksUseCase {

    private TaskToDoRepository tasks;
    private DomainEventBus eventBus;

    public ManageTasksUseCase(TaskToDoRepository tasks, DomainEventBus eventBus) {
        this.tasks = tasks;
        this.eventBus = eventBus;
    }

    public Mono<TaskToDo> createNew(String name, String description) {
        return uuid()
                //.flatMap(id -> TaskToDoFactory.createTask(id, name, description))
                .flatMap(id -> taskToDoFactory(id, name, description))
                .flatMap(tasks::save)
                .flatMap(task -> emitCreatedEvent(task).thenReturn(task));
    }

    private Mono<Void> emitCreatedEvent(TaskToDo task) {
        return Mono.from(eventBus.emit(new DomainEvent<>("task.created", task.getId(), task)));
    }

    private Mono<String> uuid() {
        return Mono.just(UUID.randomUUID().toString());
    }

    private Mono<TaskToDo> taskToDoFactory(String id, String name, String description) {
        return Mono.just(new TaskToDo(id,name,description));
    }

    //public <T> Mono<Void> functionReturningMonoVoid(DomainEvent<T> event) {
    public Mono<Void> functionReturningMonoVoid(DomainEvent<TaskToDo> event) {
       return Mono.just(event)
               .log()
               .doOnNext(algo -> System.out.println((TaskToDo)algo.getData()))
               .then();
    }
}
