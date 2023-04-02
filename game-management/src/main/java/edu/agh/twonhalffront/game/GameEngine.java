package edu.agh.twonhalffront.game;

import edu.agh.twonhalffront.dto.*;
import edu.agh.twonhalffront.model.Round;
import edu.agh.twonhalffront.model.Solution;
import edu.agh.twonhalffront.service.GameService;
import edu.agh.twonhalffront.service.room.GameConfigurationDto;
import edu.agh.twonhalffront.service.round.dto.RoundDto;
import edu.agh.twonhalffront.service.solution.dto.SolutionDto;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GameEngine extends Thread {

    private GameConfigurationDto gameConfigurationDto;
    private int currentRoomIndex = 0;
    private final GameService gameService;

    private final Map<UUID, ParticipantDto> participants = new ConcurrentHashMap<>();

    public GameEngine(GameConfigurationDto gameConfigurationDto, GameService gameService) {
        this.gameConfigurationDto = gameConfigurationDto;
        this.gameService = gameService;
    }


    @Override
    public void run() {
        gameService.sendMessage(
                gameConfigurationDto.room().id(),
                new GameActionMessage(
                "gameStart",
                null,
                null,
                null,
                        getScoreBoard()
                )
        );

        for (RoundDto round: gameConfigurationDto.room().roundDtos()) {
            gameService.sendMessage(
                    gameConfigurationDto.room().id(),
                    new GameActionMessage(
                            "roundStart",
                            round.id(),
                            round.descriptionDtos(),
                            round.imageDtos(),
                            getScoreBoard()
                    )
            );
            try {
                Thread.sleep(20000);
                currentRoomIndex++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            gameService.sendMessage(
                    gameConfigurationDto.room().id(),
                    new GameActionMessage(
                    "roundEnd",
                            round.id(),
                            null,
                            null,
                            getScoreBoard()
                    )
            );

        }
        gameService.sendMessage(gameConfigurationDto.room().id(),
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
        gameService.sendMessage(gameConfigurationDto.room().id(), new GameActionMessage(
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
        RoundDto round = gameConfigurationDto.room().roundDtos().get(currentRoomIndex);
        List<SolutionDto> solutions = round.solutionDtos();

        int correct = 0;
        for (ImageDescriptionMatch imageDescriptionMatch: userAnswer.answers) {
            for (SolutionDto solution: solutions) {
                if (imageDescriptionMatch.descriptionId.equals(solution.description().uuid())
                        && imageDescriptionMatch.ImageId.equals(solution.image().uuid())) {
                    correct++;
                }
            }
        }
        ParticipantDto old = participants.get(userId);

        participants.put(userId, new ParticipantDto(old.id(), old.username(), new Score(old.score().correct() + correct, old.score().total() + solutions.size())));

        return new Score(correct, solutions.size());
    }

    public void setGameConfiguration(GameConfigurationDto gameConfigurationDto) {
        this.gameConfigurationDto = gameConfigurationDto;
    }
}
