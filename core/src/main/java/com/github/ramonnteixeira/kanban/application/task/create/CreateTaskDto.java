package com.github.ramonnteixeira.kanban.application.task.create;

import com.github.ramonnteixeira.kanban.domain.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskDto {

	private String title;
	private String description;
	private String color;
	private Long stepId;

	public Task toModel() {
		return new Task(title, description, color, stepId);
	}
}
