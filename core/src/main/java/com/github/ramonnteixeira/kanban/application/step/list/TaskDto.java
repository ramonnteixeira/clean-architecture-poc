package com.github.ramonnteixeira.kanban.application.step.list;

import com.github.ramonnteixeira.kanban.domain.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private Long id;
    private String title;
    private String description;
    private String color;
    private Long stepId;

    public static TaskDto fromModel(Task task) {
        return new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColor(), task.getStepId());
    }
}

