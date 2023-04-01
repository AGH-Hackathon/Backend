package edu.agh.twonhalffront.example;

import edu.agh.twonhalffront.model.Description;
import edu.agh.twonhalffront.model.GameConfiguration;

public class ExampleGenerator {

    public GameConfiguration getGameConfiguration() {
        return GameConfiguration.builder()
                .imageAmount(5)
                .roundAmount(5)
                .build();
    }

//    public Description getDescription() {
//        return Description
//    }
}
