package edu.agh.twonhalffront.model;

import edu.agh.twonhalffront.service.round.dto.RoundDto;
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
            cascade = CascadeType.ALL
    )
    private List<Solution> solutions;
    @OneToMany(
            mappedBy = "round",
            cascade = CascadeType.ALL
    )
    private List<Description> descriptions;
    @OneToMany(
            mappedBy = "round",
            cascade = CascadeType.ALL
    )
    private List<Image> images;

    public RoundDto toDto() {
        return new RoundDto(id,
                solutions.stream().map(Solution::toDto).toList(),
                images.stream().map(Image::toDto).toList(),
                descriptions.stream().map(Description::toDto).toList()
        );
    }
}
