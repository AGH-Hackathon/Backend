package edu.agh.twonhalffront.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserAnswer {
    public List<ImageDescriptionMatch> answers;
}
