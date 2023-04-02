package edu.agh.twonhalffront.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST},
            fetch = FetchType.LAZY
    )
    private Room room;

    @OneToMany(
            mappedBy = "round",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Solution> solutions;
    @OneToMany(
            mappedBy = "round",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Description> descriptions;
    @OneToMany(
            mappedBy = "round",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Image> images;
}
