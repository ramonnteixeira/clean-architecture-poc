package com.github.ramonnteixeira.kanban.application.step.list;

import com.github.ramonnteixeira.kanban.domain.Step;
import com.github.ramonnteixeira.kanban.domain.Task;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class StepDto {

    private final Long id;
    private final String title;
    private final Boolean isFinally;
    private List<TaskDto> tasks;

    public static StepDto fromModel(Step model) {
        return new StepDto(model.getId(), model.getTitle(), model.isFinally())
                .withTasks(model.getTasks());
    }

    public StepDto withTasks(List<Task> tasks) {
        this.tasks = tasks.stream().map(TaskDto::fromModel).collect(Collectors.toList());
        return this;
    }
}
