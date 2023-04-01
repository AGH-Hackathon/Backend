package edu.agh.twonhalffront.controller;

import edu.agh.twonhalffront.model.GameConfiguration;
import edu.agh.twonhalffront.model.Solution;
import edu.agh.twonhalffront.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class LobbyCreatorController {

    private final LobbyCreatorService service;

    @PostMapping("/create")
    public Response<List<Solution>> produceLabelsAndImages(@RequestBody GameConfiguration config) {
        final List<Solution> result = service.generateLabelsAndImages(config);

        return Response.success(result);
    }

}
