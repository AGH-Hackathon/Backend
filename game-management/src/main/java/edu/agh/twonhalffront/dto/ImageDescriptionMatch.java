package edu.agh.twonhalffront.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ImageDescriptionMatch {
    public UUID ImageId;
    public UUID descriptionId;
}
