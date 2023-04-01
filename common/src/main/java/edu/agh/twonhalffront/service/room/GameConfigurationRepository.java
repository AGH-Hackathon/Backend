package edu.agh.twonhalffront.service.room;

import edu.agh.twonhalffront.model.GameConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GameConfigurationRepository extends JpaRepository<GameConfiguration, UUID> {
}
