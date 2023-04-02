package edu.agh.twonhalffront.controller;

import edu.agh.twonhalffront.model.GameConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/restart/{roomId}")
    public UUID restartGameRoom(@PathVariable UUID roomId) {
        return service.restartGameRoom(roomId);
    }

}
