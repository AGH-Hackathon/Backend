package edu.agh.twonhalffront.model;

import lombok.Data;

@Data
@Entity
@NoArgsConstructor
public class Image {
    @Id
    private UUID uuid;
    private byte[] image;
}
