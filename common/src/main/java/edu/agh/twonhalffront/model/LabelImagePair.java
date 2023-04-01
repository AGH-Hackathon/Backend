package edu.agh.twonhalffront.model;

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
public class LabelImagePair {
    @Id
    private UUID id;
    @OneToOne
    private Description description;
    @OneToOne
    private Image image;
    @ManyToOne(
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST}
    )
    private Round round;
}
