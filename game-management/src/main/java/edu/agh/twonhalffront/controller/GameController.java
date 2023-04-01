package edu.agh.twonhalffront.controller;

import edu.agh.twonhalffront.dto.Score;
import edu.agh.twonhalffront.dto.UserAnswer;
import edu.agh.twonhalffront.game.GameEngine;
import edu.agh.twonhalffront.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    private final SimpMessagingTemplate template;
    private final GameService gameService;

    @PostMapping("/{roomId}/start")
    public ResponseEntity<Void> startGame(@PathVariable String roomId) {

//        start game

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/{roundId}/{userId}")
    public ResponseEntity<Score> receiveUserAnswers(@PathVariable UUID roundId, @PathVariable UUID userId, @RequestBody UserAnswer userAnswer) {

        Score score = gameService.processUserAnswers(roundId, userId, userAnswer);

        return new ResponseEntity<>(score, HttpStatus.OK);
    }


}
