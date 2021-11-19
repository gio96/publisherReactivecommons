package com.example.demo.broadcast;

import com.example.demo.broadcast.entities.TaskToDo;
import org.reactivecommons.api.domain.DomainEvent;
import org.reactivecommons.async.api.HandlerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SomeConfigurationClass {

    @Autowired
    private ManageTasksUseCase someBusinessDependency;

    @Bean
    public HandlerRegistry notificationEvents() {
        return HandlerRegistry.register()
                .listenEvent("task.created", event -> someBusinessDependency.functionReturningMonoVoid(event),
                        TaskToDo.class);
    }
}
