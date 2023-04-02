package edu.agh.twonhalffront.controller;

import edu.agh.twonhalffront.model.GameConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/room")
@RequiredArgsConstructor
public class LobbyCreatorController {

    private final LobbyCreatorService service;

    @PostMapping("/create")
    public UUID produceLabelsAndImages(@RequestBody GameConfiguration config) {
        return service.generateLabelsAndImages(config);
    }

}
