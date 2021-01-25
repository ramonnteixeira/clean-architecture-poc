package com.github.ramonnteixeira;

import com.github.ramonnteixeira.kanban.application.step.list.ListSteps;
import com.github.ramonnteixeira.kanban.application.task.change.UpdateTask;
import com.github.ramonnteixeira.kanban.application.task.create.CreateTask;
import com.github.ramonnteixeira.kanban.application.task.delete.DeleteTask;
import com.github.ramonnteixeira.kanban.domain.StepRepository;
import com.github.ramonnteixeira.kanban.domain.TaskRepository;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
public class ClassFactory {

    @Produces
    public CreateTask createTask(TaskRepository taskRepository) {
        return new CreateTask(taskRepository);
    }

    @Produces
    public ListSteps listSteps(StepRepository stepRepository) {
        return new ListSteps(stepRepository);
    }

    @Produces
    public UpdateTask updateTask(TaskRepository taskRepository) {
        return new UpdateTask(taskRepository);
    }

    @Produces
    public DeleteTask deleteTask(TaskRepository taskRepository) {
        return new DeleteTask(taskRepository);
    }

}
