package edu.agh.twonhalffront.service.room.dto;

import edu.agh.twonhalffront.service.solution.dto.SolutionDto;

import java.util.List;
import java.util.UUID;

public record RoomDto(
    UUID id,
    List<SolutionDto> solutionDtos
) {
}
