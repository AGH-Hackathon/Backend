package edu.agh.twonhalffront.service;

import edu.agh.twonhalffront.dto.*;
import edu.agh.twonhalffront.game.GameEngine;
import edu.agh.twonhalffront.model.GameConfiguration;
import edu.agh.twonhalffront.model.Room;
import edu.agh.twonhalffront.model.Round;
import edu.agh.twonhalffront.model.Solution;
import edu.agh.twonhalffront.service.room.GameConfigurationDto;
import edu.agh.twonhalffront.service.room.RoomService;
import edu.agh.twonhalffront.service.room.RoundRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {

    private final SimpMessagingTemplate template;
    private final RoundRepository roundRepository;
    private final RoomService roomService;
    private final Map<UUID, GameEngine> gameThreadMap = new ConcurrentHashMap<>();

    public GameService(SimpMessagingTemplate template,
                       RoundRepository roundRepository,
                       RoomService roomService) {
        this.template = template;
        this.roundRepository = roundRepository;
        this.roomService = roomService;
    }

    public void addGameThread(UUID roomID) {
        GameConfigurationDto gameConfigurationDto = roomService.getGameConfiguration(roomID);
        GameEngine gameThread = new GameEngine(
                gameConfigurationDto,
                this
        );

        gameThreadMap.put(roomID, gameThread);
        gameThread.start();
    }

    public void deleteMe(UUID roomId) {
        GameConfigurationDto gameConfigurationDto = new GameConfigurationDto(UUID.randomUUID(), 5, 5,
                new Room(roomId, "host", List.of(new Round(), new Round(), new Round(), new Round()),
                        new GameConfiguration()));
        GameEngine gameThread = new GameEngine(
                gameConfigurationDto,
                this
        );

        gameThreadMap.put(UUID.randomUUID(), gameThread);
        gameThread.start();
    }


    public void sendMessage(UUID roomId, GameActionMessage gameActionMessage) {
        template.convertAndSend("/game/" + roomId, gameActionMessage);
    }

    public Score processUserAnswers(UUID roomId, UUID roundId, UUID userId, UserAnswer userAnswer) {
        GameEngine gameThread = gameThreadMap.get(roomId);
        return gameThread.processUserAnswers(userAnswer, userId);
    }

    public ParticipantDto addUserToGame(UUID roomId, NewUserRequest newUserRequest) {
        GameEngine gameThread = gameThreadMap.get(roomId);
        ParticipantDto newParticipant = new ParticipantDto(UUID.randomUUID(), newUserRequest.username(), new Score(0,0));
        gameThread.addParticipantToGame(newParticipant);
        return newParticipant;
    }
}
