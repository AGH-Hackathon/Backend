package edu.agh.twonhalffront.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class GameConfiguration {
    @Id
    private UUID id;
    private int imageAmount;
    private int roundAmount;
    @OneToOne
    private Room room;
}
