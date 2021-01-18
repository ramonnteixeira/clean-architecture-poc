package com.github.ramonnteixeira.kanban.application.task.delete;

import com.github.ramonnteixeira.kanban.domain.TaskRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteTask {

    private final TaskRepository taskRepository;

    public void execute(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
