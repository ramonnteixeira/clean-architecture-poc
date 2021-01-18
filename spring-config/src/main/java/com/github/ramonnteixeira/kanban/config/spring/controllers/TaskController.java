package com.github.ramonnteixeira.kanban.config.spring.controllers;

import com.github.ramonnteixeira.kanban.application.task.change.UpdateTask;
import com.github.ramonnteixeira.kanban.application.task.change.UpdateTaskDto;
import com.github.ramonnteixeira.kanban.application.task.create.CreateTask;
import com.github.ramonnteixeira.kanban.application.task.create.CreateTaskDto;
import com.github.ramonnteixeira.kanban.application.task.delete.DeleteTask;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/tasks")
public class TaskController {

    private final CreateTask createTask;
    private final UpdateTask updateTask;
    private final DeleteTask deleteTask;

    @PostMapping
    public void create(@RequestBody CreateTaskDto taskDto) {
        createTask.execute(taskDto);
    }

    @PutMapping("/{id}")
    public void updateTask(@PathVariable Long id, @RequestBody UpdateTaskDto updateTaskDto) {
        updateTask.execute(id, updateTaskDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        deleteTask.execute(id);
    }

}
