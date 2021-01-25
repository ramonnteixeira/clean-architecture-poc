package com.github.ramonnteixeira.kanban.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Task {

	private Long id;
	private String title;
	private String description;
	private String color;
	private Long stepId;

	public Task(String title, String description, String color, Long stepId) {
		this(null, title, description, color, stepId);
	}

	public void update(Task task) {
		title = task.title;
		description = task.description;
		color = task.color;
		stepId = task.stepId;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
