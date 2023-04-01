package edu.agh.twonhalffront.controller;

import edu.agh.twonhalffront.model.GameConfiguration;
import edu.agh.twonhalffront.model.Solution;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LobbyCreatorService {
    public List<Solution> generateLabelsAndImages(GameConfiguration config) {
        final List<Solution> result = List.of(Solution.builder().build());
        return result;
    }
}
