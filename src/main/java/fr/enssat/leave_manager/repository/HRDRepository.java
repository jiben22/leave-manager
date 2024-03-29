package fr.enssat.leave_manager.repository;

import fr.enssat.leave_manager.model.HRDEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HRDRepository extends JpaRepository<HRDEntity, String> {
}
