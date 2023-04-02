package edu.agh.twonhalffront.model;

import edu.agh.twonhalffront.service.image.ImageDto;
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
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    private String url;
    @ManyToOne(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    private Round round;

    public ImageDto toDto() {
        return new ImageDto(uuid, url);
    }
}
