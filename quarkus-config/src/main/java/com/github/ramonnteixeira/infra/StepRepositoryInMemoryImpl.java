package com.github.ramonnteixeira.infra;

import com.github.ramonnteixeira.kanban.domain.Step;
import com.github.ramonnteixeira.kanban.domain.StepRepository;
import com.github.ramonnteixeira.kanban.domain.Task;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.util.List;
import java.util.stream.Collectors;

@Default
@ApplicationScoped
public class StepRepositoryInMemoryImpl implements StepRepository {

    static final List<Step> steps = List.of(
            new Step(1L, "Todo", false),
            new Step(2L, "Doing", false),
            new Step(3L, "Done", true)
    );

    @Override
    public List<Step> list() {
        return steps
                .stream()
                .map(s -> s.withTasks(listTaskByStep(s.getId())))
                .collect(Collectors.toList());
    }

    private List<Task> listTaskByStep(Long stepId) {
        return TaskRepositoryInMemoryImpl.tasks
                .stream()
                .filter(t -> t.getStepId().equals(stepId))
                .collect(Collectors.toList());
    }
}
