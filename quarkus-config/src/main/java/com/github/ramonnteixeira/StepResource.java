package com.github.ramonnteixeira;

import com.github.ramonnteixeira.kanban.application.step.list.ListSteps;
import com.github.ramonnteixeira.kanban.application.step.list.StepDto;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/steps")
@RequiredArgsConstructor
public class StepResource {

    private final ListSteps listSteps;

    @GET
    public List<StepDto> listAll() {
        return listSteps.execute();
    }
}