package com.github.ramonnteixeira;

import com.github.ramonnteixeira.kanban.application.task.change.UpdateTask;
import com.github.ramonnteixeira.kanban.application.task.change.UpdateTaskDto;
import com.github.ramonnteixeira.kanban.application.task.create.CreateTask;
import com.github.ramonnteixeira.kanban.application.task.create.CreateTaskDto;
import com.github.ramonnteixeira.kanban.application.task.delete.DeleteTask;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.*;

@Path("/tasks")
@RequiredArgsConstructor
public class TaskResource {

    private final CreateTask createTask;
    private final UpdateTask updateTask;
    private final DeleteTask deleteTask;

    @POST
    public void create(CreateTaskDto createTaskDto) {
        createTask.execute(createTaskDto);
    }

    @PUT
    @Path("{id}")
    public void update(@PathParam("id") Long taskId, UpdateTaskDto updateTaskDto) {
        updateTask.execute(taskId, updateTaskDto);
    }

    @DELETE
    @Path("{id}")
    public void update(@PathParam("id") Long taskId) {
        deleteTask.execute(taskId);
    }

}