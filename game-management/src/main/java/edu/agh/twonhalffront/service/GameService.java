package edu.agh.twonhalffront.service;

import edu.agh.twonhalffront.dto.*;
import edu.agh.twonhalffront.game.GameEngine;
import edu.agh.twonhalffront.service.room.GameConfigurationDto;
import edu.agh.twonhalffront.service.room.RoomService;
import edu.agh.twonhalffront.service.room.RoundRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

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

    public GameEngine findOrCreate(UUID roomId) {
        if (gameThreadMap.containsKey(roomId)) {
            return gameThreadMap.get(roomId);
        }
        GameConfigurationDto gameConfigurationDto = roomService.getGameConfiguration(roomId);
        GameEngine gameThread = new GameEngine(
                gameConfigurationDto,
                this
        );
        gameThreadMap.put(roomId, gameThread);
        return gameThread;
    }

    public void startGame(UUID roomID) {
        GameEngine gameThread = findOrCreate(roomID);
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
        GameEngine gameThread = findOrCreate(roomId);
        ParticipantDto newParticipant = new ParticipantDto(UUID.randomUUID(), newUserRequest.username(), new Score(0,0));
        gameThread.addParticipantToGame(newParticipant);
        return newParticipant;
    }
}
