package edu.agh.twonhalffront.service;

import edu.agh.twonhalffront.dto.GameActionMessage;
import edu.agh.twonhalffront.dto.ImageDescriptionMatch;
import edu.agh.twonhalffront.dto.Score;
import edu.agh.twonhalffront.dto.UserAnswer;
import edu.agh.twonhalffront.model.Room;
import edu.agh.twonhalffront.model.Round;
import edu.agh.twonhalffront.model.Solution;
import edu.agh.twonhalffront.service.room.RoundRepository;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class GameService {

    SimpMessagingTemplate template;
    RoundRepository roundRepository;

    public void sendMessage(UUID roomId, GameActionMessage gameActionMessage) {
        template.convertAndSend("/game" + roomId, gameActionMessage);
    }


    public Score processUserAnswers(UUID roundId, UUID userId, UserAnswer userAnswer) {
        Round round = roundRepository.findById(roundId).get();
        List<Solution> solutions = round.getSolutions();

        int correct = 0;
        for (ImageDescriptionMatch imageDescriptionMatch: userAnswer.answers) {
            for (Solution solution: solutions) {
                if (imageDescriptionMatch.descriptionId.equals(solution.getDescription().getUuid())
                    && imageDescriptionMatch.ImageId.equals(solution.getImage().getUuid())) {
                    correct++;
                }
            }
        }

        return new Score(correct, solutions.size());
    }

}
