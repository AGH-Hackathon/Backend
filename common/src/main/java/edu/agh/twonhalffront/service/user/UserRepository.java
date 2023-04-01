package edu.agh.twonhalffront.service.user;

import edu.agh.twonhalffront.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Participant, Long> {
}
