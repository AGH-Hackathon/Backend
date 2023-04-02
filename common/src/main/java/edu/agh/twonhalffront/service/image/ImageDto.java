package edu.agh.twonhalffront.service.image;

import java.util.UUID;

public record ImageDto(
        UUID uuid,
        String path
) {
}
