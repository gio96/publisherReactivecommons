package com.example.demo;

import com.example.demo.broadcast.ManageTasksUseCase;
import com.example.demo.broadcast.TaskToDoRepository;
import org.reactivecommons.api.domain.DomainEventBus;
import org.reactivecommons.async.impl.config.annotations.EnableDomainEventBus;
import org.reactivecommons.async.impl.config.annotations.EnableMessageListeners;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableDomainEventBus
@EnableMessageListeners
public class ReactivecommonsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactivecommonsApplication.class, args);
    }

    @Bean
    public ManageTasksUseCase manageTasksUseCase(TaskToDoRepository tasks, DomainEventBus eventBus) {
        return new ManageTasksUseCase(tasks, eventBus);
    }
}
