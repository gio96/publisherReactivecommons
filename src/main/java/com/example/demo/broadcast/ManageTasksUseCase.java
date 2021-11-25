package com.example.demo.broadcast;

import com.example.demo.broadcast.entities.TestEvent;
import com.example.demo.broadcast.entities.TestCommand;
import org.reactivecommons.api.domain.Command;
import org.reactivecommons.api.domain.DomainEvent;
import org.reactivecommons.api.domain.DomainEventBus;
import org.reactivecommons.async.api.AsyncQuery;
import org.reactivecommons.async.api.DirectAsyncGateway;
import reactor.core.publisher.Mono;

import java.util.UUID;


public class ManageTasksUseCase {

    private TaskToDoRepository tasks;
    private DomainEventBus eventBus;
    private DirectAsyncGateway gateway;

    public ManageTasksUseCase(TaskToDoRepository tasks, DomainEventBus eventBus, DirectAsyncGateway gateway) {
        this.tasks = tasks;
        this.eventBus = eventBus;
        this.gateway = gateway;
    }

    public Mono<TestEvent> createNew(String name, String description) {
        return uuid()
                .flatMap(id -> taskToDoFactory(id, name, description))
                .flatMap(tasks::save)
                .flatMap(task -> emitCreatedEvent(task).thenReturn(task));
    }


    public Mono<Void> emitCommandEvent(TestCommand testCommand) {
        return gateway.sendCommand(new Command<>("test.command", UUID.randomUUID().toString(), testCommand), "reactivecommonssubscriber");
    }

    public Mono<String> findSomethingQuery(String query) {
        return gateway.requestReply(new AsyncQuery<>("test.query", "prueba"),
                        "reactivecommonssubscriber", String.class)
                .doOnNext(prueba -> System.out.println(prueba))
                .doOnNext(prueba -> System.out.println("Soy el query" + query));
    }


    private Mono<Void> emitCreatedEvent(TestEvent task) {
        return Mono.from(eventBus.emit(new DomainEvent<>("test.event", task.getId(), task)));
    }

    private Mono<String> uuid() {
        return Mono.just(UUID.randomUUID().toString());
    }

    private Mono<TestEvent> taskToDoFactory(String id, String name, String description) {
        return Mono.just(new TestEvent(id, name, description));
    }

}
