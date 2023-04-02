package edu.agh.twonhalffront.service.solution;

import edu.agh.twonhalffront.model.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SolutionRepository extends JpaRepository<Solution, UUID> {
}
