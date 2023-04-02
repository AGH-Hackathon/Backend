package edu.agh.twonhalffront.service.description;

import java.util.UUID;

public record DescriptionDto(
        UUID uuid,
        String content
) {

}
