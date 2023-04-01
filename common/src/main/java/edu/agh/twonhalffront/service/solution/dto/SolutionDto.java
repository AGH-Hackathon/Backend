package edu.agh.twonhalffront.service.solution.dto;

import java.util.UUID;

public record SolutionDto(UUID id,
                          String description,
                          String path) {
}
