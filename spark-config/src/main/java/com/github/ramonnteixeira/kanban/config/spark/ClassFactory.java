package com.github.ramonnteixeira.kanban.config.spark;

import com.github.ramonnteixeira.kanban.application.step.list.ListSteps;
import com.github.ramonnteixeira.kanban.application.task.change.UpdateTask;
import com.github.ramonnteixeira.kanban.application.task.create.CreateTask;
import com.github.ramonnteixeira.kanban.application.task.delete.DeleteTask;
import com.github.ramonnteixeira.kanban.domain.StepRepository;
import com.github.ramonnteixeira.kanban.domain.TaskRepository;
import com.github.ramonnteixeira.kanban.infra.JdbcUtil;
import com.github.ramonnteixeira.kanban.infra.StepRepositoryJdbcImpl;
import com.github.ramonnteixeira.kanban.infra.TaskRepositoryJdbcImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClassFactory {

    public static final ClassFactory INSTANCE = new ClassFactory();

    private CreateTask createTask;
    private UpdateTask updateTask;
    private DeleteTask deleteTask;
    private ListSteps listSteps;
    private TaskRepository taskRepository;
    private StepRepository stepRepository;

    public CreateTask buildCreateTask() {
        createTask = Optional.ofNullable(createTask)
                .orElse(new CreateTask(buildTaskRepository()));
        return createTask;
    }

    public UpdateTask buildUpdateTask() {
        updateTask = Optional.ofNullable(updateTask)
                .orElse(new UpdateTask(buildTaskRepository()));
        return updateTask;
    }

    public DeleteTask buildDeleteTask() {
        deleteTask = Optional.ofNullable(deleteTask)
                .orElse(new DeleteTask(buildTaskRepository()));
        return deleteTask;
    }

    public ListSteps buildListSteps() {
        listSteps = Optional.ofNullable(listSteps)
                .orElse(new ListSteps(buildStepRepository()));
        return listSteps;
    }

    public TaskRepository buildTaskRepository() {
        taskRepository = Optional.ofNullable(taskRepository)
                .orElse(new TaskRepositoryJdbcImpl(JdbcUtil.INSTANCE.getConnection()));
        return taskRepository;
    }

    public StepRepository buildStepRepository() {
        stepRepository = Optional.ofNullable(stepRepository)
                .orElse(new StepRepositoryJdbcImpl(JdbcUtil.INSTANCE.getConnection()));
        return stepRepository;
    }

}
