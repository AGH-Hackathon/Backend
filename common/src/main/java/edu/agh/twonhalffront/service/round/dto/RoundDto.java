package edu.agh.twonhalffront.service.round.dto;

import edu.agh.twonhalffront.model.Solution;
import edu.agh.twonhalffront.service.description.DescriptionDto;
import edu.agh.twonhalffront.service.image.ImageDto;
import edu.agh.twonhalffront.service.solution.dto.SolutionDto;

import java.util.List;
import java.util.UUID;

public record RoundDto(
        UUID id,
        List<SolutionDto> solutionDtos,
        List<ImageDto> imageDtos,
        List<DescriptionDto> descriptionDtos
) {
}
