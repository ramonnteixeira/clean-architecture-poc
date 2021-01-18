package com.github.ramonnteixeira.kanban.infra;

import com.github.ramonnteixeira.kanban.domain.Step;
import com.github.ramonnteixeira.kanban.domain.StepRepository;
import com.github.ramonnteixeira.kanban.domain.Task;
import lombok.RequiredArgsConstructor;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class StepRepositoryJdbcImpl implements StepRepository {

    private final Sql2o connection;

    @Override
    public List<Step> list() {
        String sql = "select id, title, isFinally from step";
        String taskSql = "select id, title, description, color, stepId from task where stepId=:id";
        try (Connection con = connection.open()) {
            List<Step> steps = con.createQuery(sql).executeAndFetch(Step.class);

            return steps.stream()
                    .map(step -> step.withTasks(con.createQuery(taskSql).addParameter("id", step.getId()).executeAndFetch(Task.class)))
                    .collect(Collectors.toList());
        }
    }
}
