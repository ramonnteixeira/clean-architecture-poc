package com.github.ramonnteixeira.infra;

import com.github.ramonnteixeira.kanban.domain.Task;
import com.github.ramonnteixeira.kanban.domain.TaskRepository;
import com.github.ramonnteixeira.kanban.infra.NotFoundException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Default
@ApplicationScoped
public class TaskRepositoryInMemoryImpl implements TaskRepository {

	static List<Task> tasks = new ArrayList<>();

	@Override
	public void update(Task task) {
		deleteById(task.getId());
		insert(task);
	}

	@Override
	public void insert(final Task task) {
		if (task.getId() == null) {
			task.setId(tasks.stream().map(Task::getId).max(Long::compareTo).orElse(0L) + 1);
		}
		tasks.add(task);
	}

	@Override
	public void deleteById(Long id) {
		tasks = tasks.stream()
				.filter(t -> !t.getId().equals(id))
				.collect(Collectors.toList());
	}

	@Override
	public Task findById(Long id) {
		return tasks.stream()
				.filter(t -> t.getId().equals(id))
				.findFirst()
				.orElseThrow(NotFoundException::new);
	}

}
