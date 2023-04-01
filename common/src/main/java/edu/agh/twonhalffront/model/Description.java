package edu.agh.twonhalffront.model;

import lombok.Data;

@Data
@Entity
@NoArgsConstructor
public class Description {
    @Id
    private UUID uuid;
    private String content;
}
