package com.github.ramonnteixeira.kanban.application.task.create;

import com.github.ramonnteixeira.kanban.domain.TaskRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateTask {

    private final TaskRepository taskRepository;

    public void execute(CreateTaskDto taskDto) {
        var task = taskDto.toModel();
        taskRepository.insert(task);
    }
}
