package edu.agh.twonhalffront.service.solution.dto;

import edu.agh.twonhalffront.service.description.DescriptionDto;
import edu.agh.twonhalffront.service.image.ImageDto;

import java.util.UUID;

public record SolutionDto(UUID id,
                          DescriptionDto description,
                          ImageDto image) {
}
