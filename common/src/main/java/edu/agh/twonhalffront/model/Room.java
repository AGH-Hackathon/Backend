package edu.agh.twonhalffront.model;

import lombok.Data;

import java.util.List;

@Data
public class Room {

    private String roomId;
    private String hostId;
    private List<Round> rounds;
}
