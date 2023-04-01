package edu.agh.twonhalffront.dto;

import edu.agh.twonhalffront.model.Description;
import edu.agh.twonhalffront.model.Image;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;


public record GameActionMessage(
        String action,
        UUID roundId,
        List<Description> descriptions,
        List<Image> images
) {
}
