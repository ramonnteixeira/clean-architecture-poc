package com.github.ramonnteixeira.kanban.application.task.change;

import com.github.ramonnteixeira.kanban.domain.Task;
import com.github.ramonnteixeira.kanban.domain.TaskRepository;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class UpdateTask {

    private final TaskRepository taskRepository;

    public void execute(Long taskId, UpdateTaskDto dto) {
        if (!Objects.equals(taskId, dto.getId())) {
            throw new IllegalArgumentException("Invalid task ID!");
        }
        Task task = taskRepository.findById(taskId);
        task.update(dto.toModel());
        taskRepository.update(task);
    }
}
