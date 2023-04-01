package edu.agh.twonhalffront.service.room;

import edu.agh.twonhalffront.model.GameConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoundRepository roundRepository;
    private final GameConfigurationRepository gameConfigurationRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository,
                       RoundRepository roundRepository,
                       GameConfigurationRepository gameConfigurationRepository) {
        this.roomRepository = roomRepository;
        this.roundRepository = roundRepository;
        this.gameConfigurationRepository = gameConfigurationRepository;
    }

    public void createRoom() {

    }

    public GameConfigurationDto getGameConfiguration(UUID roomId) {
        GameConfiguration gameConfiguration = roomRepository.findById(roomId).get().getGameConfiguration();
        return new GameConfigurationDto(
                gameConfiguration.getId(),
                gameConfiguration.getImageAmount(),
                gameConfiguration.getRoundAmount(),
                gameConfiguration.getRoom()
        );
    }
}
