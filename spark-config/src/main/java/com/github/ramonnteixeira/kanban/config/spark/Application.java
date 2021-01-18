package com.github.ramonnteixeira.kanban.config.spark;

import com.github.ramonnteixeira.kanban.application.step.list.ListSteps;
import com.github.ramonnteixeira.kanban.application.task.change.UpdateTask;
import com.github.ramonnteixeira.kanban.application.task.change.UpdateTaskDto;
import com.github.ramonnteixeira.kanban.application.task.create.CreateTask;
import com.github.ramonnteixeira.kanban.application.task.create.CreateTaskDto;
import com.github.ramonnteixeira.kanban.application.task.delete.DeleteTask;
import lombok.RequiredArgsConstructor;
import spark.Spark;

@RequiredArgsConstructor
public class Application {

	private final CreateTask createTask;
	private final UpdateTask updateTask;
	private final DeleteTask deleteTask;
	private final ListSteps listSteps;
	private final JsonTransformer jsonTransformer = new JsonTransformer();

	public void start() {
		Spark.port(8080);
		Spark.staticFileLocation("/public");

		Spark.post("/api/tasks", "application/json", (request, response) -> {
			var task = jsonTransformer.parse(request.body(), CreateTaskDto.class);
			createTask.execute(task);
			return "";
		});

		Spark.get("/api/steps", (request, response) -> {
			response.type("application/json");
		    return listSteps.execute();
		}, jsonTransformer);

		Spark.delete("/api/tasks/:task_id", (request, response) -> {
			Long id = Long.valueOf(request.params(":task_id"));
			deleteTask.execute(id);
		    return "";
		});

		Spark.put("/api/tasks/:task_id", (request, response) -> {
			Long id = Long.valueOf(request.params(":task_id"));
			var task = jsonTransformer.parse(request.body(), UpdateTaskDto.class);
			updateTask.execute(id, task);
		    return "";
		});
	}

	public static void main(String[] args) {
		var application = new Application(
			ClassFactory.INSTANCE.buildCreateTask(),
			ClassFactory.INSTANCE.buildUpdateTask(),
			ClassFactory.INSTANCE.buildDeleteTask(),
			ClassFactory.INSTANCE.buildListSteps()
		);
		application.start();
	}

}
