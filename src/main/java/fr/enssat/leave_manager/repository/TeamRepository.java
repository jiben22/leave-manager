package fr.enssat.leave_manager.repository;

import fr.enssat.leave_manager.model.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, String> {
    Optional<TeamEntity> findById(String id);
    List<TeamEntity> findByName(String name);
}
