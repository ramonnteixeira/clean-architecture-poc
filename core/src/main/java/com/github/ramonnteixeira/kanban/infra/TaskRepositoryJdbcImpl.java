package com.github.ramonnteixeira.kanban.infra;

import com.github.ramonnteixeira.kanban.domain.Task;
import com.github.ramonnteixeira.kanban.domain.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@RequiredArgsConstructor
public class TaskRepositoryJdbcImpl implements TaskRepository {

	private final Sql2o connection;
	private final String findByIdSql = "select id, title, description, color, stepId from task where id = :id";
	private final String insertSql = "insert into task(title, description, color, stepId) values (:title, :description, :color, :stepId)";
	private final String updateSql = "update task set title = :title, description = :description, color = :color, stepId = :stepId where id = :id";
	private final String deleteSql = "delete from task where id = :id";

	@Override
	public void update(Task task) {
		try (Connection con = connection.open()) {
		    con.createQuery(updateSql).bind(task).executeUpdate();
		}		
	}

	@Override
	public void insert(Task task) {
		try (Connection con = connection.open()) {
		    con.createQuery(insertSql).bind(task).executeUpdate();
		}				
	}

	@Override
	public void deleteById(Long id) {
		try (Connection con = connection.open()) {
		    con.createQuery(deleteSql).addParameter("id", id).executeUpdate();
		}		
	}

	@Override
	public Task findById(Long id) {
		try (Connection con = connection.open()) {
			return con.createQuery(findByIdSql)
				.addParameter("id", id)
				.executeAndFetch(Task.class)
				.stream()
				.findFirst()
				.orElseThrow(NotFoundException::new);
		}
	}

}
