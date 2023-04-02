package edu.agh.twonhalffront.model;

import edu.agh.twonhalffront.service.description.DescriptionDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    private String content;
    @ManyToOne(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    private Round round;

    public DescriptionDto toDto() {
        return new DescriptionDto(uuid, content);
    }
}
