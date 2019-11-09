package fr.enssat.leave_manager.repo;

import fr.enssat.leave_manager.model.HR;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HRRepository extends JpaRepository<HR, Integer> {
}
