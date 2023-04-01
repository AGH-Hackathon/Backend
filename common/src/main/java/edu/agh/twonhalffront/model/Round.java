package edu.agh.twonhalffront.model;

import lombok.Data;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Round {

    private List<LabelImagePair> labelImagePairs;
}
