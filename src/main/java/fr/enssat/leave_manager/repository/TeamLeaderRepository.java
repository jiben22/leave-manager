package fr.enssat.leave_manager.repository;

import fr.enssat.leave_manager.model.TeamLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamLeaderRepository extends JpaRepository<TeamLeader, String> {
}
