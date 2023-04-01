package edu.agh.twonhalffront.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
class User {
    @Id
    private UUID id;
    private String username;
}