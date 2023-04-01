package edu.agh.twonhalffront.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.agh.twonhalffront.model.GameConfiguration;
import edu.agh.twonhalffront.model.Solution;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LobbyCreatorService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Solution> generateLabelsAndImages(GameConfiguration config) {
        final List<Solution> result = List.of(Solution.builder().build());
        return result;
    }
    
}
