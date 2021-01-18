package com.github.ramonnteixeira.kanban.config.spring.controllers;

import com.github.ramonnteixeira.kanban.application.step.list.ListSteps;
import com.github.ramonnteixeira.kanban.application.step.list.StepDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StepController {

    private final ListSteps listSteps;

    @GetMapping("/api/steps")
    public List<StepDto> listSteps() {
        return listSteps.execute();
    }

}
