package edu.agh.twonhalffront.service.solution;

import edu.agh.twonhalffront.service.description.DescriptionRepository;
import edu.agh.twonhalffront.service.image.ImageRepository;
import edu.agh.twonhalffront.service.solution.dto.SolutionDto;
import edu.agh.twonhalffront.service.solution.dto.SolutionSketch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolutionService {
    private final DescriptionRepository descriptionRepository;
    private final ImageRepository imageRepository;

    @Autowired
    public SolutionService(DescriptionRepository descriptionRepository,
                           ImageRepository imageRepository) {
        this.descriptionRepository = descriptionRepository;
        this.imageRepository = imageRepository;
    }

//    public SolutionDto createSolution(SolutionSketch solutionSketch) {
//
//    }
}
