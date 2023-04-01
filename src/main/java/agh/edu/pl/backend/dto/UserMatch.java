package agh.edu.pl.backend.dto;

import java.util.List;
import java.util.UUID;

public class UserMatch {
    UUID userID;
    UUID roomId;
    List<ImageDescriptionMatch> answers;
}
