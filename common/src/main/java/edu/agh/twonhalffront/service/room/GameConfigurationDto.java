package edu.agh.twonhalffront.service.room;

import edu.agh.twonhalffront.model.Room;
import edu.agh.twonhalffront.service.room.dto.RoomDto;
import jakarta.persistence.OneToOne;

import java.util.UUID;

public record GameConfigurationDto(UUID id,
                                   int imageAmount,
                                   int roundAmount,
                                   RoomDto room) {
}
