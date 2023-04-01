package edu.agh.twonhalffront.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@NoArgsConstructor
public class GameConfiguration {
    private int imageAmount;
    private int roundAmount;
    @OneToOne
    private Room room;
}
