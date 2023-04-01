package edu.agh.twonhalffront.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Image {
    @Id
    private UUID uuid;
    private String path;
}
