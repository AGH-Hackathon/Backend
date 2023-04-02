package edu.agh.twonhalffront.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.agh.twonhalffront.model.Description;
import edu.agh.twonhalffront.model.GameConfiguration;
import edu.agh.twonhalffront.model.Image;
import edu.agh.twonhalffront.model.ImageLabelResponse;
import edu.agh.twonhalffront.model.Room;
import edu.agh.twonhalffront.model.Round;
import edu.agh.twonhalffront.model.Solution;
import edu.agh.twonhalffront.service.description.DescriptionRepository;
import edu.agh.twonhalffront.service.image.ImageRepository;
import edu.agh.twonhalffront.service.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class LobbyCreatorService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final ImageRepository imageRepository;
    private final DescriptionRepository descriptionRepository;
    private final RoomRepository roomRepository;

    public UUID generateLabelsAndImages(GameConfiguration config) {
        final int pairAmount = config.getImageAmount() * config.getRoundAmount();
        final List<ImageLabelResponse> imageLabelList = generateImagesAndLabels(pairAmount);
        final List<Image> images = saveImages(imageLabelList);
        final List<Description> descriptions = saveDescriptions(imageLabelList);
        final List<Round> rounds = saveRounds(images, descriptions, config);

        final Room room = new Room();
        room.setRounds(rounds);

        return roomRepository.save(room).getRoomId();
    }

    private List<Image> saveImages(List<ImageLabelResponse> imageLabelList) {
        final List<Image> images = new LinkedList<>();

        for (ImageLabelResponse imageLabelPair: imageLabelList) {
            Image image = new Image();
            image.setPath(imageLabelPair.getFilename());

            imageRepository.save(image);
            images.add(image);
        }

        return images;
    }

    private List<Round> saveRounds(List<Image> images, List<Description> descriptions, GameConfiguration config) {
        final List<Round> rounds = new LinkedList<>();

        for (int roundNumber = 0; roundNumber < config.getRoundAmount(); roundNumber++) {
            Round round = new Round();
            List<Solution> solutions = new LinkedList<>();

            for (int imageNumber = 0; imageNumber < config.getImageAmount(); imageNumber++) {
                int index = roundNumber * config.getRoundAmount() + imageNumber;

                Image image = images.get(index);
                Description description = descriptions.get(index);

                Solution solution = new Solution();
                solution.setImage(image);
                solution.setDescription(description);
                solutions.add(solution);
            }
            round.setSolutions(solutions);
            rounds.add(round);
        }

        return rounds;
    }

    private List<Description> saveDescriptions(List<ImageLabelResponse> imageLabelList) {
        final List<Description> descriptions = new LinkedList<>();

        for (ImageLabelResponse imageLabelPair: imageLabelList) {
            Description description = new Description();
            description.setContent(imageLabelPair.getLabel());

            descriptionRepository.save(description);
            descriptions.add(description);
        }

        return descriptions;
    }

    private List<ImageLabelResponse> generateImagesAndLabels(int amount) {
        try {
            final RestTemplate restTemplate = new RestTemplate();
            final String url = "http://localhost:8001/generate/" + amount;
            final ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return objectMapper.readValue(response.getBody(), new TypeReference<>(){});

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
