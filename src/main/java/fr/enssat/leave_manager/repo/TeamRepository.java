package fr.enssat.leave_manager.repo;

import fr.enssat.leave_manager.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, String> {
    Optional<Team> findByName(String name); // method automatically created
}
