package edu.agh.twonhalffront.controller;

import edu.agh.twonhalffront.model.GameConfiguration;
import edu.agh.twonhalffront.model.LabelImagePair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LobbyCreatorService {
    public List<LabelImagePair> generateLabelsAndImages(GameConfiguration config) {
        final List<LabelImagePair> result = List.of(LabelImagePair.builder().build());
        return result;
    }
}
