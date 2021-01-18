package com.github.ramonnteixeira.kanban.application.step.list;

import com.github.ramonnteixeira.kanban.domain.StepRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ListSteps {

    private final StepRepository stepRepository;

    public List<StepDto> execute() {
        return stepRepository.list()
                .stream()
                .map(StepDto::fromModel)
                .collect(Collectors.toList());
    }
}
