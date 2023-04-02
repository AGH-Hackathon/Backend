package edu.agh.twonhalffront.dto;

import edu.agh.twonhalffront.model.Description;
import edu.agh.twonhalffront.model.Image;
import edu.agh.twonhalffront.service.description.DescriptionDto;
import edu.agh.twonhalffront.service.image.ImageDto;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.UUID;


public record GameActionMessage(
        String action,
        UUID roundId,
        List<DescriptionDto> descriptions,
        List<ImageDto> images,
        Map<String, ParticipantDto> scoreboard
) {
}
