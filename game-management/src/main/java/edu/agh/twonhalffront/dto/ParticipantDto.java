package edu.agh.twonhalffront.dto;

import java.util.UUID;

public record ParticipantDto(
        UUID id,
        String username,
        Score score
) {
}
