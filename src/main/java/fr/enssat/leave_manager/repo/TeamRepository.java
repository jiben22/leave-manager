package fr.enssat.leave_manager.repo;

import fr.enssat.leave_manager.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {
}
