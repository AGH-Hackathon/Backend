package edu.agh.twonhalffront.service.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
