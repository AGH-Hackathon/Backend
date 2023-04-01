package edu.agh.twonhalffront.game;

import edu.agh.twonhalffront.dto.GameActionMessage;
import edu.agh.twonhalffront.model.Round;
import edu.agh.twonhalffront.service.GameService;
import edu.agh.twonhalffront.service.room.GameConfigurationDto;
import lombok.AllArgsConstructor;

public class GameEngine extends Thread {

    private final GameConfigurationDto gameConfigurationDto;
    private final GameService gameService;

    public GameEngine(GameConfigurationDto gameConfigurationDto, GameService gameService) {
        this.gameConfigurationDto = gameConfigurationDto;
        this.gameService = gameService;
    }


    @Override
    public void run() {
//        start game
        gameService.sendMessage(gameConfigurationDto.room().getRoomId(), new GameActionMessage("gameStart", null));

        for (Round round: gameConfigurationDto.room().getRounds()) {
//        start round
            gameService.sendMessage(gameConfigurationDto.room().getRoomId(), new GameActionMessage("roundStart", round.getId()));

//        sleep
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

//        end round
            gameService.sendMessage(gameConfigurationDto.room().getRoomId(), new GameActionMessage("roundEnd", round.getId()));

        }
        gameService.sendMessage(gameConfigurationDto.room().getRoomId(), new GameActionMessage("gameEnd", null));
    }
}
