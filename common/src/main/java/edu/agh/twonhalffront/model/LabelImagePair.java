package edu.agh.twonhalffront.model;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
public class LabelImagePair {

    private String id;
    private Description description;
    private Image image;
}
