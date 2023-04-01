package edu.agh.twonhalffront.model;

import lombok.Data;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Room {

    private String roomId;
    private String hostId;
    private List<Round> rounds;
    @OneToOne
    private GameConfig gameConfig;
}
