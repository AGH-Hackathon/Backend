package edu.agh.twonhalffront.service.room;

import edu.agh.twonhalffront.model.GameConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameConfigurationRepository extends JpaRepository<GameConfiguration, Long> {
}
