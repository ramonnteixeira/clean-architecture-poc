package com.github.ramonnteixeira.kanban.application.task.change;

import com.github.ramonnteixeira.kanban.domain.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskDto {
    private Long id;
    private String title;
    private String description;
    private String color;
    private Long stepId;

    public Task toModel() {
        return new Task(id, title, description, color, stepId);
    }
}
