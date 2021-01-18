package com.github.ramonnteixeira.kanban.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class Step {

	private final Long id;
	private final String title;
	private final boolean isFinally;
	private List<Task> tasks = new ArrayList<>();

	public Step withTasks(List<Task> tasks) {
		this.tasks = tasks;
		return this;
	}
}
