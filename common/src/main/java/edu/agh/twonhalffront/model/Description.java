package edu.agh.twonhalffront.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Description {
    @Id
    private UUID uuid;
    private String content;
    @ManyToOne(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    private Round round;
}
