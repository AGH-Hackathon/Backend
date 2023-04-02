package edu.agh.twonhalffront.model;

import edu.agh.twonhalffront.service.room.dto.RoomDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID roomId;

    private String hostId;
    @OneToMany(
            mappedBy = "room",
            cascade = CascadeType.ALL
    )
    private List<Round> rounds;
    @OneToOne
    private GameConfiguration gameConfiguration;

    public RoomDto toDto() {
        return new RoomDto(roomId, rounds.stream().map(Round::toDto).toList());
    }
}
