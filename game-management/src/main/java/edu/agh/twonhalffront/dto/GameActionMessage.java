package edu.agh.twonhalffront.dto;

import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class GameActionMessage {
    public String action;
    public UUID roundId;
}
