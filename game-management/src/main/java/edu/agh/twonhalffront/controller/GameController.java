package edu.agh.twonhalffront.controller;

import edu.agh.twonhalffront.dto.ImageDescriptionMatch;
import edu.agh.twonhalffront.dto.NewUserRequest;
import edu.agh.twonhalffront.dto.ParticipantDto;
import edu.agh.twonhalffront.dto.Score;
import edu.agh.twonhalffront.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class GameController {

    private final SimpMessagingTemplate template;
    private final GameService gameService;

    @PostMapping("/{roomId}/start")
    public ResponseEntity<Void> startGame(@PathVariable UUID roomId) {

        gameService.startGame(roomId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{roomId}/join")
    public ResponseEntity<ParticipantDto> joinGame(@PathVariable UUID roomId, @RequestBody NewUserRequest newUserRequest) {
        ParticipantDto newParticipant = gameService.addUserToGame(roomId, newUserRequest);
        return new ResponseEntity<>(newParticipant, HttpStatus.OK);
    }


    @PostMapping("/{roomId}/{roundId}/{userId}")
    public ResponseEntity<Score> receiveUserAnswers(@PathVariable UUID roomId,
                                                    @PathVariable UUID roundId,
                                                    @PathVariable UUID userId,
                                                    @RequestBody List<ImageDescriptionMatch> answers) {

        Score score = gameService.processUserAnswers(roomId, roundId, userId, answers);

        return new ResponseEntity<>(score, HttpStatus.OK);
    }


}
