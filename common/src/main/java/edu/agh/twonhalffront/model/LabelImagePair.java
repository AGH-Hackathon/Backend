package edu.agh.twonhalffront.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LabelImagePair {

    private String id;
    private Description description;
    private Image image;
}
