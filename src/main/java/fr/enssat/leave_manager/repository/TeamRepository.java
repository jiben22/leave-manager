package fr.enssat.leave_manager.repository;

import fr.enssat.leave_manager.model.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, String> {
    List<TeamEntity> findByName(String name);
    @Query("SELECT count(*) FROM TeamEntity")
    Integer countAll();
}
