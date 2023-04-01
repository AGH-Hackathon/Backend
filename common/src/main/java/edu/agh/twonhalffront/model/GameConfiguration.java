package edu.agh.twonhalffront.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameConfiguration {

    private int imageAmount;
    private int roundAmount;
}
