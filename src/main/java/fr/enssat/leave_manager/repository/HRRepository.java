package fr.enssat.leave_manager.repository;

import fr.enssat.leave_manager.model.HR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HRRepository extends JpaRepository<HR, String> {
}
