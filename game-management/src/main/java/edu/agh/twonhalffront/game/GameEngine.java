package edu.agh.twonhalffront.game;

import edu.agh.twonhalffront.dto.*;
import edu.agh.twonhalffront.model.Participant;
import edu.agh.twonhalffront.model.Round;
import edu.agh.twonhalffront.model.Solution;
import edu.agh.twonhalffront.service.GameService;
import edu.agh.twonhalffront.service.room.GameConfigurationDto;
import lombok.AllArgsConstructor;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GameEngine extends Thread {

    private final GameConfigurationDto gameConfigurationDto;
    private int i = 0;
    private final GameService gameService;

    private final Map<UUID, ParticipantDto> participants = new ConcurrentHashMap<>();

    public GameEngine(GameConfigurationDto gameConfigurationDto, GameService gameService) {
        this.gameConfigurationDto = gameConfigurationDto;
        this.gameService = gameService;
    }


    @Override
    public void run() {
        gameService.sendMessage(
                gameConfigurationDto.room().getRoomId(),
                new GameActionMessage(
                "gameStart",
                null,
                null,
                null,
                        getScoreBoard()
                )
        );

        for (Round round: gameConfigurationDto.room().getRounds()) {
            gameService.sendMessage(
                    gameConfigurationDto.room().getRoomId(),
                    new GameActionMessage(
                            "roundStart",
                            round.getId(),
                            round.getDescriptions(),
                            round.getImages(),
                            getScoreBoard()
                    )
            );
            try {
                Thread.sleep(20000);
                i++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            gameService.sendMessage(
                    gameConfigurationDto.room().getRoomId(),
                    new GameActionMessage(
                    "roundEnd",
                            round.getId(),
                            null,
                            null,
                            getScoreBoard()
                    )
            );

        }
        gameService.sendMessage(gameConfigurationDto.room().getRoomId(),
                new GameActionMessage(
                        "gameEnd",
                        null,
                        null,
                        null,
                        getScoreBoard()
                )
        );
    }

    public void addParticipantToGame(ParticipantDto participant) {
        participants.put(participant.id(), participant);
        gameService.sendMessage(gameConfigurationDto.room().getRoomId(), new GameActionMessage(
                "userConnect",
                null,
                null,
                null,
                getScoreBoard()
        ));
    }

    private Map<String, ParticipantDto> getScoreBoard() {
        Map<String, ParticipantDto> scoreboard = new HashMap<>();
        for (ParticipantDto participantDto: participants.values()) {
            scoreboard.put(participantDto.username(), participantDto);
        }
        return scoreboard;
    }

    public Score processUserAnswers(UserAnswer userAnswer, UUID userId) {
        Round round = gameConfigurationDto.room().getRounds().get(i);
        List<Solution> solutions = round.getSolutions();

        int correct = 0;
        for (ImageDescriptionMatch imageDescriptionMatch: userAnswer.answers) {
            for (Solution solution: solutions) {
                if (imageDescriptionMatch.descriptionId.equals(solution.getDescription().getUuid())
                        && imageDescriptionMatch.ImageId.equals(solution.getImage().getUuid())) {
                    correct++;
                }
            }
        }
        ParticipantDto old = participants.get(userId);

        participants.put(userId, new ParticipantDto(old.id(), old.username(), new Score(old.score().correct() + correct, old.score().total() + solutions.size())));

        return new Score(correct, solutions.size());
    }
}
