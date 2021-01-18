package com.github.ramonnteixeira.kanban.domain;

public interface TaskRepository {

    void insert(Task task);
    void update(Task task);
    void deleteById(Long id);

    Task findById(Long taskId);
}
