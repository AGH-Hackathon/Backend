package edu.agh.twonhalffront.service.solution.dto;

import java.util.UUID;

public record SolutionSketch(String path,
                             String description,
                             UUID roundId) {
}
