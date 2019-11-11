package fr.enssat.leave_manager.repo;

import fr.enssat.leave_manager.model.TeamLeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamLeaderRepository extends JpaRepository<TeamLeader, String> {
}
