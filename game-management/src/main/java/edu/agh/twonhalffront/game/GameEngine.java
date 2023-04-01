package edu.agh.twonhalffront.game;

import edu.agh.twonhalffront.dto.GameActionMessage;
import edu.agh.twonhalffront.dto.ImageDescriptionMatch;
import edu.agh.twonhalffront.dto.Score;
import edu.agh.twonhalffront.dto.UserAnswer;
import edu.agh.twonhalffront.model.Round;
import edu.agh.twonhalffront.model.Solution;
import edu.agh.twonhalffront.service.GameService;
import edu.agh.twonhalffront.service.room.GameConfigurationDto;
import lombok.AllArgsConstructor;

import java.util.List;

public class GameEngine extends Thread {

    private final GameConfigurationDto gameConfigurationDto;
    private int i = 0;
    private final GameService gameService;

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
                null
                )
        );

        for (Round round: gameConfigurationDto.room().getRounds()) {
            gameService.sendMessage(
                    gameConfigurationDto.room().getRoomId(),
                    new GameActionMessage(
                            "roundStart",
                            round.getId(),
                            round.getDescriptions(),
                            round.getImages()
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
                            null
                    )
            );

        }
        gameService.sendMessage(gameConfigurationDto.room().getRoomId(),
                new GameActionMessage(
                        "gameEnd",
                        null,
                        null,
                        null
                )
        );
    }

    public void startNextRound() {  // TODO

    }

    public Score processUserAnswers(UserAnswer userAnswer) {
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
        return new Score(correct, solutions.size());
    }
}
