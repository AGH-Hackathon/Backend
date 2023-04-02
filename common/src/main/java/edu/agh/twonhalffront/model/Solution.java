package edu.agh.twonhalffront.model;

import edu.agh.twonhalffront.service.solution.dto.SolutionDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Solution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    private Description description;

    @OneToOne
    private Image image;

    @ManyToOne(
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST}
    )
    private Round round;

    public SolutionDto toDto() {
        return new SolutionDto(id, description.toDto(), image.toDto());
    }
}
