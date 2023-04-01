package edu.agh.twonhalffront.controller;

import edu.agh.twonhalffront.model.GameConfiguration;
import edu.agh.twonhalffront.model.LabelImagePair;
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
    public Response<List<LabelImagePair>> produceLabelsAndImages(@RequestBody GameConfiguration config) {
        final List<LabelImagePair> result = service.generateLabelsAndImages(config);

        return Response.success(result);
    }

}
