package com.github.ramonnteixeira.kanban.config.spring;

import com.github.ramonnteixeira.kanban.application.step.list.ListSteps;
import com.github.ramonnteixeira.kanban.application.task.change.UpdateTask;
import com.github.ramonnteixeira.kanban.application.task.create.CreateTask;
import com.github.ramonnteixeira.kanban.application.task.delete.DeleteTask;
import com.github.ramonnteixeira.kanban.domain.StepRepository;
import com.github.ramonnteixeira.kanban.domain.TaskRepository;
import com.github.ramonnteixeira.kanban.infra.JdbcUtil;
import com.github.ramonnteixeira.kanban.infra.StepRepositoryJdbcImpl;
import com.github.ramonnteixeira.kanban.infra.TaskRepositoryJdbcImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class ClassFactory {

    @Bean
    public CreateTask createTask() {
        return new CreateTask(taskRepository());
    }

    @Bean
    public ListSteps listSteps() {
        return new ListSteps(stepRepository());
    }

    @Bean
    public UpdateTask updateTask() {
        return new UpdateTask(taskRepository());
    }

    @Bean
    public DeleteTask deleteTask() {
        return new DeleteTask(taskRepository());
    }

    @Bean
    public TaskRepository taskRepository() {
        return new TaskRepositoryJdbcImpl(JdbcUtil.INSTANCE.getConnection());
    }

    @Bean
    public StepRepository stepRepository() {
        return new StepRepositoryJdbcImpl(JdbcUtil.INSTANCE.getConnection());
    }
}
